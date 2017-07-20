'use strict';
app.service('UserService',['$http','$q',function($http,$q)
{
	console.log("UserService...")
	var BASE_URL='http://localhost:8081/restcontroller/'
		return{
		
		createUser:function(user){
			console.log("calling the create user")
			return $http.post(BASE_URL+'/user',user)
			.then(
		function(response)
					{
						return response.data;
					},
					function(errResponse)
					{
						console.error('error while creating the User');
						return $q.reject(errResponse);
					}
			);
		},
		
		login:function(user){
			console.log("calling the method of authentication for user:"+user)
			return $http.post (BASE_URL+'/login',user).then(function(response)
					{
				return response;
					},
					null
					);
		}
	};
		   
		   }]);