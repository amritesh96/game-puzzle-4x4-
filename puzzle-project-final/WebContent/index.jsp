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
<script src="${pageContext.request.contextPath}/resources/js/tiles.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>

	<div id="wrapper">
		<header>
			<div id="info-box">
				<div id="score-point">
					<span class="text">MOVES</span> <span class="num">0</span>
				</div>
				<div id="timepoint">
					<span class="text">TIME</span> <span class="num">00:00</span>
				</div>
				<div id="start-button">START</div>
				<a href="first.jsp">
					<div id="reset-button">BACK</div>
				</a>
			</div>
		</header>

		<div id="play-box">
			<div id="grid">
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