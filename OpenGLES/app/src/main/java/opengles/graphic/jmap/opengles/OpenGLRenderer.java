package opengles.graphic.jmap.opengles;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;
import android.opengl.GLSurfaceView.Renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by zhouqing on 2017/2/8.
 */

public class OpenGLRenderer implements Renderer {

    private static final int BYTES_PER_FLOAT = 4;


    float[] vertices = {
            0f,0f,
            0f,14f,
            9f,14f,
            9f,0f
    };

    float[] triangles = {
            0f,0f,
            9f,14f,
            0f,14f,

            0f,0f,
            9f,0f,
            9f,14f
    };


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        FloatBuffer vertexData = ByteBuffer.allocateDirect(triangles.length * BYTES_PER_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(triangles);

        GLES20.glClearColor(1.0f,0.0f,0.0f,0.0f);
    }

    @Override
    public void onDrawFrame(GL10 glUnused){
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height){
        GLES20.glViewport(0,0,width,height);
    }
}
