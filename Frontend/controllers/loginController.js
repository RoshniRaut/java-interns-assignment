
app.controller('loginController',function($scope,$location,$timeout,TokenService){
  $scope.error=""
  $scope.login=function(user){    
      TokenService.generateToken(user).then(res=>{ 
        resp=angular.fromJson(res.data);
        TokenService.setToken(resp.token,resp.username);
        $location.path("/home");
        console.log("routing to home page")
      }).catch(err=>{
        if(err.status==-1)
          $scope.error="Server is facing some issue!!";
        else
          $scope.error="Incorrect username or password!!";
          $timeout(function(){
            $scope.error="";
          },4000);
          console.log("generateToken: ",err);
        })
      }
})
    

