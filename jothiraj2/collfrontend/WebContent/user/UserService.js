'use strict';
app.service('UserService',['$http','$q',function($http,$q)
{
	console.log("UserService...")
	var BASE_URL='http://localhost:8081/restcontroller/'
		return{
		 fetchAllUsers: function() {
         	console.log("calling fetchAllUsers ")
                 return $http.get(BASE_URL+'/users')
                         .then(
                                 function(response){
                                     return response.data;
                                 }, 
                                null
                         );
         },
         
         
         accept: function(userId) {
         	console.log("calling approve ")
                 return $http.get(BASE_URL+'/accept/'+userId)
                         .then(
                                 function(response){
                                     return response.data;
                                 }, 
                                 function(errResponse){
                                     console.error('Error while accept registration');
                                    
                                 }
                         );
         },
         
         
         reject: function(userId, reason) {
         	console.log("calling reject ")
                 return $http.get(BASE_URL+'/reject/'+userId+'/'+reason)
                         .then(
                                 function(response){
                                     return response.data;
                                 }, 
                                 null
                         );
         },
         
         
         updateUser: function(user, userId){
         	console.log("calling fetchAllUsers ")
                 return $http.put(BASE_URL+'/user/', user).then(function(response){
                                     return response.data;
                                 }, 
                                 function(errResponse){
                                     console.error('Error while updating user');
                                     return $q.reject(errResponse);
                                 }
                         );
         },
	
		
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
		
		
		  logout: function(){
          	console.log('logout....')
              return $http.get(BASE_URL+'/user/logout')
                      .then(function(response){
                                  return response.data;
                              }, 
                            null
                      );
      },
		
		   authenticate: function(user){
        	   console.log("Calling the method authenticate with the user :"+user)
      		 
            return $http.post(BASE_URL+'/login',user)
                    .then(function(response){
                                return response.data;   //user json object
                            }, 
                           null
                    );
    }
     
};
		   
		   }]);