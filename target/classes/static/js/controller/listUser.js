define([ 'angular', 'main' ],
		function(angular, app) {
	
			var listUser = function($scope,UserService) {
				$scope.userList = function () {
					UserService.getAllUsers()
				      .then(function success(response) {
				          $scope.users = response.data.users;
				          $scope.message='';
				          $scope.errorMessage = '';
				      },
				      function error (response) {
				          $scope.message='';
				          $scope.errorMessage = 'Error getting users!';
				      });
				}
			}
			app.controller('listUser', [ '$scope', 'UserService', listUser ]);
		});