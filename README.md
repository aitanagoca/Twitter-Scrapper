# Twitter Scrapper

## Group Information 

Group: (P102, grup 05)

Aitana González (U186651, Bucket: lsds2024.lab1.output.u186651)

Jordi Alfonso (U111792, Bucket: lsds2024.lab1.output.u111792) 

Arnau Royo (U172499, Bucket: lsds2024.lab1.output.u172499)

## (For group mates) - How to execute

1. Log in to AWS Academy (your account) and press "Start Lab".

2. Be sure that you have created the bucket as indicated in the statement (lsds2024.lab1.output.uxxxxxx) and that when creating it you have activated "ACLs enabled" and the option to make the bucket public.

3. Terminal: aws configure (introduce the two first credentials).

4. Terminal: aws configure set aws_session_token < (your) aws_session_token >

(!! Make sure at this point that you have the tweets tar file downloaded (you can get it from the location given in the statement) and it is in the lab1 folder !!)

5. Terminal: brew install openjdk@11 (only the first time!!)

6. Mvn: mvn clean

7. Mvn: mvn validate

8. Mvn: mvn package

9. Terminal (from the lab1 folder): jar tvf target/lab1-1.0-SNAPSHOT.jar

10. Mvn: mvn compile

11. Mvn: java -cp target/lab1-1.0-SNAPSHOT.jar edu.upf.TwitterFilter < language > < name_outputFile > < lsds2024.lab1.output.uxxxxxx > < name_twittterTarFile >

## Benchmark

### Number of output tweets:

    Aitana
        Català: 4583 tweets
        Español: 509433 tweets
        English: 446601 tweets
    
    Jordi
        Català:  4583 tweets
        Español:  509433 tweets
        English: 446601 tweets

    Arnau
        Català:  4583 tweets
        Español:  509433 tweets
        English: 446601 tweets

### Execution times:

    Aitana
        Català: 133505 ms
        Español: 168216 ms
        English: 149361 ms

    Jordi
        Català:  99879 ms
        Español:  158895 ms
        English: 133520 ms

    Arnau
        Català:  403846 ms
        Español:  431285 ms
        English:  439621 ms

--> We didn't run into any problems calculating how long each run takes to perform the filtering.
