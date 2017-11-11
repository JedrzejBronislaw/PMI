package tools;

import java.util.ArrayList;
import java.util.List;

import files.VocabularyFile;

public class MaxBuffer {

	class Record{
		private float value;
		private int x;
		private int y;

		public Record(float value, int x, int y) {
			super();
			this.value = value;
			this.x = x;
			this.y = y;
		}
		public float getValue() {
			return value;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
	}



	private int size;
	private List<Record> list;
	private float min = Float.MIN_VALUE;
	private VocabularyFile vocabularyFile = null;

	public void setVocabularyFile(VocabularyFile vocabularyFile) {
		this.vocabularyFile = vocabularyFile;
	}
	public VocabularyFile getVocabularyFile() {
		return vocabularyFile;
	}

	public MaxBuffer(int size) {
		this.size = size;
		list = new ArrayList<>();
	}

	public boolean update(float value, int x, int y){
		if (value > min){
			//usun nanjmnejsze
			for(int i=0; i<list.size(); i++)
				if (list.get(i).value == min){
					list.remove(i);
					break;
				}
			//dodaj
			list.add(new Record(value, x, y));
			//zminen min
			min = value;
		}

		return false;
	}

	void show(){
		System.out.println("-----");
		for(Record r : list){
			if (vocabularyFile != null)
				System.out.println(r.getValue() + " -> " + vocabularyFile.getWord(r.x) + ", " + vocabularyFile.getWord(r.y));
			else
				System.out.println(r.getValue() + " -> " + vocabularyFile.getWord(r.x) + ", " + vocabularyFile.getWord(r.y));
		}
		System.out.println("-----");
	}
}
