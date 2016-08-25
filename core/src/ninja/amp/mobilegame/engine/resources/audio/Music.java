package ninja.amp.mobilegame.engine.resources.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.engine.resources.Resource;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

public class Music implements com.badlogic.gdx.audio.Music, Resource {

    private com.badlogic.gdx.audio.Music music;

    public Music(FileHandle file, ResourceHandler handler) {
        music = Gdx.audio.newMusic(file);

        handler.attachResource(this);
    }

    @Override
    public void play() {
        if (MobileGame.options.getMusic()) {
            music.play();
        }
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
