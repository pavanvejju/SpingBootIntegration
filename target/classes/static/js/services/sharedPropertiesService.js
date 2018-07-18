define(["angular","main"], function(angular, app){
    var _sharedProperties = function() {
        var userInfo = {};
        return {
            updateInfo : function(key, value) {
                userInfo[key] = value;
            },
            getInfo : function(key) {
                var data = userInfo[key];
                return data;
            },
            clear : function(){
                userInfo = {};
            }
        }
    };

    app.service('sharedProperties', _sharedProperties); 
});
