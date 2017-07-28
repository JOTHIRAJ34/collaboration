var app = angular.module('myapp', [ 'ngRoute', 'ngCookies' ]);


app.config(function($routeProvider) {
	$routeProvider 
	.when('/', {
		templateUrl : 'home/home.html'

	})
	
	.when('/home',{
		templateUrl:'home/home.html'
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
	
	.when('/admin',{
		templateUrl:'admin/admin.html'
	})
	
	
	.when('/adminBlogd', {
		templateUrl : 'admin/blogdetails.html',
		controller : 'BlogController',
		controllerAs : 'bcc'
	})

	.when('/adminForumd', {
		templateUrl : 'admin/forumdetails.html',
		controller : 'ForumController',
		controllerAs : 'fcc'
	})
	
	.when('/addblog',{
		templateUrl:'blog/blogform.html',
			controller:'BlogController',
			controllerAs:'bc'
			
	})
	
	.when('/viewblog',{
		templateUrl:'blog/viewblog.html',
		controller:'BlogController',
		controllerAs:'bc'
	})
	
	.when('/addforum',{
		templateUrl:'forum/forumform.html',
		controller:'ForumController',
		controllerAs:'fc'
	})
	
	.when('/viewforum',{
		templateUrl:'forum/viewforum.html',
		controller:'ForumController',
		controllerAs:'fc'
	})
	.when('/addjob',{
		 templateUrl:'job/jobform.html',
		 controller:'JobController',
		 controllerAs:'jc'
	})
	.when('/viewjob',{
		 templateUrl:'job/viewjob.html',
		 controller:'JobController',
		 controllerAs:'jc'
	})
    
	.otherwise({
		redirectTo : 'home/home.html'
	});
	});