package fr.techad.sonar.sslr.ini;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;

public final class IniPlugin extends SonarPlugin {
    public static final String FILE_SUFFIXES_KEY = "sonar.sslr4ini.file.suffixes";
    public static final String FILE_SUFFIXES_DEFVALUE = ".ini";

    @Override
    public List<Object> getExtensions() {
        PropertyDefinition suffixes = PropertyDefinition.builder(FILE_SUFFIXES_KEY)
                .defaultValue(FILE_SUFFIXES_DEFVALUE).name("File suffixes")
                .description("Comma-separated list of files to analyze.").build();

        return Arrays.asList(IniLanguage.class, IniSourceImporter.class, suffixes);
    }
}
