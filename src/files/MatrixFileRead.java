package files;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tools.nextElement.NextStringElement;
import tools.NextElement;
import tools.Progressbar;

public class MatrixFileRead extends MatrixFile {

	private Scanner s = null;
	private NextStringElement nextStr = new NextStringElement();
	private VocabularyFile vocabularyFile = null;

	public void setVocabularyFile(VocabularyFile vocabularyFile) {
		this.vocabularyFile = vocabularyFile;
	}
	public VocabularyFile getVocabularyFile() {
		return vocabularyFile;
	}

	public MatrixFileRead(String path, long max) {
		super(path, max);
		System.out.println("Opening file \"" + path + "\": " + (openFile() ? "OK" : "Error"));
	}

	public BigInteger nextBigInt(){
		return new BigInteger((String)(nextStr.next(s)));
	}

	public float nextFloat(){
		return s.nextFloat();
	}


	@Override
	boolean openFile(){
		file = new File(path);

		try {
			this.s = new Scanner(file);
		} catch (FileNotFoundException e) {
			return false;
		}

		return true;

	}

	public long[] countNumber(NextElement next){
		long time = System.nanoTime();
		long x = 0;
		long max = this.max*this.max;



		Progressbar pb = new Progressbar(50);

		pb.start();
		while(s.hasNext()){
//			try{
//				number = s.nextInt();
//				System.out.println(number);
//			} catch (InputMismatchException e){
//				System.out.println("\"" + s.next() + "\"");
//			}
			next.next(s);
			x++;
			pb.update((float)x/max);
		}
		pb.oneHundred();

		time = System.nanoTime() - time;

		return new long[]{x, time};
	}

	public long[] checkRowSize(NextElement next, long expectedSize){
		long time = System.nanoTime();
		Progressbar pb = new Progressbar(50);

		FileInputStream fis = null;
		BufferedInputStream bis = null;

		int c;
		int taby = 1;
		int linia = 0;
		boolean koniec = false;
		List<String> logs = new ArrayList<>();


		try {
			fis = new FileInputStream(path);
			bis = new BufferedInputStream(fis);
		} catch (FileNotFoundException e) {
			koniec = false;
		}


		pb.start();
		while(!koniec){
			try {
				c = bis.read();
				if (c == -1) koniec = true;

				if (c == '\t') taby++; else
				if (c == '\n' || koniec) {
					if (taby != expectedSize)
						if (vocabularyFile != null)
							logs.add(linia+1 + ". " + vocabularyFile.getWord(linia) + " -> size = " + taby); else
							logs.add(linia+1 + ". -> size = " + taby);
					taby = 1;
					linia++;
				}

				pb.update((float)linia/max);//max);

			} catch (IOException e) {
				koniec = true;
			}
		}

		try {
			fis.close();
			bis.close();
		}catch (IOException e) {}

		pb.oneHundred();

		System.out.println("Number of line: " + linia);

		System.out.println("Incorrect lines (" + logs.size() + "): ");
		for (String s : logs)
			System.out.println("\t" + s);
		System.out.println();

		time = System.nanoTime() - time;

		return new long[]{logs.size(), time};
	}
}
