'use strict';

app.service('BlogService', [
		'$http',
		'$q',

		function($http, $q) {

			console.log("blogService...")

			var BASE_URL = 'http://localhost:8081/restcontroller/'
				var factory = {
				createBlog : createBlog,
			};
			return factory;
			
			
			function createBlog(Blog) {
				console.log("calling create Blog")
				return $http.post(BASE_URL + '/blog', Blog) // 1
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while creating Blog');
					return $q.reject(errResponse);
				});
			};
	} ]);
			
