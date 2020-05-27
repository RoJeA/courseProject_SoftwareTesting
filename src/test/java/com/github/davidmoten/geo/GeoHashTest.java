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
        String adjacentHash = geoHash.adjacentHash(hash, direction,1);
        assertEquals(geoHash.adjacentHash("11w", Direction.TOP, 1), adjacentHash);

        adjacentHash = geoHash.adjacentHash(hash, direction, 5);
        assertEquals("14y", adjacentHash);

        adjacentHash = geoHash.adjacentHash("zzz", Direction.TOP,1);
        assertEquals("gzz",adjacentHash);

        adjacentHash = geoHash.adjacentHash("145",Direction.BOTTOM,1);
        assertEquals("11g",adjacentHash);

        adjacentHash = geoHash.adjacentHash("000",Direction.LEFT,1);
        assertEquals("pbp",adjacentHash);

        adjacentHash = geoHash.adjacentHash("ppp",Direction.RIGHT,1);
        assertEquals("pr0",adjacentHash);

        try{
            adjacentHash = geoHash.adjacentHash(null, direction,1);
        }
        catch (Exception e){
            //test message
            assertThat(e.getMessage(), is("hash must be non-null"));
        }
        try{
            adjacentHash = geoHash.adjacentHash("", direction,1);
        }
        catch (Exception e){
            //test message
            assertThat(e.getMessage(), is("adjacent has no meaning for a zero length hash that covers the whole world"));
        }

        adjacentHash = geoHash.adjacentHash(hash, direction,-1);
        assertEquals(geoHash.adjacentHash("11w", Direction.TOP, -1), adjacentHash);

        adjacentHash = geoHash.adjacentHash(hash, direction, -5);
        assertEquals("10q", adjacentHash);

        adjacentHash = geoHash.adjacentHash("zzz", Direction.TOP,-1);
        assertEquals("zzx",adjacentHash);

        adjacentHash = geoHash.adjacentHash("145",Direction.BOTTOM,-1);
        assertEquals("147",adjacentHash);

        adjacentHash = geoHash.adjacentHash("000",Direction.LEFT,-1);
        assertEquals("001",adjacentHash);

        adjacentHash = geoHash.adjacentHash("ppp",Direction.RIGHT,-1);
        assertEquals("ppn",adjacentHash);


        try{
            adjacentHash = geoHash.adjacentHash(null, direction,-1);
        }
        catch (Exception e){
            //test message
            assertThat(e.getMessage(), is("hash must be non-null"));
        }
        try{
            adjacentHash = geoHash.adjacentHash("", direction,-1);
        }
        catch (Exception e){
            //test message
            assertThat(e.getMessage(), is("adjacent has no meaning for a zero length hash that covers the whole world"));
        }
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
    public void testAdjacentHashAtBorder(){
        String adjacentHash = geoHash.adjacentHash("000", Direction.BOTTOM);
        assertEquals("h00",adjacentHash);

        adjacentHash = geoHash.adjacentHash("zzz", Direction.TOP);
        assertEquals("gzz",adjacentHash);

        adjacentHash = geoHash.adjacentHash("000",Direction.LEFT);
        assertEquals("pbp",adjacentHash);

        adjacentHash = geoHash.adjacentHash("rfr",Direction.RIGHT);
        assertEquals("242",adjacentHash);

        adjacentHash = geoHash.adjacentHash("11w",Direction.TOP);
        assertEquals("11y",adjacentHash);

        adjacentHash = geoHash.adjacentHash("11w",Direction.BOTTOM);
        assertEquals("11q",adjacentHash);

        adjacentHash = geoHash.adjacentHash("11w",Direction.LEFT);
        assertEquals("11t",adjacentHash);

        adjacentHash = geoHash.adjacentHash("11w",Direction.RIGHT);
        assertEquals("11x",adjacentHash);
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
        String encodeHash = geoHash.encodeHash(-50,0,5);
        assertEquals("hp058", encodeHash);

        try{
            encodeHash = geoHash.encodeHash(-50,-100,0);
        }catch (Exception e){
            assertThat(e.getMessage(), is("length must be greater than zero"));
        }

        try{
            encodeHash = geoHash.encodeHash(-100,500,5);
        }catch (Exception e){
            assertThat(e.getMessage(), is("latitude must be between -90 and 90 inclusive"));
        }

        try{
            encodeHash = geoHash.encodeHash(-100,45,0);
        }catch (Exception e){
            assertThat(e.getMessage(), is("length must be greater than zero"));
        }

        try{
            encodeHash = geoHash.encodeHash(120,45,3);
        }catch (Exception e){
            assertThat(e.getMessage(), is("latitude must be between -90 and 90 inclusive"));
        }

        try{
            encodeHash = geoHash.encodeHash(120,45,-5);
        }catch (Exception e){
            assertThat(e.getMessage(), is("length must be greater than zero"));
        }
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
        try{
            long li = 0;
            String si = geoHash.fromLongToString(li);
            assertTrue(false);
        }
        catch (Exception e){
            assertThat(e.getMessage(), is("invalid long geohash 0"));
        }

        long l = geoHash.encodeHashToLong(-50,0,3);
        String s = geoHash.fromLongToString(l);
        assertEquals("hp0", s);

        long l1 = 1;
        String s1 = geoHash.fromLongToString(l1);
        assertEquals("0", s1);
    }

    @Test
    public void encodeHashToLong() {
        long l = geoHash.encodeHashToLong(0,0,0);
        assertEquals(0,l);

        l = geoHash.encodeHashToLong(55,55,5);
        assertEquals(-2846829668114366459L, l);

        l = geoHash.encodeHashToLong(55,-55,5);
        assertEquals(8274428630898049029L,l);
    }

    @Test
    public void decodeHash() {
    }

    @Test
    public void hashLengthToCoverBoundingBox() {
        int lengthOfHash = geoHash.hashLengthToCoverBoundingBox(25,25,25,25);
        assertEquals(12,lengthOfHash);

        lengthOfHash = geoHash.hashLengthToCoverBoundingBox(25,-25,25,25);
        assertEquals(0,lengthOfHash);

        lengthOfHash = geoHash.hashLengthToCoverBoundingBox(25,25,25,-25);
        assertEquals(0,lengthOfHash);

        lengthOfHash = geoHash.hashLengthToCoverBoundingBox(25,-25,25,-25);
        assertEquals(12,lengthOfHash);
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