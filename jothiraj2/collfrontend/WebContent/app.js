var app = angular.module('myapp', [ 'ngRoute', 'ngCookies' ]);


app.config(function($routeProvider) {
	$routeProvider 
	.when('/', {
		templateUrl : 'home/home.html'

	})
	
	.when('/home',{
		templateUrl:'home/home.html'
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
	
	.when('/viewblog',{
		templateUrl:'blog/viewblog.html',
		controller:'BlogController',
		controllerAs:'bc'
	})
 
	
		.when('/viewforum',{
		templateUrl:'forum/viewforum.html',
		controller:'ForumController',
		controllerAs:'fc'
	})
	
	
	.when('/viewjob',{
		 templateUrl:'job/viewjob.html',
		 controller:'JobController',
		 controllerAs:'jc'
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
	
	.when('/blog', {
		templateUrl : 'blog/blogform.html',
		controller : 'BlogController',
		controllerAs : 'bc'
	})
	
	.when('/viewB', {
		templateUrl : 'blog/viewblog.html',
		controller : 'BlogController',
		controllerAs : 'bc'

	})
	
	
	.when('/forum',{
		templateUrl:'forum/forumform.html',
	     controller:'ForumController',
	     controllerAs:'fc'
	  })
	  
	  
	  .when('/viewF', {
		templateUrl : 'forum/viewforum.html',
		controller : 'ForumController',
		controllerAs : 'fc'

	})
	
	
	.when('/job', {
		templateUrl : 'job/jobform.html',
		controller : 'JobController',
		controllerAs : 'jc'

	})

	.when('/viewJ', {
		templateUrl : 'job/viewjob.html',
		controller : 'JobController',
		controllerAs : 'jc'

	})
	.when('/addblog',{
		templateUrl:'blog/blogform.html',
			controller:'BlogController',
			controllerAs:'bc'
			
	})
	
	.when('/addforum',{
		templateUrl:'forum/forumform.html',
		controller:'ForumController',
		controllerAs:'fc'
	})
	

	.when('/addjob',{
		 templateUrl:'job/jobform.html',
		 controller:'JobController',
		 controllerAs:'jc'
	})
	
	.when('/friend',{
		templateUrl:'friend/friend.html',
		controller:'FriendController',
		controllerAs:'fr'
	})
	.when('/find', {
		templateUrl : 'user/friendreq.html',
		controller : 'UserController',
		controllerAs : 'uc'
	})
	.when('/chat',{
		templateUrl:'chat/chat.html',
		controller:'ChatController'
	})
	
    
	.otherwise({
		redirectTo : 'home/home.html'
	});
	});