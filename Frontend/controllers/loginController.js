
app.controller('loginController',function($scope,$location,TokenService){
  
  $scope.login=function(user){    

      //response is a stirng[] [0] for token [1] for username
      TokenService.generateToken(user).then(res=>{ 
        resp=angular.fromJson(res.data);
        console.log(resp)
        TokenService.setToken(resp[0],resp[1]);
        //checking authentication
        TokenService.validateToken().then(res=>{
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
    

