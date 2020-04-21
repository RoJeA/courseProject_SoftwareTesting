package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class CoverageLongsTest {
    long[] hash;
    int count = 2;

    @Before
    public void setUp() throws Exception {
        hash = new long[]{33,22};
    }

    @After
    public void tearDown() throws Exception {
        hash = null;
    }

    @Test
    public void getHashes() {
        CoverageLongs coverageLongsGetHashes = new CoverageLongs(hash, count, 4.5);
        assertArrayEquals(hash,coverageLongsGetHashes.getHashes());
    }

    @Test
    public void getRatio() {
        CoverageLongs coverageLongsGetRatio = new CoverageLongs(hash, count, 3.0);
        assertEquals(3.0,coverageLongsGetRatio.getRatio(),0.0001);
    }

    @Test
    public void getHashLength() {  //compare[0] with 0000 1111(0x0f)
        //length = 0
        long[] hashZero = new long[]{};
        CoverageLongs coverageLongsGetZeroLength = new CoverageLongs(hashZero,0,3.3);
        assertEquals(0,coverageLongsGetZeroLength.getHashLength());

        //length !=0
        CoverageLongs coverageLongsGetHashLength = new CoverageLongs(hash,count,2.2);
        assertEquals(1,coverageLongsGetHashLength.getHashLength());
    }

    @Test
    public void testToString() {
        CoverageLongs coverageLongsToString = new CoverageLongs(hash, count,5.5);
//        assertEquals("Coverage [hashes=33,22, ratio=5.5]", coverageLongsToString.toString());
    }

    @Test
    public void getCount() {
        CoverageLongs coverageLongsGetCount = new CoverageLongs(hash, count,3.00);
        assertEquals(2, coverageLongsGetCount.getCount());
    }
}