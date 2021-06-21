import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
//		GameManager gm = new GameManager();
//		gm.Manager();
//		
		 char in = 'R'; 
		 File file = new File("Lory&Moakar.txt");
		 Scanner temp = new Scanner(file);
		 String content = "";
		 while(temp.hasNext()) {
			 String line = temp.nextLine();
			 if(line.substring(0,8).equals("Affinity")) {
				 line += in;
			 }
			 content += line + "\n";
		 } 
		 System.out.println(content);
	}

}
