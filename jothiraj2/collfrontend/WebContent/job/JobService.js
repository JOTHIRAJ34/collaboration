'use strict';

app.service('JobService', ['$http','$q',
	function($http, $q) {

			console.log("JobService...")

			var BASE_URL = 'http://localhost:8081/restcontroller/'

				 var factory = {
				
				createJob: createJob,
			};
			
			  return factory;
			  
			  
			  function createJob(job) {
					console.log("calling create Job")
					return $http.post(BASE_URL + '/Job', job) // 1
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while creating Job');
						return $q.reject(errResponse);
					});
				};
				
				
				
} ]);