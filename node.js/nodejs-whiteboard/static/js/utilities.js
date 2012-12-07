var util = {
    /**
     * Align the element in the center of the canvas div element
     * @property id
     * @type object
     */
    centerSpinner: (function () {
        // Add the jQuery center() method...
        jQuery.fn.center = function (div) {
            this.css("position", "absolute");
            this.css("top", (div.height() - this.height()) / 2 + div.scrollTop() + "px");
            this.css("left", (div.width() - this.width()) / 2 + div.scrollLeft() + "px");
            return this;
        }
    })(),

    /**
     * Searches for the object with the given id and returns that object
     * @property id
     * @type object
     */
    getObjectById: function (id, _canvas) {
        var obj;
        var objs = _canvas.getObjects();
        objs.forEach(function (object) {
            if (object.uid === id) {
                obj = object;
            }
        });
        return obj;
    },

    /**
     * Returns Random String 
     * @method randomString
     * @param null
     */
    randomString: function () {
        var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
        var string_length = 8;
        var randomstring = '';
        var i = 0;
        for (i = 0; i < string_length; i++) {
            var rnum = Math.floor(Math.random() * chars.length);
            randomstring += chars.substring(rnum, rnum + 1);
        }
        return randomstring;
    },

    /**
     * Returns unique id to attach to an object
     * @method uniqid
     * @param null
     */

    getUniqId: function () {
        var newDate = new Date();
        return this.randomString() + newDate.getTime();
    }

};
//util.centerSpinner();