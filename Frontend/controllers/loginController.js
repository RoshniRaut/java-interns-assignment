
app.controller('loginController',function($scope,$http,$location,TokenService,$rootScope){
  
  $scope.login=function(user){    

      TokenService.generateToken(user).then(res=>{
        TokenService.setToken(res.data)
        console.log(TokenService.getToken());
        //checking authentication
        TokenService.validateToken.then(res=>{
          $location.path("/home");
        }).catch(err=>{
          console.log("http validate: ",err);
        })

      }).catch(err=>{
        alert("invalid user");
        console.log("generateToken: ",err);
      })
    }

})
    

