package Chap1.Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Scanner;

/*
virtualTop和virtualBottom作为虚拟的顶部和底部，当它们两个在同一个集合中时，说明渗透
 */
public class Percolation {

    private int gridLength;
    private int open_count;
    private boolean[] grid;
    private int virtualTop;
    private int virtualBottom;
    WeightedQuickUnionUF weightedQuickUnionUF;

    public int getOpen_count() {
        return open_count;
    }

    public Percolation(int N){
        if(N <= 0) throw new IllegalArgumentException();
        open_count = 0;
        gridLength = N;
        grid = new boolean[N*N];
        weightedQuickUnionUF = new WeightedQuickUnionUF((N*N)+2);
        virtualTop = N*N;
        virtualBottom = virtualTop + 1;
    }

    public void open(int i, int j){
        if(i <= 0 || j <= 0) throw new IndexOutOfBoundsException();
        if(isOpen(i, j))
            return;
        int index = (i-1)*gridLength + j-1;
        grid[index] = true;
        open_count++;
        //open之后让它和周边的点union
        if( i == 1)
            weightedQuickUnionUF.union(index,virtualTop );
        else if(i == gridLength)
            weightedQuickUnionUF.union(index,virtualBottom );

        int neighbor;
        if(i < gridLength){//下
            if(isOpen(i+1, j)) {
                neighbor = i * gridLength + j - 1;
                weightedQuickUnionUF.union(index,neighbor );
            }
        }
        if(i > 1){//上
            if(isOpen(i-1, j)) {
                neighbor = (i-2) * gridLength + j - 1;
                weightedQuickUnionUF.union(index,neighbor );
            }
        }
        if(j > 1){//左
            if(isOpen(i, j-1)) {
                neighbor = (i-1) * gridLength + j - 2;
                weightedQuickUnionUF.union(index,neighbor );
            }
        }
        if(i < gridLength){//右
            if(isOpen(i, j+1)) {
                neighbor = (i-1) * gridLength + j ;
                weightedQuickUnionUF.union(index,neighbor);
            }
        }
    }

    public boolean isOpen(int i, int j){
        if(i <= 0 || j <= 0) throw new IndexOutOfBoundsException();
        return grid[(i-1)*gridLength + j-1] == true;
    }

    public boolean isFull(int i, int j){
        if(i <= 0 || j <= 0) throw new IndexOutOfBoundsException();
        return grid[(i-1)*gridLength + j-1] == false;
    }

    public boolean percolates(){
        return weightedQuickUnionUF.connected(virtualTop,virtualBottom );
    }

    public static void main(String[] args) {
        int N;
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        Percolation percolation = new Percolation(N);
    }
}
