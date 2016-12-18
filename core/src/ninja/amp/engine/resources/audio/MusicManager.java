package ninja.amp.engine.resources.audio;

import ninja.amp.engine.persistence.Options;

public class MusicManager implements com.badlogic.gdx.audio.Music.OnCompletionListener {

    private Music playing;
    private Options options;

    public MusicManager(Options options) {
        this.options = options;
    }

    public void enable() {
        options.setMusic(true);
        if (playing != null) {
            playing.setVolume(0.1f);
            playing.play();
        }
    }

    public void disable() {
        options.setMusic(false);
        if (playing != null) {
            playing.stop();
        }
    }

    public Music getPlaying() {
        return playing;
    }

    public void setPlaying(Music music) {
        if (playing != null) {
            playing.stop();
        }
        playing = music;
        if (playing != null && options.getMusic()) {
            playing.setVolume(0.1f);
            playing.play();
        }
    }

    public Music next() {
        return playing;
    }

    @Override
    public void onCompletion(com.badlogic.gdx.audio.Music music) {
        setPlaying(next());
    }

}
