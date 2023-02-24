app.controller('registerController',function($scope,$timeout,TokenService,$location,DeveloperService){
  $scope.error="";
  $scope.success="";
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
      $scope.error="";
        $scope.success=angular.fromJson(res.data).message;
        $timeout(()=>{
          $scope.success=""; 
         },6000)
                
      }).catch(err=>{
          $scope.error=angular.fromJson(err.data).message;
          $timeout(()=>{
           $scope.error=""; 
          },6000)
        })
      }
  })
  