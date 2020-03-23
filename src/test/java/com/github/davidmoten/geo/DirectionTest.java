package com.github.davidmoten.geo;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    @Test
    public void opposite() {
        Direction bottom = Direction.BOTTOM;
        assertEquals(Direction.TOP, bottom.opposite());

        Direction top = Direction.TOP;
        assertEquals(Direction.BOTTOM, top.opposite());

        Direction left = Direction.LEFT;
        assertEquals(Direction.RIGHT, left.opposite());

        Direction right = Direction.RIGHT;
        assertEquals(Direction.LEFT, right.opposite());
    }
}