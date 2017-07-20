'use strict';

app.controller('BlogController', ['$scope','BlogService','$location',
		function($scope, BlogService, $location) {
			console.log("BlogController...")
		
			var self = this;
			self.blog = {blogId : '',blog_name : '',title : '',description:'',user_id : '',user_name : '',createdate:'',Status:'',likes:''};
			self.blogs = [];
			self.submit = submit;
		    self.update = update;
		    self.get = get;
		    
		    function createBlog(blog){
				console.log("createBlog...")
				BlogService.createBlog(blog).then(function(d) {
					alert("Thank you for creating message")
					$location.path("/viewblog")
				}, function(errResponse) {
					console.error('Error while creating Blog.');
				});
			};
			 function submit() {
					
					console.log('Saving New Blog', self.blog);
					createBlog(self.blog);
				
				reset();
			};

			function reset() {
				self.blog = {blogId : '',blog_name : '',title : '',description:'', user_id : '',user_name : '',createdate:'',Status:'',likes:''};
				
			};

			} ]);