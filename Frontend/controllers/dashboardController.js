
app.controller('dashboardController',function($scope,$mdDialog,$location,TokenService,DeveloperService,RackService){
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
    $scope.architecture=[
      {
      "id":1,
      "name":"E2900",
      "device":0
      },
      {
        "id":2,
        "name":"E4700",
        "device":0
      }
    ];
    $scope.rack=[
      {
        "id":1,
        "name":"Rack 1",
        "device":0
      },
      {
        "id":2,
        "name":"Rack 2",
        "device":0
      }
    ];
    RackService.getAllRack().then(res=>{
      console.log(angular.fromJson(res.data))
      angular.fromJson(res.data).forEach(r=>{
        rack={name:r.rackname, id: r.rack_id,device:0};
        $scope.rack.push(rack);
      })
    })
    $scope.developer=[];
    $scope.currentUser=TokenService.getCurrentUser();

    
    //fetching data from backend
    DeveloperService.getAllDeveloper().then(res=>{
      $scope.developer=angular.fromJson(res.data);
    })
    .catch(err=>{
      alert("session expired!!");
      TokenService.removeToken(null);
      $location.path('/');
    })
    
    $scope.logout=function(){
      TokenService.removeToken(null);
      $location.path('/');
    }
    
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

    //every time a new device is added, updateCounts() method is called for re-calculation
    $scope.updateCounts=function(){
      $scope.status={occupied:0,free:0}
      $scope.rack.forEach(r=>{
        r.device=0;
      })
      $scope.architecture.forEach(a=>{
        a.device=0;
      })
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
    //when the page loads method once
    $scope.updateCounts();
    
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
          if(device!=='cancel')
          //put request for add device
            len=$scope.Device.length;
            device.device_number=$scope.Device[len-1].device_number+1;
            console.log(device)
            console.log(device.blocked_since,device.blocked_till)
            if(device.blocked_since)
              device.blocked_since=moment(device.blocked_since).format("YYYY-MM-DD");
            if(device.blocked_till)
              device.blocked_till=moment(device.blocked_till).format("YYYY-MM-DD");
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
          targetEvent: ev,
          clickOutsideToClose: true,  
          transclude: true,
          replace:true
        }).then(function(rack) {
            if(rack!=='cancel'){
              RackService.addRack(rack).then(res=>{
                rack={name:res.data.rackname, id: res.data.rack_id,device:0};
                $scope.rack.push(rack);
              })
              .catch(err=>{
                console.log(err);
                alert(err);
              })
            }
        });
        
      };
      function addRackController($scope, $mdDialog) {
        $scope.hide = function(){ $mdDialog.hide();};
        $scope.cancel =function(){$mdDialog.cancel();};
        $scope.return =function(device){$mdDialog.hide(device);};
      }

      $scope.openMenu=function($mdMenu, ev) {
        originatorEv = ev;
        $mdMenu.open(ev);
      };


      $scope.delete=function(ev){
        console.log(ev.target.value);
        device=$scope.Device.filter(d=> d.device_number==ev.target.value);
        index=$scope.Device.indexOf(device[0]);
        $scope.Device.splice(index,1);
        $scope.updateCounts();
      }
})
