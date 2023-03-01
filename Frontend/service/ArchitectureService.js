app.service("ArchitectureService",function($http, $rootScope,TokenService){
    let auth={Authorization: 'Bearer '+TokenService.getToken()}

    this.getAllArchitecture=function(){
        return $http({
            method:'GET',
            url: $rootScope.location+'/getAllArchitecture',
            headers: auth
        })
    } 
    this.addArchitecture=function(architecture){
        return $http({
            method:'POST',
            url: $rootScope.location+'/addArchitecture',
            headers: auth,
            data: architecture
        })
    }

})