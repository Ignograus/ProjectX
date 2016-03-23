package flappy.level;

import flappy.graphics.Shader;
import flappy.graphics.Texture;
import flappy.graphics.VertexArray;
import flappy.math.Matrix4f;
import flappy.math.Vector3f;
import org.lwjgl.glfw.GLFW;

/**
 * Created by Ignograus on 23.03.2016.
 */
public class Bird {

    private float SIZE = 1;
    private VertexArray mesh;
    private Texture texture;
    private Vector3f position = new Vector3f();
    private float rot;
    private float delta = 0;

    public Bird() {
        float[] vertices = new float[] {
                -SIZE / 2, -SIZE / 2, 0,
                -SIZE / 2,  SIZE / 2, 0,
                 SIZE / 2,  SIZE / 2, 0,
                 SIZE / 2, -SIZE / 2, 0,
        };

        byte[] indices = new byte[] {
                0, 1, 2,
                2, 3, 0
        };

        float[] tcs = new float[] {
                0, 1,
                0, 0,
                1, 0,
                1, 1
        };

        mesh = new VertexArray(vertices, indices, tcs);
        texture = new Texture("res/bird.png");
    }

    public void update() {
        if ( flappy.Input.keys[GLFW.GLFW_KEY_UP])
            position.y++;
        if ( flappy.Input.keys[GLFW.GLFW_KEY_DOWN])
            position.y--;
        if ( flappy.Input.keys[GLFW.GLFW_KEY_LEFT])
            position.x--;
        if ( flappy.Input.keys[GLFW.GLFW_KEY_RIGHT])
            position.x++;
    }

    public void render() {
        Shader.BIRD.enable();
        Shader.BIRD.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        texture.bind();
        mesh.render();
        Shader.BIRD.disable();
    }

}
