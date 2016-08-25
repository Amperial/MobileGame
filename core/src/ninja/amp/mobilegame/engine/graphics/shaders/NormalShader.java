package ninja.amp.mobilegame.engine.graphics.shaders;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

public class NormalShader extends LightingShader {

    private static String VERTEX =
              "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
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
    private static String FRAGMENT =
              "#ifdef GL_ES\n"
            + "#define LOWP lowp\n"
            + "precision mediump float;\n"
            + "#else\n"
            + "#define LOWP \n"
            + "#endif\n"
            + "varying LOWP vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n"
            + "uniform sampler2D u_texture;\n"
            + "uniform sampler2D u_normals;\n"
            + "uniform vec2 resolution;\n"
            + "uniform LOWP vec4 ambient_color;\n"
            + "uniform int lights;\n"
            + "uniform LOWP vec4 light_color[64];\n"
            + "uniform vec3 light_position[64];\n"
            + "uniform vec3 light_att[64];\n"
            + "void main()\n"
            + "{\n"
            + "    vec4 DiffuseColor = texture2D(u_texture, v_texCoords);\n"
            + "    vec3 NormalMap = texture2D(u_normals, v_texCoords).rgb;\n"
            + "    vec3 N = normalize(NormalMap * 2.0 - 1.0);\n"
            + "    float ratio = resolution.x / resolution.y;\n"
            + "    vec3 Sum = DiffuseColor.rgb * ambient_color.rgb * ambient_color.a;\n"
            + "    for (int i = 0; i < lights; i++) {\n"
            + "        vec3 LightDir = vec3(light_position[i].xy - (gl_FragCoord.xy / resolution.xy), light_position[i].z);\n"
            + "        LightDir.x *= ratio;\n"
            + "        float D = length(LightDir);\n"
            + "        Sum += DiffuseColor.rgb * (light_color[i].rgb * light_color[i].a) * max(dot(N, normalize(LightDir)), 0.0) * 1.0 / (light_att[i].x + (light_att[i].y*D) + (light_att[i].z*D*D));\n"
            + "    }\n"
            + "    gl_FragColor = v_color * vec4(Sum, DiffuseColor.a);\n"
            + "}";

    public NormalShader(ResourceHandler handler, int maxLights) {
        super(new ShaderProgram(VERTEX, FRAGMENT), maxLights, handler);
    }

    @Override
    public void setup() {
        program.begin();
        program.setUniformi("u_normals", 1);
        program.setUniformf("ambient_color", 1f, 1f, 1f, 0.5f);
        program.end();
    }

    @Override
    public void update() {
        super.update();

        program.begin();
        program.setUniformi("lights", active);
        program.setUniform4fv("light_color", color, 0, active * 4);
        program.setUniform3fv("light_position", position, 0, active * 3);
        program.setUniform3fv("light_att", attenuation, 0, active * 3);
        program.end();
    }

    public void resize(int width, int height) {
        program.begin();
        program.setUniformf("resolution", width, height);
        program.end();
    }

    public void bindTextures(Texture texture, Texture normal) {
        normal.getRegion().getTexture().bind(1);
        texture.getRegion().getTexture().bind(0);
    }

}
