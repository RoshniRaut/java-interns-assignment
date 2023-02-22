
app.controller('loginController',function($scope,$location,$timeout,TokenService){
  $scope.error=""
  $scope.login=function(user){    
      //response is a stirng[] [0] for token [1] for username
      TokenService.generateToken(user).then(res=>{ 
        resp=angular.fromJson(res.data);
        TokenService.setToken(resp[0],resp[1]);
        //checking authentication
        TokenService.validateToken().then(res=>{
          $location.path("/home");
        }).catch(err=>{
          console.log("http validate: ",err);
        })
      }).catch(err=>{
        $scope.error="Incorrect username or password!!";
        $timeout(function(){
          $scope.error="";
        },4000);
        console.log("generateToken: ",err);
      })
    }

})
    

