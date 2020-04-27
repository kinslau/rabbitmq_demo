package com.kinslau.demo.lock;

import org.apache.tomcat.util.threads.TaskThread;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;

public class Test {


    private static ReentrantLock lock = new ReentrantLock();


    public void a(){
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
    }


    public void b(){
        int[] arr = new int[1000000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        System.out.println(search(arr,173));
    }




    public static void c(){
        int[] arr = {1,9,8,6,7,4,10,5,2};
        arr = bubble(arr);
        System.out.println(CollectionUtils.arrayToList(arr));

    }



    public static void d(){
        int[] arr = {1,9,8,6,7,4,10,5,2};
        arr = selectionSort(arr);
        System.out.println(CollectionUtils.arrayToList(arr));

    }


    public static void e(){
        int[] arr = {1,9,8,6,7,4,10,5,2};
        arr = insertSort(arr);
        System.out.println(CollectionUtils.arrayToList(arr));

    }


    //二分法
    public static int search(int[] array,int target){
        int start = 0;
        int end = array.length-1;

        int mid;

        while (start<=end){
            mid = (start+end)/2;
            if (array[mid]==target){
                return mid;
            }else if (array[mid]<target){
                start = mid+1;
            }else {
                end = mid - 1;
            }
        }
        return -1;
    }



    //冒泡排序
    public static int[] bubble(int[] arr){

        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                if (arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }

        return arr;
    }


    //选择排序
    public static int[] selectionSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i]>arr[j]){
                    swap(arr,i,j);
                }
            }
        }

        return arr;
    }

    public static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }



    // 插入排序
    public static int[]  insertSort(int[] arr){

        for (int i = 1; i < arr.length; i++) {

            int j = i-1;
            while (j>=0 && arr[j] > arr[j+1]){
                swap(arr,j,j+1);
                j--;
            }

        }

        return arr;
    }







    public static void main(String[] args){


        e();

    }
}
