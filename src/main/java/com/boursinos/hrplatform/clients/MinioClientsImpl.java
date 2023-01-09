package com.boursinos.hrplatform.clients;

import com.boursinos.hrplatform.model.storage.ObjectInfos;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class MinioClientsImpl implements MinioClients{

    @Value("${S3_KEY}")
    private String accessKey;

    @Value("${S3_SECRET}")
    private String secretKey;

    @Value("${S3_URL}")
    private String minioServerUrl;

    static final Logger logger = LogManager.getLogger(MinioClientsImpl.class);

    /**
     * Upload a csv file to a specific bucket on Minio.
     *
     * @param bucket The bucket name which we will upload the file
     * @param filename The name of the csv file
     * @throws InvalidKeyException throw exception if the accessKey or secretKey is incorrect
     * @throws IOException throw exception if the bucket cannot be created
     */
    public void uploader(String bucket, String filename, String localFolder)
            throws NoSuchAlgorithmException, IOException, InvalidKeyException {

        MinioClient minioClient = minioConnector(bucket);

        Path path = Paths.get(localFolder, filename);
        BufferedInputStream bais =
                new BufferedInputStream(new FileInputStream(path.toFile()), 16 * 1024);

        try {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket).object(filename).stream(bais, bais.available(), -1)
                            .build());
        } catch (ErrorResponseException
                 | InsufficientDataException
                 | InternalException
                 | ServerException
                 | XmlParserException
                 | InvalidResponseException e) {
            e.printStackTrace();
        }
        bais.close();

        logger.info("file is successfully uploaded to " + bucket + " bucket.");
    }

    /**
     * delete a csv file from a specific bucket on Minio.
     *
     * @param bucket The bucket name which we will delete the file
     * @param filename The name of the csv file
     */
    public void deleteFile(String bucket, String filename) {
        List<DeleteObject> objects = new LinkedList<>();
        objects.add(new DeleteObject(filename));

        deleteFileClient(bucket, objects);
    }

    /** See {@link MinioClientsImpl#deleteBucket(String)} */
    public void deleteBucket(Bucket bucket) throws IOException {
        deleteBucket(bucket.name());
    }

    /**
     * Deletes all files and then the bucket in minio
     *
     * @param bucket The bucket which will be deleted
     */
    public void deleteBucket(String bucket) throws IOException {
        MinioClient minioClient = minioRawConnector();
        deleteAllFiles(bucket);
        RemoveBucketArgs rmBucketArgs = RemoveBucketArgs.builder().bucket(bucket).build();
        try {
            minioClient.removeBucket(rmBucketArgs);
        } catch (ErrorResponseException
                 | InsufficientDataException
                 | InternalException
                 | InvalidKeyException
                 | InvalidResponseException
                 | NoSuchAlgorithmException
                 | ServerException
                 | XmlParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete all files from a specific bucket on Minio.
     *
     * @param bucket The bucket name which we will delete the file
     */
    public void deleteAllFiles(String bucket) throws IOException {
        Iterable<Result<Item>> files;
        List<DeleteObject> objects = new LinkedList<>();

        MinioClient mc = minioRawConnector();
        files = mc.listObjects(ListObjectsArgs.builder().bucket(bucket).build());

        for (Result<Item> file : files) {
            try {
                Item it = file.get();
                if (!it.isDir()) {
                    objects.add(new DeleteObject(it.objectName()));
                }
            } catch (ErrorResponseException
                     | XmlParserException
                     | ServerException
                     | NoSuchAlgorithmException
                     | InvalidResponseException
                     | InvalidKeyException
                     | InternalException
                     | InsufficientDataException e) {
                e.printStackTrace();
            }
        }

        deleteFileClient(bucket, objects);
    }


    /**
     * Get list of buckets.
     */
    public List<String> getAllBuckets() throws IOException {

        MinioClient minioClient = minioRawConnector();
        List<Bucket> all_buckets = new LinkedList<>();
        try {
            all_buckets = minioClient.listBuckets();
        } catch (ErrorResponseException
                 | InsufficientDataException
                 | InternalException
                 | InvalidKeyException
                 | InvalidResponseException
                 | NoSuchAlgorithmException
                 | ServerException
                 | XmlParserException e) {
            e.printStackTrace();
        }
        return all_buckets.stream().map(n -> n.name()).collect(Collectors.toList());
    }

    /**
     * Attempts to return a list of objects.
     *
     * @param bucket Request bucket to show the object inside to it
     * @return List of objects
     */
    @Override
    public List<ObjectInfos> showObjects(String bucket) throws Exception {


        MinioClient minioClient = minioRawConnector();

        List<ObjectInfos> objects = new ArrayList<>();
        ListObjectsArgs args = ListObjectsArgs.builder().bucket(bucket).build();
        Iterable<Result<Item>> results = minioClient.listObjects(args);
        for (Result<Item> result : results) {
            Item item = result.get();
            ObjectInfos objectInfos =
                    new ObjectInfos(item.lastModified().toString(), item.size(), item.objectName());
            objects.add(objectInfos);
        }
        Comparator<ObjectInfos> comparator = Collections.reverseOrder();
        Collections.sort(objects, comparator);

        return objects;
    }

    /**
     * Download a csv file from a specific bucket on Minio.
     *
     * @param bucket The bucket name which we will download the file
     * @param filename The name of the csv file
     */
    public String downloadFile(String bucket, String filename) {
        String newFilename = String.format("files/%s_%s", UUID.randomUUID(), filename);
        downloadFile(bucket, filename, newFilename);
        return newFilename;
    }

    /**
     * Download a csv file from a specific bucket on Minio.
     *
     * @param bucket The bucket name which we will download the file
     * @param filename The name of the csv file
     */
    public void downloadFile(String bucket, String filename, String outputFilename) {
        try {
            MinioClient minioClient = minioConnector(bucket);
            DownloadObjectArgs args =
                    DownloadObjectArgs.builder()
                            .bucket(bucket)
                            .object(filename)
                            .filename(outputFilename)
                            .build();
            minioClient.downloadObject(args);
            logger.info("file is downloaded inside the container");
        } catch (ErrorResponseException
                 | InsufficientDataException
                 | InternalException
                 | ServerException
                 | XmlParserException
                 | InvalidResponseException
                 | InvalidKeyException
                 | IOException
                 | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void deleteFileClient(String bucket, List<DeleteObject> objects) {
        try {
            MinioClient minioClient = minioConnector(bucket);

            Iterable<Result<DeleteError>> results =
                    minioClient.removeObjects(
                            RemoveObjectsArgs.builder().bucket(bucket).objects(objects).build());
            for (Result<DeleteError> result : results) {
                DeleteError error = result.get();
                logger.error("Error in deleting object " + error.objectName() + "; " + error.message());
            }
        } catch (ErrorResponseException
                 | InsufficientDataException
                 | InternalException
                 | ServerException
                 | XmlParserException
                 | InvalidResponseException
                 | InvalidKeyException
                 | IOException
                 | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a minio client without connecting to a specific bucket
     *
     * @return Minio Client
     * @throws IOException
     */
    public MinioClient minioRawConnector() {
        return MinioClient.builder().endpoint(minioServerUrl).credentials(accessKey, secretKey).build();
    }

    public MinioClient minioConnector(String bucket) throws IOException {

        MinioClient minioClient = null;

        try {

            minioClient = minioRawConnector();

            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (isExist) {
                logger.info("Bucket already exists.");
            } else {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            logger.error("Error occurred: " + e);
        }
        return minioClient;
    }
}
