package com.dream95.tdengineparser;


import org.antlr.v4.runtime.CharStreams;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class TDengineLexerTest {

    private static final Logger log = LoggerFactory.getLogger(TDengineLexerTest.class);

    @Test
    public void test() {

        File[] tests = new File("src/test/resources/examples").listFiles(file -> file.isFile() && file.getName().endsWith(".sql"));

        Assert.assertNotNull(tests);

        for (File test : tests) {
            try {
                TDengineLexer lexer = new TDengineLexer(CharStreams.fromFileName(test.getAbsolutePath()));
                assert !lexer.getAllTokens().isEmpty();
            }
            catch (Exception e) {
                log.error("{}", e.getMessage());
                return;
            }
        }
    }



}
