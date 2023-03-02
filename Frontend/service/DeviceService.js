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
        return $http({
            method:'POST',
            url: $rootScope.location+'/addDevice',
            headers:auth,
            data: device
        })
    }
    this.updateDevice=function(device){
        return $http({
            method:'PUT',
            url: $rootScope.location+'/device/'+device.device_id,
            headers:auth,
            data: device
        })
    }
    this.deleteDevice=function(id){
        return $http({
            method:'DELETE',
            url: $rootScope.location+'/device/'+id,
            headers:auth,
        })
    }
})