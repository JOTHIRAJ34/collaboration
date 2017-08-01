 'use strict';
 app.controller('UserController',['$scope','UserService','FriendService','$location','$rootScope','$cookies','$cookieStore','$http',
	 function($scope, UserService,FriendService, $location, $rootScope,$cookies,$cookieStore, $http) {
							
console.log("UserController...")
var i = 0;
var j = 0;
var self=this;
self.user={userId:'',user_name:'',first_name:'',last_name:'',password:'',email_id:'',role:''};
self.currentUser={userId:'',user_name:'',first_name:'',last_name:'',password:'',email_id:'',role:''};
self.friend = {fid:'',friendId : '',userId: '',userName:'',status:'',friendName:'',isOnline:'',lastSeen:''};
self.userLoggedIn = "";


self.users=[];
self.friends = [];
var arr=[];
var friendarr=[];
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
		
		console.log("fetchAllRequestedFriend...")
									FriendService.fetchAllRequestedfriends($scope.loginUser.userId).then(function(d) {
										self.friends = d;
										console.log(self.friends)					
							
										
											for(j=0; j<self.us.length; j++){
												for(i=0; i<self.friends.length; i++){
												if(self.friends[i].friendId === self.us[j].userId){
													self.us.splice(j, 1);
													console.log(self.us)
												}
											}
										}
										self.asd = self.us;
										
									},function(errResponse) {
										console.error('Error while fetching Friends');
									} );
		
	},function(errResponse) {
		console.error('Error while fetching Users');
	});	
	
};

self.requestedFriend = function() {
	$rootScope.loginUser =$rootScope.currentUser;
	console.log("GetAllRequestedFriends...")
	FriendService.fetchRequestedfriends($rootScope.loginUser.user_name).then(function(d) {
						self.reqFriend = d;
						
						console.log(self.reqFriend)
					},function(errResponse) {  
						console.error('Error while fetching By Friend Name');
					});
};

self.acceptFriend = function(reqFriend) {
	
	
	console.log('accept the friend request')
	FriendService.updateFriendReq(reqFriend);
	
	console.log('Accepted')
$location.path("/find")


};

self.AcceptedFriendCurrentUser = function() {
	$rootScope.loginUser =$rootScope.currentUser;
	console.log("GetAllAcceptedFriendCurrentUser...")
	FriendService.fetchAcceptedFriends($rootScope.loginUser.user_name).then(function(d) {
						self.accFriend = d;
						
						console.log(self.accFriend)
					},function(errResponse) {  
						console.error('Error while fetching Accepted list');
					});
};


self.deleteFriendRequest = function(req){
	FriendService.deleteFriendRequest(req.fid).then(function(d) {
		self.deleteFriendRequestid = d;		    			
		console.log(self.deleteFriendRequestid)
			$location.path("/find")
	}, function(errResponse){
            console.error('Error while deleting FriendRequest');
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

self.accept = function(userId) {
	console.log("accept...")
	UserService.accept(userId).then(function(d) {
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

self.reject = function(userId) {
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
		console.log("it is logout")
	$rootScope.currentUser = {};
	$cookieStore.remove('currentUser');
	UserService.logout()
	$location.path('/login');

};

self.send = function(friendUser){
	console.log("sending friend request...")
	FriendService.createFriend(friendUser).then(function(d) {
		console.log(d)
	/*	self.frndreq=d;
		console.log(frndreq)*/
		
						//alert("Thank you for  creating friend")
		$location.path("/find")
		//self.fetchAllUsers();
					},
					function(errResponse) {
						console.error('Error while creating friend..');
					});

};

self.submit=function(){
	console.log('Saving a New User',self.user);
	self.createUser(self.user);
	 self.reset();
	 };
	 
self.reset=function()
{
	self.user={userId:null,user_name:'',first_name:'',last_name:'',password:'',email_id:'',role:''};
	self.friend = {fid:'',friendId : '',userId: '',userName:'',status:'',friendName:'',isOnline:'',lastSeen:''};

	self.users=[];
	self.friends = [];

	var arr=[];
	var friendarr=[];

	};
	
	
	self.login = function() {
		{
			console.log('login validation????????',
					self.user);
			self.authenticate(self.user);
		}

	};
        }]);
