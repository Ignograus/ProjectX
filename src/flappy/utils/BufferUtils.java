package flappy.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Created by Ignograus on 22.03.2016.
 */
public class BufferUtils {

    private BufferUtils() {}

    public static ByteBuffer createByteBufer(byte[] array) {
        ByteBuffer result = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
        result.put(array).flip();
        return result;
    }

    public static FloatBuffer createFloatBuffer(float[] array) {
        FloatBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        result.put(array).flip();
        return result;
    }

    public static IntBuffer createIntBuffer(int[] array) {
        IntBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
        result.put(array).flip();
        return result;
    }
}
