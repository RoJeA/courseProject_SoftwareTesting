package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Base32Test {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void encodeBase32() throws Exception {
        String encode_pos = Base32.encodeBase32(75324, 4);
        assertEquals("29jw", encode_pos);

        String encode_neg = Base32.encodeBase32(-122,4);
        assertEquals("-003u",encode_neg);
    }
    @Test
    public void decodeBase32()throws Exception{
        long decode_W = Base32.decodeBase32("w");
        assertEquals(28, decode_W);

        long decode_neg = Base32.decodeBase32("-j");
        assertEquals(-17,decode_neg);
    }

    @Test
    public void getCharIndexException(){
        try{
            int getCharIndex_exp = Base32.getCharIndex('-');
//            fail();
        }
        catch (Exception e){
            //test message
            assertThat(e.getMessage(), is("not a base32 character: -"));
        }

    }



}