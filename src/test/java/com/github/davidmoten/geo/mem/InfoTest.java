package com.github.davidmoten.geo.mem;

import com.google.common.base.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.Line;

import static org.junit.Assert.*;

public class InfoTest {
    Optional<String> id;
    Info info;

    @Before
    public void setUp() throws Exception {
        String a = "a";
        id = Optional.of(a);
        info = new Info(25.5, 30.0, 10000, 555, id);
    }

    @After
    public void tearDown() throws Exception {
        info = null;
    }

    @Test
    public void id() {
        System.out.print(info.id());
        assertEquals(Optional.of("a"), info.id());

    }

    @Test
    public void lat() {
        assertEquals(25.5,info.lat(),0.001);
    }

    @Test
    public void lon() {
        assertEquals(30.0,info.lon(),0.001);
    }

    @Test
    public void time() {
        assertEquals(10000,info.time());
    }

    @Test
    public void value() {
        assertEquals(555,info.value());
    }

    @Test
    public void testToString() {
        assertEquals("Info [lat=25.5, lon=30.0, time=10000, value=555, id=" + Optional.of("a") + "]", info.toString());
    }
}