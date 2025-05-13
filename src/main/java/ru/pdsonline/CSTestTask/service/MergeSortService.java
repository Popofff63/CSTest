package ru.pdsonline.CSTestTask.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service(value = "mergeSortService")
public class MergeSortService implements SortService{

    @Override
    public int[] sortArray(int[] unsortedArr) {
        return sort(unsortedArr);
    }
    private int[] sort(int[] arr){
        if(arr.length < 2){
            return arr;
        }
        int[] leftArr = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] rightArr = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
        return merge(sort(leftArr), sort(rightArr));
    }
    private int[] merge(int[] leftArr, int[] rigthArr){
        int[] res = new int[leftArr.length + rigthArr.length];
        int resIndex = 0; int leftIndex = 0; int rightIndex = 0;
        while (leftIndex < leftArr.length && rightIndex < rigthArr.length){
            res[resIndex++] = leftArr[leftIndex] < rigthArr[rightIndex] ? leftArr[leftIndex++] : rigthArr[rightIndex++];
        }
        while (resIndex < res.length){
            res[resIndex++] = leftIndex != leftArr.length ? leftArr[leftIndex++] : rigthArr[rightIndex++];
        }
        return res;
    }
}
