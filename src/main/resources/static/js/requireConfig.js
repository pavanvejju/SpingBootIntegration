define('jquery', [], function() {
    return $;
});

var _config = {
    waitSeconds : 10000,
    paths : {
        'angular' : 'https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.20/angular',
        'angular-bootstrap' : 'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/1.3.2/ui-bootstrap.min',
        'angular-ui' : 'plugins/angular/angular-ui',
        'angular-ui-router' : 'plugins/angular/angular-ui-router',
        'domReady' : 'plugins/require/domReady.min',       
        'ocLazyLoad': 'plugins/angular/ocLazyLoad'        
    },

    // angular does not support AMD out of the box, put it in a shim
    shim : {
    	'angular' : {
            exports : 'angular'
        },
        'angular-ui' : {
            deps : [ 'angular' ],
            exports : 'angular-ui'
        },
        'angular-ui-router' : {
            deps : [ 'angular-ui' ],
            exports : 'angular-ui-router'
        },
        'ocLazyLoad' : ['angular'],
        'domReady' : {
            exports : 'domReady'
        },
        'angular-bootstrap' : {
            deps : [ 'angular-ui-router' ],
            exports : 'angular-bootstrap'
        },
        'domReady' : {
            exports : 'domReady'
        }
    }
};
require.config(_config);
require(['domReady'], function(document) {
    require(["main"], function() {
        require.config({
            urlArgs: ""
        });
    });
});
