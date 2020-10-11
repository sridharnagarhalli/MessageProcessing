package com.sid.services;

import com.sid.message.proccessing.services.FileService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileServiceTest {

    @Test
    public void nonEmptyFileShouldBeSavedCorrectly() {
        FileService fileService = new FileService();
        String filename = fileService.saveToFile("test", "abc");
        assertNotNull(filename);
        assertEquals("test", filename);
    }

    @Test
    public void emptyFileShouldBeSavedCorrectly() {
        FileService fileService = new FileService();
        String filename = fileService.saveToFile("test", "");
        assertNotNull(filename);
        assertEquals("test", filename);
    }

}
