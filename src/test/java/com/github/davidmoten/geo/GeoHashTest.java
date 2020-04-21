package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GeoHashTest {
    String hash;
    Direction direction;
    GeoHash geoHash;
    @Before
    public void setUp() throws Exception {
        hash = "11w";
        direction = Direction.TOP;
    }

    @After
    public void tearDown() throws Exception {
        hash = null;
        direction = null;
    }

    @Test
    public void adjacentHash() {
        String adjacentHash = geoHash.adjacentHash(hash, direction,-1);
        assertEquals(geoHash.adjacentHash("11w", Direction.BOTTOM, 1), adjacentHash);

        adjacentHash = geoHash.adjacentHash(hash, direction, 5);
        assertEquals("14y", adjacentHash);
    }

    @Test
    public void right() {
        assertEquals("11x", geoHash.right(hash));
    }

    @Test
    public void left() {
        assertEquals("11t", geoHash.left(hash));
    }

    @Test
    public void top() {
        assertEquals("11y", geoHash.top(hash));
    }

    @Test
    public void bottom() {
        assertEquals("11q", geoHash.bottom(hash));
    }

    @Test
    public void testAdjacentHash() {
        String adjacentHash = geoHash.adjacentHash(hash, direction);
        assertEquals("11y",adjacentHash);

        adjacentHash = geoHash.adjacentHash("zzz", Direction.TOP);
        assertEquals("gzz",adjacentHash);

        adjacentHash = geoHash.adjacentHash("145",Direction.BOTTOM);
        assertEquals("11g",adjacentHash);

        adjacentHash = geoHash.adjacentHash("000",Direction.LEFT);
        assertEquals("pbp",adjacentHash);

        adjacentHash = geoHash.adjacentHash("ppp",Direction.RIGHT);
        assertEquals("pr0",adjacentHash);

        //add ISP

        try{
            adjacentHash = geoHash.adjacentHash(null, direction);
        }
        catch (Exception e){
            //test message
            assertThat(e.getMessage(), is("hash must be non-null"));
        }
        try{
            adjacentHash = geoHash.adjacentHash("", direction);
        }
        catch (Exception e){
            //test message
            assertThat(e.getMessage(), is("adjacent has no meaning for a zero length hash that covers the whole world"));
        }


    }

    @Test
    public void neighbours() {
        List<String> neighbours = new ArrayList<String>();
        neighbours = geoHash.neighbours(hash);
        List<String> compare = new ArrayList<String>();
        compare.add("11t"); //left
        compare.add("11x"); //right
        compare.add("11y"); //top
        compare.add("11q"); //bottom
        compare.add("11v"); //left-top
        compare.add("11m"); //left-bottom
        compare.add("11z"); //right-top
        compare.add("11r"); //right-bottom
        assertEquals(compare, neighbours);
    }

    @Test
    public void encodeHash() {

    }

    @Test
    public void adjacentHashAtBorder(){

    }

    @Test
    public void testEncodeHash() {
    }

    @Test
    public void testEncodeHash1() {
    }

    @Test
    public void testEncodeHash2() {
    }

    @Test
    public void fromLongToString() {
    }

    @Test
    public void encodeHashToLong() {
    }

    @Test
    public void decodeHash() {
    }

    @Test
    public void hashLengthToCoverBoundingBox() {
    }

    @Test
    public void hashContains() {
    }

    @Test
    public void coverBoundingBox() {
    }

    @Test
    public void coverBoundingBoxMaxHashes() {
    }

    @Test
    public void testCoverBoundingBox() {
    }

    @Test
    public void coverBoundingBoxLongs() {
    }

    @Test
    public void heightDegrees() {
    }

    @Test
    public void widthDegrees() {
    }

    @Test
    public void gridAsString() {
    }

    @Test
    public void testGridAsString() {
    }

    @Test
    public void testGridAsString1() {
    }
}