package files;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.BeforeClass;
import org.junit.Test;

public class PMITest {

//	private static PMI pmi;

	@BeforeClass
	public static void perform(){
//        pmi = new PMI();
	}

	@Test
	public void testCompute() {
        float pmi_result = PMI.compute(	new BigInteger("50000952"),
        								new BigInteger("1938"),
        								new BigInteger("1311"),
        								new BigInteger("1159"));

        assertEquals(10.0349081703, pmi_result, 0.00001);
	}

}
