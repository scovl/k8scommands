package com.example.main;

import com.example.app.App;
import com.example.download.Download;

public class main {
    // run shell command

    public static void main(String[] args) {

        // check if file exists in the directory
        String fileName = "/usr/bin/kubectl";
        if (Download.fileExists(fileName)) {
            System.out.println("File already exists");
        } else {
            // call downloadFile method from Download.java
            Download.downloadFile("kubectl",
                    "https://s3.us-west-2.amazonaws.com/amazon-eks/1.22.6/2022-03-09/bin/linux/amd64/kubectl");
        }

        // k8scommand function
        App.k8scommand("default");

    }
}
