
(function ($) {

    $.widget("ui.chatwindow", {

        // set default options
        options:{
            title: "Chat",
            placeHolderText: "Type here"
        },

        // initialize the plugin
        _create:function () {
            var self = this,
                _options = self.options,
                _element = self.element,
                _textareaForm = $('<textarea>').attr('placeholder', _options.placeHolderText),
                _chatTextDiv = $('<div>').addClass('chat-text'),
                _chatWindowTitle = $('<div>')
                                    .addClass('box-title')
                                    .append(_options.title);

            _element.addClass('ui-chatwindow')
                    .append(_chatWindowTitle, _chatTextDiv, _textareaForm);

            _textareaForm.keydown(function (event) {
                if (event.keyCode === 13 && event.shiftKey) {
                    event.stopPropagation();

                } else if (event.keyCode === 13) {
                    if($.trim($(this).val()).length <=0 ) return;
                    self._trigger("textSubmitted", event);
                    return false;
                }
            });

            this._chatTextDiv = _chatTextDiv;
        },

        displayMessage: function (user, message) {
            var messageEle = $('<span>').html(user + ' ' + message)
                                        .addClass('message');
            this._chatTextDiv.append(messageEle);
        },

         destroy:function () {
            this.element.removeClass('ui-chatwindow')
                .empty()
                .remove();
        },

        _setOption:function (option, value) {
            $.Widget.prototype._setOption.apply(this, arguments);
            var el = this.element;
            if (option === "title") {
                //todo: write code here
            }
        }
    });
})(jQuery);