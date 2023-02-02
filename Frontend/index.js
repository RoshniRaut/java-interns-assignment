var app=angular.module('myApp',['ngMaterial','ngRoute']);

app.config(function($routeProvider){

  $routeProvider
  .when("/",{
    templateUrl:"login.html",
    controller: "loginController"
  })
  .when("/register",{
    templateUrl:"register.html",
    controller:"registerController"
  })
  .when("/home",{
    templateUrl:"dashboard.html",
    controller:"dashboardController",
    authenticated: true,
  })
  .otherwise({
    redirectTo: "/",
  })

})

 app.run(function($rootScope,$location,authFact){
//   //$routeChangeStart is the event
  $rootScope.$on('$routeChangeStart',function(event, next, current){
   /* if the route is authenticated, then the user should access token */
    if(next.$$route.authenticated){
      //jwt token
      var userAuth=authFact.getAccessToken();
      console.log(userAuth)
      if(!userAuth){
        console.log("not authenticated")
        $location.path('/');
      }
    }
  })
 })

app.factory('authFact',function(){
  var authfact={};
  authfact.setAccessToken=function(accessToken){
    authfact.authToken= accessToken;
  };
  authfact.getAccessToken=function(){
    return authfact.authToken;
  }
  return authfact;
})