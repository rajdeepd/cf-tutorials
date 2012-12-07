/* app.js */
(function () {

    /**
        * import required modules and initialize servers
        * @method init
        * @param none
        */
    function init() {
        var express = require('express'),
            http = require('http'),
            app = express();

        /* Set static folder*/
        app.use('/static', express.static(__dirname + '/static'));

        /* redirect user to index.html*/
        app.get('/', function (req, res) {
            res.sendfile(__dirname + '/index.html');
        });

        loadConfig(function(dataObj) {
            /* Create http server */
            var server = http.createServer(app);
            server.listen(process.env.VCAP_APP_PORT || dataObj.port);
            initSockServer(server);
        });
    }

    /**
        * loads configuration.json file and sends data to callback
        * @method loadConfig
        * @param {Function} callback
        */

    function loadConfig(callback) {
        var fs = require('fs');
        /* load configuration data */
        fs.readFile(__dirname + '/config.json', function(err, data) {
            callback(JSON.parse(data))
        });
    }

    /**
     * Initializes the sockJS server
     * @method initSockServer
     * @param  server - http server instance
     */

    function initSockServer(server) {
        var sockJSServer = require('./sockJSServer');
        sockJSServer.initSockJS(server);
    }
    init();
})();