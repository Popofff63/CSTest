package ru.pdsonline.CSTestTask.service;

import org.springframework.stereotype.Service;

@Service
public class SearchUniqueNMin implements SearchNService{
    @Override
    public int serachN(int[] arr, int n) {
        int latest = 0;
        int uniqueCount = 0;
        for(int i = 0; uniqueCount < n; i++){
            //if(i >= arr.length) throw new ArrayIndexOutOfBoundsException();
            if(i == 0 || latest != arr[i]){
                latest = arr[i];
                uniqueCount++;
            }
        }
        return latest;
    }
}
