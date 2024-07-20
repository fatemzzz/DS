//package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Integer> minHeap=new ArrayList<>();
    private static long sumMax=0;
    private static long sumMin=0;
    private static final ArrayList<Integer> maxHeap=new ArrayList<>();
    private static final ArrayList<Integer> entry=new ArrayList<>();
    static int[]  revMaxHeap;
    static int[]  revMinHeap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        revMaxHeap= new int[n+1];
        revMinHeap = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            revMinHeap[i] = -1;
        }
        for (int i = 0; i < n+1 ; i++) {
            revMaxHeap[i] = -1;
        }
        int med;
        int j=-k;
        for (int i=0 ; i <n ; i++){
            entry.add(scanner.nextInt());
            insertion(entry.get(i),i);
            if (j==-1){
                med=findMed();
                System.out.print(diff(med)+" ");
            }
            if (j>=0&&j<n-k+1){

                if(revMinHeap[j]==-1 && revMaxHeap[j]!= -1){
                    deleteMax(revMaxHeap[j]);
                }
                else if(revMaxHeap[j]==-1 && revMinHeap[j]!= -1){
                    deleteMin(revMinHeap[j]);
                }
                med=findMed();
                System.out.print(diff(med)+" ");
            }
            j++;
        }
    }

    public static long diff(int med){

        return (Math.abs(sumMax-((long) med *maxHeap.size())))+(Math.abs(sumMin-((long) med *minHeap.size())));

    }
    public static int findMed(){
        if (minHeap.size()>maxHeap.size()){
            return (entry.get(minHeap.get(0)));
        }
        else {
            return (entry.get(maxHeap.get(0)));
        }
    }
    public static void  insertion(int num, int index){
        if (minHeap.isEmpty() || num >= entry.get(minHeap.get(0))){
            insertMin(index);
        }
        else {
            insertMax(index);
        }
        check();
    }
    public static void check(){
        if (minHeap.size()-maxHeap.size()>1||(minHeap.size()>=2 && maxHeap.isEmpty())){
            int dp=minHeap.get(0);
            deleteMin(0);
            insertMax(dp);
        }
        else if(maxHeap.size()-minHeap.size()>1||(maxHeap.size()>=2 && minHeap.isEmpty())){
            int dp=maxHeap.get(0);
            deleteMax(0);
            insertMin(dp);
        }
    }
    public static void insertMin(int index) {
        minHeap.add(index);
        revMinHeap[index]=minHeap.size()-1;
        bubbleUpMin(minHeap.size() - 1);
        sumMin += entry.get((index));
    }
    public static void insertMax(int index) {
        maxHeap.add(index);
        revMaxHeap[index] = maxHeap.size()-1;
        bubbleUpMax(maxHeap.size() - 1);
        sumMax += entry.get(index);
    }
    public static void deleteMin(int i) {
        if (i==-1){
            return;
        }
        sumMin =sumMin - entry.get(minHeap.get(i));
        if (minHeap.isEmpty()) {
            return;
        }
        swap(minHeap.get(i), minHeap.get(minHeap.size()-1),revMinHeap);
        revMinHeap[minHeap.get(i)]=-1;
        swap(i, minHeap.size() - 1,minHeap);
        minHeap.remove(minHeap.size() - 1);
        if (minHeap.size()>1 && minHeap.size()!=i){
            bubbleUpMin(i);
            bubbleDownMin(i);
        }
        check();
        revMinHeap[minHeap.get(minHeap.size()-1)]=minHeap.size()-1;
    }
    public static void deleteMax(int i) {
        if (i==-1){
            return;
        }
        sumMax =sumMax - entry.get(maxHeap.get(i));
        if (maxHeap.isEmpty()) {
            return;
        }
        swap(maxHeap.get(i), maxHeap.get(maxHeap.size()-1),revMaxHeap);
        revMaxHeap[maxHeap.get(i)]=-1;
        swap(i, maxHeap.size() - 1,maxHeap);
        maxHeap.remove(maxHeap.size() - 1);
        if (maxHeap.size()>1 && maxHeap.size()!=i){
            bubbleUpMax(i);
            bubbleDownMax(i);
        }
        check();
        revMaxHeap[maxHeap.get(maxHeap.size()-1)]=maxHeap.size()-1;
    }
    private static void bubbleUpMin(int index) {
        int parentIndex = (index - 1) / 2;
        while ( index > 0 && (entry.get(minHeap.get(index)) < entry.get(minHeap.get(parentIndex)))) {
            swap(minHeap.get(index), minHeap.get(parentIndex),revMinHeap);
            swap(index, parentIndex,minHeap);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }
    private static void bubbleUpMax(int index) {
        int parentIndex = (index - 1) / 2;

        while (index > 0 && entry.get(maxHeap.get(index)) > entry.get(maxHeap.get(parentIndex))) {
            swap(maxHeap.get(index),maxHeap.get(parentIndex),revMaxHeap);
            swap(index, parentIndex,maxHeap);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }
    private static void bubbleDownMin(int index) {
        int smallest = index;
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;

        if (leftChildIndex < minHeap.size() && entry.get(minHeap.get(leftChildIndex)) < entry.get(minHeap.get(smallest))) {
            smallest = leftChildIndex;
        }

        if (rightChildIndex < minHeap.size() && entry.get(minHeap.get(rightChildIndex)) < entry.get(minHeap.get(smallest))) {
            smallest = rightChildIndex;
        }

        if (smallest != index) {
            swap(minHeap.get(index), minHeap.get(smallest),revMinHeap);
            swap(index, smallest,minHeap);

            bubbleDownMin(smallest);
        }
    }
    private static void bubbleDownMax(int index) {
        int biggest = index;
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;

        if (leftChildIndex < maxHeap.size() && entry.get(maxHeap.get(leftChildIndex)) > entry.get(maxHeap.get(biggest))) {
            biggest = leftChildIndex;
        }

        if (rightChildIndex < maxHeap.size() && (entry.get(maxHeap.get(rightChildIndex)) > entry.get(maxHeap.get(biggest)))) {
            biggest = rightChildIndex;
        }

        if (biggest != index) {
            swap(maxHeap.get(index),maxHeap.get(biggest),revMaxHeap);
            swap(index, biggest,maxHeap);

            bubbleDownMax(biggest);
        }
    }
    private static void swap(int index1, int index2, ArrayList<Integer> heap1) {
        int temp = heap1.get(index1);
        heap1.set(index1, heap1.get(index2));
        heap1.set(index2, temp);
    }
    private static void swap(int index1, int index2, int[] heap1) {
        int temp = heap1[index1];
        heap1[index1]= heap1[index2];
        heap1[index2]= temp;
    }
}
