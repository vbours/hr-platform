package com.boursinos.hrplatform.utils;

import com.boursinos.hrplatform.model.employee.Employee;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class FileUtils {

    static final Logger logger = Logger.getLogger(FileUtils.class);

    public static String createFile(Map<String, List<Employee>> map, String storageFolder, String filename){
        String filePath = String.format("%s/%s",storageFolder, filename);
        File uploadFile  = new File(filePath);
        BufferedWriter bf = null;
        try {
            bf = new BufferedWriter(new FileWriter(uploadFile));
            for (Map.Entry<String, List<Employee>> entry : map.entrySet()) {
                bf.write(entry.getKey() + ":"
                        + entry.getValue());
                bf.newLine();
            }
            bf.flush();
        } catch (Exception ex) {
            logger.error("error: " + ex.toString());
        }
        return filePath;
    }
}
