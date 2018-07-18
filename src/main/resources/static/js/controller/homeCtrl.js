define([ 'angular','main'], function(angular, app) {
	var homeCtrl = function($scope,$state,UserService) {
		 
		 $scope.init	=	function(){
				$scope.mysite	=	"This is pavan first web page";
				$scope.lastName		=	"Kumar";
			}
			$scope.init();
			
			UserService.fetchTopicById("/api/wc/topics/Adobe").then(callbacksuccess, callbackfailure);
			
			var callbacksuccess	=	function(data){
				$scope.topicName=data.name;
			};
			var callbackfailure	=	function(data){
				 console.error('Error while fetching');
			};
			$state.transitionTo('login');
			
	}
app.controller('homeCtrl', [ '$scope','$state','UserService', homeCtrl]);
});
