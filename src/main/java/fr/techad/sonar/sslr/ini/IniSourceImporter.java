package fr.techad.sonar.sslr.ini;

import org.sonar.api.batch.AbstractSourceImporter;
import org.sonar.api.batch.Phase;

@Phase(name = Phase.Name.PRE)
public class IniSourceImporter extends AbstractSourceImporter {

    public IniSourceImporter(IniLanguage ini) {
        super(ini);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}