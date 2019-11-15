package Chap1.Percolation;

import java.util.Scanner;

public class PercolationStats {

    private int matrixLength;
    private double[] results;
//    private double m_mean;
//    private double m_stddev;
//    private double m_confidenceLo;
//    private double m_confidenceHi;

    public PercolationStats(int N, int T){
        if(N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        matrixLength = N;
        results = new double[T];
        for(int i = 0; i < T; i ++){
            Percolation percolation = new Percolation(N);
            while (!percolation.percolates()){
                int row = (int)(Math.random()*(matrixLength))+1;
                int column = (int)(Math.random()*(matrixLength))+1;
                percolation.open(row,column );
            }
            results[i] = ((double) percolation.getOpen_count()/(matrixLength*matrixLength));
        }
//        m_mean = mean();
//        m_stddev = stddev();
//        m_confidenceLo = confidenceLo();
//        m_confidenceHi = confidenceHi();
    }

    public double mean(){
        double sum = 0;
        for(int i = 0; i < results.length; i++)
            sum += results[i];
        return sum/results.length;
    }

    public double stddev(){
        double sum = 0;
        for(int i = 0; i < results.length; i ++)
            sum += Math.pow(results[i]-mean(),2 );
        return Math.sqrt(sum/(results.length - 1));
    }

    public double confidenceLo(){
        return mean() - 1.96*stddev()/Math.sqrt(results.length);
    }

    public double confidenceHi(){
        return mean() + 1.96*stddev()/Math.sqrt(results.length);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int T = scanner.nextInt();
        PercolationStats percolationStats = new PercolationStats(N, T);
        System.out.println("平均数："+percolationStats.mean());
        System.out.println("标准差："+percolationStats.stddev());
        System.out.println("置信区间："+percolationStats.confidenceLo()+"" +
                "---"+percolationStats.confidenceHi());
    }
}
