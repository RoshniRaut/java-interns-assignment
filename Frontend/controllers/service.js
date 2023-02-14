
app.service('TokenService',function($http,$cookies,$rootScope){
    this.generateToken=function(user){
      return $http({
        method:'POST',
        url: $rootScope.location+'/authenticate',
        data: user,
        transformResponse: [function (data) { return data; }]
      })
    }
    this.validateToken=function(){
      return $http({
        method: 'GET',
        url:$rootScope.location+'/validate',
        headers:{
          Authorization: 'Bearer '+ this.getToken()
        },
        transformResponse: [function(data){ return data;}]
      })
    }
    this.getToken=function(){
      return $cookies.get("token");
    }
    this.setToken=function(token){
      $cookies.put("token",token);
    }
    this.removeToken=function(){
      $cookies.remove('token');
    }
  })
  
  app.service('DeveloperService',function($http,$rootScope,TokenService){
    this.getAllDeveloper=function(){
      return $http({
        method: 'GET',
        url:$rootScope.location+'/allDeveloper',
        headers:{
          Authorization: 'Bearer '+ TokenService.getToken()
        },
        transformResponse: [function(data){ return data;}]
      })
    }
    this.addDeveloper=function(user){
      return $http({
        method:'POST',
        url: $rootScope.location+'/addDeveloper',
        data: user,
        transformResponse: [function (user) { return user; }]
      })
    }
  })
  
  