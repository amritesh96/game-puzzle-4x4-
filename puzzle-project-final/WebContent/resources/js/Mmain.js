
var ali= [];
ali = localStorage.getItem("key").split(',');// [6, 13, 7, 10, 8, 9, 11, 0,
												// 15, 2, 12, 5, 14, 3, 1, 4];
var positions = [];
var tiles = [];
var time = 0;
var moves = 0;
var counter = null;
var paused = true;
var won = false;

$(document).on('click', '.tile', function(){
	if(!paused){
		var num = $(this).attr('num');
		var tile = getTile(num);
		tile.move();
	}
});

$(document).on('click', '#start-button', function(){
	startGame();
});

function startGame(){
	paused = false;
	$('#start-button').html('PAUSE');
	$(this).css('opacity', '1');
	if(tiles.length == 0){
		resetContents();
	}
	counter = setInterval(function(){
		time++;
		displayCurrentTime();
	}, 1000);
}

function pauseGame(){
	paused = true;
	$('#start-button').html('START');
	$('#overlay-paused').show();
	$('#overlay').fadeIn('fast');
	clearInterval(counter);
}


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
	// setTimeout( function(){
	// shuffle();}
	// ,1000 );
	
}

// function shuffle(){
// var current = null;
// var directions = ['left', 'right', 'up', 'down'];
// for(var i = 0; i < 200; i++){
// current = directions[Math.floor(Math.random() * directions.length)];
// console.log(current)
// moveSwipedTile(current);
// }
// }

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

function win(){
	pauseGame();
	$('#overlay-paused').hide();
	$('#overlay-inner').show();
	$('#overlay-inner #overlay-message').html('SOLVED!').show();
	var finalTime = $('#timepoint .num').html();
	var finalMoves = $('#score-point .num').html();
	$('#overlay-buttons').show();
	$('#overlay-inner #overlay-submessage').html('<b>Time</b>: ' + finalTime +'&nbsp&nbsp&nbsp<b>Moves</b>: ' + finalMoves).show();
	tiles = [];
	won = true;
}

$(document).keydown(function(e) {
	var tile = null;
	var position = getFreePosition();
	if(!paused){
		e.preventDefault(); 
    	switch(e.which) {
       		case 37: // left
				console.log('left');
					if(position.y < 4){
						tile = getTileInPosition(position.x, position.y + 1);
						tile.move();
					}
        	break;
        	case 38: // up
				console.log('up');
				if(position.x < 4){
					tile = getTileInPosition(position.x + 1, position.y);
					tile.move();
				}
        	break;
        	case 39: // right
				console.log('right');
				if(position.y > 1){
					tile = getTileInPosition(position.x, position.y - 1);
					tile.move();
				}
        	break;
        	case 40: // down
				console.log('down');
				if(position.x > 1){
					tile = getTileInPosition(position.x - 1, position.y);
					tile.move();
				}
        	break;	
			case 27: // esc
				console.log('esc');
				pauseGame();
        	break;
        	default: return;
    	}
	} else {
		switch(e.which) {
		 case 27: 	// esc
			console.log(won);
			if($('#options-inner').is(":visible")){
				$("#options-inner" ).slideUp("slow");
			}
			if($('#new-challenge-box').is(":visible") || $("#challenge-details" ).is(":visible")){
				$("#challenge-button" ).click();
			}
			if($('#challenge-code-input').is(":visible")){
//				$('#challenge-code-input').hide();
//				$('#challenge-button').fadeIn('fast');
			}
			if(paused && $('#timepoint .num').html() != '00:00' && !won){
				startGame();
			}
        break;
		case 13: 	// enter
			console.log('enter');
			if(paused  && !won && !$('#start-button').hasClass('disabled')){
				startGame();
			}
			if(won && !$('#overlay-play').is(":visible")){
				//resetGame();
			}
        break;

        default: return;
    	}
	}
});