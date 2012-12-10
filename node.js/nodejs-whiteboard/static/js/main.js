/**
 * main.js
 * About this : This is the main javascript file to handle adding, editing, deleting all elements on canvas (text, rectangle, circle etc)
 * Uses 'Fabric.js' library for client side
 * Node.js and  Node Package Manager (NPM) for server side - JavaScript environment that uses an asynchronous event-driven model.
 */
var whiteboardApp = {
    chatDivElement: null,
    chatInputElement: null,
    currentIcon: null,
    userName: 'Guest',

    init: function () {
        this.handleEscKeyPress();
        this.showSpinner();
        this.initCanvas();
        this.initToolbar();
        this.initChatWindow();
        this.initShowPrompt();
        this.sockJSClient.init();
        this.initEventListeners();
        this.resize();
    },

    showSpinner:function() {
        $('#spinner').show().center($(window));
    },

    initEventListeners:function() {
        $(document).bind('keydown', this.onKeyDown);
        $(window).resize(whiteboardApp.resize);
    },

    handleEscKeyPress: function() {
        /* Prevent from closing SockJS connection when ESC key is pressed*/
        window.addEventListener('keydown', function(e) { (e.keyCode === 27 && e.preventDefault()) })
    },

    initToolbar: function () {
        var tb = $("#shapesToolbar").toolbar(
            {
                shapes:whiteboardApp.shapes, // shapes object with shape 'name' and 'iconname' ex: shapes = {  rectangle: {  name: 'rectangle', imagesPath:'/static/images/' } }
                dropTarget:$('.ui-canvas'),
                title:'Shapes',
                shapeSelected:this.onShapeSelect,  // callback
                dropTargetClicked:this.onClickDropTarget   //callback
            }
        );
    },

    initChatWindow: function() {
        whiteboardApp.chatWidget = $(".chat-div").chatwindow(
            {
                title: "Chat",
                textSubmitted:this.onTextSubmit
            } );
    },

    initCanvas:function() {
        whiteboardApp.canvasWidgetInstance = $("#canvas-holder").canvas(
            {
                title: "Canvas",
                fabric: fabric,
                shapeModified: this.onShapeModify,
                applyModify: this.onApplyModify,
                shapeDeleted: this.onShapeDelete
            } );

        whiteboardApp.canvas = whiteboardApp.canvasWidgetInstance.canvas("getCanvasInstance");
    },

    onApplyModify: function(event, data) {
        whiteboardApp.shapes[data.name].modifyAction.apply(this, data.args);
    },

    onShapeSelect:function(event) {
        whiteboardApp.shapeToDraw = event.shapeSelected;
        whiteboardApp.shapeSelected = true;
        $('#freeow').show();
    },

    onClickDropTarget:function(event) {
        if (whiteboardApp.shapeSelected) {
            var scrollLeft = $('.ui-canvas').scrollLeft(),
                mouseX = event.pageX - $('.ui-canvas').offset().left + scrollLeft, // offset X
                mouseY = event.pageY - $('.ui-canvas').offset().top; // offset Y
            whiteboardApp.notifyNewShapeEvent({
                x: mouseX,
                y: mouseY
            });
            whiteboardApp.shapeSelected = false;
        }
    },

    onTextSubmit: function (event) {
        var target = $(event.currentTarget),
            data = {
                userName: whiteboardApp.userName,
                message: target.val(),
                action: 'text'
            };

        whiteboardApp.showTextMessage(data);

        whiteboardApp.sockJSClient.sockJS.send(JSON.stringify(data));

        target.val('').focus();

        return false;
    },

    createNewShape: function (data) {
        var args = [],
            argsObj = whiteboardApp.shapes[data.shape].defaultValues;

        argsObj.left = data.positionObj.x;
        argsObj.top = data.positionObj.y;
        argsObj.uid = data.args[0].uid;
        args.push(argsObj);
        whiteboardApp.shapes[data.shape].toolAction.apply(this, args);
        $("#freeow").hide();
    },

    showTextMessage: function (data) {
        var _userName = (data.userName === undefined) ? "user *"  : data.userName;
        var userNameString  = "<b>[ " + _userName + " ]:</b>"
        whiteboardApp.chatWidget.chatwindow('displayMessage' , userNameString, data.message);
    },

    notifyNewShapeEvent: function (posObj) {
        var uniqId = util.getUniqId(),
            _data = {};
        whiteboardApp.sockJSClient.sockJS.send(JSON.stringify({
            action: 'new_shape',
            positionObj: posObj,
            shape: whiteboardApp.shapeToDraw,
            args: [
                {
            	uid: uniqId
                }
            ]
        }));
        _data.positionObj = posObj;
        _data.args = [{uid : uniqId }];
        _data.shape = whiteboardApp.shapeToDraw;
        whiteboardApp.createNewShape(_data);
    },

    onShapeModify:function(event, data) {
        whiteboardApp.sockJSClient.sockJS.send(whiteboardApp.getModifiedShapeJSON(data, "modified"));
    },

    onShapeDelete:function(event, data) {
        whiteboardApp.sockJSClient.sockJS.send(whiteboardApp.getModifiedShapeJSON(data, "deleted"));
    },

    getModifiedShapeJSON: function (shape, _action) {
        var _obj = JSON.stringify({
            action: _action,
            name: shape.name,
            args: [{
                uid: shape.uid,
                object: shape
            }] // When sent only 'object' for some reason object 'uid' is not available to the receiver method.
        });
        return _obj;
    },

    initShowPrompt: function () {
        window.showPrompt = function () {
            do {
                whiteboardApp.userName = prompt("Please enter your name( 4 to 15 chars)");
            }
            while (whiteboardApp.userName === null || whiteboardApp.userName.length < 4 || whiteboardApp.userName.length > 15);
            $('#username').text(whiteboardApp.userName);
        };
        window.showPrompt();
    },

    onKeyDown: function (e) {
        var evt = (e) ? e : (window.event) ? window.event : null;
        if (evt) {
            var key = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode : ((evt.which) ? evt.which : 0));
            if (key === 46) { //  DELETE
                whiteboardApp.canvasWidgetInstance.canvas("onDeletePress");
              } else if (key === 37) {
                //left arrow
                whiteboardApp.canvasWidgetInstance.canvas("moveObject", 'left');
            } else if (key === 38) {
                // up arrow
                whiteboardApp.canvasWidgetInstance.canvas("moveObject", 'up');
            } else if (key === 39) {
                // right arrow
                whiteboardApp.canvasWidgetInstance.canvas("moveObject", 'right');
            } else if (key === 40) {
                // down arrow
                whiteboardApp.canvasWidgetInstance.canvas("moveObject", 'down');
            }
        }
    },

    resize: function () {
        var docWidth = $(window).width(),
            chatDivWidth = $('.chat-div').outerWidth(),
            toolBarWidth = $('.ui-toolbar').outerWidth();
        $('.ui-canvas').width(docWidth - ( chatDivWidth + toolBarWidth ) - toolBarWidth/2);
        whiteboardApp.canvas.renderAll();
        whiteboardApp.canvasWidgetInstance.canvas('setCanvasDimensions');
    }

}; //end of app