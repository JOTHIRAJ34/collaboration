'use strict';

app.controller('JobController',['$scope','JobService','$location',function($scope, JobService, $location) {
							console.log("JobController...")
							var self = this;
							self.job = {jobid:'',jobProfile : '',qualification: '',email_id:'',postDate:'',jobDescription:'',status:''};
							this.jobs = [];
							self.submit = submit;
						    /*self.update = update;
						    self.get = get;*/
						    reset();
							
						    
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