package ninja.amp.mobilegame.engine.graphics.shaders;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

public class FadeShader extends Shader {

    private static String VERTEX = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
            + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
            + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "uniform mat4 u_projTrans;\n"
            + "varying vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n"
            + "void main()\n"
            + "{\n"
            + "    v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
            + "    v_color.a = v_color.a * (255.0/254.0);\n"
            + "    v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "    gl_Position = u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
            + "}\n";
    private static String FRAGMENT = "#ifdef GL_ES\n"
            + "#define LOWP lowp\n"
            + "precision mediump float;\n"
            + "#else\n"
            + "#define LOWP \n"
            + "#endif\n"
            + "varying LOWP vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n"
            + "uniform sampler2D u_texture;\n"
            + "uniform float fade;\n"
            + "void main()\n"
            + "{\n"
            + "    vec4 color = v_color * texture2D(u_texture, v_texCoords);\n"
            + "    gl_FragColor = vec4(color.rgb, color.a * fade);\n"
            + "}";

    private float fade;

    public FadeShader(ResourceHandler handler) {
        super(new ShaderProgram(VERTEX, FRAGMENT), handler);
    }

    @Override
    public void update() {
        program.begin();
        program.setUniformf("fade", fade);
        program.end();
    }

    public void setFade(float fade) {
        this.fade = fade;
    }

}
