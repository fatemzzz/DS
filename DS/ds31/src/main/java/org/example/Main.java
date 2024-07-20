//package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static long [] hash1=new long[200001];
    static long sum=0;
    static long [] degrees=new long[200001];
    static long [] hash2=new long[200001];
    static long [] po=new long[200001];
    public static long m = 1000000009;
    public static void hash(String s){
        int p = 31;

        long hash_value = 0;
        po [0]=1;
        int n =s.length();
        for (int i=0;i<n ; i++) {
            hash_value = (hash_value + (s.charAt(i) - 'a' + 1) * po[i]) % m;
            po[i+1] = (po[i] * p) % m;
            hash1[i] = hash_value;
        }
        hash_value = 0;

        for (int i=n-1;i>=0 ; i--) {
            hash_value = (hash_value + (s.charAt(i) - 'a' + 1) * po[n-1-i]) % m;
            hash2[n-1-i] = hash_value;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sag = scanner.nextLine();
        hash(sag);
//        for (int i=0 ; i<sag.length() ; i++){
//            System.out.print(hash1[i]+" ");
//        }
//        System.out.println();
//        for (int i=0 ; i<sag.length() ; i++){
//            System.out.print(hash2[i]+" ");
//        }
        int hi=0;
        for (int i=0; i<sag.length(); i++){
            if (i<sag.length()-1){
                hi=sag.length()-i-2;
                if (((hash1[i]*po[-i+sag.length()-1])+m)%m==(((hash2[sag.length()-1]-hash2[hi]))+m)%m){
                    degree(i);
                }
            }
            else {
                if (((hash1[i])+m)%m==(((hash2[sag.length()-1]))+m)%m){
                    degree(i);
                }
            }
        }
        System.out.println(sum);
    }
    public static void degree(int index){
        index++;
        if (index==0){
            degrees[index]=1;
        }
        else {
            degrees[index]=degrees[index/2]+1;
        }
       // System.out.println(index+"---"+degrees[index]);
        sum+=degrees[index];
    }
}


