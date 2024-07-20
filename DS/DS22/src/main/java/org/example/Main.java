package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long k = scanner.nextLong();
        MedianHandler medianHandler = new MedianHandler(n);
        long[] A = new long[(int) n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextLong();
            if (i < k)
                medianHandler.addNum(A[i], i);
        }
        for (int i = 0; i < n - k + 1; i++) {
            if (i - 1 > -1) {
                medianHandler.removeNum(A[i - 1], i - 1);
                medianHandler.addNum(A[(int) (i + k - 1)], i + k - 1);
            }
            System.out.print(medianHandler.calcDiff() + " ");
        }
    }
}




