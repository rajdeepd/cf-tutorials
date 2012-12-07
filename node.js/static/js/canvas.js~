var canvasObj = {

    canvas: null,
    canvasWidth: 850,
    canvasHeight: 500,

    init: function() {
    	this.canvas = new fabric.Canvas('canvas_area');
    	this.resize();
    	this.addObservers();
    },
    
    prepareCanvas: function () {
        /* Need to set canvas dimensions when window is resized */
        this.canvas.setDimensions({
            width: this.canvasWidth,
            height: this.canvasHeight
        });
        this.canvasOffset = $('.canvas-div').offset();
   },

   resize: function () {
        var docWidth = $(window).width();
        var chatDivWidth = $('#chat-div').outerWidth();
        var toolBarWidth = $('.left-bar').outerWidth();
        $('.canvas-div').width(docWidth - ( chatDivWidth + toolBarWidth ) - toolBarWidth/2);
        this.canvas.renderAll();
        this.prepareCanvas();
   },

   addObservers: function () {
        this.canvas.observe('object:modified', function (e) {
        	   var activeGroup = canvasObj.canvas.getActiveGroup();
            if (activeGroup) {
            	 canvasObj.canvas.discardActiveGroup();
                var objectsInGroup = activeGroup.getObjects();
	             objectsInGroup.forEach(function (object) {
	             	 if(object.name === 'line') object.scaleY = 1;
	                app.sockjs.send(app.getModifiedShapeJSON(object, "modified"));
	            });
               return;
            }
            var obj = e.target;
            if(obj.name === 'line') obj.scaleY = 1;
            app.sockjs.send(app.getModifiedShapeJSON(obj, "modified"));
        })
   }, //end of addObservers
   
   modifyObject: function (data) {
        var obj = util.getObjectById(data.args[0].uid, this.canvas);
        if (obj) {
            shapes[data.name].modifyAction.apply(this, data.args);
            this.canvas.setActiveObject(obj);
            obj.setCoords(); // without this object selection pointers remain at orginal postion(beofore modified)
        }
        this.canvas.renderAll();
   },
   
   onDeletePress: function () {
        var activeObject = this.canvas.getActiveObject(),
            activeGroup = this.canvas.getActiveGroup();
        if (activeObject) {
            this.canvas.remove(activeObject);
 				app.sockjs.send(app.getModifiedShapeJSON(activeObject, "deleted"));
        } else if (activeGroup) {
            var objectsInGroup = activeGroup.getObjects();
            this.canvas.discardActiveGroup();
            objectsInGroup.forEach(function (object) {
               canvasObj.canvas.remove(object);
                app.sockjs.send(app.getModifiedShapeJSON(object, "deleted"));
            });
        }
    },

	deleteObject: function(data) {
		var obj = util.getObjectById(data.args[0].uid, this.canvas);
		console.log(obj);
      if (obj) {
			this.canvas.remove(obj);
		}
	},    
    
    moveObject: function (direction) {
        var canvas = canvasObj.canvas;
        var activeObject = canvas.getActiveObject(),
            activeGroup = canvas.getActiveGroup();
        if (activeObject) {
            switch (direction) {
                case 'left':
                    var leftX = activeObject.left;
                    activeObject.set('left', leftX - 5);
                    break;
                case 'up':
                    var topY = activeObject.top;
                    activeObject.set('top', topY - 5);
                    break;
                case 'right':
                    var leftX = activeObject.left;
                    activeObject.set('left', leftX + 5);
                    break;
                case 'down':
                    var topY = activeObject.top;
                    activeObject.set('top', topY + 5);
                    break;
            }
            activeObject.setCoords();
            canvas.renderAll();
            app.sockjs.send(app.getModifiedShapeJSON(activeObject));
        } else {
            canvas.discardActiveGroup();
        }
    },
    
    updateProperties: function (obj, recvdObj) {
			obj.left = recvdObj.left;
			obj.top = recvdObj.top;
			obj.scaleX = recvdObj.scaleX;
			obj.scaleY = recvdObj.scaleY;
			obj.width = recvdObj.width;
			obj.height = recvdObj.height;
			obj.setAngle(recvdObj.angle);
			if (recvdObj.fill) {
				obj.set("fill", recvdObj.fill);
			}
			if (recvdObj.stroke) {
				obj.set("stroke", recvdObj.stroke);
			}
			if (obj.text) {
				obj.text = recvdObj.text;
			}
			if(recvdObj.path) {
				obj.path = recvdObj.path;
			}
	
			if(obj.name === 'line') {
				obj.scaleY = 1;
			}
		}	
}