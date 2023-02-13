
app.controller('loginController',function($scope,$http,$location,TokenService){
  
  $scope.login=function(user){    

      TokenService.generateToken(user).then(res=>{
        TokenService.setToken(res.data)
        console.log(TokenService.getToken());
        //checking authentication
        
        $http({
          method:'GET',
          url: 'http://localhost:8081/admin',
          headers:{
            'Authorization': 'Bearer '+TokenService.getToken()
          },
          transformResponse: [function (data) { return data; }]
        }).then(res=>{
          alert("Role: admin");
          $location.path("/home");
        }).catch(err=>{
          console.log("http admin: ",err);
        })

        $http({
          method:'GET',
          url: 'http://localhost:8081/user',
          headers:{
            'Authorization': 'Bearer '+TokenService.getToken()
          },
          transformResponse: [function (data) { return data; }]
        }).then(res=>{
          alert("Role: user");
          $location.path("/home");
        }).catch(err=>{
          console.log("http admin: ",err);
        })

      }).catch(err=>{
        alert("invalid user");
        console.log("generateToken: ",err);
      })
    }

})
    

