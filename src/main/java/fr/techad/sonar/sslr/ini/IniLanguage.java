package fr.techad.sonar.sslr.ini;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.AbstractLanguage;

public class IniLanguage extends AbstractLanguage {

    public static final String KEY = "ini";

    private Settings settings;

    public IniLanguage(Settings configuration) {
        super(KEY, "Ini");
        this.settings = configuration;
    }

    @Override
    public String[] getFileSuffixes() {
        String[] suffixes = settings.getStringArray(IniPlugin.FILE_SUFFIXES_KEY);
        if (suffixes == null || suffixes.length == 0) {
            suffixes = StringUtils.split(IniPlugin.FILE_SUFFIXES_DEFVALUE, ",");
        }
        return suffixes;
    }

}