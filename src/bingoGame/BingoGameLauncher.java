package bingoGame;

import java.util.Scanner;

public class BingoGameLauncher {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("빙고 테마 선택(동물 / 과자): ");
		String theme = sc.nextLine();
		System.out.println("보드판 크기 n(3*3 / 4*4 / 5*5 가능): ");
		int n = sc.nextInt();
		BingoGame game1 = new BingoGame(n, theme);
		game1.setWordsList();
		game1.bot.setBoardAuto();
		game1.bot.printBoard();
		game1.pl.setBoard();
		game1.pl.printBoard();
	}
}