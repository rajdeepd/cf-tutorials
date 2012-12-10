whiteboardApp.sockJSClient = {
    sockjs_url: '/echo',
    init: function () {
        this.sockJS = new SockJS(this.sockjs_url);
        this.sockJS.onopen = this.onSocketOpen;
        this.sockJS.onmessage = this.onSocketMessage;
        this.sockJS.onclose = this.onSocketClose;
    },

    onSocketOpen: function (conn) {
        $('#spinner').hide();
        whiteboardApp.chatWidget.chatwindow('displayMessage' ," <b>[ Opened ]:</b>  ", whiteboardApp.sockJSClient.sockJS.protocol);
        whiteboardApp.sockJSClient.sockJS.send(JSON.stringify({
            action: 'text',
            message: 'Joined',
            userName: whiteboardApp.userName
        }));
    },

    onSocketMessage: function (e) {
        var data = JSON.parse(e.data);
        switch (data.action) {
            case 'text':
                whiteboardApp.showTextMessage(data);
            break;
            case 'new_shape':
                whiteboardApp.createNewShape(data);
            break;
            case 'modified':
                whiteboardApp.canvasWidgetInstance.canvas('modifyObject', data);
            break;
            case 'deleted':
                whiteboardApp.canvasWidgetInstance.canvas('deleteObject', data);
            break;
        }
    },

    onSocketClose: function (conn) {
        whiteboardApp.chatWidget.chatwindow('displayMessage' ," <b>[ closed ]</b>", "");
        whiteboardApp.sockJSClient.sockJS.send(JSON.stringify({
            action: 'text',
            message: 'Left',
            userName: whiteboardApp.userName
        }));
    }
};