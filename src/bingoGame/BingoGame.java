package bingoGame;
import java.util.*;
import java.io.*;
import java.lang.*;

abstract class Piece{
	ArrayList<String> board; //카드를 채울 보드 
	ArrayList<String>[] bingo; //빙고 확인용 리스트
	abstract void chooseCard();
	abstract void insertBingo();
	void setBoardAuto(){
		System.out.println(this.toString()+": setBoardAuto 작동 시작");
		int n = BingoGame.n;
		Collections.shuffle(BingoGame.wordsList);
		for(int i = 0; i<n*n; i++) {
			this.board.add(BingoGame.wordsList.get(i));
		}
		System.out.println(this.toString()+": setBoardAuto 작동 완료");
	}
	void printBoard() {
		System.out.println(this.toString()+": printBoard 작동 시작");
		Iterator<String> it = this.board.iterator();
		while(it.hasNext()) {
			for(int i=0; i<BingoGame.n; i++) {
				System.out.print(it.next()+"\t");
			}
			System.out.println("\n");
		}
		System.out.println(this.toString()+": printBoard 작동 완료");
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
		System.out.println(this.toString()+": Player 만들어짐");
		this.board = new ArrayList<>(n*n);
		this.bingo = new ArrayList[n*2+2];
	}
	void setBoard() {
		int n = BingoGame.n;
		System.out.println(this.toString()+": setBoard 작동 시작");
		System.out.println("player board 채우기 시작  "+this.board.size());
		for(int i=0; i<Math.pow(n, 2); i++) {
			this.board.add("EMPTY");
		}
		System.out.println("player board 채우기 완료 "+this.board.size());
		Scanner sc = new Scanner(System.in);
		String position; int row; int col; int index;
		//position: 칸 위치, row: 행, col: 열, i: index 값
		String word;
		for(int i=0; i<Math.pow(n, 2); i++) {
			System.out.println("칸 선택 후 낱말을 입력해주세요.");
			position = sc.nextLine();
			row = position.charAt(0)-48; col = position.charAt(1)-48;
			index = row*n+col;
			if(this.board.get(index)!="EMPTY"){
				i--;
				System.out.println("이미 채운 칸 입니다.");
				continue;
			}
			word = sc.nextLine();
			this.board.set(index, word);
			if(!BingoGame.wordsList.contains(word)) {
				BingoGame.addNewWord(word);
			}
		}
		sc.close();
		System.out.println(this.toString()+": setBoard 작동 완료");
	}
	void insertBingo(String choose) {
		
		int n = BingoGame.n;
		while(true) {
			this.bingo[row].add(position);
			this.bingo[col+n].add(position);piofdgfdrawteaweii;i;aawi;hea;;;;;;;;;;;;;;;으아악
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
				System.out.println("이미 선택한 칸 입니다.");
				continue;
			}
			this.insertBingo();
		}
		sc.close();
	}
}

class Bot extends Piece{
	Bot (int n){
		System.out.println(this.toString()+": Bot 만들어짐");
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
			 return Arrays.asList(count).indexOf(max); //max인 행이 여러개면 그 중 index가 가장 작은 값이 반환됨(랜덤일 필요 없을 듯)
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
		return "본 게임은 컴퓨터와 사용자 1:1로 진행됩니다";
	}
	void setWordsList() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\javaPractice\\BingoGame\\data\\"+theme+".txt"), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordsList.add(line); // 한 라인에 하나의 단어
            }
            System.out.println("파일을 성공적으로 읽어왔습니다.");
        } catch (FileNotFoundException e) { 
            System.out.println(theme+" 파일이 없습니다");
        } catch (IOException e) {
            System.out.println("파일을 읽는 중 오류가 발생했습니다.");
        }
	}
	static void addNewWord(String newWord) {
		String filePath = "C:\\javaPractice\\BingoGame\\data\\" + theme + ".txt";
	        // 파일에 단어 추가
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"))){        	
            writer.write(newWord);
            writer.newLine();
    	    System.out.println("파일에 성공적으로 추가하였습니다.");
        }catch (IOException e) {
	        System.out.println("파일에 쓰는 중 오류가 발생했습니다.");
	    }
	}
}
