var sockJSServer = {
    initSockJS:function (server) {
        var sockjs = require('sockjs');

        /*List of incoming clients will be maintained in this map*/
        var clients = {};

        /*List of commands place here */
        var clientData = {};

        /* Create sockjs server */
        var sockjs_echo = sockjs.createServer({
            sockjs_url:"http://cdn.sockjs.org/sockjs-0.3.min.js",
            websocket:false
        });

        /*we call installHandlers passing in the app server so we can listen and answer any incoming requests on the echo path.*/
        sockjs_echo.installHandlers(server, {
            prefix:'/echo'
        });

        /*Listens for incoming connect events*/
        sockjs_echo.on('connection', onSockJSConnection);

        /*Is triggered when a new user joins in*/
        function onSockJSConnection(conn) {
             /*Add him to the clients list*/
            clients[conn.id] = conn;

            /* Send new user all the previous data to update his whitebaord */
            sendShapeActionsToClient(conn, clientData);
            sendChatDataToClient(conn, clientData);

            /*Listen for data events on this client*/
            conn.on('data', function (data) {
                onDataHandler(data, conn.id);
                pushUserData(data);
            });

            conn.on('close', function (data) {
                delete clients[conn.id];
                onDataHandler(JSON.stringify({userName: conn.userName, action:'text', message: 'left'}), conn.id);
            });

        }

        function sendShapeActionsToClient(connection, shapesData) {
            for (var index in shapesData) {
                if (shapesData.hasOwnProperty(index)) {
                    if (shapesData[index].action === 'new_shape') {
                        connection.write(JSON.stringify(shapesData[index]));
                    }
                    if (shapesData[index].modify) {
                        for (var action in shapesData[index].modify) {
                            connection.write(JSON.stringify(shapesData[index].modify[action]));
                        }
                    }
                }
            }
        }

        function sendChatDataToClient(connection, chatData) {
            if (chatData.textObj) {
                for (var action in chatData.textObj) {
                    connection.write(JSON.stringify(chatData.textObj[action]));
                }
            }
        }

        function pushUserData(data) {
            /* Push the data received from client*/
            var dataObj = JSON.parse(data);
            if (dataObj.action !== 'text') {
                pushShapesData(dataObj);
            } else {
                pushChatData(dataObj);
            }

        }

        function pushChatData(dataObj)  {
            if (clientData.textObj === undefined) {
                clientData.textObj = [];
            }
            clientData.textObj.push(dataObj);
        }

        function pushShapesData(dataObj) {
            var shapeId = dataObj.args[0].uid;
            switch (dataObj.action) {
                case 'new_shape':
                    clientData[shapeId] = dataObj;
                    break;
                case 'modified':
                    if (clientData[shapeId] !== undefined && clientData[shapeId].modify === undefined) {
                        clientData[shapeId].modify = {};
                    }
                    clientData[shapeId].modify[shapeId] = dataObj;
                    break;
                case 'deleted':
                    delete clientData[shapeId];
                    break;
            }
        }

        /* Handling data received from client to broadcast */
        function onDataHandler(dataStr, id) {
            var dataObj = JSON.parse(dataStr);
            dataObj.id = id;
            broadcast(dataObj);
        }

        /*To broadcast the data received from one client to all other active clients*/
        function broadcast(message, includeSelf) {
            for (var index in clients) {
                if (clients.hasOwnProperty(index)) {
                    var client = clients[index];
                    if (includeSelf || client.id !== message.id) {
                        client.write(JSON.stringify(message));
                    }
                }
            }
        }
    }
};

module.exports = sockJSServer;