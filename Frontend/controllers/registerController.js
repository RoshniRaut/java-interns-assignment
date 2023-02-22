app.controller('registerController',function($scope,$timeout,TokenService,$location,DeveloperService){
  $scope.error="";
  //password validation
  $scope.passwordError=[];
  $scope.validPassword=function(){
    $scope.passwordError=[];
    if($scope.user.password){
      if(!$scope.user.password.match(/[a-z]/))
        $scope.passwordError.push("atleast one lower case");

      if(!$scope.user.password.match(/[A-Z]/))
        $scope.passwordError.push("atleast one upper case");

      if(!$scope.user.password.match(/[0-9]/))
        $scope.passwordError.push("atleast one digit")

      if(!$scope.user.password.match(/[^a-zA-Z0-9]/))
        $scope.passwordError.push("atleast one speacial character")
        
      if($scope.user.password.length<6 || $scope.user.password.length>16)
        $scope.passwordError.push("invalid length");
    }
    if($scope.passwordError.length>0)
      $scope.registerForm.password.$setValidity("password",false);
    else
    $scope.registerForm.password.$setValidity("password",true);
  }  

  //creating a new user
  $scope.register=function(user){
    //adding new user
    DeveloperService.addDeveloper(user).then(res=>{
        console.log(res.data);
        $scope.error=res.data;
        $timeout(()=>{
          $scope.error=""; 
         },4000)
        if(res.data=="added"){
          //getting token for new user and logging in 
          user1={username:user.name,password:user.password};
          TokenService.generateToken(user1)
          .then(res=>{
            resp=angular.fromJson(res.data);
            TokenService.setToken(resp[0],resp[1]);
            $location.path("/home");
          })
          .catch(err=>{
            console.log(err);
          })
        }
      }).catch(err=>{
          $scope.error="Server is facing some issue!! ",err.status;
          $timeout(()=>{
           $scope.error=""; 
          },4000)
        })
      }
  })
  