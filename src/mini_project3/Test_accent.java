package mini_project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test_accent {

	public static void main(String[] args) throws FileNotFoundException {
//		char x = 'ñ';
//		System.out.println(x);
//		if(x == 'ñ') {
//			x = 'n';
//		}
//		System.out.println(x);
//	
//	String tlk = "what a ñ pig";
//	
//	String nr = tlk.toLowerCase().replaceAll("[ñ]", "n");
//	System.out.println(nr);
		File file1 = new File("FirstItalian.txt");
		File file2 = new File("SecondItalian.txt");
		if (!file1.exists()) {
			System.out.println("File1 does not exist");
		}
		if (!file2.exists()) {
			System.out.println("File2 does not exist");
		}
		StringBuilder inOne = new StringBuilder();
	    String bigString = "";
		Scanner input1 = new Scanner(file1);
		Scanner input2 = new Scanner(file2);
		while (input1.hasNext()) {
			
			String textLine = input1.nextLine();
			String delimiter = "[\\[\\]\"-.,;:!?(){ \\s ]+";
			String[] token = textLine.trim().split(delimiter);		
			for(int i =0; i< token.length; i++) {
				inOne.append(token[i]);
			  }
			
		}
		input1.close();
		while (input2.hasNext()) {
	
			String textLine = input2.nextLine();
			String delimiter = "[\\[\\]\"-.,;:!?(){ \\s ]+";
			String[] token = textLine.trim().split(delimiter);		
			for(int i =0; i< token.length; i++) {
				inOne.append(token[i]);
	  }
	
	}
		input2.close();
		bigString = inOne.toString().toLowerCase();
		String x1 = bigString.replaceAll("[á]", "a");
		String x2 = x1.replaceAll("[à]", "a");
		String x3 = x2.replaceAll("[é]", "e");
		String x4 = x3.replaceAll("[è]", "e");
		String x5 = x4.replaceAll("[í]", "i");
		String x6 = x5.replaceAll("[ì]", "i");
		String x7 = x6.replaceAll("[ó]", "o");
		String x8 = x7.replaceAll("[ò]", "o");
		String x9 = x8.replaceAll("[ú]", "u");
		String x10 = x9.replaceAll("[ù]", "e");
		System.out.println(x10);
	}
	
	

}
