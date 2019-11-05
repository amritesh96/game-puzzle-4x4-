// $(function() {
//     FastClick.attach(document.body);
// });
var ali= [];
ali = localStorage.getItem("key").split(',');// [6, 13, 7, 10, 8, 9, 11, 0,
												// 15, 2, 12, 5, 14, 3, 1, 4];
var solveArray = [];
solveArray=localStorage.getItem("solution").split(',');
console.log("solve Array:"+solveArray)
var positions = [];
var tiles = [];
var time = 0;
var moves = 0;
var counter = null;
var paused = true;
var won = false;

$(document).on('click', '#start-button', function(){
	console.log("inside click----------------");
	if(!$('#start-button').hasClass('disabled')){
		startGame();
	} else {
		showBtnErrorMessage();
	}
});

$(document).on('click', '#reset-button', function(){
	if(!$(this).hasClass('disabled')){
		// resetGame();
	} else {
		showBtnErrorMessage();
	}
});

function startGame(){
	console.log("inside start Game----------------");
	paused = false;
	$('#start-button').html('PAUSE');
	$(this).css('opacity', '1');
	if(tiles.length == 0){
		console.log("inside reset contents----------------");
		resetContents();
		setTimeout(solveTheBoard, 3000);
	}
	counter = setInterval(function(){
		time++;
		displayCurrentTime();
	}, 1000);
}

function sleep(ms) {
	return new Promise(resolve => setTimeout(resolve, ms));
}

async function solveTheBoard(){
	index=0;
	console.log("solve function");
	while(index<solveArray.length){
		// console.log("called");
		var tile = getTile(solveArray[index]);
		tile.move();
		await sleep(50);
		// console.log("after 2 secs");
		index++;
	}
	
}

function pauseGame(){
	paused = true;
	$('#start-button').html('START');
	$('#overlay-paused').show();
	$('#overlay').fadeIn('fast');
	clearInterval(counter);
}

// to get board to starting position
// function resetGame(){
// pauseGame();
// resetContents();
// $('#overlay-paused').hide();
// $('#overlay-play').show();
// $('#overlay-message').hide();
// $('#overlay-submessage').hide();
// $('#overlay-buttons').hide();
// }


function resetContents(){
	tiles = [];
	positions = loadPositions();
	generateTiles(positions);
	time = 0;
	moves = 0;
	$('#score-point .num').html('0');
	$('#timepoint .num').html('00:00');
	won = false;
}

function generateTiles(positions){
	console.log('Generating tiles');
	var position = null;
	var tile = null;
	var k=0;
	for(var i = 1; i < 5; i ++){
		for(var j = 1; j < 5; j++){
			if(k<16){
				if(ali[k]==0){
					position = getPosition(i,j);
					position.free = true;
					position = null;
					tile = null;
					k++;
				}
				else{
					tile = new Tile(i, j, ali[k]);
					tiles.push(tile);
					tile.insertTile();
					position = getPosition(i,j);
					position.free = false;
					position = null;
					tile = null;
					k++;
				}
				
			}
		}
	}
}

function addMove(){
	moves++;
	$('#score-point .num').html(moves);
}

function displayCurrentTime(){
	var minutes = Math.floor(time / 60);
	var seconds = time - minutes * 60;
	$('#timepoint .num').html(convert(minutes) + ':' + convert(seconds));
}

function convert(n){
    return n > 9 ? "" + n: "0" + n;
}

//function win(){
//	pauseGame();
//	$('#overlay-paused').hide();
//	$('#overlay-inner').show();
//	$('#overlay-inner #overlay-message').html('SOLVED!').show();
//	var finalTime = $('#timepoint .num').html();
//	var finalMoves = $('#score-point .num').html();
//	$('#overlay-buttons').show();
//	$('#overlay-inner #overlay-submessage').html('<b>Time</b>: ' + finalTime +'&nbsp&nbsp&nbsp<b>Moves</b>: ' + finalMoves).show();
//	tiles = [];
//	won = true;
//}