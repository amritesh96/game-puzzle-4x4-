package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SolvePuzzle {

	private final int N = 4;
	static LinkedList<Node> solution = new LinkedList<>();

	class Node {
		int i;
		int j;
		int[][] board;
		int[][] target;
		String path;

		Node(int i, int j, int[][] board, int[][] target, String path) {
			this.i = i;
			this.j = j;
			this.board = board;
			this.target = target;
			this.path = path;
		}

		public int estimateError() {
			int wrong = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((board[i][j] > 0) && (board[i][j] != target[i][j])) {
						wrong++;
					}
				}
			}
			return wrong;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + Arrays.deepHashCode(board);
			result = prime * result + i;
			result = prime * result + j;
			result = prime * result + Arrays.deepHashCode(target);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (!Arrays.deepEquals(board, other.board))
				return false;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			if (!Arrays.deepEquals(target, other.target))
				return false;
			return true;
		}

		private SolvePuzzle getOuterType() {
			return SolvePuzzle.this;
		}

		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					s.append(String.format("%4d", board[i][j]));
				}
				s.append("\n");
			}
			return s.toString();
		}

	}

	class Pair {
		int i;
		int j;

		Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	private Pair findBlankPosition(int puzzle[][]) {
		int n = puzzle.length;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				if (puzzle[i][j] == 0) {
					Pair p = new Pair(i, j);
					return p;
				}
			}
		}
		return null;
	}

	public static ArrayList<Integer> solvePuzzle(int[][] board) {

		int n = 4;
//		int board[][] = { { 1, 2, 3, 4 }, { 6, 7, 14, 8 }, { 9, 5, 11, 12 }, { 13, 0, 10, 15 } };
//		int board1[][] = { { 15, 13, 14, 12 }, { 11, 10, 9, 8 }, { 7, 6, 5, 4 }, { 3, 2, 1, 0 } };

//		int board3[][] = { { 6, 13, 7, 10 }, { 8, 9, 11, 0 }, { 15, 2, 12, 5 }, { 14, 3, 1, 4 } };

//		int board2[][] = { { 6, 13, 7, 10 }, { 8, 9, 11, 0 }, { 15, 2, 12, 5 }, { 14, 3, 1, 4 } };
//		int board5[][] = { { 5, 8, 7, 11 }, { 1, 6, 12, 2 }, { 9, 0, 13, 10 }, { 14, 3, 4, 15 } };
//		int board6[][] = { { 2, 4, 0, 12 }, { 6, 7, 8, 9 }, { 1, 3, 14, 11 }, { 5, 13, 10, 15 } };

		int target[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } };
		SolvePuzzle puzz = new SolvePuzzle();
		long start = System.currentTimeMillis();
		Pair p = puzz.findBlankPosition(board);
		puzz.solvePuzzle(board, target, p.i, p.j, 3);
		long end = System.currentTimeMillis();
		int i = 1;
		int j = 0;
		ArrayList<Integer> res = new ArrayList<>();

		for (Node node : solution) {
			System.out.println("----------" + i++ + "---------------------");
			System.out.println(node.toString());
			if (node.path.length() > 0 && node.path.charAt(node.path.length() - 1) == 'U') {
				res.add(node.board[node.i + 1][node.j]);
			}
			if (node.path.length() > 0 && node.path.charAt(node.path.length() - 1) == 'D') {
				res.add(node.board[node.i - 1][node.j]);
			}
			if (node.path.length() > 0 && node.path.charAt(node.path.length() - 1) == 'L') {
				res.add(node.board[node.i][node.j + 1]);
			}
			if (node.path.length() > 0 && node.path.charAt(node.path.length() - 1) == 'R') {
				res.add(node.board[node.i][node.j - 1]);
			}
//				if (node.path.length() > 0 && node.path.charAt(node.path.length() - 1) == 'U') {
//					res.add("\"up\"");
//				}
//				if (node.path.length() > 0 && node.path.charAt(node.path.length() - 1) == 'D') {
//					res.add("\"down\"");
//				}
//				if (node.path.length() > 0 && node.path.charAt(node.path.length() - 1) == 'L') {
//					res.add("\"left\"");
//				}
//				if (node.path.length() > 0 && node.path.charAt(node.path.length() - 1) == 'R') {
//					res.add("\"right\"");
//				}

			// int board3[][] = { { 6, 13, 7, 10 }, { 8, 9, 11, 0 }, { 15, 2, 12, 5 }, { 14,
			// 3, 1, 4 } };

			// [11, 7, 10, 11, 5, 12, 1, 3, 14, 15, 8, 6, 13, 9, 2, 8, 6, 13, 9, 2, 13, 6,
			// 8, 13, 6, 8, 13, 14, 15, 13, 14, 1, 3, 15, 1, 14, 13, 1, 14, 3, 7, 10, 11, 5,
			// 10, 7, 3, 13, 1, 14, 13, 3, 15, 13, 14, 1, 3, 15, 13, 14, 15, 13, 14, 15, 13,
			// 3, 1, 13, 3, 6, 8, 9, 2, 8, 6, 1, 9, 6, 8, 2, 6, 8, 1, 14, 7, 10, 5, 11, 10,
			// 7, 15, 3, 14, 1, 8, 6, 2, 8, 6, 2, 8, 10, 11, 5, 12, 4, 3, 15, 4, 12, 5, 11,
			// 10, 8, 2, 6, 1, 4, 7, 10, 8, 2, 6, 1, 10, 7, 4, 10, 2, 6, 1, 2, 6, 8, 7, 4,
			// 12, 5, 4, 7, 11, 4, 5, 12, 7, 11, 8, 6, 11, 7, 10, 11, 6, 8, 7, 10, 11, 6, 2,
			// 1, 8, 2, 10, 7, 2, 8, 1, 10, 8, 2, 7, 8, 6, 11, 12, 5, 8, 7, 4, 8, 5, 12, 7,
			// 5, 8, 4, 5, 7, 11, 6, 2, 1, 10, 2, 6, 9, 2, 10, 1, 6, 10, 2, 9, 10, 7, 5, 6,
			// 7, 2, 1, 7, 2, 5, 6, 2, 7, 1, 5, 7, 2, 6, 8, 4, 6, 8, 7, 2, 8, 6, 4, 7, 6, 8,
			// 2, 6, 7, 12, 3, 15, 11, 3, 12, 7, 3, 11, 15, 12, 7, 4, 8, 3, 4, 7, 12, 15,
			// 11, 4, 7, 8, 3, 7, 4, 11, 15, 12, 8, 4, 7, 3, 4, 8, 12]

		}

		System.out.println("total steps " + "-->" + i);
		j=0;i=0;
		System.out.println("time taken " + (end - start) + "  miliseconds");
		System.out.println("-----------------------------------------------------");
		return res;

	}

	private boolean isSafe(int i, int j, int n) {
		if (i >= 0 && i <= n && j >= 0 && j <= n)
			return true;
		return false;
	}

	private boolean solvePuzzle(int[][] board, int[][] target, int i, int j, int n) {
		HashMap<Node, Integer> depth = new HashMap<>();
		HashMap<Node, Node> predecessor = new HashMap<>();
		HashMap<Node, Integer> score = new HashMap<Node, Integer>();
		Comparator<Node> comparator = new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				return score.get(node1) - score.get(node2);
			}
		};
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(comparator);
		Node node = new Node(i, j, board, target, "");
		score.put(node, node.estimateError());
		depth.put(node, 0);
		predecessor.put(node, null);
		priorityQueue.add(node);
		int cnt = 0;
		int height = 10000000;
		String path;
		while (!priorityQueue.isEmpty()) {
			Node parent = priorityQueue.poll();
			i = parent.i;
			j = parent.j;
			board = parent.board;
			path = parent.path;
			if (isSolved(board, target)) {
				System.out.printf("Solution considered boards\n");
				Node backtrace = parent;
				while (backtrace != null) {
					solution.addFirst(backtrace);
					backtrace = predecessor.get(backtrace);
				}
				System.out.println(path);
				break;
			}
			if (isSafe(i, j + 1, n)) {
				int[][] tempBoard = new int[N][N];
				for (int index = 0; index < tempBoard.length; index++) {
					tempBoard[index] = Arrays.copyOf(board[index], tempBoard[index].length);
				}
				swap(tempBoard, i, j, i, j + 1);
				Node newNode = new Node(i, j + 1, tempBoard, target, path + "R");
				if (!predecessor.containsKey(newNode)) {
					predecessor.put(newNode, parent);
					depth.put(newNode, depth.get(parent) + 1);
					int error = newNode.estimateError();
					score.put(newNode, error);
					if (depth.get(newNode) < height)
						priorityQueue.add(newNode);
				}
			}
			if (isSafe(i, j - 1, n)) {
				int[][] tempBoard = new int[N][N];
				for (int index = 0; index < tempBoard.length; index++) {
					tempBoard[index] = Arrays.copyOf(board[index], tempBoard[index].length);
				}
				swap(tempBoard, i, j, i, j - 1);
				Node newNode = new Node(i, j - 1, tempBoard, target, path + "L");
				if (!predecessor.containsKey(newNode)) {
					predecessor.put(newNode, parent);
					depth.put(newNode, depth.get(parent) + 1);
					int error = newNode.estimateError();
					score.put(newNode, error);
					if (depth.get(newNode) < height)
						priorityQueue.add(newNode);
				}
			}
			if (isSafe(i + 1, j, n)) {
				int[][] tempBoard = new int[N][N];
				for (int index = 0; index < tempBoard.length; index++) {
					tempBoard[index] = Arrays.copyOf(board[index], tempBoard[index].length);
				}
				swap(tempBoard, i, j, i + 1, j);
				Node newNode = new Node(i + 1, j, tempBoard, target, path + "D");
				if (!predecessor.containsKey(newNode)) {
					predecessor.put(newNode, parent);
					depth.put(newNode, depth.get(parent) + 1);
					int error = newNode.estimateError();
					score.put(newNode, error);
					if (depth.get(newNode) < height)
						priorityQueue.add(newNode);
				}
			}
			if (isSafe(i - 1, j, n)) {
				int[][] tempBoard = new int[N][N];

				for (int index = 0; index < tempBoard.length; index++) {
					tempBoard[index] = Arrays.copyOf(board[index], tempBoard[index].length);
				}
				swap(tempBoard, i, j, i - 1, j);
				Node newNode = new Node(i - 1, j, tempBoard, target, path + "U");
				if (!predecessor.containsKey(newNode)) {
					predecessor.put(newNode, parent);
					depth.put(newNode, depth.get(parent) + 1);
					int error = newNode.estimateError();
					score.put(newNode, error);
					if (depth.get(newNode) < height)
						priorityQueue.add(newNode);
				}
			}
		}

		return false;

	}

	public boolean isSolved(int[][] puzzle, int[][] correctPuzzle) {
		for (int y = 0; y < puzzle.length; ++y) {
			for (int x = 0; x < puzzle[y].length; ++x) {
				if (puzzle[y][x] != correctPuzzle[y][x]) {
					return false;
				}
			}
		}

		return true;
	}

	private void swap(int[][] board, int i, int j, int k, int l) {
		int x = board[i][j];
		board[i][j] = board[k][l];
		board[k][l] = x;
	}

}
