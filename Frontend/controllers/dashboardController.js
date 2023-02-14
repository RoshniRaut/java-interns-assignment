
app.controller('dashboardController',function($scope,$mdDialog,$location,TokenService){
    $scope.Device =
    [
      {
        "architecture": "E2900",
        "blocked_since": "2022-04-04",
        "blocked_till": "2022-07-03",
        "comments": "EV 10.11.14.167",
        "developer": "madhur",
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
        "developer": null,
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
        "developer": "roshni",
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
        "developer": "madhur",
        "device_model": "2900A",
        "device_number": 10,
        "mac": "54:39:68:16:D5:7F",
        "rack": "Rack 1",
        "device_id": 4
      }
    ]
    $scope.user=[{
      "email": "madhur@ness.com",
      "name": "madhur"
    },
    {
      "email": "roshni@ness.com",
      "name": "roshni"
    }]

    $scope.logout=function(){
      TokenService.removeToken(null);
      $location.path('/');
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
    $scope.addDev = function(ev) {
      $mdDialog.show({
        controller: DialogController,
        templateUrl:'add_developer.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose: true,  
      }).then(function(answer) {
          if(answer!=='cancel')
            //put request for user
            $scope.user.push(answer);
            console.log($scope.user)
      }, function() {
        $scope.status = "You didn't choose";
      });
    };
    

    $scope.addDevice = function(ev) {
      $mdDialog.show({
        controller: DialogController,
        templateUrl:'add_device.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose: true,  
        transclude: true,
        replace:true
      }).then(function(answer) {
          if(answer!=='cancel')
          //put request for device
            $scope.Device.push(answer);
            $scope.updateCounts();
            console.log($scope.Device)
      });
      
    };

    function DialogController($scope, $mdDialog) {
        $scope.hide = function() {
          $mdDialog.hide();
        };
    
        $scope.cancel = function() {
          $mdDialog.cancel();
        };
    
        $scope.answer = function(answer) {
          $mdDialog.hide(answer);
        };
      }
    
      $scope.openMenu=function($mdMenu, ev) {
        originatorEv = ev;
        $mdMenu.open(ev);
      };

      $scope.edit=function(ev){
        console.log(ev.target.value)
      }

      $scope.delete=function(ev){
        console.log(ev.target.value);
        device=$scope.Device.filter(d=> d.device_id==ev.target.value);
        index=$scope.Device.indexOf(device[0]);
        $scope.Device.splice(index,1);
        $scope.updateCounts();
      }
})
