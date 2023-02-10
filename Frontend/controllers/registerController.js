app.controller('registerController',function($scope,$http,$location,authFact){
  
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
  }  

  //creating a new user
  $scope.register=function(user){
    
    $http.post("http://localhost:8080/newUser",JSON.stringify(user))
          // $http.post("http://0.0.0.0:9090/employees",JSON.stringify(user))
            .then(res=>{
              console.log(res.data);
              //to do: verify and set token
              $http.post("http://localhost:8080/authenticate",JSON.stringify(user)).then(res=>{
                authFact.setAccessToken(res.data);
                console.log(res.data);
                $location.path("/home");
              })
            }).catch(error=>{
              alert("Server down!!")
          })
        }
  })
  