app.controller('registerController',function($scope,$http,$location,authFact){
    $scope.register=function(user){
      
      $http.get("http://0.0.0.0:9090/employees").then( res=>{
        var found=res.data.find(e=>e.email==user.email)
        console.log(res.data)
        if(found){
          alert("user already exist!!")
        }
        else{
          console.log(user.password)
          // $http.post("http://0.0.0.0:9090/employees",JSON.stringify(user))
          // .then(res=>{
          //   //verify and set token
          //   authFact.setAccessToken("1234")
          //   $location.path("/home");
          // })
        }
      })
    }
  })
  