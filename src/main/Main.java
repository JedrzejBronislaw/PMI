package main;

import java.math.BigInteger;

import files.MatrixFileRead;
import files.MatrixFileWrite;
import files.PMI;
import files.VocabularyFile;
import tools.nextElement.NextIntElement;
import tools.nextElement.NextStringElement;
import tools.Calc;
import tools.Progressbar;

public class Main {

    public static void main(String[] args) {
        System.out.println("PMI 26 paz 2017 r.\n");

        PMI pmi = new PMI(null, null);
        float pmi_result = pmi.compute(	new BigInteger("50000952"),
        								new BigInteger("1938"),
        								new BigInteger("1311"),
        								new BigInteger("1159"));
        System.out.println("PMI: " + pmi_result);

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
	private static BigInteger totalSum(String path, long max){
        VocabularyFile df = new VocabularyFile("files//vocabTotal", 10000);
        return df.getSum();
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
