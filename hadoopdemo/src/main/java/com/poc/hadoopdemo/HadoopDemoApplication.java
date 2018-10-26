package com.poc.hadoopdemo;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HadoopDemoApplication implements CommandLineRunner {

    @Autowired
    private HadoopOperation hadoopOperation;


    public static void main(String[] args) {
        SpringApplication.run(HadoopDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String uploadDir = args[0];

        String sourceFile = args[1];

        hadoopOperation.open();

        hadoopOperation.makeDirectories(uploadDir);

        Path path = Paths.get(sourceFile);


        String outPath = new org.apache.hadoop.fs.Path(uploadDir, path.getFileName().toString()).toUri().getPath();


        try( FSDataOutputStream outStream = hadoopOperation.getOutputStream(outPath, false)){

            byte[] bytes = Files.readAllBytes(Paths.get(sourceFile));

            outStream.write(bytes);
        }
    }
}
