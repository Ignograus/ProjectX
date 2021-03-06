package flappy;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.*;

import flappy.graphics.Shader;
import flappy.level.Level;
import flappy.math.Matrix4f;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

/**
 * Created by Ignograus on 22.03.2016.
 */
public class Main implements Runnable{

    private int width = 1280;
    private int height = 720;

    private Thread thread;
    private boolean running = false;

    private long window;

    private Level level;

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    public void run() {
        init();

        long lastTime = System.nanoTime();
        double ns = 1000000000 / 60;
        long timer = System.currentTimeMillis();
        double delta = 0;
        int updates = 0;
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now -lastTime) / ns;
            lastTime = now;
            if(delta >= 1) {
                update();
                updates++;
                delta--;
            }

            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " ups, " + frames + " fps" );
                updates = 0;
                frames = 0;
            }

            if(glfwWindowShouldClose(window) == GL_TRUE)
                running = false;
        }
    }

    private void init() {
        if(glfwInit() != GL_FALSE) {
            //handle it
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        window = glfwCreateWindow(width,height,"Flappy Bird", NULL, NULL);

        if(window == NULL) {
            return;
        }

        PointerBuffer b = glfwGetMonitors();
        GLFWVidMode vidmode = glfwGetVideoMode(b.get(1));


        glfwSetWindowPos(window, (vidmode.width() - width)/2, (vidmode.height() - height)/2);
        glfwSetKeyCallback(window, new Input());
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        GL.createCapabilities();

        glClearColor(1, 1, 1, 1 );
        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));
        Shader.loadAll();

        Matrix4f pr_matrix = Matrix4f.orthographics(-10,10,-10 * 9 / 16,10*9/16,-1,1);
        Shader.BG.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.BG.setUniform1i("tex",1);
        level = new Level();
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        level.render();
        glfwSwapBuffers(window);
    }

    private void update() {
        glfwPollEvents();
        level.update();
        if (Input.keys[GLFW_KEY_SPACE])
            System.out.println("Flap");
    }
}
