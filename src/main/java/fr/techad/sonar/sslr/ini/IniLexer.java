package fr.techad.sonar.sslr.ini;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import com.sonar.sslr.impl.Lexer;
import com.sonar.sslr.impl.channel.BlackHoleChannel;
import com.sonar.sslr.impl.channel.IdentifierAndKeywordChannel;
import com.sonar.sslr.impl.channel.PunctuatorChannel;

import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.commentRegexp;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.regexp;

public final class IniLexer {

    private IniLexer() {
    }

    public static enum Literals implements TokenType {

        STRING;

        public String getName() {
            return name();
        }

        public String getValue() {
            return name();
        }

        public boolean hasToBeSkippedFromAst(AstNode node) {
            return false;
        }

    }

    public static enum Punctuators implements TokenType {

        SQBRACE_L("["), SQBRACE_R("]"), EQ("="), HASH("#");

        private final String value;

        private Punctuators(String value) {
            this.value = value;
        }

        public String getName() {
            return name();
        }

        public String getValue() {
            return value;
        }

        public boolean hasToBeSkippedFromAst(AstNode node) {
            return false;
        }

    }

    public static enum Keywords implements TokenType {

        EMPTY("");

        private final String value;

        private Keywords(String value) {
            this.value = value;
        }

        public String getName() {
            return name();
        }

        public String getValue() {
            return value;
        }

        public boolean hasToBeSkippedFromAst(AstNode node) {
            return false;
        }

        public static String[] keywordValues() {
            Keywords[] keywordsEnum = Keywords.values();
            String[] keywords = new String[keywordsEnum.length];
            for (int i = 0; i < keywords.length; i++) {
                keywords[i] = keywordsEnum[i].getValue();
            }
            return keywords;
        }

    }

    public static Lexer create() {
        return Lexer
                .builder()
                .withFailIfNoChannelToConsumeOneCharacter(true)
                .withChannel(
                        new IdentifierAndKeywordChannel("[a-zA-Z]([a-zA-Z0-9_]*[a-zA-Z0-9])?+", true, Keywords.values()))
                .withChannel(regexp(Literals.STRING, "[a-zA-Z]([a-zA-Z0-9_]*[a-zA-Z0-9])?+"))
                .withChannel(commentRegexp("(?s)/\\*.*?\\*/"))
                .withChannel(new PunctuatorChannel(Punctuators.values()))
                .withChannel(new BlackHoleChannel("[ \t\r\n]+")).build();
    }
}
