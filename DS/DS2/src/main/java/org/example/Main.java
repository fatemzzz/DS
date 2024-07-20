package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static Double minTime = Double.POSITIVE_INFINITY;
    public static void main(String[] args) throws IOException {
        FastIO io=new FastIO();
        int n= io.nextInt();
        Double[][] planets=new Double[n][3];
        for (int i=0;i<n;i++){
            planets [i][0]= io.nextDouble();
            planets [i][1]= io.nextDouble();
            planets [i][2]= io.nextDouble();
        }
        Arrays.sort(planets, Comparator.comparingDouble(a -> a[2]));
        Double sag;

        for (int i=0;i<n-1;i++){
            for (int j=i+1;j<n;j++){
                if (!(planets[i][1]<=planets[j][1])){
                    sag=collision(planets[i],planets[j]);
                    if (minTime>sag){
                        minTime=sag;
                    }
                }
                if(planets[i][0]<=planets[j][0]){
                    break;
                }
            }
        }
        if (minTime!=Double.POSITIVE_INFINITY){
            io.printf("%.2f",minTime);
        }
        else {
            System.out.println("Collision-Free System");
        }
        io.close();
    }
    public static Double collision(Double[] b, Double[] a){
        Double term1 = 2 * Math.sqrt(a[0] * b[0]);
        Double term2 = a[1] - b[1];
        Double term3 = a[2] - b[2];

        return  (term1 - term3) / term2;

    }
}

class FastIO extends PrintWriter {

    private InputStream stream;
    private byte[] buf = new byte[1 << 16];
    private int curChar;
    private int numChars;

    // standard input
    public FastIO() { this(System.in, System.out); }

    public FastIO(InputStream i, OutputStream o) {
        super(o);
        stream = i;
    }

    // file input
    public FastIO(String i, String o) throws IOException {
        super(new FileWriter(o));
        stream = new FileInputStream(i);
    }

    // throws InputMismatchException() if previously detected end of file
    private int nextByte() {
        if (numChars == -1) { throw new InputMismatchException(); }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) { throw new InputMismatchException(); }
            if (numChars == -1) {
                return -1;  // end of file
            }
        }
        return buf[curChar++];
    }

    // to read in entire lines, replace c <= ' '
    // with a function that checks whether c is a line break
    public String next() {
        int c;
        do { c = nextByte(); } while (c <= ' ');

        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = nextByte();
        } while (c > ' ');
        return res.toString();
    }

    public int nextInt() {  // nextLong() would be implemented similarly
        int c;
        do { c = nextByte(); } while (c <= ' ');

        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = nextByte();
        }

        int res = 0;
        do {
            if (c < '0' || c > '9') { throw new InputMismatchException(); }
            res = 10 * res + c - '0';
            c = nextByte();
        } while (c > ' ');
        return res * sgn;
    }

    public double nextDouble() { return Double.parseDouble(next()); }
}