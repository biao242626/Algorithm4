package project1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Scanner;

public class PercolationStats {

    private double[] results;
    private double u;
    private double sigma;
    private double low;
    private double high;

    public PercolationStats(int N,int T){
        if(N<=0||T<=0)
            throw new IllegalArgumentException();
        results = new double[T];
        u = 0;
        sigma = 0;
        low = 0;
        high = 0;
        for(int n = 0; n < T; n++){
            Percolation percolation = new Percolation(N);
            while (!percolation.percolates()){
                int i = StdRandom.uniform(N)+1;
                int j = StdRandom.uniform(N)+1;
                percolation.open(i, j);
                //percolation.addopenCount();
            }
            results[n] = percolation.getOpencount()/(double)(N*N);
            //System.out.println("result="+percolation.getOpencount()/(double)(N*N));
        }
        u = StdStats.mean(results);
        sigma = StdStats.stddev(results);
        double diff = (1.96*sigma)/Math.sqrt(T);
        low = u-diff;
        high = u+diff;
    }
    public double mean(){
        return u;
    }
    public double stddev(){
        return sigma;
    }
    public double confidenceLo(){
        return low;
    }
    public double confidenceHi(){
        return high;
    }

    public static void main(String[] args) {
        int N,T;
        Scanner scanner = new Scanner(System.in);
        N=scanner.nextInt();
        T=scanner.nextInt();
        long start_time = System.currentTimeMillis();
        PercolationStats percolationStats = new PercolationStats(N, T);
        System.out.println("Example values after creating project1.PercolationStats("+N+","+T+")");
        System.out.println("mean()"+"                  "+percolationStats.mean());
        System.out.println("stddev()"+"                "+percolationStats.stddev());
        System.out.println("confidenceLow()"+"         "+percolationStats.confidenceLo());
        System.out.println("confidenceHigh()"+"        "+percolationStats.confidenceHi());
        long consumingtime=System.currentTimeMillis()-start_time;
        System.out.println("运行时间："+consumingtime+"ms");
        N=scanner.nextInt();
        T=scanner.nextInt();
        long start_time2 = System.currentTimeMillis();
        PercolationStats percolationStats2 = new PercolationStats(N, T);
        System.out.println("Example values after creating project1.PercolationStats("+N+","+T+")");
        System.out.println("mean()"+"                  "+percolationStats2.mean());
        System.out.println("stddev()"+"                "+percolationStats2.stddev());
        System.out.println("confidenceLow()"+"         "+percolationStats2.confidenceLo());
        System.out.println("confidenceHigh()"+"        "+percolationStats2.confidenceHi());
        long consumingtime2=System.currentTimeMillis()-start_time2;
        System.out.println("运行时间："+consumingtime2+"ms");
    }
}
