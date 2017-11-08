package files;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PMI {

	private MatrixFile mf;
	private VocabularyFile df;

	public PMI(MatrixFile mf, VocabularyFile df) {
		this.mf = mf;
		this.df = df;
	}

	public float compute(	BigInteger tSum,
							BigInteger sV1,
							BigInteger sV2,
							BigInteger sV1V2){
//		BigInteger tSum;
//		BigInteger sV1;
//		BigInteger sV2;
//		BigInteger sV1V2;
		float mpi;



		BigDecimal bd_tSum = new BigDecimal(tSum);
		BigDecimal bd_sV1V2 = new BigDecimal(sV1V2);
		BigDecimal bd_sV1 = new BigDecimal(sV1);

//		System.out.println("bd_tSum.scale() = " + bd_tSum.scale());
//		bd_tSum.setScale(3);
//		System.out.println("bd_tSum.scale() = " + bd_tSum.scale());

		System.out.println("bd_tSum:  " + bd_tSum.toString());
		System.out.println("bd_sV1V2: " + bd_sV1V2.toString());
		System.out.println("bd_sV1:   " + bd_sV1.toString());
		
		System.out.println();
		System.out.println("bd_sV1V2.divide(bd_tSum,200,BigDecimal.ROUND_HALF_UP):" + bd_sV1V2.divide(bd_tSum,200,BigDecimal.ROUND_HALF_UP));

		double pV1V2 = bd_sV1V2.divide(bd_tSum,200,BigDecimal.ROUND_HALF_UP).doubleValue();
		double pV1 = bd_sV1.divide(bd_tSum,200,BigDecimal.ROUND_HALF_UP).doubleValue();
		double pV2 = new BigDecimal(sV2).divide(bd_tSum,200,BigDecimal.ROUND_HALF_UP).doubleValue();

		System.out.println();
		System.out.println("pV1: " + pV1);
		System.out.println("pV2: " + pV2);
		System.out.println("pV1V2: " + pV1V2);

		mpi = (float) Math.log(pV1V2/(pV1*pV2));

		System.out.println("mpi: " + mpi);

		return mpi;
	}
}
