package com.dream95.tdengineparser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.BitSet;

public class TDengineLexerTest {

    private static final Logger log = LoggerFactory.getLogger(TDengineLexerTest.class);

    @Test
    public void test() throws  Exception{

        File[] tests = new File("src/test/resources/examples").listFiles(file -> file.isFile() && file.getName().endsWith(".sql"));

        Assert.assertNotNull(tests);

        for (File test : tests) {

            TDengineLexer lexer = new TDengineLexer(CharStreams.fromFileName(test.getAbsolutePath()));
            TDengineParser parser = new TDengineParser(new CommonTokenStream(lexer));
            ANTLRErrorListener listener = new ANTLRErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
                    throw new RuntimeException(e);
                }

                @Override
                public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {

                }

                @Override
                public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {

                }

                @Override
                public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {

                }
            };
            parser.addErrorListener(listener);
            parser.sqlCommandList();
        }
    }

    @Test
    public void testSelectError() throws IOException {
        File test = new File("src/test/resources/errors/select.sql");
        TDengineLexer lexer = new TDengineLexer(CharStreams.fromFileName(test.getAbsolutePath()));
        TDengineParser parser = new TDengineParser(new CommonTokenStream(lexer));

        final RecognitionException[] error = {null};
        ANTLRErrorListener listener = new ANTLRErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
                error[0] = e;
            }

            @Override
            public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {

            }

            @Override
            public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {

            }

            @Override
            public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {

            }
        };

        parser.addErrorListener(listener);
        parser.sqlCommandList();

        assert error[0] != null;
    }
}
