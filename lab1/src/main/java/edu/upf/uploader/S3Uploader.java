package edu.upf.uploader;

import java.util.List;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;

/**
 * The S3Uploader class implements the Uploader interface and provides functionality to upload files to an Amazon S3 bucket.
 */

public class S3Uploader implements Uploader {
    
    private String bucketName;
    private String prefix;
    private AmazonS3 credentialClient;

    /**
     * Constructs a new S3Uploader with the specified bucket name, prefix, and AWS credentials profile name.
     *
     * @param bucketName            the name of the Amazon S3 bucket
     * @param prefix                the prefix to add to the file names when uploading
     * @param credentialsProfileName the name of the AWS credentials profile to use
     */
    public S3Uploader(String bucketName, String prefix, String credentialsProfileName){
        
        this.bucketName = bucketName;
        this.prefix = prefix;
        // Initialize the Amazon S3 client with the specified credentials profile
        AWSCredentialsProvider credentials = new ProfileCredentialsProvider(credentialsProfileName);
        this.credentialClient = AmazonS3ClientBuilder.standard().withCredentials(credentials).build();
    }
   
    /**
     * Uploads the list of files to the Amazon S3 bucket.
     *
     * @param files the list of file paths to upload
     */
    @Override
    public void upload(List<String> files) {
        
        // Iterate through the list of files and upload each one to the S3 bucket
        for (String file: files){
            
            try {
                this.credentialClient.putObject(new PutObjectRequest(this.bucketName, prefix + file, new File(file)));
                System.out.println("Output file successfully uploaded to bucket: " + bucketName);
            } 
            
            catch (AmazonServiceException e){
                // Print an error message if an exception occurs during the upload process
                System.err.println("Error uploading file " + file + ": " + e.getMessage());
            }
        }
    }
}
