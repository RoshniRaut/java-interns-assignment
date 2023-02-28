app.service('RackService',function($http,TokenService,$rootScope){
    let auth={
        Authorization: 'Bearer '+TokenService.getToken()
    }
    this.getAllRack=function(){
        return $http({
            method:'GET',
            url: $rootScope.location+'/getAllRack',
            headers:auth
        })
    }
    this.addRack=function(rack){
        console.log(rack)
        return $http({
            method:'POST',
            url: $rootScope.location+'/addRack',
            headers:auth,
            data: rack
        })
    }
})