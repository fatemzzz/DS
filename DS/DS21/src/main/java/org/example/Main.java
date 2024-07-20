package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Integer> minHeap=new ArrayList<>();
    private static int sumMax=0;
    private static int sumMin=0;
    private static final ArrayList<Integer> maxHeap=new ArrayList<>();
    private static final ArrayList<Integer> revMaxHeap=new ArrayList<>();
    private static final ArrayList<Integer> revMinHeap=new ArrayList<>();
    private static final ArrayList<Integer> entry=new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int sum =0;
        for (int i=0 ; i <n ; i++){
            entry.add(scanner.nextInt());
            System.out.println(i);
            if (i==1){
                if (entry.get(0)>entry.get(1)){
                    insertMin(0);
                    insertMax(1);
                }
                else {
                    insertMin(1);
                    insertMax(0);
                }
            }
            if (i>1){
                insertion(entry.get(i),i);
            }

        }


        int med;
        if (minHeap.size()>maxHeap.size()){
            med=minHeap.get(0);
        }
        else{
            med = maxHeap.get(0);
        }
        for (Integer value : minHeap) {
            sum += Math.abs(entry.get(med) - entry.get(value));
        }
        for (Integer integer : maxHeap) {
            sum += Math.abs(entry.get(med) - entry.get(integer));
        }
        System.out.println(minHeap);
        System.out.println(maxHeap);
        System.out.println(sum);
        System.out.println(entry);
    }
    public static void  insertion(int num, int index){
        if (num > entry.get(minHeap.get(0))){
            insertMin(index);
        }
        else {
            insertMax(index);

        }
        if (minHeap.size()-maxHeap.size()>1){
            int dp=minHeap.get(0);
            deleteMin(0);
            insertMax(dp);
        }
        else if(maxHeap.size()-minHeap.size()>1){
            int dp=maxHeap.get(0);
            deleteMax(0);
            insertMin(dp);
        }
    }
    public static void insertMin(int index) {
        minHeap.add(index);
        revMinHeap.add()
        bubbleUpMin(minHeap.size() - 1);
    }
    public static void insertMax(int index) {
        maxHeap.add(index);
        bubbleUpMax(maxHeap.size() - 1);
    }

    public static void deleteMin(int i) {
        if (minHeap.isEmpty()) {
            return;
        }
        swap(i, minHeap.size() - 1,minHeap);
        minHeap.remove(minHeap.size() - 1);
        bubbleDownMin(i);
    }
    public static void deleteMax(int i) {
        if (maxHeap.isEmpty()) {
            return;
        }
        swap(i, maxHeap.size() - 1,maxHeap);
        maxHeap.remove(maxHeap.size() - 1);
        bubbleUpMax(i);
    }
    private static void bubbleUpMin(int index) {
        int parentIndex = (index - 1) / 2;

        while (index > 0 && entry.get(minHeap.get(index)) < entry.get(minHeap.get(parentIndex))) {
            swap(index, parentIndex,minHeap);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }
    private static void bubbleUpMax(int index) {
        int parentIndex = (index - 1) / 2;

        while (index > 0 && entry.get(maxHeap.get(index)) > entry.get(maxHeap.get(parentIndex))) {
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

        if (rightChildIndex < maxHeap.size() && entry.get(maxHeap.get(rightChildIndex)) > entry.get(maxHeap.get(biggest))) {
            biggest = rightChildIndex;
        }

        if (biggest != index) {
            swap(index, biggest,maxHeap);
            bubbleDownMax(biggest);
        }
    }
    private static void swap(int index1, int index2, ArrayList<Integer> heap1) {
        int temp = heap1.get(index1);
        heap1.set(index1, heap1.get(index2));
        heap1.set(index2, temp);
    }
}
