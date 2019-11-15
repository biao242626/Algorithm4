package project1;

import edu.princeton.cs.algs4.*;
public class Percolation {
    private int gridLength;
    private int opencount;
    private boolean[] grid;//网格数组
    private int virtualTop;//虚拟一个顶部,位于并查集数组的倒数第二个元素
    private int virtualBottom;//虚拟一个底部,位于并查集数组的最后一个元素
    private WeightedQuickUnionUF weightedQuickUnionUF;
    public Percolation(int N) {
        if(N <= 0){
            throw new IllegalArgumentException("网格边长必须为正数");
        }
        gridLength = N;
        opencount = 0;
        virtualTop = gridLength*gridLength;
        virtualBottom = virtualTop + 1;
        grid = new boolean[virtualTop];
        weightedQuickUnionUF = new WeightedQuickUnionUF(virtualTop+2);
    }
    public int getOpencount(){
        return opencount;
    }
    public int xy_to1D(int i, int j){
        return (i-1)*gridLength+j-1;
    }
    public boolean judege_index(int i, int j){
        if(i<=0 || i>gridLength || j<=0 || j>gridLength)
            return false;
        return true;
    }
    public void judge(int i, int j){
        if(i<=0 || i>gridLength || j<=0 || j>gridLength)
            throw new IndexOutOfBoundsException();
    }
    public void open(int i, int j){
        judge(i, j);
        int index = xy_to1D(i,j);
        if(isOpen(i, j)) return;
        grid[index] = true;
        if(i == 1){
            //所有第一行都和顶部合并
            weightedQuickUnionUF.union(index, virtualTop);
        }else if(i==gridLength){
            //最后一行和底部合并
            weightedQuickUnionUF.union(index,virtualBottom );
        }
        //上下左右去合并
        //up
        if(judege_index(i-1, j)  && isOpen(i-1, j)){
            weightedQuickUnionUF.union(index, xy_to1D(i-1, j));
        }
        //down
        if(judege_index(i+1, j)  && isOpen(i+1, j)){
            weightedQuickUnionUF.union(index, xy_to1D(i+1, j));
        }
        //left
        if(judege_index(i, j-1)  && isOpen(i, j-1)){
            weightedQuickUnionUF.union(index, xy_to1D(i, j-1));
        }
        //right
        if(judege_index(i, j+1)  && isOpen(i, j+1)){
            weightedQuickUnionUF.union(index, xy_to1D(i, j+1));
        }
        opencount++;
    }
    public boolean isOpen(int i, int j){
        judge(i, j);
        return grid[xy_to1D(i, j)];
    }
    public boolean isFull(int i, int j){
        judge(i, j);
        return weightedQuickUnionUF.connected(xy_to1D(i, j), virtualTop);
    }
    public boolean percolates(){
        return weightedQuickUnionUF.connected(virtualTop,virtualBottom);
    }
    public static void main(String[] args){
        //
    }
}
