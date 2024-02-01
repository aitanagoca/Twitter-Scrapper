package edu.upf.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import edu.upf.parser.SimplifiedTweet;

public class FileLanguageFilter {
    final String inputFile;
    final String outputFile;

    public FileLanguageFilter(String input, String output){
        this.inputFile = input;
        this.outputFile = output;
    }

    public void filterLanguage(String language) throws Exception{

        // files
        FileReader reader_f = new FileReader(this.inputFile);
        FileWriter writer_f = new FileWriter(this.outputFile);

        // we create buffers to save memory to able to read and write.
        BufferedWriter writing_b = new BufferedWriter(writer_f); 
        BufferedReader reading_b = new BufferedReader(reader_f);

        // counter of tweers
        int twitter_counter = 0;
            
        // if the tweet is not "complete", we pass on to the following tweet.
        try {
            Scanner scan = new Scanner(new File(inputFile));

            // read while there is a next line
            while (scan.hasNextLine()) {
                Optional <SimplifiedTweet> aux_tweet = SimplifiedTweet.fromJson(scan.nextLine());
                if(aux_tweet.isPresent()){
                    SimplifiedTweet tweet = aux_tweet.get();
                    if(tweet.getLanguage().equals(language)){
                        // write the tweet
                        writing_b.write(tweet.toString());
                        // count the new tweet
                        twitter_counter = twitter_counter+1;
                    }
                }
            }
            scan.close();

            // message of success, informing of number of tweets, unput and languange.
            System.out.println("Number of tweets: " + twitter_counter + " in file "+ inputFile +" using language: "+ language);
        }
        
        catch(IOException error) {
            // message of error.
            System.out.println("Something went wrong. This is the error:");
            error.printStackTrace();


        }
        
        finally{
            // closing of buffers.
            writing_b.close();
            reading_b.close();
            // message of success, closing the buffers.
            System.out.println("We close the buffer correctly");
        }
    }
}
