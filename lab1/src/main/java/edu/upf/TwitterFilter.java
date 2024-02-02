package edu.upf;

import edu.upf.filter.FileLanguageFilter;
import edu.upf.uploader.S3Uploader;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TwitterFilter {
    public static void main( String[] args ) throws IOException {

        // start of counting time.
        Instant start_time = Instant.now();

        List<String> argsList = Arrays.asList(args);
        String language = argsList.get(0);
        String outputFile = argsList.get(1);
        String bucket = argsList.get(2);
        System.out.println("Language: " + language + ". Output file: " + outputFile + ". Destination bucket: " + bucket);
        for(String inputFile: argsList.subList(3, argsList.size())) {
            System.out.println("Processing: " + inputFile);
            final FileLanguageFilter filter = new FileLanguageFilter(inputFile, outputFile);
            filter.filterLanguage(language);
        }

        final S3Uploader uploader = new S3Uploader(bucket, "prefix", "upf");
        uploader.upload(Arrays.asList(outputFile));

        // end of counting time.
        Instant finish_time = Instant.now();

        // calculation of the difference between two instants.
        long performance_time = Duration.between(start_time, finish_time).toMillis();
        
        // message of the performance time.
        System.out.println("Performance time : "+ performance_time +" milliseconds.");
    }
}
