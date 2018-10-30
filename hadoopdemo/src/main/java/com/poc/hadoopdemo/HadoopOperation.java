package com.poc.hadoopdemo;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Component
@Singleton
@Slf4j
public class HadoopOperation {

    private FileSystem fs;

    @Autowired
    private Configuration hadoopConfig;


    public void open() throws IOException {
        if (fs == null) {
            fs = FileSystem.get(hadoopConfig);
        }
    }

    public void close() {
        if (fs != null) {
            try {
                fs.close();
            } catch (final IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public Configuration getHadoopConfig() {
        return hadoopConfig;
    }

    public boolean isDirectory(final String path) throws IOException {
        return fs.isDirectory(new Path(path));
    }

    public boolean exists(final String file) throws IOException {
        return fs.exists(new Path(file));
    }

    public InputStream getInputStream(final Path path) throws IOException {
        return fs.open(path);
    }

    public void makeDirectories(final String directory) throws IOException {
        //make directories if not exists
        final Path dir = new Path(directory);
        if (!fs.exists(dir)) {
            fs.mkdirs(dir);
        }
    }

    public List<LocatedFileStatus> getFiles(final String directory, final boolean recursive) throws IOException {
        final List<LocatedFileStatus> list = new ArrayList<>();

        final RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path(directory), recursive);
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public void deleteFiles2(final List<LocatedFileStatus> files) throws IOException {
        for (final LocatedFileStatus f : files) {
            fs.delete(f.getPath(), false);
        }

    }

    public void deleteFiles(final List<String> files) throws IOException {
        for (final String f : files) {
            fs.delete(new Path(f), true);
        }

    }

    public void deleteDirectory(final String directory) throws IOException {
        final Path dir = new Path(directory);
        if (fs.exists(dir)) {
            fs.delete(dir, true);
        }
    }

    public FSDataOutputStream getOutputStream(final String file, final boolean append) throws IOException {
        final FSDataOutputStream stream;
        final Path path = new Path(file);
        if (fs.exists(path) && append) {
            stream = fs.append(path);
        } else {
            stream = fs.create(path);
        }
        return stream;
    }


}
