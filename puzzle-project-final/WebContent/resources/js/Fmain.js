var generateBasic = [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0 ];
var tiles = [];
var i = 1;
var j = 1;
var inputArray = [];
var k;

function generateRandom() {
	var size = generateBasic.length;
	var temp;
	for (var k = 0; k < size; k++) {
		inputArray[k] = generateBasic[k];
	}
	inputArray = shuffle(inputArray);
	for (var k = 0; k < inputArray.length; k++) {
		var tile = getTile(inputArray[k]);
		tile.move(i, j);
		if (i < 5) {
			j++;
			if (j % 4 == 1) {
				i++;
				j = 1;
			}
		}
		if (i == 5) {
			localStorage.setItem("key", inputArray);
			$('#submit-button').show();
			$('#manual-button').show();
			$('#random-button').hide();


		}
	}

}
function shuffle(array) {
	var tmp, current, top = array.length;
	if (top)
		while (--top) {
			current = Math.floor(Math.random() * (top + 1));
			tmp = array[current];
			array[current] = array[top];
			array[top] = tmp;
		}
	return array;
}

$(document).on('click', '.tile', function() {
	var num = $(this).attr('num');
	inputArray.push(parseInt(num));
	console.log(inputArray);
	var tile = getTile(num);
	tile.move(i, j);
	if (i < 5) {
		j++;
		if (j % 4 == 1) {
			i++;
			j = 1;
		}
	}
	if (i == 5) {
		localStorage.setItem("key", inputArray);
		$('#submit-button').show();
		$('#manual-button').show();
		$('#random-button').hide();


	}
});

function startGame() {
	$('#overlay').fadeOut('fast');
	$('#overlay-play').hide();
	$(this).css('opacity', '1');
	generateTiles();
}
document.addEventListener("DOMContentLoaded", function() {
	generateTiles();
});
function on() {
	document.getElementById("overlay").style.display = "block";
	$('#submit-button').hide();
	$('#manual-button').hide();

}
document.addEventListener("DOMContentLoaded", function() {
	on();
});
function off() {
	document.getElementById("overlay").style.display = "none";
}

function generateTiles() {
	console.log('Generating tiles');
	var tile = null;
	var k = 0;
	for (var i = 1; i < 5; i++) {
		for (var j = 1; j < 5; j++) {
			if (k < 16) {
				tile = new Tile(i, j, generateBasic[k]);
				tiles.push(tile);
				tile.insertTile();
				k++;
			}
		}
	}
}
