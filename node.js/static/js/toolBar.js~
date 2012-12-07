var toolBar = {
    shapeSelected: null,
    currentShape: null,

    addShapes: function () {
        var toolbarShapesMarkup = '';
        for (var index in shapes) {
            if (shapes.hasOwnProperty(index)) {
                var _shape = shapes[index].name;
                var _shapeSource = "static/images/" + _shape + "_g.png"
                toolbarShapesMarkup += '<div class="shape_icon" id="' + _shape + '"><a href="#" rel="tooltip" title="' + _shape + '"><img alt="' + _shape + '" class="image_style"  src="' + _shapeSource + '" /></a></div><hr />';
            }
        }
        $('.left-bar').append(toolbarShapesMarkup);
    }
}