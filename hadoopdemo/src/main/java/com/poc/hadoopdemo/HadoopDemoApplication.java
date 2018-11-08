package com.poc.hadoopdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HadoopDemoApplication implements CommandLineRunner {

    @Autowired
    private HadoopOperation hadoopOperation;


    public static void main(final String[] args) {
        SpringApplication.run(HadoopDemoApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {


        final String basePath = "/tmp/ProviderAnalytics_T108400/";

        final String dir = "Raw_Data/";

        //final String sourceFile = args[1];


        hadoopOperation.open();


        hadoopOperation.deleteDirectory(basePath + dir);

        //hadoopOperation.makeDirectories(uploadDir);

        //final Path path = Paths.get(sourceFile);


        hadoopOperation.close();
    }
}