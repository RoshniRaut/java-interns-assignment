app.service('DeviceService',function($http,$rootScope,TokenService){
    let auth={Authorization: 'Bearer '+TokenService.getToken()}
    this.getAllDevice=function(){
        return $http({
            method:'GET',
            url:$rootScope.location+'/getAllDevice',
            headers: auth
        })
    }
    this.addDevice=function(device){
        console.log(device)
        return $http({
            method:'POST',
            url: $rootScope.location+'/addDevice',
            headers:auth,
            data: device
        })
    }
})