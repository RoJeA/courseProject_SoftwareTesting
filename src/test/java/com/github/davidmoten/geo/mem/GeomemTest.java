package com.github.davidmoten.geo.mem;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GeomemTest {

    private static final double topLeftLat = -5;
    private static final double topLeftLong = 100;
    private static final double bottomRightLat = -90;
    private static final double bottomRightLong = 170;
    private static final double PRECISION = 0.00001;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void find() {
        Geomem<String, String> g = new Geomem<String, String>();
        g.add(-90, 150, 500, "X", "1");
        List<Info<String, String>> list = Lists.newArrayList(g.find(topLeftLat,
                topLeftLong, bottomRightLat, bottomRightLong, 0, 1000));
        assertEquals(1, list.size());
        assertEquals(-90, list.get(0).lat(), PRECISION);
        assertEquals(150, list.get(0).lon(), PRECISION);
        assertEquals(500L, list.get(0).time());
        assertEquals("X", list.get(0).value());
        assertEquals("1", list.get(0).id().get());
    }

    private Info<String, String> createInfo(double lat, double lon) {
        return new Info<String, String>(lat, lon, 100, "B", Optional.of("B"));
    }

    @Test
    public void createRegionFilter() {
        Geomem<String, String> g = new Geomem<String, String>();
        Predicate<Info<String, String>> predicate = g.createRegionFilter(topLeftLat, topLeftLong, bottomRightLat, bottomRightLong);
        {
            // inside
            assertTrue(predicate.apply(createInfo(topLeftLat - 1, topLeftLong + 1)));
            // outside north
            assertFalse(predicate.apply(createInfo(topLeftLat + 1, topLeftLong + 1)));
            // outside west
            assertFalse(predicate.apply(createInfo(topLeftLat - 1, topLeftLong - 1)));
            // outside east
            assertFalse(predicate.apply(createInfo(topLeftLat - 1, bottomRightLong + 1)));
            // outside south
            assertFalse(predicate.apply(createInfo(bottomRightLat - 1, bottomRightLong - 1)));
        }
    }

    @Test
    public void add() {
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testAdd1() {
    }

    @Test
    public void testAdd2() {
    }
}