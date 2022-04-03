package br.com.zarus.zarus_engine.lang;

import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LangTemplate {

    private static final LangTemplate INSTANCE = new LangTemplate();

    private StringSubstitutor substitutor;

    private LangTemplate() {}

    @SuppressWarnings("unchecked,rawtypes")
    public void load(Properties properties) {
        substitutor = new StringSubstitutor(new HashMap<>((Map<String, String>) (Map) properties));
    }

    public static LangTemplate getInstance() {
        return INSTANCE;
    }

    public String templateString(String templateString) {
       return this.substitutor.replace(templateString);
    }
}
