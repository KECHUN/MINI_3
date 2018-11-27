package mini_project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class Bigram_French_Spanish_Italian {

	public static void main(String[] args) throws FileNotFoundException {
		File file1 = new File("spanish1.txt");
		File file2 = new File("spanish2.txt");
		File file3 = new File("fr-le-petit-prince.txt");
		File file4 = new File("fr-vingt-mille-lieues-sous-les-mers.txt");
		File file5 = new File("FirstItalian.txt");
		File file6 = new File("SecondItalian.txt");
		if (!file1.exists()) {
			System.out.println("File1 does not exist");
		}
		if (!file2.exists()) {
			System.out.println("File2 does not exist");
		}
		if (!file3.exists()) {
			System.out.println("File3 does not exist");
		}
		if (!file4.exists()) {
			System.out.println("File4 does not exist");
		}
		if (!file5.exists()) {
			System.out.println("File5 does not exist");
		}
		if (!file6.exists()) {
			System.out.println("File6 does not exist");
		}
		
		
		else {
			
		Map<String, Double> biMapSpanish = BigramTrainingSpanish(file1,file2);
		Map<String, Double> biMapItalian = BigramTrainingItalian(file5,file6);
		Map<String, Double> biMapFrench = BigramTraining(file3,file4);
		Map<String, Double> uniMapSpanish = UnigramTrainingSpanish(file1,file2);
		Map<String, Double> uniMapFrench = UnigramTraining(file3,file4);
		Map<String, Double> uniMapItalian = UnigramTrainingItalian(file5,file6);
		Scanner input_test = new Scanner(System.in);
		System.out.print("Enter a sentence: ");
	    String sentence = input_test.nextLine();
	    System.out.println(sentence);
	    UnigramClassify(sentence,uniMapFrench,uniMapSpanish,uniMapItalian);
	    BigramClassify(sentence,biMapFrench,biMapSpanish,biMapItalian);
	    
	    
	   }
	  }

	private static Map<String, Double> UnigramTraining(File file1, File file2) throws FileNotFoundException {
	      ArrayList <String> pairs = new ArrayList<>();
			
			for(int i = 0; i < 26; i++) {
				char x = (char) ('a' + i);
				String str = new String(new char[]{x});
			    pairs.add(str);
				 
			}
			Map<String, Double> pair_map = new TreeMap<>();
			final double delta = 0.5;
			for(int i = 0; i < pairs.size(); i++) {
				String pair = pairs.get(i);
				pair_map.put(pair, delta);
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
			bigString = inOne.toString();
			char [] charArray = bigString.toLowerCase().toCharArray();
			for(int i = 0; i < charArray.length ; i++) {
				char x = charArray[i];
				String str = new String(new char[]{x});
				if(pair_map.containsKey(str)) {
					double count = pair_map.get(str);
					count = count + 1.0;
					pair_map.put(str, count);
				}
			}
			double totalBi = 0;
			for(int i = 0; i < pairs.size(); i++) {
				String pair = pairs.get(i);
				totalBi = totalBi + pair_map.get(pair);

			}	
			
			for(int i = 0; i < pairs.size(); i++) {
				String pair = pairs.get(i);
				double count = pair_map.get(pair);
				double prob = count/totalBi;
				pair_map.put(pair,prob);
			}
			
//			for(int i = 0; i < pairs.size(); i++) {
//				String pair = pairs.get(i);
//				double prob = pair_map.get(pair);
//				//System.out.println(pair + " " + prob);
//			}
			
			
			return pair_map;
		 }
	private static Map<String, Double> BigramTraining(File file1, File file2) throws FileNotFoundException {
	      ArrayList <String> pairs = new ArrayList<>();
			
			for(int i = 0; i < 26; i++) {
				char x = (char) ('a' + i);
				 for(int j = 0; j < 26; j++) {
					 char y = (char)('a' +j);
					 String str = new String(new char[]{x,y});
					 pairs.add(str);
				 }
			}
			Map<String, Double> pair_map = new TreeMap<>();
			final double delta = 0.5;
			for(int i = 0; i < pairs.size(); i++) {
				String pair = pairs.get(i);
				pair_map.put(pair, delta);
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
			bigString = inOne.toString();
			char [] charArray = bigString.toLowerCase().toCharArray();
			for(int i = 0; i < charArray.length - 1; i++) {
				char x = charArray[i];
				char y = charArray[i+1];
				String str = new String(new char[]{x,y});
				if(pair_map.containsKey(str)) {
					double count = pair_map.get(str);
					count = count + 1.0;
					pair_map.put(str, count);
				}
			}
			double totalBi = 0;
			for(int i = 0; i < pairs.size(); i++) {
				String pair = pairs.get(i);
				totalBi = totalBi + pair_map.get(pair);

			}	
			
			for(int i = 0; i < pairs.size(); i++) {
				String pair = pairs.get(i);
				double count = pair_map.get(pair);
				double prob = count/totalBi;
				pair_map.put(pair,prob);
			}
			
//			for(int i = 0; i < pairs.size(); i++) {
//				String pair = pairs.get(i);
//				double prob = pair_map.get(pair);
//				//System.out.println(pair + " " + prob);
//			}
			
			
			return pair_map;
		   }

	private static Map<String, Double> BigramTrainingItalian(File file5, File file6) throws FileNotFoundException {
		StringBuilder inOne = new StringBuilder();
	    String bigString = "";
		Scanner input1 = new Scanner(file5);
		Scanner input2 = new Scanner(file6);
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
		String x10 = x9.replaceAll("[ù]", "u");
		ArrayList <String> pairs = new ArrayList<>();
		for(int i = 0; i < 26; i++) {
			char x = (char) ('a' + i);
			 for(int j = 0; j < 26; j++) {
				 char y = (char)('a' +j);
				 String str = new String(new char[]{x,y});
				 pairs.add(str);
			 }
		}
		Map<String, Double> pair_map = new TreeMap<>();
		final double delta = 0.5;
		for(int i = 0; i < pairs.size(); i++) {
			String pair = pairs.get(i);
			pair_map.put(pair, delta);
		}
		char [] charArray = x10.toCharArray();
		for(int i = 0; i < charArray.length - 1; i++) {
			char x = charArray[i];
			char y = charArray[i+1];
			String str = new String(new char[]{x,y});
			if(pair_map.containsKey(str)) {
				double count = pair_map.get(str);
				count = count + 1.0;
				pair_map.put(str, count);
			}
		}
		double totalBi = 0;
		for(int i = 0; i < pairs.size(); i++) {
			String pair = pairs.get(i);
			totalBi = totalBi + pair_map.get(pair);

		}	
		
		for(int i = 0; i < pairs.size(); i++) {
			String pair = pairs.get(i);
			double count = pair_map.get(pair);
			double prob = count/totalBi;
			pair_map.put(pair,prob);
		}
		
//		for(int i = 0; i < pairs.size(); i++) {
//			String pair = pairs.get(i);
//			double prob = pair_map.get(pair);
//			//System.out.println(pair + " " + prob);
//		}
		
		
		return pair_map;
		
	}

	private static void BigramClassify(String sentence, Map<String, Double> MapFrench, Map<String, Double> MapSpanish, Map<String, Double> MapItalian ) {
		
		String shortString = "";
		String delimiter = "[\\[\\]\"-.,;:!?(){ \\s ]+";
		String[] token = sentence.trim().split(delimiter);	
		StringBuilder test_s = new StringBuilder();
		for(int i =0; i< token.length; i++) {
			test_s.append(token[i]);
		  }
		shortString =  test_s.toString();
		char [] charArray1 = shortString.toLowerCase().toCharArray();
		double total_probSpan = 0.0;
		double total_probFren = 0.0;
		double total_probItalian = 0.0;
		System.out.println("BIGRAM MODEL:");
		for(int i = 0; i < charArray1.length - 1; i++) {
			char x = charArray1[i];
			char y = charArray1[i+1];
			String str = new String(new char[]{x,y});
			
			if(MapFrench.containsKey(str)) {
				System.out.println("BIGRAM: " + str);
				double probFren = MapFrench.get(str);
				double probSpanish = MapSpanish.get(str);
				double probItalian = MapItalian.get(str);
				total_probFren = total_probFren + Math.log10(probFren);
				total_probSpan = total_probSpan + Math.log10(probSpanish);
				total_probItalian = total_probItalian + Math.log10(probItalian);
				System.out.println("French: P(" + y + "|" + x + ") = " + probFren + " ==> log prob of sentence so far:" + total_probFren);
				System.out.println("Spanish: P(" + y + "|" + x + ") = " + probSpanish + " ==> log prob of sentence so far:" + total_probSpan);
				System.out.println("Italian: P(" + y + "|" + x + ") = " + probItalian + " ==> log prob of sentence so far:" + total_probItalian);
			}
			
		}
		
		if(total_probFren > total_probSpan && total_probFren> total_probItalian) {
			System.out.println("According to the bigram model, the sentence is in French");
		}
		if(total_probSpan > total_probFren && total_probSpan > total_probItalian) {
			System.out.println("According to the bigram model, the sentence is in Spanish");
		}
		if(total_probItalian > total_probFren && total_probItalian > total_probSpan) {
			System.out.println("According to the bigram model, the sentence is in Italian");
		}
		
		
		
	}
	
	

	
	private static Map<String, Double> UnigramTrainingItalian(File file5, File file6) throws FileNotFoundException {
		StringBuilder inOne = new StringBuilder();
	    String bigString = "";
		Scanner input1 = new Scanner(file5);
		Scanner input2 = new Scanner(file6);
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
		String x10 = x9.replaceAll("[ù]", "u");
		ArrayList <String> pairs = new ArrayList<>();
		for(int i = 0; i < 26; i++) {
			char x = (char) ('a' + i);
		    String str = new String(new char[]{x});
			pairs.add(str);	 
		}
		Map<String, Double> pair_map = new TreeMap<>();
		final double delta = 0.5;
		for(int i = 0; i < pairs.size(); i++) {
			String pair = pairs.get(i);
			pair_map.put(pair, delta);
		}
		char [] charArray = x10.toCharArray();
		for(int i = 0; i < charArray.length; i++) {
			char x = charArray[i];
			String str = new String(new char[]{x});
			if(pair_map.containsKey(str)) {
				double count = pair_map.get(str);
				count = count + 1.0;
				pair_map.put(str, count);
			}
		}
		double totalBi = 0;
		for(int i = 0; i < pairs.size(); i++) {
			String pair = pairs.get(i);
			totalBi = totalBi + pair_map.get(pair);

		}	
		
		for(int i = 0; i < pairs.size(); i++) {
			String pair = pairs.get(i);
			double count = pair_map.get(pair);
			double prob = count/totalBi;
			pair_map.put(pair,prob);
		}
		
//		for(int i = 0; i < pairs.size(); i++) {
//			String pair = pairs.get(i);
//			double prob = pair_map.get(pair);
//			//System.out.println(pair + " " + prob);
//		}
		
		
		return pair_map;
		
	}

	
	
private static void UnigramClassify(String sentence, Map<String, Double> MapFrench, Map<String, Double> MapSpanish, Map<String, Double> MapItalian ) {
		
		String shortString = "";
		String delimiter = "[\\[\\]\"-.,;:!?(){ \\s ]+";
		String[] token = sentence.trim().split(delimiter);	
		StringBuilder test_s = new StringBuilder();
		for(int i =0; i< token.length; i++) {
			test_s.append(token[i]);
		  }
		shortString =  test_s.toString();
		char [] charArray1 = shortString.toLowerCase().toCharArray();
		double total_probSpan = 0.0;
		double total_probFren = 0.0;
		double total_probItalian = 0.0;
		System.out.println("UNIGRAM MODEL:");
		for(int i = 0; i < charArray1.length ; i++) {
			char x = charArray1[i];
			String str = new String(new char[]{x});
			if(MapFrench.containsKey(str)) {
				System.out.println("UNIGRAM: " + str);
				double probSpan = MapSpanish.get(str);
				double probFren = MapFrench.get(str);
				double probItalian = MapItalian.get(str);
				total_probSpan = total_probSpan + Math.log10(probSpan);
				total_probFren = total_probFren + Math.log10(probFren);
				total_probItalian = total_probItalian + Math.log10(probItalian);
				
				System.out.println("French: P(" + x + ") = " + probFren + " ==> log prob of sentence so far:" + total_probFren);
				System.out.println("Spanish: P("+ x + ") = " + probSpan + " ==> log prob of sentence so far:" + total_probSpan);
				System.out.println("Italian: P("+ x + ") = " + probItalian + " ==> log prob of sentence so far:" + total_probItalian);
			}
			
		}
		
		if(total_probSpan > total_probFren && total_probSpan > total_probItalian) {
			System.out.println("According to the unigram model, the sentence is in Spanish");
		}
		if(total_probFren > total_probSpan && total_probFren > total_probItalian) {
			System.out.println("According to the unigram model, the sentence is in French");
		}
		if(total_probItalian > total_probFren && total_probItalian > total_probSpan) {
			System.out.println("According to the unigram model, the sentence is in Italian");
		}
		
		
		
	}

private static Map<String, Double> UnigramTrainingSpanish(File file5, File file6) throws FileNotFoundException {
	StringBuilder inOne = new StringBuilder();
    String bigString = "";
	Scanner input1 = new Scanner(file5);
	Scanner input2 = new Scanner(file6);
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
	String x2 = x1.replaceAll("[é]", "e");
	String x3 = x2.replaceAll("[ñ]", "n");
	String x4 = x3.replaceAll("[í]", "i");
	String x5 = x4.replaceAll("[ó]", "o");
	String x6 = x5.replaceAll("[ú]", "u");
	String x7 = x6.replaceAll("[ü]", "u");
	ArrayList <String> pairs = new ArrayList<>();
	for(int i = 0; i < 26; i++) {
		char x = (char) ('a' + i);
	    String str = new String(new char[]{x});
		pairs.add(str);	 
	}
	Map<String, Double> pair_map = new TreeMap<>();
	final double delta = 0.5;
	for(int i = 0; i < pairs.size(); i++) {
		String pair = pairs.get(i);
		pair_map.put(pair, delta);
	}
	char [] charArray = x7.toCharArray();
	for(int i = 0; i < charArray.length; i++) {
		char x = charArray[i];
		String str = new String(new char[]{x});
		if(pair_map.containsKey(str)) {
			double count = pair_map.get(str);
			count = count + 1.0;
			pair_map.put(str, count);
		}
	}
	double totalBi = 0;
	for(int i = 0; i < pairs.size(); i++) {
		String pair = pairs.get(i);
		totalBi = totalBi + pair_map.get(pair);

	}	
	
	for(int i = 0; i < pairs.size(); i++) {
		String pair = pairs.get(i);
		double count = pair_map.get(pair);
		double prob = count/totalBi;
		pair_map.put(pair,prob);
	}
	
//	for(int i = 0; i < pairs.size(); i++) {
//		String pair = pairs.get(i);
//		double prob = pair_map.get(pair);
//		//System.out.println(pair + " " + prob);
//	}
	
	
	return pair_map;
	
}

private static Map<String, Double> BigramTrainingSpanish(File file5, File file6) throws FileNotFoundException {
	StringBuilder inOne = new StringBuilder();
    String bigString = "";
	Scanner input1 = new Scanner(file5);
	Scanner input2 = new Scanner(file6);
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
	String x2 = x1.replaceAll("[é]", "e");
	String x3 = x2.replaceAll("[ñ]", "n");
	String x4 = x3.replaceAll("[í]", "i");
	String x5 = x4.replaceAll("[ó]", "o");
	String x6 = x5.replaceAll("[ú]", "u");
	String x7 = x6.replaceAll("[ü]", "u");
	ArrayList <String> pairs = new ArrayList<>();
	for(int i = 0; i < 26; i++) {
		char x = (char) ('a' + i);
		 for(int j = 0; j < 26; j++) {
			 char y = (char)('a' +j);
			 String str = new String(new char[]{x,y});
			 pairs.add(str);
		 }
	}
	Map<String, Double> pair_map = new TreeMap<>();
	final double delta = 0.5;
	for(int i = 0; i < pairs.size(); i++) {
		String pair = pairs.get(i);
		pair_map.put(pair, delta);
	}
	char [] charArray = x7.toCharArray();
	for(int i = 0; i < charArray.length - 1; i++) {
		char x = charArray[i];
		char y = charArray[i+1];
		String str = new String(new char[]{x,y});
		if(pair_map.containsKey(str)) {
			double count = pair_map.get(str);
			count = count + 1.0;
			pair_map.put(str, count);
		}
	}
	double totalBi = 0;
	for(int i = 0; i < pairs.size(); i++) {
		String pair = pairs.get(i);
		totalBi = totalBi + pair_map.get(pair);

	}	
	
	for(int i = 0; i < pairs.size(); i++) {
		String pair = pairs.get(i);
		double count = pair_map.get(pair);
		double prob = count/totalBi;
		pair_map.put(pair,prob);
	}
	
//	for(int i = 0; i < pairs.size(); i++) {
//		String pair = pairs.get(i);
//		double prob = pair_map.get(pair);
//		//System.out.println(pair + " " + prob);
//	}
//	
	
	return pair_map;
	
}

	}


