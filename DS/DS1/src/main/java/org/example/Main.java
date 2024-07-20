package org.example;

import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;

 class BinaryIndexedTree {
     //  static final public Long mod = 1000000007L;
     final static int MAX = 500002;
     long BITree[] = new long[MAX];
     long getSum(int index, int index2) {
         long sum = 0;
         while (index > 0) {
             sum += BITree[index];
             sum %= 1000000007L;
             index -= index & (-index);
         }
         long sum2 = 0;
         index2 = index2 + 1;
         while (index2 > 0) {
             sum2 += BITree[index2];
             sum2 %= 1000000007L;
             index2 -= index2 & (-index2);
         }
         return ((sum2 - sum) + 1000000007L) % 1000000007L;
     }

     public void updateBIT(int n, int index, long val) {
         index = index + 1;
         while (index <= n) {
             BITree[index] += val;
             BITree[index] %= 1000000007L;
             index += index & (-index);
         }
     }

     void constructBITree(int n) {
         for (int i = 0; i <= n; i++)
             BITree[i] = 0;

     }
 }
 public class Main {
    public static void main(String args[])
    {
        Scanner scanner= new Scanner(System.in);
        int n=scanner.nextInt();
        ArrayList<Integer> entry=new ArrayList<>();
        int[] indexes = new int[n+2];
        for (int i=0 ; i< n ;i++){
            entry.add(scanner.nextInt());
        }
        for (int i=0 ; i<n ; i++){
            indexes[entry.get(i)]=i;
        }
        BinaryIndexedTree visited=new BinaryIndexedTree();
        visited.constructBITree(n+1);
        BinaryIndexedTree left=new BinaryIndexedTree();
        left.constructBITree(n+1);
        BinaryIndexedTree right=new BinaryIndexedTree();
        right.constructBITree(n+1);
        long a;
        long b;
        long x;
        long y;
        long res=0;
        long mod=1000000007L;
        for ( int i = n ; i >= 1; i--){
            visited.updateBIT(n,indexes[i],1 );
            a = visited.getSum(indexes[i]+1,n-1);
            b = visited.getSum(0,indexes[i]-1);
            right.updateBIT(n,indexes[i],a);
            left.updateBIT(n,indexes[i],b);
            x = right.getSum(indexes[i]+1,n-1);
            y = left.getSum(0,indexes[i]-1);
            res += (((((a*(a-1)/2)%mod)-x+mod)%mod)*((((b*(b-1)/2)%mod)-y+mod)%mod))%mod;
            res %= mod;
        }
        System.out.println(res);
    }
}

