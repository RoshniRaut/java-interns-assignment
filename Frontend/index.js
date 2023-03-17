var app=angular.module('myApp',['ngMaterial','ngRoute','ngCookies']);

app.config(function($routeProvider,$mdDateLocaleProvider){
  $routeProvider
  .when("/",{
    templateUrl:"login.html",
    controller: "loginController",
    authenticated:true,
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

 app.run(function($rootScope,$location,$http,TokenService,$rootScope){
  $rootScope.location="http://0.0.0.0:8081"
  // $rootScope.location="http://localhost:8081"
//   //$routeChangeStart is the event
  $rootScope.$on('$routeChangeStart',function(event, next, current){
   /* if the route is authenticated, then the user should access token */
    if(next.$$route.authenticated){
      //jwt token
      var userAuth=TokenService.getToken();
      //check validation of token
      if(!userAuth){
        $location.path('/');
      }
      else{
        $location.path("/home");
      }
    }
  })
 })

