'use strict';

app.controller('BlogController', ['$scope','BlogService','$location','$rootScope','$cookieStore','$http',
		function($scope, BlogService, $location,$rootScope, $cookieStore,
				$http) {
			console.log("BlogController...")
		    
			var self = this;
			self.blog = {blogId : '',title : '',description:'',userId : '',user_name : '',createdate:'',Status:'',likes:''};
			self.blogs = [];
			
			self.submit = submit;
		    self.update = update;
		    self.get = get;
		    self.adminGet = adminGet;
		    self.AcceptedBlog = AcceptedBlog;
		    self.notAcceptedBlogs = notAcceptedBlogs;
			self.accept = accept;
			self.rejectBlog = rejectBlog;
		    
		    fetchAllBlogs();
		    AcceptedBlog();
		    reset();
		    
		    function fetchAllBlogs() {
				BlogService.fetchAllBlogs().then(function(d) {
					self.blogs = d;
					console.log(self.blogs)
				}, function(errResponse) {
					console.error('Error while fetching Blogs');
				});
			}
		    
		    function AcceptedBlog() {
				console.log("AcceptedBlog...")
				BlogService.AcceptedBlog().then(function(d) {
									//alert("Thank you for creating message")
					console.log(d)
									self.blogsAccept = d;
								},
								function(errResponse) {
									console.error('Error while creating AcceptedBlogs.');
								});
			};
			
			function notAcceptedBlogs() {
				console.log("notAcceptedBlogs...")
				BlogService.notAcceptedBlogs().then(function(d) {
									//alert("Thank you for creating message")
					console.log(d)
									self.blogsNotAccepted = d;
									console.log(self.blogsNotAccepted)
								},
								function(errResponse) {
									console.error('Error while creating notAcceptedBlogs.');
								});
			};
			
			function createBlogComment(blogcomment){
				console.log("createBlogComment...")
				
					
				$scope.recentBlog =$rootScope.viewblog;
				console.log($scope.recentBlog);
					BlogCommentService.createBlogComment(blogcomment).then(function(d) {
						self.bcomment = d;
						
					alert("Thank you for creating message")
					get($scope.recentBlog);
					reset();
					//$location.path("/viewBlog")
				}, function(errResponse) {
					console.error('Error while creating Comment.');
				});
			};
		    
		    function createBlog(blog){
				console.log("createBlog...")
				BlogService.createBlog(blog).then(function(d) {
					alert("Thank you for creating message")
					$location.path("/viewblog")
				}, function(errResponse) {
					console.error('Error while creating Blog.');
				});
			};
			
			function reject(blogId){
				console.log("reject...")
				var reason = prompt("Please enter the reason");
				BlogService.reject(blogId, reason).then(function(d) {
					self.blog = d;
					self.fetchAllBlogs
					$location.path("/viewblog")
					alert(self.Blog.errorMessage)

				}, null);
			};
			
			function rejectBlog(viewblogs){
		    	BlogService.deleteBlogRequest(viewblogs.blogId).then(function(d) {
					self.deleteBlogRequestId = d;		    			
					console.log(self.deleteBlogRequestId);
		    			$location.path("/admin")
		    	}, function(errResponse){
		                console.error('Error while deleting BlogRequest');
		            });
		    };
			
			
			function updateBlog(currentBlog){
				console.log("updateBlog...")
				BlogService.updateBlog(currentBlog).then(self.fetchAllBlogs,
						null);
			};

			function update() {
				{
					console.log('Update the Blog details',
							$rootScope.currentBlog);
					updateBlog($rootScope.currentBlog);
				}
				reset();
			};
			
			
			function accept(viewblogs) {
				{
					console.log('accept the Blog details')
						
					BlogService.accept(viewblogs);
					console.log(viewblogs)
					$location.path("/admin")
				}
				
			};
			function get(blog){
				BlogCommentService.fetchAllBlogComments(blog.blogId) .then(function(d) {
					self.blogComments = d;
					$rootScope.bcomment = d;
					console.log($rootScope.bcomment);
					console.log(self.blogComments);
				
				
				$scope.bv=blog;
				$scope.bcmt=d;
				console.log($scope.bv);
				console.log($scope.bcmt);
				console.log("fetchingAllBlogComments...")
				$rootScope.viewBlog=$scope.bv;
				$rootScope.bct=$scope.bcmt;
				$location.path("/viewblog");
				}, function(errResponse) {
					console.error('Error while fetching BlogComments');
				});
				
			};
			
			
			function adminGet(blogs){
				$scope.bvv=blogs;
				console.log($scope.bvv);
				$rootScope.viewBlogs=$scope.bvv;
				$location.path("/adminBlogs");
			};
			
			
			 function submit() {
					
					console.log('Saving New Blog', self.blog);
					createBlog(self.blog);
				
				reset();
			};

			function reset() {
				self.blog = {blogId : '',title : '',description:'', userId : '',user_name : '',createdate:'',Status:'',likes:''};
				
			};

			} ]);