package tools.nextElement;

import java.util.Scanner;

import tools.NextElement;

public class NextIntElement implements NextElement{

	public Object next(Scanner s) {
		s.nextInt();
		return null;
	};
}
