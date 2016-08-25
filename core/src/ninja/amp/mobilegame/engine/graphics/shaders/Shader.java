package ninja.amp.mobilegame.engine.graphics.shaders;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import ninja.amp.mobilegame.engine.resources.Resource;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

public class Shader implements Resource {

    protected ShaderProgram program;

    public Shader(ShaderProgram program, ResourceHandler handler) {
        this.program = program;

        handler.attachResource(this);

        setup();
    }

    public void apply(Batch batch) {
        update();
        batch.setShader(program);
    }

    public void setup() {
    }

    public void update() {
    }

    @Override
    public void dispose() {
        program.dispose();
        program = null;
    }

}
