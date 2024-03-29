package ninja.amp.engine.resources.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import ninja.amp.engine.persistence.Options;
import ninja.amp.engine.resources.Resource;
import ninja.amp.engine.resources.ResourceHandler;

public class Sound implements com.badlogic.gdx.audio.Sound, Resource {

    private Options options;
    private com.badlogic.gdx.audio.Sound sound;

    public Sound(FileHandle file, Options options, ResourceHandler handler) {
        this.options = options;

        sound = Gdx.audio.newSound(file);

        handler.attachResource(this);
    }

    @Override
    public long play() {
        if (options.getSound()) {
            return sound.play();
        } else {
            return -1;
        }
    }

    @Override
    public long play(float volume) {
        if (options.getSound()) {
            return sound.play(volume);
        } else {
            return -1;
        }
    }

    @Override
    public long play(float volume, float pitch, float pan) {
        if (options.getSound()) {
            return sound.play(volume, pitch, pan);
        } else {
            return -1;
        }
    }

    @Override
    public long loop() {
        if (options.getSound()) {
            return sound.loop();
        } else {
            return -1;
        }
    }

    @Override
    public long loop(float volume) {
        if (options.getSound()) {
            return sound.loop(volume);
        } else {
            return -1;
        }
    }

    @Override
    public long loop(float volume, float pitch, float pan) {
        if (options.getSound()) {
            return sound.loop(volume, pitch, pan);
        } else {
            return -1;
        }
    }

    @Override
    public void stop() {
        sound.stop();
    }

    @Override
    public void pause() {
        sound.pause();
    }

    @Override
    public void resume() {
        if (options.getSound()) {
            sound.resume();
        }
    }

    @Override
    public void dispose() {
        sound.dispose();
        sound = null;
    }

    @Override
    public void stop(long soundId) {
        sound.stop(soundId);
    }

    @Override
    public void pause(long soundId) {
        sound.pause(soundId);
    }

    @Override
    public void resume(long soundId) {
        if (options.getSound()) {
            sound.resume(soundId);
        }
    }

    @Override
    public void setLooping(long soundId, boolean looping) {
        sound.setLooping(soundId, looping);
    }

    @Override
    public void setPitch(long soundId, float pitch) {
        sound.setPitch(soundId, pitch);
    }

    @Override
    public void setVolume(long soundId, float volume) {
        sound.setVolume(soundId, volume);
    }

    @Override
    public void setPan(long soundId, float pan, float volume) {
        sound.setPan(soundId, pan, volume);
    }

}
