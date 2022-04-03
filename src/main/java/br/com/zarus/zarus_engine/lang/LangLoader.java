package br.com.zarus.zarus_engine.lang;

import br.com.zarus.zarus_engine.conf.Configurations;
import br.com.zarus.zarus_engine.core.BaseContext;
import br.com.zarus.zarus_engine.event.Message;

import java.io.IOException;
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
            this.texts.load(LangLoader.class.getResourceAsStream("/lang/" + Configurations.getString(Configurations.LANGUAGE) + ".properties"));
            LangTemplate.getInstance().load(this.texts);
        } catch (IOException e) {
            pushMessage(new Message(LangMessages.LOAD_EXCEPTION_MESSAGE));
        }
    }

    public static LangLoader getInstance() {
        return INSTANCE;
    }

}
