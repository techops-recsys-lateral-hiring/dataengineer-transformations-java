package com.thoughtworks.de.wordcount;

import java.io.Serializable;
import org.apache.spark.sql.SparkSession;
import org.junit.After;
import org.junit.Before;


public class DefaultTestWithSpark implements Serializable {
  protected transient SparkSession spark;

  /**
   * Test setup.
   */
  @Before
  public void setUp() {
    spark = SparkSession.builder()
        .master("local[*]")
        .appName("testing")
        .getOrCreate();
  }

  @After
  public void tearDown() {
    spark.stop();
    spark = null;
  }


}