package com.dream95.tdengineparser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Example {

    public static void main(String[] args) {
        TDengineLexer lexer = new TDengineLexer(CharStreams.fromString("select * from d1.s1"));

        TDengineParser parser = new TDengineParser(new CommonTokenStream(lexer));

        TDengineParser.SqlCommandListContext sqlCommandList = parser.sqlCommandList();

        TDengineParser.CmdContext cmd = sqlCommandList.cmd().get(0);

        ParseTreeWalker.DEFAULT.walk(new TDengineParserBaseListener() {

            @Override
            public void enterDbName(TDengineParser.DbNameContext ctx) {
                System.out.println("dbName is :"+ctx.getText());
            }

            @Override
            public void enterTableName(TDengineParser.TableNameContext ctx) {
                System.out.println("tableName is :"+ctx.getText());
            }
        }, cmd);
    }

}
