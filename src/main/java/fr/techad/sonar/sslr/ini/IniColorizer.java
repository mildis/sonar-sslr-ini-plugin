package fr.techad.sonar.sslr.ini;

import org.sonar.colorizer.CDocTokenizer;
import org.sonar.colorizer.CppDocTokenizer;
import org.sonar.colorizer.KeywordsTokenizer;
import org.sonar.colorizer.Tokenizer;

import java.util.Arrays;
import java.util.List;

public final class IniColorizer {

  private IniColorizer() {
  }

  public static List<Tokenizer> getTokenizers() {
    return Arrays.asList(
        new CDocTokenizer("<span class=\"cd\">", "</span>"),
        new CppDocTokenizer("<span class=\"cppd\">", "</span>"),
        new KeywordsTokenizer("<span class=\"k\">", "</span>", IniLexer.Keywords.keywordValues()));
  }

}
