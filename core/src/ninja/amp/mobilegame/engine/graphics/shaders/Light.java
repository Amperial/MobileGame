package ninja.amp.mobilegame.engine.graphics.shaders;

import com.badlogic.gdx.math.Vector3;

public class Light {

    private Vector3 position;
    private Vector3 color;
    private float intensity;
    private Vector3 attenuation;

    public Light(Vector3 position, Vector3 color, float intensity, Vector3 attenuation) {
        this.position = position;
        this.color = color;
        this.intensity = intensity;
        this.attenuation = attenuation;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getColor() {
        return color;
    }

    public float getIntensity() {
        return intensity;
    }

    public Vector3 getAttenuation() {
        return attenuation;
    }

}
