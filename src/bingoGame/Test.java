package bingoGame;
import java.util.*;
import java.io.*;

public class Test {
	
	static ArrayList readTxtFile(String theme) {
		ArrayList<String> words = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\javaPractice\\BingoGame\\data\\"+theme+".txt"), "UTF-8"));
			String line;
			while((line = reader.readLine()) != null) {
				words.add(line);
			}
			reader.close();
			//Scanner fScanner = new Scanner(new InputStreamReader(new FileInputStream("C:\\javaPractice\\BingoGame\\data\\과자.txt"),  "UTF-8"));
//			while(fScanner.hasNext()) 
//				words.add(fScanner.nextLine()); // 한 라인에 하나의 단어
//			fScanner.close();
		}catch(FileNotFoundException e) { 
			System.out.println("프로젝트 폴더 밑의 words.txt 파일이 없습니다");
		}catch(IOException e) {
			System.out.println("파일을 읽는 중 오류 발생");
		}
//		catch (UnsupportedEncodingException e) {
//            System.out.println("지원하지 않는 인코딩입니다.");
//        }
		System.out.println("프로젝트 폴더 밑의 words.txt 파일을 읽었습니다...");
		return words;
	}
	void vectorCapacityNSize() {
		Vector<String> arr = new Vector<>(7);
		System.out.println(arr.size());
		System.out.println(arr.capacity());
		for(int i=0; i<7; i++) {
			arr.add(i, "Empty");
		}
		arr.add(2,"C is Cat");
		System.out.println(arr.get(2));
		System.out.println(arr.size());
		System.out.println(arr.indexOf("C is Cat"));
		System.out.println(arr.capacity());
		Iterator<String> it = arr.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	public static void main(String[] args) {
//		System.out.println("a");
//		ArrayList<String> wordsList = readTxtFile("과자");
//		ArrayList<String> board = new ArrayList<>(16);
//		
//		for(int i=0; i<5; i++) {
//			board.add("ab");
//		}
//		Iterator<String> it = board.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
		
		int[] arr = {2, 5, 2, 9, 7, 3};
		
		int row = 5; int col = 3;
		String st = row+""+col;
		System.out.println(st);
		
	}

}
