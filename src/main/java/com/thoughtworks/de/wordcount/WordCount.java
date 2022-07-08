package com.thoughtworks.de.wordcount;

import java.time.LocalDateTime;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

public class WordCount {
  static Logger log = LogManager.getRootLogger();

  /**
   * Entry point for execution.
   * @param args arguments input and output path
   */
  public static void main(String[] args) {
    log.setLevel(Level.INFO);

    SparkSession spark = SparkSession.builder().appName("Word Count").getOrCreate();
    log.info("Application Initialized: " + spark.sparkContext().appName());

    final String inputPath = (args.length > 0) ? args[0] : "./src/test/resources/data/words.txt";
    final String outputPath = (args.length > 1) ? args[1] : "./target/test-" + LocalDateTime.now();
    run(spark, inputPath, outputPath);

    log.info("Application Done: " + spark.sparkContext().appName());
    spark.stop();
  }

  /**
   * Run the spark application.
   * @param spark : spark session
   * @param inputPath : input path
   * @param outputPath : output path
   */
  public static void run(SparkSession spark, String inputPath, String outputPath) {
    log.info("Reading text file from: " + inputPath);
    log.info("Writing csv to directory: " + outputPath);

    Dataset<String> lines = spark.read().text(inputPath).as(Encoders.STRING());
    Dataset<String> splitWords = WordCountUtils.splitWords(lines);
    Dataset<String> wordCounts = WordCountUtils.countByWord(splitWords);
    wordCounts.write()
        .csv(outputPath);

  }
}

