import java.nio.ByteBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

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
        while(running) {
            update();
            render();

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
        System.out.println("OpenGL: " + glGetString(GL_VERSION));

    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwSwapBuffers(window);
    }

    private void update() {
        glfwPollEvents();
        if (Input.keys[GLFW_KEY_SPACE])
            System.out.println("Flap");
    }
}
