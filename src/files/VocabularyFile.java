package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tools.Progressbar;

public class VocabularyFile {

	class Record{
		String word;
		BigInteger count;

		public Record(String word, BigInteger count) {
			super();
			this.word = word;
			this.count = count;
		}
	}

	private String path;
	private long max;
	private File file;
	private Scanner s = null;
	private BigInteger sum = null;
	private List<Record> list = null;
	private boolean loaded = false;

	public VocabularyFile(String path, long max) {
		this.path = path;
		this.max = max;
		System.out.println("Opening file \"" + path + "\": " + (openFile() ? "OK" : "Error"));
	}

	public long getMax() {
		return max;
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

	private void close(){
		s.close();
	}

	public BigInteger getTotalSum(){
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

	public void load() {
		// TODO Auto-generated method stub
//		throw new UnsupportedOperationException("Not jet implemented");

		close();
		openFile();

		String word, count;
		list = new ArrayList<>();

		while (s.hasNext()){
			s.next();
			word = s.next();
			count = s.next();
			list.add(new Record(word, new BigInteger(count)));
		}
		close();
		openFile();

		loaded = true;
	}

	public BigInteger getSum(int i) {
		// TODO Auto-generated method stub
//		throw new UnsupportedOperationException("Not jet implemented");
//		return null;

		if (loaded)
			return list.get(i).count;

		int j = 0;
		while (s.hasNext() && j<i){
			s.next();
			s.next();
			s.next();
			j++;
		}

		s.next();
		s.next();
		return new BigInteger(s.next());
	}

	public String getWord(int i) {

		if (loaded)
			return list.get(i).word;
//		else
			//TODO
		return null;
	}
}
