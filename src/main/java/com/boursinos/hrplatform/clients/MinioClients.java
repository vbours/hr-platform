package com.boursinos.hrplatform.clients;

import com.boursinos.hrplatform.model.entity.storage.ObjectInfos;
import io.minio.messages.Bucket;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface MinioClients {
    public void uploader(String bucket, String filename, String localFolder)
            throws NoSuchAlgorithmException, IOException, InvalidKeyException;

    public void deleteFile(String bucket, String filename);

    public void deleteBucket(Bucket bucket) throws IOException;

    public void deleteBucket(String bucket) throws IOException;

    public void deleteAllFiles(String bucket) throws IOException;

    public List<String> getAllBuckets() throws IOException;

    public List<ObjectInfos> showObjects(String bucket) throws Exception;

    public String downloadFile(String bucket, String filename);

    public void downloadFile(String bucket, String filename, String outputFilename);




    }
