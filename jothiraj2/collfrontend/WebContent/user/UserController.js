 'use strict';
app.controller('UserController', [ '$scope', 'UserService','$location','$rootScope', '$cookieStore','$cookies',
	function($scope,UserService,$location,$rootScope,$cookieStore,$cookies)
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

var currentLoginUser = $cookies.getObject('currentLoginUser');
console.log(currentLoginUser);


self.fetchAllUsers = function() {
	self.asd = null;
	self.us = '';
	console.log("fetchAllUsers...")
	$scope.loginUser =$rootScope.currentUser;
	console.log("fetchUserList...")
	UserService .fetchAllUsers().then(function(d) {
		self.users = d;
		for(i=0; i<self.users.length; i++)
			{
			if(self.users[i].role!='HR'){
				arr.push(self.users[i])													
			}
			}
		self.us = arr;	
		console.log(self.us)
		
	},function(errResponse) {
		console.error('Error while fetching Users');
	});	
	
};

self.createUser = function(user){
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
};

self.accept = function(id) {
	console.log("accept...")
	UserService.accept(id).then(function(d) {
						self.user = d;
						self.fetchAllUsers
						$location.path("/manage_users")
						alert(self.user.errorMessage)

					}, 

					function(errResponse) {
						console.error('Error while updating User.');
					});
};

/*self.fetchAllUserId = function (friend) {
	console.log("fetchAllFriends...")
	FriendService.fetchAllUser().then(function(d) {
						self.friendss = d;
						console.log(self.friendss)
					},function(errResponse) {  
						console.error('Error while fetching By User Id');
					});
};*/

self.reject = function(id) {
	console.log("reject...")
	var reason = prompt("Please enter the reason");
	UserService.reject(id, reason).then(
			function(d) {
				self.user = d;
				self.fetchAllUsers
				$location.path("/manage_users")
				alert(self.user.errorMessage)

			}, null);
};

self.updateUser = function(currentUser) {
	console.log("updateUser...")
	UserService.updateUser(currentUser).then(
			self.fetchAllUsers, null);
};
self.update = function() {
	
	console.log('Update the user details',
			$rootScope.currentUser);
	self.updateUser($rootScope.currentUser);

self.reset();
};


self.authenticate = function(user) {
	console.log("authenticate...")
	UserService.authenticate(user)
			.then(function(d) {
                        self.user = d;
						console.log("user.errorCode: "+ self.user.errorCode)
						if (self.user.errorCode == "404")

						{
							alert(self.user.errorMessage)

							self.user.email_id = "";
							self.user.password = "";
							$location.path('/');

						} else {
							console.log("Valid credentials. Navigating to admin page")
							
							console.log('Current user : '+ self.user)
							$rootScope.currentUser = self.user
							console.log($rootScope.currentUser)
							$cookieStore.put('currentUser',self.user);
							
							self.userLoggedIn = "true"
							if (self.user.role == "HR") {
								console.log("You are admin")
								 $location.path('/admin')
								
							}else if (self.user.role == "READER") {
								console.log("You are reader")
								 $location.path('/home')
								
							}
							else{
							
							 $location.path('/')
							
							}
						}

					},
					function(errResponse) {

						console.error('Error while authenticate Users');
					});
};


self.logout = function() {
	console.log("logout")
	self.userLoggedIn = "false"
	$rootScope.currentUser = {};
	$cookieStore.remove('currentUser');
	UserService.logout()
	$location.path('/login');

}

self.submit=function(){
	console.log('Saving a New User',self.user);
	self.createUser(self.user);
	 self.reset();
	 };
	 
self.reset=function()
{
	self.user={userId:null,user_name:'',first_name:'',last_name:'',password:'',email_id:'',role:''};
	self.users=[];
	var arr=[];
	};
	
	
	self.login = function() {
		{
			console.log('login validation????????',
					self.user);
			self.authenticate(self.user);
		}

	};
        }]);
