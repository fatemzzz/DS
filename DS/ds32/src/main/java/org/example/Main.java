package org.example;

import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static final int INF = Integer.MAX_VALUE;
    public static final void BFS(int[][] graph, int a, int b){
        Queue<Node> queue=new Queue<Node>() {
        };
        for ()
    }

    public static void main(String[] args) {
        int n , m;
        Scanner scanner = new Scanner(System.in);
        n= scanner.nextInt();
        m= scanner.nextInt();
        int[][] graph =new int[n][m];
        char ent;
        for (int i=0; i< n; i++){
            for (int j=0; j<m; j++){
                ent = scanner.next().charAt(0);
                if (ent=='#'){
                    graph [i][j] = INF;
                }
                else if (ent=='*'){
                    graph [i][j] = 1;
                }
                else if (ent=='.'){
                    graph [i][j] = 0;
                }
                else if (ent == 'A'){
                    graph [i][j] = 0;
                }
                else if (ent == 'B'){
                    graph [i][j] = INF;

                }
                else if (ent == 'C'){
                    graph [i][j] = INF;

                }
            }
            System.out.println();
        }

    }
}
