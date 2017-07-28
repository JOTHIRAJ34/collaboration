'use strict';

app.service('BlogService', [
		'$http',
		'$q','$rootScope',

		function($http, $q,$rootScope) {

			console.log("BlogService...")

			var BASE_URL = 'http://localhost:8081/restcontroller/'
				var factory = {
				fetchAllBlogs : fetchAllBlogs,
				createBlog : createBlog,
				updateBlog : updateBlog,
				AcceptedBlog : AcceptedBlog,
				notAcceptedBlogs : notAcceptedBlogs,
				accept: accept,
				deleteBlogRequest:deleteBlogRequest
			};
			return factory;
			
			function fetchAllBlogs() {
				console.log("calling fetchAllblogs ")
				return $http.get(BASE_URL + '/blogs').then(function(response) {
					return response.data;
				}, null);
			};
			
			function AcceptedBlog() {
				console.log("calling AcceptedBlog ")

				return $http.get(BASE_URL + '/acceptedblog').then(
						function(response) {
							console.log('response');
							return response.data;
							console.log(response)
						}, null);
			};
			
			function notAcceptedBlogs() {
				console.log("calling notAcceptedBlogs ")

				return $http.get(BASE_URL + '/notAcceptedblog').then(
						function(response) {
							console.log(response)
							return response.data;

						}, null);
			};
			function createBlog(blog) {
				console.log("calling create Blog")
				return $http.post(BASE_URL + '/blog', blog) // 1
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while creating Blog');
					return $q.reject(errResponse);
				});
			};
			
			
			function updateBlog(blog) {
				console.log("calling fetchAllBlogs ")
				return $http.put(BASE_URL + '/blog', blog) // 2
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while updating Blog');
					return $q.reject(errResponse);
				});
			};
			
			
			function accept(blog) {
				console.log("calling accept Blogs ")
				return $http.put(BASE_URL + '/acceptBlog', blog) // 2
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while accepting Blog');
					return $q.reject(errResponse);
				});
			};
			
			function deleteBlogRequest(blogId){
				console.log("Deleting Blog Request");
				return $http.delete(BASE_URL + '/blog/'+blogId).then(function(response){
						
					return response.data;
						},function(errResponse) {
							console.error('Error while deleting Blog request');
							return $q.reject(errResponse);
						});
		
			};
	} ]);
			
