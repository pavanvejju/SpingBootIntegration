define(['angular', 'angular-ui','angular-ui-router', 'angular-bootstrap' ,'ocLazyLoad'], function(angular) {

    var $platfomDependentModules = ['ui','ui.router', 'ui.compat','oc.lazyLoad'];

    window.Platform = angular.module("platform", $platfomDependentModules);


    require(['routes'], function() {
    	angular.bootstrap(document, ['platform']);
    });
    
    return Platform;
});
