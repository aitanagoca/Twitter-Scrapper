package edu.upf;

import edu.upf.filter.FileLanguageFilter;
import edu.upf.uploader.S3Uploader;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * The TwitterFilter class is the entry point for the Twitter filtering application.
 * It reads command-line arguments, filters tweets based on language, writes the filtered tweets to a file,
 * and uploads the file to an Amazon S3 bucket.
 */

public class TwitterFilter {
    public static void main( String[] args ) throws Exception {
        
        // Record the start time of the application
        Instant startTime = Instant.now();

        // Convert command-line arguments to a list for easier access
        List<String> argsList = Arrays.asList(args);
        String language = argsList.get(0); // Extract language from arguments
        String outputFile = argsList.get(1); // Extract output file path from arguments
        String bucket = argsList.get(2); // Extract destination bucket from arguments
       
        // Display by terminal the configuration information
        System.out.println("Language: " + language + ". Output file: " + outputFile + ". Destination bucket: " + bucket);
        
        // Process each input file specified in the arguments
        for(String inputFile: argsList.subList(3, argsList.size())) {
            System.out.println("Processing: " + inputFile);
            // Create a FileLanguageFilter instance and filter tweets based on language
            final FileLanguageFilter filter = new FileLanguageFilter(inputFile, outputFile);
            filter.filterLanguage(language);
        }

        // Upload the output file to the specified S3 bucket
        final S3Uploader uploader = new S3Uploader(bucket, "output_", "default");
        uploader.upload(Arrays.asList(outputFile));
        
        // Record the end time of the application
        Instant endTime = Instant.now();
        // Calculate and print the performance time of the application
        long performanceTime = Duration.between(startTime, endTime).toMillis();
        System.out.println("Performance time : " + performanceTime + " milliseconds.");
    }
}
