
app.controller('loginController',function($scope,$location,$timeout,TokenService){
  $scope.error=""
  $scope.login=function(user){    
  
      TokenService.generateToken(user).then(res=>{ 
        resp=angular.fromJson(res.data);
        console.log(resp.token,resp.username);
        TokenService.setToken(resp.token,resp.username);
        $location.path("/home");
      }).catch(err=>{
          console.log(err)
          $scope.error="Incorrect username or password!!";
          $timeout(function(){
            $scope.error="";
          },4000);
          console.log("generateToken: ",err);
        })
      }
})
    

