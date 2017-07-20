var app = angular.module('myapp', [ 'ngRoute', 'ngCookies' ]);


app.config(function($routeProvider) {
	$routeProvider 
	.when('/', {
		templateUrl : 'index.html'

	})
 
	.when('/login', {
		templateUrl : 'user/login.html',
		controller:'UserController',
		controllerAs:'uc'
	  

	})
	.when('/register',{
		 templateUrl:'user/register.html',
		 controller:'UserController',
			controllerAs:'uc'
	})
	
	.when('/addblog',{
		templateUrl:'blog/blogform.html',
			controller:'BlogController',
			controllerAs:'bc'
			
	})

	});