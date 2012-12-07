(function ($) {

    $.widget("ui.toolbar", {

        // set default options
        options:{
            title:"Shapes"
        },

        // initialize the plugin
        _create:function () {
            var self = this,
                _options = self.options,
                _element = self.element,
                _titleDiv = $('<div>').addClass('box-title')
                                      .append(_options.title);

            _element.addClass('ui-toolbar')
                    .append(_titleDiv);

            self._addShapes(_element, _options.shapes);

            $('.ui-toolbar .shape').on('click', function (event) {
                self._onShapeClicked(event);
            });

	    console.log(_options.dropTarget);
            if(_options.dropTarget) {
                _options.dropTarget.click(function (e) {
		    console.log('target clicked');
                    if (_element.currentShape) {
                        self._trigger('dropTargetClicked', e);
                        _element.currentShape.removeClass('selected');
                    }
                });
            }
            this.titleDiv = _titleDiv;
        },

        _onShapeClicked: function(event) {
            var _element = this.element;

            if (_element.currentShape) {
                (_element.currentShape).removeClass('selected');
            }

            _element.currentShape = $(event.currentTarget);
            _element.currentShape.addClass('selected');

            event.shapeSelected =  $(_element.currentShape).attr('id');
            this._trigger('shapeSelected', event);
        },

        _addShapes:function (_element, shapes) {
            for (var index in shapes) {
                if (shapes.hasOwnProperty(index)) {
                    var _shapeId = shapes[index].name,
                        _shape = $('<div>').addClass(_shapeId)
                            .addClass('shape')
                            .attr('id', _shapeId);
                    _element.append(_shape);
                }
            }
        },

        destroy:function () {
            this.element.removeClass('ui-toolbar')
                .empty()
                .remove();
        },

        _setOption:function (option, value) {
            $.Widget.prototype._setOption.apply(this, arguments);
            var el = this.element;
            if (option === "title") {
                this.titleDiv.html(option);
            }
        }
    });
})(jQuery);
