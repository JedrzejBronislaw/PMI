package tools;

import java.math.BigInteger;

public class Calc {

	public BigInteger sumaBI(int a1, int a2, int a3, int a4){
		BigInteger b1 = BigInteger.valueOf(a1);
		BigInteger b2 = BigInteger.valueOf(a2);
		BigInteger b3 = BigInteger.valueOf(a3);
		BigInteger b4 = BigInteger.valueOf(a4);
		return b1.add(b2).add(b3).add(b4);
	}
	public BigInteger suma(BigInteger a1, BigInteger a2, BigInteger a3, BigInteger a4){
		return a1.add(a2).add(a3).add(a4);
	}

//	public String suma(int a1, int a2, int a3, int a4){
//		throw new UnsupportedOperationException("not implemented jet");
//	}

	public String suma(int a1, int a2) {
		int sum = 0;
		String wynik;
		String sumStr;

		try {
			sum = Math.addExact(a1, a2);
			sumStr = sum + "";
			wynik = "int: ";
		} catch (ArithmeticException e)
		{
			BigInteger bigSum = new BigInteger(a1 + "");
			BigInteger bigA2 = new BigInteger(a2 + "");
			bigSum = bigSum.add(bigA2);
			sumStr = bigSum.toString();
			wynik = "big: ";
		}

		return wynik + sumStr;
	}
}
