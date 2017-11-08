package files;

import java.io.File;

public abstract class MatrixFile {

	protected String path;
	protected long max;
	protected File file;

	protected MatrixFile(String path, long max) {
		this.path = path;
		this.max = max;
	}

	abstract boolean openFile();

//	private void closeFile(){
//		s.close();
//	}

}
