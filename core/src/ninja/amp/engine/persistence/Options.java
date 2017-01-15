package ninja.amp.engine.persistence;

import ninja.amp.engine.resources.ResourceHandler;

public class Options extends Persistent {

    public Options(ResourceHandler handler) {
        super("options", handler);
    }

    public boolean getSound() {
        return persistent.getBoolean("sound");
    }

    public void setSound(boolean sound) {
        persistent.putBoolean("sound", sound);
    }

    public boolean getMusic() {
        return persistent.getBoolean("music");
    }

    public void setMusic(boolean music) {
        persistent.putBoolean("music", music);
    }

}
