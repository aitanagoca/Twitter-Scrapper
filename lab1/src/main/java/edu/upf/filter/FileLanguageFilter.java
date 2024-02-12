package edu.upf.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import edu.upf.parser.SimplifiedTweet;

/**
 * The FileLanguageFilter class implements the LanguageFilter interface and provides functionality to filter tweets
 * based on language from an input file and write the filtered tweets to an output file.
 */

public class FileLanguageFilter implements LanguageFilter {
    
    private final String inputFile;
    private final String outputFile;

    /**
     * Constructs a new FileLanguageFilter with the specified input and output file paths.
     *
     * @param inputFile  the path of the input file containing tweets
     * @param outputFile the path of the output file to write the filtered tweets
     */
    public FileLanguageFilter(String inputFile, String outputFile) {
        
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    /**
     * Filters tweets based on the specified language from the input file and writes the filtered tweets to the output file.
     *
     * @param language the language to filter the tweets
     * @throws Exception if an error occurs while filtering or writing the tweets
     */
    @Override
    public void filterLanguage(String language) throws Exception {
        
        int totalTweetsProcessed = 0;
        int tweetsWritten = 0;
        
        // Try-with-resources to automatically close the reader and writer
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            
            String line;
            
            while ((line = reader.readLine()) != null) {
                
                totalTweetsProcessed++;

                // Parse the line into a SimplifiedTweet object
                Optional<SimplifiedTweet> optionalTweet = SimplifiedTweet.fromJson(line);
                
                // If the parsing is successful and the tweet's language matches the specified language
                if (optionalTweet.isPresent()) {
                    
                    SimplifiedTweet tweet = optionalTweet.get();
                    
                    if (tweet.getLanguage().equals(language)) {
                        
                        // Write the tweet to the output file
                        writer.write(tweet.toString());
                        writer.newLine();
                        tweetsWritten++;
                    }
                }
            }

            System.out.println("Number of processed tweets: " + totalTweetsProcessed);
            System.out.println("Number of written tweets: " + tweetsWritten + " (inputFile: " + inputFile + " - language: " + language + ")");
        } 
        
        catch (IOException e) {
            // Print the error message and stack trace if an IOException occurs
            System.out.println("Something went wrong. This is the error:");
            e.printStackTrace();
        }
    }
}
