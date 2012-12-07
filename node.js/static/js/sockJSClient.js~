var sockJSClient = {
    sockjs_url: '/echo',
    init: function () {
        app.sockjs = new SockJS(this.sockjs_url);
        app.sockjs.onopen = this.onSocketOpen;
        app.sockjs.onmessage = this.onSocketMessage;
        app.sockjs.onclose = this.onSocketClose;
    },

    onSocketOpen: function () {
        $('#spinner').hide();
        $('#wait').hide();
        chat.displayMessage('[*] open', app.sockjs.protocol);
        app.sockjs.send(JSON.stringify({
            action: 'text',
            message: 'Joined',
            userName: app.userName
        }));
    },

    onSocketMessage: function (e) {
        var data = JSON.parse(e.data);
        switch (data.action) {
            case 'text':
                app.textMessage(data);
            break;
            case 'new_shape':
                app.createNewShape(data);
            break;
            case 'modified':
                canvasObj.modifyObject(data);
            break;
            case 'deleted':
                canvasObj.deleteObject(data);
            break;    
        }
    },

    onSocketClose: function () {
        chat.displayMessage('[*] close', '');
        app.sockjs.send(JSON.stringify({
            action: 'text',
            message: 'Left',
            userName: app.userName
        }));
    }

}