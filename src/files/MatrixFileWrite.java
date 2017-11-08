package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MatrixFileWrite extends MatrixFile{

	private PrintWriter w = null;
	private int inLine;

	public MatrixFileWrite(String path, long max) {
		super(path, max);
		System.out.println("Opening file \"" + path + "\": " + (openFile() ? "OK" : "Error"));
	}


	@Override
	protected boolean openFile(){
		inLine = 0;
		file = new File(path);


		try {
			w = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			return false;
		}

		return true;
	}

	public void write(String str){
		if (inLine == 0){
			inLine++;
		}else
		if (inLine >= max){
			w.flush();
			w.println();
			inLine = 1;
		} else {
			w.print("\t");
			inLine++;
		}

		w.print(str);
	}

	public void flush(){
		w.flush();
	}
}
