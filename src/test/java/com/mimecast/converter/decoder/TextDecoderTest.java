package com.mimecast.converter.decoder;

import com.mimecast.TestUtil;
import com.mimecast.converter.Decoder;
import com.mimecast.converter.decoder.exception.ReadException;
import com.mimecast.model.World;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;

public class TextDecoderTest {
    private Decoder rightDecoder;
    private Decoder errorDecoder;

    @Before
    public void setUp() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        rightDecoder = new TextDecoder(classLoader.getResource("rightMap.txt").getFile());
        errorDecoder = new TextDecoder(classLoader.getResource("wrongMap.txt").getFile());

    }

    @Test
    public void decodeTest() throws ReadException {
        World world = rightDecoder.decode();
        assertEquals(4, world.getCities().size());
    }


    @Test(expected = ReadException.class)
    public void decoderThrowsExceptionTest() throws ReadException {
        errorDecoder.decode();
    }
}