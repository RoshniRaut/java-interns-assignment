
app.controller('loginController',function($scope,$location,authFact,employeeService,authenticationService){
      
  $scope.login=function(user){    
  
      authenticationService.getToken(user).then(res=>{
        authFact.setAccessToken(res.data)
        $location.path("/home");
      }).catch(err=>{
        console.log(err);
      })
        // employeeService.getEmployees().then(function(result){
        //   let found=result.data.find(e=> e.email==user.email)
        //   console.log(result.data)
        //   if(found){
        //     if(found.password!=user.password)
        //       alert("invalid password");
        //       else{
        //         //verify and set token
        //         authFact.setAccessToken("1234")
        //         $location.path("/home");
        //       }
        //   }
        //   else
        //     alert("user not registered!!!")
        // }).catch(error=>{
        //     alert("Server down!!")
        // })
      }

})
    
app.service('employeeService',function($http){
    this.getEmployees=function(){
      return $http.get("http://localhost:8080/all")
      // return $http.get("http://0.0.0.0:9090/all")
    }
})

app.service('authenticationService',function($http){
  this.getToken=function(user){
    return $http({
      method:'POST',
      // url: 'http://0.0.0.0:9090/authenticate',
      url: 'http://localhost:8080/authenticate',
      data: user,
      transformResponse: [function (user) { return user; }]
    })
  }
})