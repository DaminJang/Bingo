package bingoGame;
import java.util.*;
import java.io.*;
import java.lang.*;

abstract class Piece{
	ArrayList<String> board; //ī�带 ä�� ���� 
	ArrayList<String>[] bingo; //���� Ȯ�ο� ����Ʈ
	abstract void chooseCard();
	abstract void insertBingo();
	void setBoardAuto(){
		System.out.println(this.toString()+": setBoardAuto �۵� ����");
		int n = BingoGame.n;
		Collections.shuffle(BingoGame.wordsList);
		for(int i = 0; i<n*n; i++) {
			this.board.add(BingoGame.wordsList.get(i));
		}
		System.out.println(this.toString()+": setBoardAuto �۵� �Ϸ�");
	}
	void printBoard() {
		System.out.println(this.toString()+": printBoard �۵� ����");
		Iterator<String> it = this.board.iterator();
		while(it.hasNext()) {
			for(int i=0; i<BingoGame.n; i++) {
				System.out.print(it.next()+"\t");
			}
			System.out.println("\n");
		}
		System.out.println(this.toString()+": printBoard �۵� �Ϸ�");
	}
	int countBingo(ArrayList<String>[] bingo) {
		int count = 0;
		for(int i=0; i<bingo.length; i++) {
			if(bingo[i].size()==BingoGame.n) {
				count++;
			}
		}
		return count;
	}
}

class Player extends Piece{
	Player(int n){
		System.out.println(this.toString()+": Player �������");
		this.board = new ArrayList<>(n*n);
		this.bingo = new ArrayList[n*2+2];
	}
	void setBoard() {
		int n = BingoGame.n;
		System.out.println(this.toString()+": setBoard �۵� ����");
		System.out.println("player board ä��� ����  "+this.board.size());
		for(int i=0; i<Math.pow(n, 2); i++) {
			this.board.add("EMPTY");
		}
		System.out.println("player board ä��� �Ϸ� "+this.board.size());
		Scanner sc = new Scanner(System.in);
		String position; int row; int col; int index;
		//position: ĭ ��ġ, row: ��, col: ��, i: index ��
		String word;
		for(int i=0; i<Math.pow(n, 2); i++) {
			System.out.println("ĭ ���� �� ������ �Է����ּ���.");
			position = sc.nextLine();
			row = position.charAt(0)-48; col = position.charAt(1)-48;
			index = row*n+col;
			if(this.board.get(index)!="EMPTY"){
				i--;
				System.out.println("�̹� ä�� ĭ �Դϴ�.");
				continue;
			}
			word = sc.nextLine();
			this.board.set(index, word);
			if(!BingoGame.wordsList.contains(word)) {
				BingoGame.addNewWord(word);
			}
		}
		sc.close();
		System.out.println(this.toString()+": setBoard �۵� �Ϸ�");
	}
	void insertBingo(String choose) {
		
		int n = BingoGame.n;
		while(true) {
			this.bingo[row].add(position);
			this.bingo[col+n].add(position);piofdgfdrawteaweii;i;aawi;hea;;;;;;;;;;;;;;;���ƾ�
			if(row == col)
				this.bingo[n*2].add(position);
			if(row+col == n-1)
				this.bingo[n*2+1].add(position);
			break;
		}
		
	}
	@Override
	void chooseCard() {
		int n = BingoGame.n;
		Scanner sc = new Scanner(System.in);
		String choose = sc.nextLine();
		int i = this.board.indexOf(choose);
		int row = i/n; int col = i%n;
		String position = row+""+col; 
		for(ArrayList<String> list : this.bingo) {
			if(list.contains(position)) {
				System.out.println("�̹� ������ ĭ �Դϴ�.");
				continue;
			}
			this.insertBingo();
		}
		sc.close();
	}
}

class Bot extends Piece{
	Bot (int n){
		System.out.println(this.toString()+": Bot �������");
		this.board = new ArrayList<>(n*n);
		this.bingo = new ArrayList[n*2+2];
	}
	void chooseCard() {
		this.bingoPossibility();
	}
	int bingoPossibility() {
		int n = BingoGame.n;
		int[] count = new int[n];
		for(int i=0; i<this.bingo.length; i++) {
			count[i] = this.bingo[i].size();
		}
		int max = Arrays.stream(count).max().getAsInt(); 
		if(max < 1) {
			return(int)(Math.random()*2)+(n*2);
		}
		else {
			 return Arrays.asList(count).indexOf(max); //max�� ���� �������� �� �� index�� ���� ���� ���� ��ȯ��(������ �ʿ� ���� ��)
		}
	}
	@Override
	void insertBingo() {
		// TODO Auto-generated method stub
		
	}
}

public class BingoGame{
	static int n;
	static String theme;
	static ArrayList<String> wordsList = new ArrayList<>();
	Player pl = new Player(n);
	Bot bot = new Bot(n);
	BingoGame(int n, String theme){
		this.n= n;
		this.theme = theme;
	}
	@Override
	public String toString() {
		return "�� ������ ��ǻ�Ϳ� ����� 1:1�� ����˴ϴ�";
	}
	void setWordsList() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\javaPractice\\BingoGame\\data\\"+theme+".txt"), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordsList.add(line); // �� ���ο� �ϳ��� �ܾ�
            }
            System.out.println("������ ���������� �о�Խ��ϴ�.");
        } catch (FileNotFoundException e) { 
            System.out.println(theme+" ������ �����ϴ�");
        } catch (IOException e) {
            System.out.println("������ �д� �� ������ �߻��߽��ϴ�.");
        }
	}
	static void addNewWord(String newWord) {
		String filePath = "C:\\javaPractice\\BingoGame\\data\\" + theme + ".txt";
	        // ���Ͽ� �ܾ� �߰�
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"))){        	
            writer.write(newWord);
            writer.newLine();
    	    System.out.println("���Ͽ� ���������� �߰��Ͽ����ϴ�.");
        }catch (IOException e) {
	        System.out.println("���Ͽ� ���� �� ������ �߻��߽��ϴ�.");
	    }
	}
}
