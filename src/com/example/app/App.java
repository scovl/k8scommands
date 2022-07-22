package com.example.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    // k8scommand function
    /**
     * @param namespace
     */
    public static void k8scommand(String namespace) {
        String command = String.format("kubectl get ns %s -o json", namespace);

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

    }

}
