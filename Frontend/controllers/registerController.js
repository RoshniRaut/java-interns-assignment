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

  $scope.register=function(user){
      $http.get("http://localhost:8080/employees").then( res=>{
      // $http.get("http://0.0.0.0:9090/employees").then( res=>{
        var found=res.data.find(e=>e.email==user.email)
        console.log(res.data)
        if(found){
          alert("user already exist!!")
        }
        else{
          $http.post("http://localhost:8080/employees",JSON.stringify(user))
            // $http.post("http://0.0.0.0:9090/employees",JSON.stringify(user))
            .then(res=>{
              //verify and set token
              authFact.setAccessToken("1234")
              $location.path("/home");
            }).catch(error=>{
              alert("Server down!!")
          })
      
        }
      }).catch(error=>{
        alert("Server down!!")
    })
    }
  })
  