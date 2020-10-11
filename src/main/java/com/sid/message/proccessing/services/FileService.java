package com.sid.message.proccessing.services;

import com.sid.message.proccessing.utils.Constants;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class FileService {
    final static Logger LOGGER = Logger.getLogger(FileService.class);

    public String saveToFile(String fileName,String content){
        try (FileWriter writer = new FileWriter(Constants.BASE_URL.getPath() + fileName, true)) {
            writer.append(content);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return fileName;
    }
}
