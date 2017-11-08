package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

import tools.Progressbar;

public class VocabularyFile {

	private String path;
	private long max;
	private File file;
	private Scanner s = null;
	private BigInteger sum = null;

	public VocabularyFile(String path, long max) {
		this.path = path;
		this.max = max;
		System.out.println("Opening file \"" + path + "\": " + (openFile() ? "OK" : "Error"));
	}

	private boolean openFile(){
		file = new File(path);

		try {
			this.s = new Scanner(file);
		} catch (FileNotFoundException e) {
			return false;
		}

		return true;
	}

	public BigInteger getSum(){
		if (sum == null) computeSum();
		return sum;
	}

	private void computeSum() {
		String x;
		long i=0;
		Progressbar pb = new Progressbar(50);

		sum = BigInteger.ZERO;

		pb.start();
		while (s.hasNext()){
			s.next();
			s.next();
			x = s.next();
			sum = sum.add(new BigInteger(x));
			pb.update(++i/max);
		}
		pb.oneHundred();
	}
}
