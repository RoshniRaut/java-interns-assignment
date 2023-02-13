
app.service('TokenService',function($http,$cookies){
    this.generateToken=function(user){
      return $http({
        method:'POST',
        // url: 'http://0.0.0.0:9090/authenticate',
        url: 'http://localhost:8081/authenticate',
        data: user,
        transformResponse: [function (data) { return data; }]
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
  
  app.service('userService',function($http){
    this.getUser=function(){
      return $http.get("http://localhost:8081/all")
      // return $http.get("http://0.0.0.0:9090/all")
    }
  })
  
  