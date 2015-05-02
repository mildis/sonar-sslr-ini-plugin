package fr.techad.sonar.sslr.ini;

import com.sonar.sslr.api.Grammar;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

import static com.sonar.sslr.api.GenericTokenType.EOF;
import static com.sonar.sslr.api.GenericTokenType.EOL;
import static com.sonar.sslr.api.GenericTokenType.IDENTIFIER;
import static fr.techad.sonar.sslr.ini.IniLexer.Literals.STRING;
import static fr.techad.sonar.sslr.ini.IniLexer.Punctuators.SQBRACE_L;
import static fr.techad.sonar.sslr.ini.IniLexer.Punctuators.SQBRACE_R;
import static fr.techad.sonar.sslr.ini.IniLexer.Punctuators.EQ;

public enum IniGrammar implements GrammarRuleKey {

    COMPILATION_UNIT, DEFINITION, SECTION_DEFINITION, VARIABLE_DEFINITION, BIN_VARIABLE_DEFINITION;

    public static Grammar create() {
        LexerfulGrammarBuilder b = LexerfulGrammarBuilder.create();

        b.rule(COMPILATION_UNIT).is(b.zeroOrMore(DEFINITION), EOF);

        b.rule(DEFINITION).is(b.firstOf(SECTION_DEFINITION, VARIABLE_DEFINITION));

        b.rule(SECTION_DEFINITION).is(SQBRACE_L, IDENTIFIER, SQBRACE_R);

        b.rule(VARIABLE_DEFINITION).is(BIN_VARIABLE_DEFINITION, EQ, b.optional(STRING), EOL);

        b.setRootRule(COMPILATION_UNIT);

        return b.build();
    }

}
