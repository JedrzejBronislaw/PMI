package files;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PMIFileWrite extends PMIFile {

	DataOutputStream w;

	protected PMIFileWrite(String path, long max) {
		super(path, max);
		System.out.println("Opening file \"" + path + "\": " + (openFile() ? "OK" : "Error"));
	}

	@Override
	boolean openFile() {
		file = new File(path);


		try {
			w = new DataOutputStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			return false;
		}

		try {
			w.writeLong(max);
		} catch (IOException e) {
			return false;
		}

		return true;
	}

	public boolean write(float value){
		try {
			w.writeFloat(value);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public boolean flush(){
		try {
			w.flush();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
