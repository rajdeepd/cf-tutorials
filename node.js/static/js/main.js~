/**
 * main.js
 * About this : This is the main javascipt file to handle adding, editing, deleting all elements on canvas (text, rectangle, circle etc)
 * Uses 'Fabric.js' library for client side
 * Node.js and  Node Package Manager (NPM) for server side - JavaScript environment that uses an asynchronous event-driven model.
 */
var app = {
    sockjs: null,
    chatDivElement: null,
    chatInputElement: null,
    currentIcon: null,
    userName: 'Guest',

    init: function () {
        $('#wait').hide();
        $('#spinner').show().center($('.canvas-div'));
        toolBar.addShapes();
        this.initShowPrompt();
        eventHandler.bindEvents();
        sockJSClient.init();
        this.initToolTip();
        canvasObj.init();
        chat.init();
    },

    initToolTip: function () {
        $(function () {
            $('[rel=tooltip]').tooltip({
                placement: 'right'
            });
        });
    },

    createNewShape: function (data) {
        var args = [];
        var argsObj = shapes[data.shape].defaultValues;
        argsObj['left'] = data.positionObj.x;
        argsObj['top'] = data.positionObj.y;
        argsObj['uid'] = data.args[0].uid;
        args.push(argsObj)
        shapes[data.shape].toolAction.apply(this, args);
        $("#freeow").hide();
    },

    textMessage: function (data) {
        chat.displayMessage('<b>[ ' + data.userName + ' ]:</b> ', data.message);
    },

    notifyNewShapeEvent: function (posObj) {
        var uniqId = util.getUniqId();
        app.sockjs.send(JSON.stringify({
            action: 'new_shape',
            positionObj: posObj,
            shape: app.shapeToDraw,
            args: [{
            	uid: uniqId
            }]
        }));
        var _data = {};
        _data.positionObj = posObj;
        _data.args = [{uid : uniqId }];
        _data.shape = app.shapeToDraw;
        app.createNewShape(_data);
    },

    getModifiedShapeJSON: function (obj, _action) {
        var obj = JSON.stringify({
            action: _action,
            name: obj.name,
            args: [{
                uid: obj.uid,
                object: obj
            }] // When sent only 'object' for some reason object 'uid' is not available to the receiver method.
        })
        return obj;
    },

    initShowPrompt: function () {
        window.showPrompt = function () {
            do {
                app.userName = prompt("Please enter your name( 4 to 15 chars)");
            }
            while (app.userName == null || app.userName.length < 4 || app.userName.length > 15);
            $('#username').text(app.userName);
        }
        showPrompt();
    }

} //end of app				