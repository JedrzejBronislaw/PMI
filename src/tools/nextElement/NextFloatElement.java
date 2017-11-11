package tools.nextElement;

import java.util.Scanner;

import tools.NextElement;

public class NextFloatElement implements NextElement{

	public Object next(Scanner s) {
		s.nextFloat();
		return null;
	};
}
