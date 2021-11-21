package com.thoughtworks.de.wordcount;

import org.apache.spark.sql.SparkSession;
import org.junit.After;
import org.junit.Before;

import java.io.Serializable;


public class DefaultJavaTestWithSpark implements Serializable {
    protected transient SparkSession spark;

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