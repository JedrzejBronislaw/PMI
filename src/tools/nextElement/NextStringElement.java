package tools.nextElement;

import java.util.Scanner;

import tools.NextElement;

public class NextStringElement implements NextElement{

	public Object next(Scanner s) {
		return s.next();
	};
}
