<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Fifteen Puzzle Game</title>
<meta name="description"
	content="Fifteen Puzzle game (15 puzzle-game): move tiles in grid to order them from 1 to 15!">
<meta name="keywords"
	content="Fifteen puzzle, game, fifteen puzzle, game, tile">
<meta name="author" content="Lorenzo Cioni">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="Robots" content="All" />


<script
	src="${pageContext.request.contextPath}/resources/lib/jquery-2.1.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/lib/jquery.touchSwipe.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Ftiles.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Fmain.js"></script>

<script>
	var N = 4;
	var arr;
	var newArr = [];
	function getInvCount(arr) {
		var inv_count = 0;
		for (var i = 0; i < arr.length - 1; i++) {
			for (var j = i + 1; j < arr.length; j++) {
				if ((arr[j] != 0 && arr[i] != 0) && arr[i] > arr[j])
					inv_count++;
			}
		}
		return inv_count;
	}

	function findXPosition(arr) {
		for (var j = 0; j < arr.length; j++) {
			if (arr[j] == 0) {
				return N - Math.floor(j / 4);
			}
		}
		return -1;
	}

	function isSolvable(arr) {
		var invCount = getInvCount(arr);
		console.log(invCount);
		if ((N & 1) > 0) {
			return (invCount & 1) == 0;
		} else {
			var pos = findXPosition(arr);
			if ((pos & 1) == 0) {
				if ((invCount & 1) == 0) {
					return false;
				} else {
					return true;
				}
			} else {
				if ((invCount & 1) == 0) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	function goToIndexPage(newArr) {
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "getArray", true);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send(JSON.stringify({
			"key" : newArr.toString()
		}));

		console.log("successFully Sended Data to BackEnd");
		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				console.log("Response Json")
				var data = xhr.responseText;
				var jsonData = JSON.parse(data);
				console.log(typeof jsonData);
				console.log(jsonData);
				localStorage.setItem("solution", jsonData.responseData);
				window.location = "index.jsp";
			}
		};
	}
	function sendDataToAPI() {
		arr = localStorage.getItem("key");
		console.log("arr " + arr);
		newArr = arr.split(",").map(Number);
		console.log(newArr);
		if (isSolvable(newArr)) {
			goToIndexPage(newArr);
		} else {
			console.log("not possible to solve");
			alert("Unsolvable State");

			var num1 = prompt("Please enter First Number");
			var num2 = prompt("Please enter Second Number");
			swap(num1, num2);
			localStorage.setItem("key", newArr);
			if (isSolvable(newArr)) {
				goToIndexPage(newArr);
			}
		}
	}

	function swap(num1, num2) {
		var index1;
		var index2;
		for (var i = 0; i < newArr.length; i++) {
			if (newArr[i] == num1) {
				index1 = i;
			}
			if (newArr[i] == num2) {
				index2 = i;
			}

		}
		var temp = newArr[index1];
		newArr[index1] = newArr[index2];
		newArr[index2] = temp;
	}
	
	function sendToManual(){
		window.location = "manual.jsp";
	}

</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style1.css">
</head>
<body>
	<div id="overlay" onclick="off()">
		<div id="text">START GAME</div>
	</div>

	<div id="wrapper">

		<div id="play-box">
			<div id="grid">
				<div id="tiles-container"></div>
				<div id="random-button" onclick="generateRandom()">RANDOM</div>
				<div id="manual-button" onclick="sendToManual()">MANUAL</div>
				<div id="submit-button" onclick="sendDataToAPI()">SUBMIT</div>
				<div id="tiles-container"></div>
				<div id="cells-container">
					<div id="row-1" class="row">
						<div id="position-1-1" goal="1" class="grid-cell"></div>
						<div id="position-1-2" goal="2" class="grid-cell"></div>
						<div id="position-1-3" goal="3" class="grid-cell"></div>
						<div id="position-1-4" goal="4" class="grid-cell"></div>
					</div>
					<div id="row-2" class="row">
						<div id="position-2-1" goal="5" class="grid-cell"></div>
						<div id="position-2-2" goal="6" class="grid-cell"></div>
						<div id="position-2-3" goal="7" class="grid-cell"></div>
						<div id="position-2-4" goal="8" class="grid-cell"></div>
					</div>
					<div id="row-3" class="row">
						<div id="position-3-1" goal="9" class="grid-cell"></div>
						<div id="position-3-2" goal="10" class="grid-cell"></div>
						<div id="position-3-3" goal="11" class="grid-cell"></div>
						<div id="position-3-4" goal="12" class="grid-cell"></div>
					</div>
					<div id="row-4" class="row">
						<div id="position-4-1" goal="13" class="grid-cell"></div>
						<div id="position-4-2" goal="14" class="grid-cell"></div>
						<div id="position-4-3" goal="15" class="grid-cell"></div>
						<div id="position-4-4" goal="0" class="grid-cell"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>