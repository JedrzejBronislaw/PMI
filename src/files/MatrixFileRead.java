package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

import tools.nextElement.NextStringElement;
import tools.NextElement;
import tools.Progressbar;

public class MatrixFileRead extends MatrixFile {

	private Scanner s = null;
	private NextStringElement nextStr = new NextStringElement();

	public MatrixFileRead(String path, long max) {
		super(path, max);
		System.out.println("Opening file \"" + path + "\": " + (openFile() ? "OK" : "Error"));
	}

	public BigInteger nextBigInt(){
		return new BigInteger((String)(nextStr.next(s)));
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
}
