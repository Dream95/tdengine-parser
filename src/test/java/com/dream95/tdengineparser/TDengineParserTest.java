package com.dream95.tdengineparser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class TDengineParserTest {

    @Test
    public void test() throws  Exception{
        File[] tests = new File("src/test/resources/examples").listFiles(file -> file.isFile() && file.getName().endsWith(".sql"));

        Assert.assertNotNull(tests);

        for (File test : tests) {
            TDengineLexer lexer = new TDengineLexer(CharStreams.fromFileName(test.getAbsolutePath()));
            TDengineParser parser = new TDengineParser(new CommonTokenStream(lexer));
            TDErrorListener listener = new TDErrorListener();
            parser.addErrorListener(listener);
            parser.sqlCommandList();

            assert !listener.hasError;
        }
    }


}
