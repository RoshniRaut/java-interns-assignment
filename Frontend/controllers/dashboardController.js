
app.controller('dashboardController',function($scope,$mdDialog,$location,TokenService,DeveloperService){
    $scope.Device =
    [
      {
        "architecture": "E2900",
        "blocked_since": "2022-04-04",
        "blocked_till": "2022-07-03",
        "comments": "EV 10.11.14.167",
        "developer_id": "2",
        "device_model": "2900A",
        "device_number": 10,
        "mac": "54:39:68:16:D5:7F",
        "rack": "Rack 1",
        "device_id": 1
      },
      {
        "architecture": "E4700",
        "blocked_since": "",
        "blocked_till": "",
        "comments": "",
        "developer_id": null,
        "device_model": "4700",
        "device_number": 11,
        "mac": "54:39:68:03:FF:76",
        "rack": "Rack 1",
        "device_id": 2
      },
      {
        "architecture": "E4700",
        "blocked_since": "",
        "blocked_till": "",
        "comments": "",
        "developer_id": "1",
        "device_model": "4700",
        "device_number": 11,
        "mac": "54:39:68:03:FF:76",
        "rack": "Rack 2",
        "device_id": 3
      },
      {
        "architecture": "E2900",
        "blocked_since": "2022-04-04",
        "blocked_till": "2022-07-03",
        "comments": "EV 10.11.14.167",
        "developer_id": "2",
        "device_model": "2900A",
        "device_number": 10,
        "mac": "54:39:68:16:D5:7F",
        "rack": "Rack 1",
        "device_id": 4
      }
    ]
    $scope.developer=[];

    DeveloperService.getAllDeveloper().then(res=>{
      $scope.developer=JSON.parse(res.data);
    });
    
    $scope.logout=function(){
      TokenService.removeToken(null);
      $location.path('/');
    }
    
    $scope.order=null;
    $scope.reverse=null;
    $scope.sortBy=function(){
      if(!$scope.order && !$scope.reverse){
        $scope.order='device_number';
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

    $scope.updateCounts=function(){
      $scope.rack={};
      $scope.arch={};
      $scope.status={occupied:0,free:0}
     
      $scope.Device.forEach(ele => {
      if(!$scope.rack[ele.rack])
        $scope.rack[ele.rack]=1
      else
        $scope.rack[ele.rack]+=1;
      if(!$scope.arch[ele.architecture])
        $scope.arch[ele.architecture]=1
      else
        $scope.arch[ele.architecture]+=1;

      if(ele.developer==null)
        $scope.status.free+=1
      else  
        $scope.status.occupied+=1
      
    });  
  }
    $scope.updateCounts();
    
    $scope.addDevice = function(ev) {
      $mdDialog.show({
        locals: {developers:$scope.developer},
        controller: addDeviceController,
        templateUrl:'add_device.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose: true,  
        transclude: true,
        replace:true
      }).then(function(device) {
          if(device!=='cancel')
          //put request for add device
            $scope.Device.push(device);
            $scope.updateCounts();
            console.log($scope.Device)
      });
      
    };

    $scope.editDevice = function(ev) {
      let device=$scope.Device.filter(d=> d.device_id==ev.target.value)[0];
      $mdDialog.show({
        locals: {device:device,developer:$scope.developer},
        controller: editDeviceController,
        templateUrl:'edit_device.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose: true,  
        transclude: true,
        replace:true
      }).then(function(device) {
          if(device!=='cancel')
          //put request for PUT device
            $scope.Device.forEach(d=>{
              if(d.device_id==device.device_id){
                d=device;
              }
            })
            $scope.updateCounts();
      });
      
    };

    function addDeviceController($scope, $mdDialog,developers) {
        $scope.hide = function(){ $mdDialog.hide();};
        $scope.cancel =function(){$mdDialog.cancel();};
        $scope.return =function(device){$mdDialog.hide(device);};
        $scope.developer=developers;
      }
    
    function editDeviceController($scope, $mdDialog,device,developer) {
        $scope.hide = ()=>{ $mdDialog.hide();};
        $scope.cancel =()=> {$mdDialog.cancel();};
        $scope.answer =(answer)=> {$mdDialog.hide(answer);};
        $scope.device=device;
        $scope.developer=developer;
      }

      $scope.openMenu=function($mdMenu, ev) {
        originatorEv = ev;
        $mdMenu.open(ev);
      };


      $scope.delete=function(ev){
        console.log(ev.target.value);
        device=$scope.Device.filter(d=> d.device_id==ev.target.value);
        index=$scope.Device.indexOf(device[0]);
        $scope.Device.splice(index,1);
        $scope.updateCounts();
      }
})
