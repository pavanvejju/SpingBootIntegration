define(['angular',"main", 'utils/dependencyResolverFor','services/index'],  function (angular,app,dependencyResolverFor) {
 
	app.config( ['$routeProvider', '$stateProvider', '$controllerProvider','$ocLazyLoadProvider',  function ($routeProvider, $st, $controllerProvider,$ocLazyLoadProvider) {
			 
    app.controller = $controllerProvider.register;
		
    $ocLazyLoadProvider.config({
		jsLoader: requirejs,
		debug: true
	});
    
    
    $st.state('login', {
    	url : '',    	
        views : {
           "main" : {
              templateUrl : 'templates/loginTpl.html',
              resolve : dependencyResolverFor(['js/controller/loginCtrl.js']),
              controller: 'loginCtrl'
             
            }
          }
        });

    $st.state('listUser', {
    	url : 'listUser',    	
        views : {
           "main" : {
              templateUrl : 'templates/listUser.html',
              resolve : dependencyResolverFor(['js/controller/listUser.js']),
              controller: 'listUser'
             
            }
          }
        });
	  
	  $st.state('dashboard', {
          url : 'dashboard',
          views : {
            "main" : {
              templateUrl : 'templates/dashboard.html',
              resolve : dependencyResolverFor(['js/controller/dashboardCtrl.js']),
              controller: 'dashboardCtrl'
            	  
              
             
            }
          }
        });
	  
		
		} ]).run([ '$state', '$rootScope', '$ocLazyLoad',function($state, $rootScope,$ocLazyLoad) {
			
			app.ocLazyLoad = $ocLazyLoad;
			//$state.transitionTo('index');

		    $rootScope.view = {};
		    
			var _call = function(hash, $state) {
				$state.transitionTo(hash);
			};
			
			$rootScope.call = function(hash) {
			   	_call.apply(self, [ hash, $state ])
		   }; 
	} ]);
});






