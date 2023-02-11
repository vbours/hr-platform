package com.boursinos.hrplatform.clients;

import com.boursinos.hrplatform.BaseTests;
import com.boursinos.hrplatform.model.entity.storage.ObjectInfos;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
public class MinioTests extends BaseTests {

    @Autowired
    private MinioClients minioClients;

    private String bucket = "test";
    private String filename = "images.jpeg";

    private String localFolder = "src/test/resources/files/";

    @Test
    public void uploadFile() throws Exception {
        minioClients.uploader(bucket,filename,localFolder);
        List<ObjectInfos> objects =  minioClients.showObjects(bucket);
        Assert.assertTrue(objects.get(0).getObjectName().contains(filename));
    }

    @Test
    public void deleteFile() throws Exception {
        minioClients.uploader(bucket,filename,localFolder);
        minioClients.deleteFile(bucket,filename);
        List<ObjectInfos> objects =  minioClients.showObjects(bucket);
        Assert.assertEquals(objects.size(),0);
    }

    @Test
    public void getBucket() throws Exception {
        minioClients.uploader(bucket,filename,localFolder);
        List<String> buckets = minioClients.getAllBuckets();
        Assert.assertTrue(buckets.contains(bucket));
    }

    @Test
    public void deleteBucket() throws Exception {
        minioClients.uploader(bucket,filename,localFolder);
        minioClients.deleteBucket(bucket);
        List<String> buckets = minioClients.getAllBuckets();
        Assert.assertEquals(buckets.contains(bucket),false);
    }

    @AfterAll
    public void deleteFiles() throws IOException {
        minioClients.deleteAllFiles(bucket);
    }

}
