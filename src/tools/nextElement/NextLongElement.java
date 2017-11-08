package tools.nextElement;

import java.util.Scanner;

import tools.NextElement;

public class NextLongElement implements NextElement{

	public Object next(Scanner s) {
		s.nextLong();
		return null;
	};
}
