define(["angular","main","services/sharedPropertiesService","services/httpService"], function(angular,app){
	var _userService = function ($rootScope,sharedProperties, httpService,$q,$http) {

		var baseurl	=	'api/wc/';
		
		var http_post	=	function(url,obj){
			var deferred = $q.defer();
	        $http.post(url,obj)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while fetching Users');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
		}
		
		var http_get	=	function(url){
			var deferred = $q.defer();
	        $http.get(url)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while fetching Users');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
		}
	        
	        
			this.createUsers = function addUser(userobj) {
			    return $http({
			        method : 'POST',
			        url : 'api/wc/user',
			        data : userobj
			    });
			}
			

			this.loginUser = function loginUser(userobj) {
				var url	=	baseurl+"loginsubmit";
				return http_post(url,userobj);
			}
			
			this.getAllUsers = function getAllUsers() {
			    var url	=	baseurl+'users'
			    return http_get(url);
			}
			
			this.fetchAllUsersLoggedIn  =   function(callbacksuccess,callbackfail){
                httpService.getService().sendRequest('/api/wc/users',{}, null, 'GET', callbacksuccess,callbackfail);
            };
            
			this.fetchUserById  =   function(userId,callbacksuccess,callbackfail){
                httpService.getService().sendRequest('/users/'+userId,{}, null, 'GET', callbacksuccess,callbackfail);
            };
            
        	this.fetchTopicById  =   function(url){
        		return http_get(url);
            };
            
            this.fetchAllUsers  =   function(callbacksuccess,callbackfail){
                httpService.getService().sendRequest('/users/fetchuserList',{}, null, 'GET', callbacksuccess,callbackfail);
            };
            	
			this.userSearch = function(data,callbacksuccess,callbackfail){
                httpService.getService().sendRequest("/users",{}, data,'GET',callbacksuccess,callbackfail);
            };
		};
	 app.service('UserService',['$rootScope', 'sharedProperties', 'httpService','$q','$http', _userService]);
 });
