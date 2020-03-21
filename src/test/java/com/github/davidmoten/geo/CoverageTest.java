package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CoverageTest {
    Set<String> getHashSets;

    @Before
    public void setUp() throws Exception {
        getHashSets= new HashSet<String>();
    }

    @After
    public void tearDown() throws Exception {
        getHashSets=null;
    }
    @Test
    public void testCoverage(){
        getHashSets.add("22");
        getHashSets.add("33");
        getHashSets.add("44");
        Coverage coverage = new Coverage(getHashSets,2.5);
        assertEquals(getHashSets,coverage.getHashes());
        assertEquals(2.5,coverage.getRatio(),0.001);

        //CoverageLongs Test
        long[] hashes = new long[]{22,33,44};
        CoverageLongs coverageLongs = new CoverageLongs(hashes,3,2.5);
        Coverage coverage_long = new Coverage(coverageLongs);
        assertEquals(1, coverage_long.getHashLength());
        assertEquals(coverage.getRatio(), coverage_long.getRatio(),0.01);

    }
    @Test
    public void getHashes() {
        getHashSets.add("aaa");
        getHashSets.add("bbb");
        Coverage getHashCoverage = new Coverage(getHashSets,5.5);
        assertEquals(getHashSets,getHashCoverage.getHashes());
    }

    @Test
    public void getRatio() {
        double ratio = 0.005;
        Coverage getRatioCoverage = new Coverage(getHashSets,ratio);
        assertEquals(ratio,getRatioCoverage.getRatio(),0.00001);
    }

    @Test
    public void getHashLength() {
        Coverage getHashLengthCoverage = new Coverage(getHashSets,2.4);
        assertEquals(0,getHashLengthCoverage.getHashLength());
        getHashSets.add("something");
        assertEquals(9,getHashLengthCoverage.getHashLength());
    }

    @Test
    public void testToString() {
        Coverage testToStringCoverage = new Coverage(getHashSets,6.66);
        assertEquals("Coverage [hashes=[], ratio=6.66]",testToStringCoverage.toString());
    }
}