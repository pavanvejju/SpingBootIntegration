define([ 'angular', 'main' ],
		function(angular, app) {
			var loginCtrl = function($scope, $state,$http, UserService) {
				
				$scope.getAllUsers = function () {
					UserService.getAllUsers()
				      .then(function success(response) {
				          $scope.users = response.data._embedded.users;
				          $scope.message='';
				          $scope.errorMessage = '';
				      },
				      function error (response) {
				          $scope.message='';
				          $scope.errorMessage = 'Error getting users!';
				      });
				}
				
				var loginSuccess	=	function(data){
					if(data!=undefined && data!=""){
						$scope.message = 'Hello Logged In Succesfully!'+data.username;
						$scope.errorMessage = '';
						$state.transitionTo('dashboard');
					}else{
						$scope.errorMessage = 'Invalid Login!';
						$scope.message = '';
					}
					
					
				};
				var loginFailure	=	function(data){
					$scope.errorMessage = 'Invalid Login!';
					$scope.message = '';
				};
				

				function clearForm() {
					$scope.userform.username = "";
					$scope.userform.password = "";
				};
				$scope.submit = function() {
					UserService.loginUser($scope.userform).then(loginSuccess, loginFailure);
				}
			}
			app.controller('loginCtrl', [ '$scope', '$state', '$http', 'UserService', loginCtrl ]);
		});
