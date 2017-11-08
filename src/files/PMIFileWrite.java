package files;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PMIFileWrite extends PMIFile {

	DataOutputStream w;

	public PMIFileWrite(String path, long max) {
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
			System.out.println("max: " + max);
			w.writeLong(max*max);
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
