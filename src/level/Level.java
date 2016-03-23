package level;

import graphics.Shader;
import graphics.Texture;
import graphics.VertexArray;

/**
 * Created by Ignograus on 23.03.2016.
 */
public class Level {

    private VertexArray background;
    private Texture bgTexture;

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

    public void render() {
        bgTexture.bind();
        Shader.BG.enable();
        background.render();
        Shader.BG.disable();
        bgTexture.unbind();
    }
}
