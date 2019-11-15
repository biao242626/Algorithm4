package Chap2.practice1;
/*
对插入排序进行优化，不在交换，只是让较大元素右移
为了解决只有两个元素的问题，用j-1来代替j，
一开始j=i，而不是直接赋初值i-1
 */
public class InsertSortImproved {
    public static void main(String[] args) {
        int[] array = new int[]{5,4,3,2,1};
        new InsertSortImproved()._sort(array);
        for(int i : array)
            System.out.printf(" "+i);
    }
    public void _sort(int[] a){//升序排列
        for (int i = 1; i < a.length; i ++){
            int temp = a[i];
            int j = i;
            while (j > 0 && temp < a[j-1])//java判断从左到右进行，避免越界
            {
                a[j] = a[j-1];
                j --;
            }
            a[j] = temp;
        }
    }
}
