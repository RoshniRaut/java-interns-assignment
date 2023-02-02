
app.controller('loginController',function($scope,$location,authFact,employeeService){
      $scope.login=function(user){
        
        employeeService.getEmployees().then(function(result){
          let found=result.data.find(e=> e.email==user.email)
          console.log(result.data)
          if(found){
            if(found.password!=user.password)
              alert("invalid password");
              else{
                //verify and set token
                authFact.setAccessToken("1234")
                $location.path("/home");
              }
          }
          else
            alert("user not registered!!!")
        }).catch(error=>{
            alert("Server down!!")
        })
      }

})
    
app.service('employeeService',function($http){
    this.getEmployees=function(){
      return $http.get("http://0.0.0.0:9090/employees")
    }
})
