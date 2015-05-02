package fr.techad.sonar.sslr.ini;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;
import org.apache.commons.io.FileUtils;

import java.io.File;

public final class IniParser {

    private static final Parser<Grammar> P = IniParser.create();

    private IniParser() {
    }

    public static Parser<Grammar> create() {
        return Parser.builder(IniGrammar.create()).withLexer(IniLexer.create()).build();
    }

    public static AstNode parseFile(String filePath) {
        File file = FileUtils.toFile(IniParser.class.getResource(filePath));
        if (file == null || !file.exists()) {
            throw new AssertionError("The file \"" + filePath + "\" does not exist.");
        }

        return P.parse(file);
    }

    public static AstNode parseString(String source) {
        return P.parse(source);
    }

}
