define([ 'angular', 'main' ],
		function(angular, app) {
			var dashboardCtrl = function($scope,UserService) {
				$scope.dashboard="This is dashboard page";
				
				var fetchUserListSuccess	=	function(response){
					alert("dfas");
					$scope.usersList	=	response;
				};
				var fetchUserListfailure	=	function(response){
					console.log("unable to fetch user data!");
					$scope.errorMessage = 'unable to fetch user data!';
				};
				
				UserService.getAllUsers().then(fetchUserListSuccess, fetchUserListfailure);
			}
			app.controller('dashboardCtrl', [ '$scope','UserService', dashboardCtrl ]);
		});
