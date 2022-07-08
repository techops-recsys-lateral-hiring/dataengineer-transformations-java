package com.thoughtworks.de.citibike;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class CitibikeTransformer {
  static Logger log = LogManager.getRootLogger();

  /**
   * Entry point method.
   * @args org.jetbrains.annotations.NotNull
   */
  public static void main(String[] args) {
    log.setLevel(Level.INFO);
    SparkSession spark = SparkSession.builder().appName("Citibike Transformer").getOrCreate();
    log.info("Citibike Transformer Application Initialized: " + spark.sparkContext().appName());

    if (args.length < 2) {
      log.warn("Input source and output path are required");
      System.exit(1);
    }

    final String ingestPath = args[0];
    final String transformationPath = args[1];
    run(spark, ingestPath, transformationPath);

    log.info("Citibike Application Done: " + spark.sparkContext().appName());
    spark.stop();
  }

  /**
   * Run the Spark transformation.
   * @param sparkSession this is spark session
   * @param ingestPath ingestion path
   * @param outputPath output of transformation
   */
  public static void run(SparkSession sparkSession, String ingestPath, String outputPath) {
    Dataset<Row> df = sparkSession.read()
        .parquet(ingestPath);

    Dataset<Row> computeDistances = computeDistances(df);

    computeDistances.show(false);

    computeDistances.write().parquet(outputPath);
  }

  private static Dataset<Row> computeDistances(Dataset<Row> df) {
    final Double MetersPerFoot = 0.3048;
    final Integer FeetPerMile = 5280;

    final Double EarthRadiusInM = 6371e3;
    final Double MetersPerMile = MetersPerFoot * FeetPerMile;
    return df;
  }
}
