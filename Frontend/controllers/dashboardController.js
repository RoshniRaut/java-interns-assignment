
app.controller('dashboardController',function($scope,$mdDialog,$location,TokenService,DeveloperService,RackService,ArchitectureService,DeviceService){
    $scope.Device =
    [
      {
        "architecture": "E2900",
        "blocked_since": "",
        "blocked_till": "",
        "comments": "EV 10.11.14.167",
        "developer": null,
        "device_model": "2900A",
        "device_number": 10,
        "mac": "54:39:68:16:D5:7F",
        "rack": "Rack 1"
      },
      {
        "architecture": "E4700",
        "blocked_since": "",
        "blocked_till": "",
        "comments": "",
        "developer": null,
        "device_model": "4700",
        "device_number": 11,
        "mac": "54:39:68:03:FF:76",
        "rack": "Rack 1"
      },
      {
        "architecture": "E2900",
        "blocked_since": "",
        "blocked_till": "",
        "comments": "EV 10.11.14.167",
        "developer": null,
        "device_model": "2900A",
        "device_number": 12,
        "mac": "54:39:68:16:D5:7F",
        "rack": "Rack 2"
      },
      {
        "architecture": "E4700",
        "blocked_since": "",
        "blocked_till": "",
        "comments": "",
        "developer": null,
        "device_model": "4700",
        "device_number": 13,
        "mac": "54:39:68:03:FF:76",
        "rack": "Rack 2"
      }
    ];
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
          if(r.name==ele.rack)
            r.device+=1;
        })
        $scope.architecture.forEach(a=>{
          if(a.name==ele.architecture)
            a.device+=1;
        })
      
        if(ele.developer==null)
          $scope.status.free+=1
        else  
          $scope.status.occupied+=1
      
      });  
    }
    function load(){
      ArchitectureService.getAllArchitecture().then(res=>{
        arch=angular.fromJson(res.data)
        arch.forEach(a=>{
          architecture={name:a.architectureName, id: a.architecture_id,device:0};
          $scope.architecture.push(architecture);
        })
      })
      RackService.getAllRack().then(res=>{
        angular.fromJson(res.data).forEach(r=>{
          rack={name:r.rackName, id: r.rack_id,device:0};
          $scope.rack.push(rack);
        })
      }).then(()=>{
        //when the page loads all data call method once
        $scope.updateCounts();
      })
      $scope.currentUser=TokenService.getCurrentUser();

      DeveloperService.getAllDeveloper().then(res=>{
        $scope.developer=angular.fromJson(res.data);
      })
      .catch(err=>{
        alert("session expired!!");
        $scope.logout();
      })
      DeviceService.getAllDevice().then(res=>{
        $scope.Device=angular.fromJson(res.data);
        console.log(res.data[0])
      })
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
            //put request for add device
            console.log(device)
            if(device.blocked_since)
              device.blocked_since=moment(device.blocked_since).format("YYYY-MM-DD");
            if(device.blocked_till)
              device.blocked_till=moment(device.blocked_till).format("YYYY-MM-DD");
            DeviceService.addDevice(device).then(res=>{
              $scope.Device.push(device);
            }).catch(err=>{
              console.log(err);
            })
            $scope.Device.push(device);
            $scope.updateCounts();
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
      let device=$scope.Device.filter(d=> d.device_number==ev.target.value)[0];
      $mdDialog.show({
        locals: {device:device, developer:$scope.developer, rack:$scope.rack, architecture:$scope.architecture},
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
            $scope.Device.forEach(d=>{
              if(d.device_number==device.device_number){
                console.log(device.blocked_since,device.blocked_till)
            if(device.blocked_since)
              device.blocked_since=moment(device.blocked_since).format("YYYY-MM-DD");
            if(device.blocked_till)
              device.blocked_till=moment(device.blocked_till).format("YYYY-MM-DD");
                d=device
              }
            })
            $scope.updateCounts();
      });
      
    };
    
    function editDeviceController($scope, $mdDialog,device,developer,rack,architecture) {
        $scope.hide = ()=>{ $mdDialog.hide();};
        $scope.cancel =()=> {$mdDialog.cancel();};
        $scope.answer =(answer)=> {$mdDialog.hide(answer);};
        $scope.device=device;
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
                console.log($scope.architecture)
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
        device=$scope.Device.filter(d=> d.device_number==ev.target.value);
        index=$scope.Device.indexOf(device[0]);
        $scope.Device.splice(index,1);
        $scope.updateCounts();
      }
})
