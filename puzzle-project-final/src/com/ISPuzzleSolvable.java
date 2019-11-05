package com;

public class ISPuzzleSolvable {

	static int N = 4;
	//static int arr[] = {6, 13, 7, 10, 8, 9, 11, 0, 15, 2, 12, 5, 14, 3, 1, 4};
	//static int arr[] = { 15, 13, 14, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
	//static int board2[] = { 6, 1, 3, 9, 5, 2, 11, 7, 0, 10, 4, 8, 13, 14, 15, 12 };
	static int arr[] = { 3, 9, 1, 15, 14, 11, 4, 6, 13, 0, 10, 12, 2, 7, 8, 5 };

	static int getInvCount() {
		int inv_count = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if ((arr[j] != 0 && arr[i] != 0) && arr[i] > arr[j])
					inv_count++;
			}
		}
		return inv_count;
	}

	static int findXPosition() {
		// start from bottom-right corner of matrix
		// for (var i = N - 1; i >= 0; i--)
		// for (var j = N - 1; j >= 0; j--)
		// if (puzzle[i][j] == 0)
		// return N - i;
		// return -1;

		for (int j = 0; j < arr.length; j++) {
			if (arr[j] == 0) {
				return N - (j / 4);
			}
		}
		return -1;
	}

	static boolean isSolvable() {
		int invCount = getInvCount();
		System.out.println(invCount);

		if ((N & 1) > 0) {
			System.out.println("1");
			return (invCount & 1) == 0;
		} else {
			int pos = findXPosition();
			System.out.println("Pos "+pos);
			if ((pos & 1) == 0) {
				if ((invCount & 1) == 0) {
					System.out.println("2");

					return false;
				} else {
					System.out.println("5");

					return true;
				}
			} else {
				if ((invCount & 1) == 0) {
					System.out.println("3");
					return true;
				} else {
					System.out.println("4");

					return false;
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(isSolvable());
	}
}
