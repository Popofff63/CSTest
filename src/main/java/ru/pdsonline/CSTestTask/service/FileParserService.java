package ru.pdsonline.CSTestTask.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileParserService {
    int[] parseFile(File file) throws IOException, InvalidFormatException;
}
