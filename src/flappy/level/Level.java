package flappy.level;

import flappy.graphics.Shader;
import flappy.graphics.Texture;
import flappy.graphics.VertexArray;
import flappy.math.Matrix4f;
import flappy.math.Vector3f;

/**
 * Created by Ignograus on 23.03.2016.
 */
public class Level {

    private VertexArray background;
    private Texture bgTexture;

    private int xScroll = 0;
    private int map = 0;


    public Level() {
        float[] vertices = new float[] {
          -10, -10 * 9 / 15, 0,
          -10, 10 * 9 / 15, 0,
          0, 10 * 9 / 15, 0,
          0, -10 * 9 / 15, 0
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

        background = new VertexArray(vertices, indices, tcs);
        bgTexture = new Texture("res/bg.jpeg");

    }

    public void update() {
        xScroll--;
        if (-xScroll % 335 == 0) map++;
    }

    public void render() {
        bgTexture.bind();
        Shader.BG.enable();
        background.bind();
        for (int i = map; i < map + 3; i++) {
            Shader.BG.setUniformMat4f("vw_matrix", Matrix4f.translate(new Vector3f(i*10+xScroll * 0.03f,0,0)));
            background.draw();
        }
        Shader.BG.disable();
        bgTexture.unbind();
    }
}
