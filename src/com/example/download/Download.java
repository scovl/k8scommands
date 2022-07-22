package com.example.download;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.codec.binary.StringUtils;

public class Download {
    // download file by url

    public static void downloadFile(String fileName, String url) {
        String command = new StringBuffer.append("curl -o").append(fileName).append(" ").append(url).toString();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean fileExists(String fileName) {
        return false;
    }
}
