package ninja.amp.mobilegame.engine.graphics.shaders;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

public class LightingShader extends Shader {

    protected int active = 0;
    protected Light[] lights;
    protected float[] color;
    protected float[] position;
    protected float[] attenuation;

    public LightingShader(ShaderProgram program, int maxLights, ResourceHandler handler) {
        super(program, handler);
        lights = new Light[maxLights];
        color = new float[maxLights * 4];
        position = new float[maxLights * 3];
        attenuation = new float[maxLights * 3];
    }

    public void addLight(Light light) {
        this.lights[active++] = light;
    }

    @Override
    public void update() {
        for (int i = 0; i < active; i++) {
            Light light = lights[i];

            int i3 = i*3;
            int i4 = i*4;
            color[i4] = light.getColor().x;
            color[i4+1] = light.getColor().y;
            color[i4+2] = light.getColor().z;
            color[i4+3] = light.getIntensity();
            position[i3] = light.getPosition().x;
            position[i3+1] = light.getPosition().y;
            position[i3+2] = light.getPosition().z;
            attenuation[i3] = light.getAttenuation().x;
            attenuation[i3+1] = light.getAttenuation().y;
            attenuation[i3+2] = light.getAttenuation().z;
        }
    }

}
