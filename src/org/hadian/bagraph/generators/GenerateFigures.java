package org.hadian.bagraph.generators;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by ali on 18/01/17.
 */
public class GenerateFigures {
    private final static int NUM_TESTS = 10;
    private final static String[] columns = {"SamplingMode", "NumNodes", "m", "NumEdges", "NumComparisons", "TotalTime", "SamplingTime", "MaintenanceTime", "NumBuckets", "TreeCodeWordLength", "TreeOptimalHuffmanCodeWordLength", "TotalBucketsInserted", "TotalBucketsRemoved"};
    private final static int MAX_COLUMNS = columns.length;
    private static final List<String> baseParams = Arrays.asList(new String[]{"java", "-Xmx1g", "-jar", "target/ROLL-0.3-SNAPSHOT-jar-with-dependencies.jar"});
    private static final DecimalFormat df = new DecimalFormat("#.0000000");

    public static void main(String args[]) throws IOException, InterruptedException {
        runFig2();

    }

    private static void runFig2() throws IOException, InterruptedException {
        System.out.println("Fig 2");
        for(int m : new int[]{2,10,20}) {
            for (int np = 3; np <= 9; np++) {
                String n = String.valueOf((long) Math.pow(10, np));
                Map<String, Double> resultRollBucket = testAndAverage("-s", "roll-bucket","-n", n, "-m", String.valueOf(m));
                double ratio = 100 * (1-(resultRollBucket.get("NumBuckets")-resultRollBucket.get("NumNodes")));
                persist("data.red.m"+m+".txt",n,df.format(ratio));
            }
        }
    }

    public static void persist(String fileName, String key, String value) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
        bw.write(key + "\t" + value);
        bw.newLine();
        bw.flush();
        bw.close();
    }

    public static Map<String, Double> testAndAverage(String... params) throws IOException, InterruptedException {
        double[] sums = new double[MAX_COLUMNS];
        HashMap<String, Double> results = new HashMap<String, Double>();
        String[] res = null;

        ArrayList<String> fullParams = new ArrayList<String>(baseParams);
        for (String param:params)
            fullParams.add(param);


        for (int i = 0; i < NUM_TESTS; i++) {
            res = runTest(fullParams);
            for (int col = 1; col < Math.min(MAX_COLUMNS, res.length); col++) {
                sums[col] += Double.parseDouble(res[col]);
            }
            Arrays.stream(res).forEachOrdered(s -> System.err.print(s + "\t"));
            System.err.println();
        }

        for (int col = 1; col < MAX_COLUMNS; col++)
            results.put(columns[col], sums[col] / NUM_TESTS);

        return results;
    }

    public static String[] runTest(List<String> params) throws IOException, InterruptedException {
        final ProcessBuilder builder = new ProcessBuilder(params);
        //"java", "-Xmx2g", "-jar", "target/ROLL-0.3-SNAPSHOT-jar-with-dependencies.jar", "-n", "1000000", "-m", "4", "-s", "roll-tree");
        Process process = builder.start();
        process.waitFor();
        String line = null;
        try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String lastLine;
            while ((lastLine = input.readLine()) != null) {
                System.err.println(lastLine);
                line = lastLine;
            }
        }
        //System.out.println("output: " + process.exitValue());
        if (process.exitValue() != 0)
            throw new IOException("cannot run the program for parameters: " + params.toString());

        if (line == null)
            throw new IOException("output is null for parameters: " + params.toString());

        String[] outputList = line.split(" ");
        if (outputList.length < 11)
            throw new IOException("output is not valid for parameters: " + params.toString() + "\n\n" + line);

        return outputList;
    }
}