package main;

import java.math.BigInteger;

import files.MatrixFileRead;
import files.MatrixFileWrite;
import files.PMI;
import files.PMIFileWrite;
import files.VocabularyFile;
import tools.nextElement.NextFloatElement;
import tools.nextElement.NextIntElement;
import tools.nextElement.NextStringElement;
import tools.Calc;
import tools.MaxBuffer;
import tools.Progressbar;

public class Main {

    public static void main(String[] args) {
//        System.out.println("PMI 26 paz 2017 r.\n");
        System.out.println("PMI 09 lis 2017 r.\n");

        checkRowSize();
//        lab2();
//        computePMI();


//        float pmi_result = PMI.compute(	new BigInteger("50000952"),
//        								new BigInteger("1938"),
//        								new BigInteger("1311"),
//        								new BigInteger("1159"));
//        System.out.println("PMI: " + pmi_result);



//        BigInteger tSum = totalSum("files//vocabTotal", 10000);
//        System.out.println("Total: " + tSum.toString());

//        zliczLiczby("files//t1.txt");
//        matrixSum("files//t.txt", new String[]{"files//t1.txt","files//t2.txt","files//t3.txt","files//t4.txt"}, 3);
//        matrixSum("files//coocMX-sum.txt", new String[]{	"files//coocMX1",
//        													"files//coocMX2",
//        													"files//coocMX3",
//        													"files//coocMX4"}, 10000);

    }

	@SuppressWarnings("unused")
	private static void lab2() {
		int max = 10000;
		float value;

		MatrixFileRead mf = new MatrixFileRead("files//pmiMX", max);
		VocabularyFile vf = new VocabularyFile("files//totalVocab", max);
		MaxBuffer maxBuffer = new MaxBuffer(10);

		vf.load();

		for(int i=0; i<max; i++)
			for(int j=0; j<max; j++){
				value = mf.nextFloat();
				maxBuffer.update(value, i, j);
				if (value>8)
					System.out.println(value + " -> " + vf.getWord(i) + ", " + vf.getWord(j));
			}


	}

	@SuppressWarnings("unused")
	private static void checkRowSize(){
        int max = 10000;
		MatrixFileRead mf = new MatrixFileRead("files//pmiMX", max);
		VocabularyFile vocabulary = new VocabularyFile("files//totalVocab", 100000000);
		long[] results;

		vocabulary.load();
		mf.setVocabularyFile(vocabulary);

		results = mf.countNumber(new NextStringElement());
		System.out.println("size: " + results[0]);
		System.out.println("time: " + (results[1]/1000000) + " ms");

		results = mf.checkRowSize(new NextStringElement(),10000);
//		System.out.println("number of lines: " + results[0]);
		System.out.println("time: " + (results[1]/1000000) + " ms");
	}


	@SuppressWarnings("unused")
	private static void computePMI(){
	    PMI pmi = new PMI();

	    MatrixFileRead mf = new MatrixFileRead("files//coocMX_sum", 10000);
	    VocabularyFile vf = new VocabularyFile("files//vocabTotal", 10000);
	    PMIFileWrite pf = new PMIFileWrite("files//result", 10000);

	    long time = System.nanoTime();
	    pmi.computeFile(mf, vf, pf);

	    time = System.nanoTime()-time;
	    System.out.println("Time: " + (time/1000000) + " ms");
	}


    @SuppressWarnings("unused")
	private static BigInteger totalSum(String path, long max){
        VocabularyFile df = new VocabularyFile("files//vocabTotal", 10000);
        return df.getTotalSum();
    }

    @SuppressWarnings("unused")
	private static void zliczLiczby(String path) {
        MatrixFileRead mf;
        long[] result;
        Calc calc = new Calc();
        long max = 10000;

        System.out.println(calc.sumaBI(Integer.MAX_VALUE,0  ,0,0).toString());
        System.out.println(calc.sumaBI(Integer.MAX_VALUE,100,0,0).toString());

        mf = new MatrixFileRead(path, max);
        result = mf.countNumber(new NextStringElement());
        System.out.println("next: " + result[0]);
        System.out.println((result[1])/1000000 + " ms");


        mf = new MatrixFileRead(path, max);
        result = mf.countNumber(new NextIntElement());
        System.out.println("nextInt: " + result[0]);
        System.out.println((result[1])/1000000 + " ms");

    }

    @SuppressWarnings("unused")
	private static boolean matrixSum(String resultPath, String[] sourcePath, long max){
        int n = sourcePath.length;
        MatrixFileRead[] mf = new MatrixFileRead[n];
        MatrixFileWrite  mfOut = new MatrixFileWrite(resultPath, max);
        BigInteger[] bInt = new BigInteger[n];
        BigInteger bIntResult;
        long max2 = max*max;
        Progressbar pb = new Progressbar(50);
        long time = System.nanoTime();


        for (int i=0; i<n; i++)
            mf[i] = new MatrixFileRead(sourcePath[i], max);

        pb.start();
        for(long l=0; l< max2; l++){
            for (int i=0; i<n; i++){
                bInt[i] = mf[i].nextBigInt();
            }
            bIntResult = BigInteger.ZERO;
            for (int i=0; i<n; i++)
                bIntResult = bIntResult.add(bInt[i]);

            mfOut.write(bIntResult.toString());

            pb.update((float)l/max2);
        }
        mfOut.flush();
        pb.oneHundred();

        time = System.nanoTime() - time;

        System.out.println("Time: "+ (time/1000000) + " ms");

        return true;
    }




}
