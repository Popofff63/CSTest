package ru.pdsonline.CSTestTask.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class XLSXFileParserService implements FileParserService{
    @Override
    public int[] parseFile(File file) throws IOException, InvalidFormatException {
        List<Integer> list = new ArrayList<>();
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet hs = wb.getSheetAt(0);
        Iterator<Row> rowIterator = hs.rowIterator();
        while(rowIterator.hasNext()) {
            XSSFRow row = (XSSFRow) rowIterator.next();
            list.add((int)row.getCell(0).getNumericCellValue());
        }
        int[] resArr = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            resArr[i] = list.get(i);
        }
        return resArr;
    }
}
