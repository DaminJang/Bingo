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
			//Scanner fScanner = new Scanner(new InputStreamReader(new FileInputStream("C:\\javaPractice\\BingoGame\\data\\����.txt"),  "UTF-8"));
//			while(fScanner.hasNext()) 
//				words.add(fScanner.nextLine()); // �� ���ο� �ϳ��� �ܾ�
//			fScanner.close();
		}catch(FileNotFoundException e) { 
			System.out.println("������Ʈ ���� ���� words.txt ������ �����ϴ�");
		}catch(IOException e) {
			System.out.println("������ �д� �� ���� �߻�");
		}
//		catch (UnsupportedEncodingException e) {
//            System.out.println("�������� �ʴ� ���ڵ��Դϴ�.");
//        }
		System.out.println("������Ʈ ���� ���� words.txt ������ �о����ϴ�...");
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
//		ArrayList<String> wordsList = readTxtFile("����");
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
