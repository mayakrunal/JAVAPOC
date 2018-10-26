package com.poc.hadoopdemo.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "hadoop")
public class HadoopConfiguration {

    private String fsUri;

    private String hadoopUser;

    @Bean
    org.apache.hadoop.conf.Configuration getConfig() {

        final org.apache.hadoop.conf.Configuration config = new org.apache.hadoop.conf.Configuration();
        config.set("fs.defaultFS", fsUri);
        //set hadoop user
        System.setProperty("HADOOP_USER_NAME", hadoopUser);

        return config;
    }
}
