package com.dream95.tdengineparser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TDengineParserErrorTest {

    @Test
    public void testSelectError() throws IOException {
        File test = new File("src/test/resources/errors/select.sql");
        TDengineLexer lexer = new TDengineLexer(CharStreams.fromFileName(test.getAbsolutePath()));
        TDengineParser parser = new TDengineParser(new CommonTokenStream(lexer));

        TDErrorListener listener = new TDErrorListener();
        parser.addErrorListener(listener);
        parser.sqlCommandList();

        assert listener.hasError;
    }


    @Test
    public void testInsertError() throws IOException {
        File test = new File("src/test/resources/errors/insert.sql");
        TDengineLexer lexer = new TDengineLexer(CharStreams.fromFileName(test.getAbsolutePath()));
        TDengineParser parser = new TDengineParser(new CommonTokenStream(lexer));

        TDErrorListener listener = new TDErrorListener();
        parser.addErrorListener(listener);
        parser.sqlCommandList();

        assert listener.hasError;
    }



}
