package com.mimecast.converter.encoder;

import com.mimecast.TestUtil;
import com.mimecast.converter.Encoder;
import com.mimecast.converter.encoder.exception.WriteException;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TextEncoderTest {

    private Encoder encoder;
    ClassLoader classLoader = getClass().getClassLoader();

    @Before
    public void setUp() throws IOException {
        File file = new File(classLoader.getResource("expected_output.txt").getFile());
        System.out.println("strPath: "+file.getParent());
        encoder = new TextEncoder(file.getParent()+"/output.txt");
    }

    @Test
    public void encode() throws Exception {
        encoder.encode(TestUtil.generateTestWorld().getCities(),new ArrayList<>());
       // assertTrue(new File(classLoader.getResource("output.txt").getFile()).exists());
        File result = new File(classLoader.getResource("expected_output.txt").getFile());
        File expected = new File(classLoader.getResource("output.txt").getFile());
        assertTrue(FileUtils.contentEquals(expected, result));
    }

    @After
    public void tearDown() {
        File file = new File(classLoader.getResource("output.txt").getFile());
        if (file.exists()) {
            file.delete();
        }
    }
}