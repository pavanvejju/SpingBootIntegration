define(["angular","main","services/sharedPropertiesService"], function(angular,app){
    var _httpService = function($http,$rootScope,sharedProperties){
            var _callback;
            var CachedData = {};
            var successHandler = function(responseData) {
                    _callback(responseData);
            };

            return {
                doPost : function(path, data, callback) {
                    _callback = callback;
                    $http.post(path, data).success(successHandler);
                },
                get : function(url, config) {
                    return $http.get(url, config);
                },
                post : function(url, data, config) {
                    return $http.post(url, data, config);
                },
                fetchTemplate : function(template_url) {
                    return $.ajax({
                        type : "GET",
                        url : template_url,
                        async : false
                    }).responseText;
                },
                http : function(method, url, config, cache, successFuncCallBack, errorFuncCallBack) {
                    if (typeof cache == 'undefined') {
                        cache = false;
                    }

                    cache = false;

                    config['method'] = method;
                    config['cache'] = cache;
                    config['url'] = url;

                    if (!config.headers) {
                        config.headers = {};
                    }

                    if (config.headers && !config.headers['Content-Type']) {
                        config.headers['Content-Type'] = "application/json";
                    }

                    $http(config).success(function(data, status) {
                        if (typeof successFuncCallBack != 'undefined') {
                            successFuncCallBack(data, status);
                        }
                    }).error(function(data, status) {
                        if (typeof errorFuncCallBack != 'undefined') {
                            errorFuncCallBack(data, status);
                        }
                    });
                },
                xhrGetCall : function(url, config, callBackFunction, cache) {
                    if (typeof cache == 'undefined') {
                        cache = false;
                    }

                    if (cache == true) {
                        if (typeof CachedData[url] != 'undefined') {
                            callBackFunction(CachedData[url]);
                            return;
                        }
                    }

                    if (typeof config == 'undefined') {
                        $http.get(url).success(function(data) {

                            CachedData[url] = data;

                            callBackFunction(data);
                        });
                    } else {
                        $http.get(url, config).success(function(data) {

                            CachedData[url] = data;

                            callBackFunction(data);
                        });
                    }
                },
                getService: function($scope){
                    var self = this;
                    var scope = $scope;
                    return  {scope: $scope, sendRequest: function(path, headerPairs, requestPojo, method, successCallback, failCallback, isSync) {
                                if (this.scope) {
                                    this.scope.msg = [];
                                }

                                $rootScope.errorsList = '';
                                $rootScope.errorDetails = [];
                                $rootScope.errorDescription = null;
								$rootScope.isCommonError = null;
                                $rootScope.logoutSuccessShow = null;
                                $rootScope.sessionTimeOutShow=null;
								
                                async = (true != isSync);
                                path = '../Application' + path;
                                if (isSync) {
                                    $http = $.ajax;
                                }
                                var failWrap = function(data) {
                                        $rootScope.errorDetails = data.errorDetails;
                                        
                                        if (!failCallback && scope ){
                                            var errs = data.errorDetails;
                                            scope.errorDetails = errs;
                                        }
                                        if (failCallback)
                                            failCallback(data);
                                }
                                

                                var callServer = function() {
                                    if (headerPairs instanceof Array || !headerPairs) {
                                        headerPairs = {};
                                    }
                                  
                                    var args = {
                                        method: method,
                                        url: path,
                                        async: async,
                                        headers: headerPairs,
                                        data: requestPojo,
                                    };
                                    if (headerPairs['transformRequest'] == 'angular.identity') {
                                        args = {
                                            method: method,
                                            url: path,
                                            async: async,
                                            headers: headerPairs,
                                            data: requestPojo,
                                            transformRequest: angular.identity
                                        };
                                    }
                                    if (method == 'GET') {
                                        args = {
                                            method: method,
                                            url: path,
                                            async: async,
                                            headers: headerPairs,
                                            params: requestPojo,
                                        };

                                    }
                                    $http(args)
                                        .success(
                                            function(data,status,headers,config) {
                                                try {
                                                    if (data.errorDetails==undefined || (data.errorDetails == null || data.errorDetails[0].errorCode == 0)) {
                                                        successCallback(data,status,headers,config);
                                                    } else {
                                                        failWrap.apply(self, [data]);
                                                    }
                                                } finally {
                                                }
                                            }).error(failWrap);
                                };
                                callServer.apply(self, []);
                            }
                        };//return sendRequest
                } 
                
            };//main return 
    };//httpService end
	app.factory('httpService', ['$http','$rootScope','sharedProperties',  _httpService]);
});