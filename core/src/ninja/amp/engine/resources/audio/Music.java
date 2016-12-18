package ninja.amp.engine.resources.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import ninja.amp.engine.resources.Resource;
import ninja.amp.engine.resources.ResourceHandler;

public class Music implements com.badlogic.gdx.audio.Music, Resource {

    private MusicManager manager;
    private com.badlogic.gdx.audio.Music music;

    public Music(FileHandle file, MusicManager manager, ResourceHandler handler) {
        this.manager = manager;

        music = Gdx.audio.newMusic(file);
        music.setOnCompletionListener(manager);

        handler.attachResource(this);
    }

    @Override
    public void play() {
        music.play();
    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void stop() {
        music.stop();
    }

    @Override
    public boolean isPlaying() {
        return music.isPlaying();
    }

    @Override
    public void setLooping(boolean isLooping) {
        music.setLooping(isLooping);
    }

    @Override
    public boolean isLooping() {
        return music.isLooping();
    }

    @Override
    public void setVolume(float volume) {
        music.setVolume(volume);
    }

    @Override
    public float getVolume() {
        return music.getVolume();
    }

    @Override
    public void setPan(float pan, float volume) {
        music.setPan(pan, volume);
    }

    @Override
    public void setPosition(float position) {
        music.setPosition(position);
    }

    @Override
    public float getPosition() {
        return music.getPosition();
    }

    @Override
    public void dispose() {
        music.dispose();
        music = null;
    }

    @Override
    public void setOnCompletionListener(OnCompletionListener listener) {
        music.setOnCompletionListener(listener);
    }

}
