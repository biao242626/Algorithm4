package Chap1.union_find;

public class WeightedQuickUnionUF {
    private int[] id;
    private int[] sz;
    private int count;
    public WeightedQuickUnionUF(int N){
        count = N;
        id = new int[N];
        sz = new int[N];
        for(int i = 0; i < N; i ++) id[i] = i;
        for(int i = 0; i < N; i ++) sz[i] = 1;
    }
    public int getCount(){ return  count;}
    public boolean isconnected(int p, int q){
        return find(p) == find(q);
    }
    public int find(int p){
        //找到根节点
        if(p == id[p]) return p;
        else {
            id[p] = find(p);
            return id[p];//递归时直接修改
        }
    }
    public void union(int p, int q){
        int qid = find(q);
        int pid = find(p);
        if(pid == qid) return;
        else{
            id[qid] = id[p];//左归
        }
        count --;
    }

    public static void main(String[] args) {
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(6);
        for(int i = 1; i < 6; i ++)
            weightedQuickUnionUF.union(0, i);
        for(int i : weightedQuickUnionUF.id)
            System.out.printf(" "+i);
    }
}
