package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LatLongTest {
    double lat;
    double lon;
    LatLong latLong;
    @Before
    public void setUp() throws Exception {
        lat = 25.5;
        lon = 20.5;
        latLong = new LatLong(lat,lon);
    }

    @After
    public void tearDown() throws Exception {
        lat = 0;
        lon = 0;
        latLong = null;

    }

    @Test
    public void getLat() {
        double getLat = latLong.getLat();
        assertEquals(25.5,getLat,0.0000);
    }

    @Test
    public void getLon() {
        double getLon = latLong.getLon();
        assertEquals(20.5,getLon,0.0000);
    }

    @Test
    public void add() {
        LatLong expect = new LatLong(30.5,25.5);
        LatLong added = latLong.add(3.3,5,5);
        assertEquals(expect.getLat(),added.getLat(),0.0000);
        assertEquals(expect.getLon(),added.getLon(),0.0000);
    }

    @Test
    public void testToString() {
        String s = latLong.toString();
        assertEquals("LatLong [lat=25.5, lon=20.5]", s);

    }
}