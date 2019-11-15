package Chap03;

import edu.princeton.cs.algs4.*;

import java.util.Scanner;

public class FrequencyCounter {
    public static void main(String[] args) {
        //int minlen = 5;
        Scanner scanner = new Scanner(System.in);
        ST<String, Integer> st = new ST<String, Integer>();
        String word = scanner.next();
        while(!word.equals("0")){//输入0结束
            if(!st.contains(word)){
                st.put(word, 1);
            }else{
                st.put(word, st.get(word)+1);
            }
            word = scanner.next();
        }
        String max = "";
        st.put(max, 0);
        for(String wordd : st.keys()){
            if(st.get(wordd) > st.get(max))
                max = wordd;
        }
        System.out.println(max+" "+ st.get(max));
    }
}
