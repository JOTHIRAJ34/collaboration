'use strict';
app.controller('UserController', [ '$scope', 'UserService','$location','$rootScope', '$cookieStore','$cookies',function($scope,UserService,$location,$rootScope,$cookieStore,$cookies)
{
console.log("UserController...")
var i = 0;
var j = 0;
var self=this;
self.user={userId:'',user_name:'',first_name:'',last_name:'',password:'',email_id:'',role:''};
self.currentUser={userId:'',user_name:'',first_name:'',last_name:'',password:'',email_id:'',role:''};
self.users=[];
var arr=[];
$scope.orderByMe=function(x)
{
	$scope.myOrderBy=x;
	}

self.createUser=function(user)
{
	console.log("createUser....")
	UserService.createUser(user).then(function(d)
			{
		alert("Thank you register")
		$location.path("/login")
			},
			function(errResponse)
			{
				console.error("Error while creating your User");
				});
}


self.login=function()
{
	UserService.login(self.user).then(function(response)
			{
		console.log(response.status)
		$scope.user=response.data;
		$rootScope.currentUser = response.data;
		$cookieStore.put("hi", response.data);
		$cookies.putObject('currentLoginUser', response.data);
		$location.path("/login")
			},
			function(response)
			{
				console.log(response.status)
				$scope.message=response.data.message
				$location.path("/login")
			});
	}
self.submit=function()
{
	console.log('Saving a New User',self.user);
	self.createUser(self.user);
	 self.reset();
	 };
	 
self.reset=function()
{
	self.user={userId:'',user_name:'',first_name:'',last_name:'',password:'',email_id:'',role:''};
	self.users=[];
	var arr=[];
	};
        }
	      ])
