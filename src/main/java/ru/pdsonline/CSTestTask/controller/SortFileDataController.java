package ru.pdsonline.CSTestTask.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pdsonline.CSTestTask.service.FileParserService;
import ru.pdsonline.CSTestTask.service.SearchNService;
import ru.pdsonline.CSTestTask.service.SortService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(value = "/sortfiledata")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class SortFileDataController {
    FileParserService parserService;
    SortService sortService;
    SearchNService searchNService;
    @GetMapping
    public ResponseEntity<String> sortFileData(@RequestParam String filename, Integer n){
        File file = new File(filename);
        if(file.exists() && !file.isDirectory()){
            try {
                int[] res = parserService.parseFile(file);
                log.info("Read array from file - {}", res);
                res = sortService.sortArray(res);
                log.info("Array after sorting - {}", res);
                try{
                    int resN = searchNService.serachN(res, n);
                    return ResponseEntity.ok(String.valueOf(resN));
                } catch (ArrayIndexOutOfBoundsException ex){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The N-number is greater than the number of unique elements in the array.");
                }
            } catch (InvalidFormatException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Support only xlsx format");
            }
            catch (IOException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't read file");
            }
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }
}
