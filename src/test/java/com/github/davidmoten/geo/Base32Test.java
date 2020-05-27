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

        String encode_iLessThan32 = Base32.encodeBase32(5,0);
        assertEquals("5",encode_iLessThan32);

        String encode_iMoreThanNeg32 = Base32.encodeBase32(-5,0);
        assertEquals("-5",encode_iMoreThanNeg32);

        String encode_Base32 = Base32.encodeBase32((long) 32.0);
        assertEquals("000000000010",encode_Base32);

        String encode_pos_0 = Base32.encodeBase32(75324,0);
        assertEquals("29jw",encode_pos_0);

        String encode_pos_neg = Base32.encodeBase32(75324,-5);
        assertEquals("29jw",encode_pos_neg);

        String encode_0_pos = Base32.encodeBase32(0, 5);
        assertEquals("00000",encode_0_pos);

        String encode_0_0 = Base32.encodeBase32(0, 0);
        assertEquals("0",encode_0_0);

        String encode_0_neg = Base32.encodeBase32(0, -5);
        assertEquals("0",encode_0_neg);

        String encode_neg_0 = Base32.encodeBase32(-122,0);
        assertEquals("-3u",encode_neg_0);

        String encode_neg_neg = Base32.encodeBase32(-122,-3);
        assertEquals("-3u", encode_neg_neg);

        String encode_Base32_0 = Base32.encodeBase32((long) 0);
        assertEquals("000000000000",encode_Base32_0);

        String encode_Base32_neg = Base32.encodeBase32((long) -5);
        assertEquals("-000000000005",encode_Base32_neg);
    }
    @Test
    public void decodeBase32()throws Exception{
        long decode_W = Base32.decodeBase32("w");
        assertEquals(28, decode_W);

        long decode_neg = Base32.decodeBase32("-j");
        assertEquals(-17,decode_neg);

        long decode_negNull = Base32.decodeBase32("-");
        assertEquals(-0,decode_negNull);

        long decode_posNull = Base32.decodeBase32("");
        assertEquals(0,decode_posNull);
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

    @Test
    public void getCharIndex(){
        int getCharIndex = Base32.getCharIndex('1');
        assertEquals(1,getCharIndex);
    }



}