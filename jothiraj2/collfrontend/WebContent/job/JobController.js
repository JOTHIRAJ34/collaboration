'use strict';

app.controller('JobController',['$scope','JobService','$location','$rootScope','$cookieStore','$http',function($scope, JobService, $location, $rootScope,$cookieStore, $http) {
							console.log("JobController...")
							var self = this;
							self.job = {jobid:'',jobProfile : '',qualification: '',email_id:'',postDate:'',jobDescription:'',status:''};
							this.jobs = [];
							self.submit = submit;
						    self.update = update;
						    self.get = get;
						    
						    fetchAllJobs();
						    reset();
							
						    
						    function fetchAllJobs() {
								console.log("fetchAllJobs...")
								JobService.fetchAllJobs().then(function(d) {
													self.jobs = d;
													console.log(self.jobs)
												},function(errResponse) {  
													console.error('Error while fetching Jobs');
												});
							};
						    
						    function createJob(job){
								console.log("createJob...")
								JobService.createJob(job).then(function(d) {
													alert("Thank you for creating message")
													$location.path("/viewjob")
												},
												function(errResponse) {
													console.error('Error while creating Job.');
												});
							};
							
							
							function reject(jobid){
								console.log("reject...")
								var reason = prompt("Please enter the reason");
								JobService.reject(id, reason).then(function(d) {
											self.job = d;
											self.fetchAllJobs
											$location.path("/manage_Jobs")
											alert(self.Job.errorMessage)

										}, null);
							};
							
							
							function updateJob(currentJob){
								console.log("updateJob...")  
								JobService.updateJob(currentJob).then(
										self.fetchAllJobs, null);
							};
							
							
							function update() {
								{
									console.log('Update the Job details',
											$rootScope.currentJob);
									updateJob($rootScope.currentJob);
								}
					 			reset();
							};
							
							
							function get(job){
								$scope.jv=job;
								console.log($scope.jv);
								$rootScope.viewjob=$scope.jv;
								console.log('viewjob')
								$location.path("/viewjob");
								
								
							};
							
							 function submit() {
									{
										console.log('Saving New Job', self.job);
										createJob(self.job);
									}
									reset();  
								};

								function reset() {
									self.job = {jobid:'',jobProfile : '',qualification: '',email_id:'',postDate:'',jobDescription:'',status:''};
									//$scope.myForm.$setPristine(); // reset Form
								};

							} ]);