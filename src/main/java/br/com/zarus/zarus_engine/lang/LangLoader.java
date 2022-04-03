package br.com.zarus.zarus_engine.lang;

import br.com.zarus.zarus_engine.conf.Configurations;
import br.com.zarus.zarus_engine.core.BaseContext;
import br.com.zarus.zarus_engine.event.Message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LangLoader extends BaseContext {

    private static final LangLoader INSTANCE = new LangLoader();

    private final Properties texts;

    private LangLoader() {
        this.texts = new Properties();
        load();
        super.registerToReceiveMessages();
    }

    public void load() {
        try {
            InputStream gameLanguage = loadLanguageFrom("lang");
            if (gameLanguage != null)
                this.texts.load(gameLanguage);
            this.texts.load(loadLanguageFrom("lang/engine"));
            LangTemplate.getInstance().load(this.texts);
        } catch (IOException e) {
            pushMessage(new Message(LangMessages.LOAD_EXCEPTION_MESSAGE));
        }
    }

    private InputStream loadLanguageFrom(String path) {
        return LangLoader.class.getResourceAsStream("/" + path + "/" + Configurations.getString(Configurations.LANGUAGE) + ".properties");
    }

    public static LangLoader getInstance() {
        return INSTANCE;
    }

}
