
function Tile(x, y, num) {
    this.x = x;
    this.y = y;
    this.num = num;
    this.offset = null;
    this.current = null;

    this.insertTile = function () {
        var box = '<div class="tile position-' + this.x + '-' + this.y
            + '" num="' + this.num + '"id="tile-' + this.num + '" >';
        box += num + '</div>';
        $('#tiles-container').append(box);
        var position = getGridOffset(this.x, this.y);
        position.left += 6;
        position.top += 6;
        this.offset = position;
        $('#tile-' + this.num).css({
            'left': position.left,
            'top': position.top
        });
        $('#tile-' + this.num).fadeIn('slow');
        this.current = getPositionInNumber(this.x, this.y);
    };

    this.move = function (i, j) {
        if (this.num > 0) {
            var offsetEnd = getGridOffset(i, j);
            console.log(i + " " + j);
            console.log(offsetEnd.top + " " + offsetEnd.left);
            $('#tile-' + this.num).css({
                'top': (offsetEnd.top + 6) + 'px',
                'left': (offsetEnd.left + 806) + 'px',
            });
        }
        else
            $('#tile-0').remove();
    }


};

function getTile(num) {
    for (var i = 0; i < tiles.length; i++) {
        if (tiles[i].num == num) {
            return tiles[i];
        }
    }
}

function getGridOffset(x, y) {
    var selectedCell = 'position-' + x + '-' + y;
    return $('#' + selectedCell).position();
}

// Define position class

function Position(x, y, free) {
    this.x = x;
    this.y = y;
    this.free = free;
}

function getPositionInNumber(x, y) {
    return 4 * (x - 1) + y;
}