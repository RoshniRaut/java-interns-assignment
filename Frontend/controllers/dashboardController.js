
app.controller('dashboardController',function($scope,$mdDialog,$location,TokenService,DeveloperService,RackService,ArchitectureService,DeviceService){
    $scope.Device =[];
    $scope.architecture=[];
    $scope.rack=[];
    $scope.developer=[];
    //every time a new device is added, updateCounts() method is called for re-calculation
    $scope.updateCounts=function(){
      $scope.status={occupied:0,free:0}
      $scope.rack.forEach(r=>r.device=0)
      $scope.architecture.forEach(a=>a.device=0)
      $scope.Device.forEach(ele => {
        $scope.rack.forEach(r=>{
          if(r.name==ele.rackName)
            r.device+=1;
        })
        $scope.architecture.forEach(a=>{
          if(a.name==ele.architectureName)
            a.device+=1;
        })
        if(ele.developerName==null)
          $scope.status.free+=1
        else  
          $scope.status.occupied+=1
      });  
    }

    function load(){
      ArchitectureService.getAllArchitecture().then(arch=>{
        arch.data.forEach(a=>{
          architecture={name:a.architectureName, id: a.architecture_id, device:0};
          $scope.architecture.push(architecture);
        })

        RackService.getAllRack().then(rack=>{
          rack.data.forEach(r=>{
            rack={name:r.rackName, id: r.rack_id,device:0};
            $scope.rack.push(rack);
          })

          DeveloperService.getAllDeveloper().then(dev=>{
            $scope.developer=angular.fromJson(dev.data);
            DeviceService.getAllDevice().then(res=>{
              res.data.forEach(d=>{
                d.architectureName=$scope.architecture.find(a=> a.id==d.architectureId).name;
                d.rackName=$scope.rack.find(r=>r.id==d.rackId).name;
                if(d.developerId){
                  d.developerName=$scope.developer.find(dev=>dev.id==d.developerId).name;
                }
                $scope.Device.push(d);
              })
              $scope.updateCounts();
            })
          })
        })
      }).catch(err=>{
        alert("session expired!!");
        $scope.logout();
      })
           
      $scope.currentUser=TokenService.getCurrentUser();    
      
  }
    load();
    $scope.logout=function(){
      TokenService.removeToken(null);
      $location.path('/');
    }
    
    //sorting on each column
    $scope.order=null;
    $scope.reverse=null;
    $scope.sortBy=function(col){
      console.log(col)
      if(!$scope.order && !$scope.reverse){
        $scope.order=col;
        $scope.reverse=false;
      }
      else if($scope.order && !$scope.reverse){
        $scope.reverse=true;
      }
      else{
        $scope.order=null;
        $scope.reverse=null;
      }
    }

        
    $scope.addDevice = function(ev) {
      $mdDialog.show({
        locals: {developers:$scope.developer,rack:$scope.rack, architecture:$scope.architecture},
        controller: addDeviceController,
        templateUrl:'./dialogs/add_device.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose: true,  
        transclude: true,
        replace:true
      }).then(function(device) {
            if(device.blocked_since)
              device.blocked_since=moment(device.blocked_since).format("YYYY-MM-DD");
            if(device.blocked_till)
              device.blocked_till=moment(device.blocked_till).format("YYYY-MM-DD");
            
            DeviceService.addDevice(device).then(res=>{
              res.data.architectureName=$scope.architecture.find(a=> a.id=res.data.architectureId).name
              res.data.rackName=$scope.rack.find(r=>r.id==res.data.rackId).name;
              if(res.data.developerId)
                res.data.developerName=$scope.developer.find(dev=>dev.id==res.data.developerId).name;
              
              $scope.Device.push(angular.fromJson(res.data));
              $scope.updateCounts();
            }).catch(err=>{
              console.log(err);
            })
      });
      
    };

    function addDeviceController($scope, $mdDialog,developers,rack,architecture) {
      $scope.hide = function(){ $mdDialog.hide();};
      $scope.cancel =function(){$mdDialog.cancel();};
      $scope.return =function(device){$mdDialog.hide(device);};
      $scope.developer=developers;
      $scope.rack=rack;
      $scope.architecture=architecture;
    }

    // editing device
    $scope.editDevice = function(ev) {
      let olddevice=$scope.Device.filter(d=> d.device_id==ev.target.value)[0];

      $mdDialog.show({
        locals: {olddevice:olddevice, developer:$scope.developer, rack:$scope.rack, architecture:$scope.architecture},
        controller: editDeviceController,
        templateUrl:'./dialogs/edit_device.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose: true,  
        transclude: true,
        replace:true
      }).then(function(device) {
          if(device!=='cancel')
          //put request for PUT device
            if(device.blocked_since)
              device.blocked_since=moment(device.blocked_since).format("YYYY-MM-DD");
            if(device.blocked_till)
              device.blocked_till=moment(device.blocked_till).format("YYYY-MM-DD");
        
            DeviceService.updateDevice(device).then(res=>{
              console.log("updated");
              $scope.Device.forEach(d=>{
                if(d.device_id==res.data.device_id){
                  d.architectureName=$scope.architecture.find(a=> a.id==d.architectureId).name;
                  d.rackName=$scope.rack.find(r=>r.id==d.rackId).name;
                  if(d.developerId){
                    d.developerName=$scope.developer.find(dev=>dev.id==d.developerId).name;
                  }
                  d=res.data;
                }
              });
              $scope.updateCounts();
            }).catch(err=>{
              console.log(err)
            });
      });
      
    };
    
    function editDeviceController($scope, $mdDialog,olddevice,developer,rack,architecture) {
        $scope.hide = ()=>{ $mdDialog.hide();};
        $scope.cancel =()=> {$mdDialog.cancel();};
        $scope.answer =(device)=> {$mdDialog.hide(device);};
        $scope.oldDevice=olddevice;
        $scope.developer=developer;
        $scope.rack=rack;
        $scope.architecture=architecture;
      }

      $scope.addRack = function(ev) {
        $mdDialog.show({
          controller: addRackController,
          templateUrl:'./dialogs/add_rack.html',
          parent: angular.element(document.body),
          targetEvent: ev
        }).then(function(rack) {
              RackService.addRack(rack).then(res=>{
                rack={name:res.data.rackName, id: res.data.rack_id,device:0};
                $scope.rack.push(rack);
              })
              .catch(err=>{
                $scope.error=err.data.message;
                console.log(err.data.message);
                
              })
        });
        
      };
      function addRackController($scope, $mdDialog) {
        $scope.hide = function(){ $mdDialog.hide();};
        $scope.cancel =function(){$mdDialog.cancel();};
        $scope.return =function(rack){$mdDialog.hide(rack);};
      }

      //add Architecture popup
      $scope.addArchitecture = function(ev) {
        $mdDialog.show({
          controller: addArchitectureController,
          templateUrl:'./dialogs/add_architecture.html',
          parent: angular.element(document.body),
        
        }).then(function(architecture) {
            if(architecture!=='cancel'){
              ArchitectureService.addArchitecture(architecture).then(res=>{
                arch={name:res.data.architectureName, id: res.data.architecture_id,device:0};
                $scope.architecture.push(arch);
              })
              .catch(err=>{
                console.log(err.data.message);
                alert(err.data.message);
              })
            }
        });
        
      };
      function addArchitectureController($scope, $mdDialog) {
        $scope.hide = function(){ $mdDialog.hide();};
        $scope.cancel =function(){$mdDialog.cancel();};
        $scope.return =function(architecture){$mdDialog.hide(architecture);};
      }

      //open drop down menu for edit and delete
      $scope.openMenu=function($mdMenu, ev) {
        originatorEv = ev;
        $mdMenu.open(ev);
      };
      //delete a device call a api
      $scope.delete=function(ev){
        console.log(ev.target.value);
        DeviceService.deleteDevice(ev.target.value).then(res=>{
          console.log("successfully deleted");
          device=$scope.Device.filter(d=> d.device_id==ev.target.value);
          index=$scope.Device.indexOf(device[0]);
          $scope.Device.splice(index,1);
        }).catch(err=>{
          console.log(err)
        })
        $scope.updateCounts();
      }
})
