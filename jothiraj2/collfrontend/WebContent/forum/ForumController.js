'use strict';

app.controller('ForumController', ['$scope','ForumService','$location','$rootScope','$cookieStore','$http',
	function($scope, ForumService, $location ,$rootScope, $cookieStore,$http) {
			console.log("ForumController...")
			 var self = this;
			self.forum = {forumid:'',title : '',description : '',user_id:'',forumDate:''};
			console.log("comment")
			this.forums = [];// json array
				
			
			$scope.cmt={};
			self.comments = [];
			
			self.submit = submit;
			  self.update = update;
			    self.get = get;
			    self.AcceptedForums = AcceptedForums;
				 self.notAcceptedForums = notAcceptedForums;
				 
				 
				 fetchAllForums();
					AcceptedForums();
					reset();
					
					
					function fetchAllForums() {
						console.log("fetchingAllForums...")
						ForumService.fetchAllForums().then(function(d) {
							self.forums = d;
							console.log(self.forums)
						}, function(errResponse) {
							console.error('Error while fetching Forums');
						});
					};
					
					
					
					function AcceptedForums() {
						console.log("AcceptedForums...")
						ForumService.AcceptedForums().then(function(d) {
											//alert("Thank you for creating message")
							console.log(d)
											self.forumsAccept = d;
										},
										function(errResponse) {
											console.error('Error while creating Acceptedforums.');
										});
					};
					
					
					
					function notAcceptedForums() {
						console.log("notAcceptedForums...")
						ForumService.notAcceptedForums().then(function(d) {
											//alert("Thank you for creating message")
							console.log(d)
											self.forumsNotAccepted = d;
											console.log(self.forumsNotAccepted)
										},
										function(errResponse) {
											console.error('Error while creating notAcceptedForums.');
										});
					};

			
			function createForum(forum){
				console.log("createForum...")
				ForumService.createForum(forum).then(function(d) {
					/*self.forum = d;
					$scope.cforum = self.forum;
					$rootScope.currentForum = $scope.cforum;*/
					alert("Thank you for creating message")
					$location.path("/viewforum")
				}, function(errResponse) {
					console.error('Error while creating Forum.');
				});
			};
			
			
			function reject(forumid){
				console.log("reject...")
				var reason = prompt("Please enter the reason");
				ForumService.reject(id, reason).then(function(d) {
					self.forum = d;
					self.fetchAllForums
					$location.path("/manage_Forums")
					alert(self.forum.errorMessage)

				}, null);
			};
			
			function updateForum(currentForum){
				console.log("updateForum...")
				ForumService.updateForum(currentForum).then(
						self.fetchAllForums, null);
			};
			
			
			function update() {
				{
					console.log('Update the Forum details',
							$rootScope.currentForum);
					updateForum($rootScope.currentForum);
				}
				reset();
			};
			
			
			function accept(viewForums) {
				{
					console.log('accept the Forum details')
						
					ForumService.accept(viewForums);
					console.log(viewForums)
					$location.path("/admin")
				}
				
			};
			
			function get(forum){
				CommentService.fetchAllComments(forum.forumid) .then(function(d) {
					self.forumComments = d;
					$rootScope.fcomment = d;
					console.log($rootScope.fcomment);
					console.log(self.forumComments);
					
					
					$scope.fv=forum;
					$scope.cmt=d;
					console.log($scope.fv);
					console.log($scope.cmt);
					console.log("fetchingAllComments...")
					
					$rootScope.viewforum=$scope.fv;
					$rootScope.ct=$scope.cmt;
					$location.path("/viewforum");
				}, function(errResponse) {
					console.error('Error while fetching Comments');
				});
				
				
				
			};
			
			function adminGet(forums){
				$scope.fvv=forums;
				console.log($scope.fvv);
				$rootScope.viewForums=$scope.fvv;
				$location.path("/adminForumd");
			};
			
			
			function rejectForum(viewForums){
				ForumService.deleteForumRequest(viewForums.forumid).then(function(d) {
					self.deleteForumRequestId = d;		    			
					console.log(self.deleteForumRequestId);
		    			$location.path("/admin")
		    	}, function(errResponse){
		                console.error('Error while deleting ForumRequest');
		            });
		    };
			
			
			
			
			 function submit() {
					{
						console.log('Saving New Forum', self.forum);
						createForum(self.forum);
					}
					reset();
				};

				 
				
				function reset() {
					self.forum = {forumid:'',title : '',description : '',user_id:'',forumDate:''};
					//$scope.myForm.$setPristine(); // reset Form
				};

			} ]);