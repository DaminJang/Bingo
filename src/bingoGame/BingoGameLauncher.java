package bingoGame;

import java.util.Scanner;

public class BingoGameLauncher {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("���� �׸� ����(���� / ����): ");
		String theme = sc.nextLine();
		System.out.println("������ ũ�� n(3*3 / 4*4 / 5*5 ����): ");
		int n = sc.nextInt();
		BingoGame game1 = new BingoGame(n, theme);
		game1.setWordsList();
		game1.bot.setBoardAuto();
		game1.bot.printBoard();
		game1.pl.setBoard();
		game1.pl.printBoard();
	}
}