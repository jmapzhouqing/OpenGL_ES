package opengles.graphic.jmap.opengles;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Shader;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;
import android.opengl.GLSurfaceView.Renderer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by zhouqing on 2017/2/8.
 */

public class OpenGLRenderer implements Renderer {

    private static final int BYTES_PER_FLOAT = 4;

    private final Context context;

    float[] vertices = {
            0f,0f,
            -0.5f,-0.5f,
            0.5f,-0.5f,
            0.5f,0.5f,
            -0.5f,0.5f,
            -0.5f,-0.5f
    };

    float[] triangles = {
            /*
            -0.6f,-0.6f,
            0.6f,0.6f,
            -0.6f,0.6f,

            -0.6f,-0.6f,
            0.6f,-0.6f,
            0.6f,0.6f,*/

            0f,0f,1f,1f,1f,
            -0.5f,-0.5f,0.7f,0.7f,0.7f,
            0.5f,-0.5f,0.7f,0.7f,0.7f,
            0.5f,0.5f,0.7f,0.7f,0.7f,
            -0.5f,0.5f,0.7f,0.7f,0.7f,
            -0.5f,-0.5f,0.7f,0.7f,0.7f,

            -0.5f,0f,1f,0f,0f,
            0.5f,0f,1f,0f,0f,

            0f,-0.25f,0f,0f,1f,
            0f,0.25f,1f,0f,0f
    };

    public OpenGLRenderer(Context context){
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        FloatBuffer vertexData = ByteBuffer.allocateDirect(triangles.length * BYTES_PER_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(triangles);

        String vertex_shader_soruce = TextResourceReader.readTextFileFromResource(context,R.raw.vertex_shader);
        String frag_shader_soruce = TextResourceReader.readTextFileFromResource(context,R.raw.fragment_shader);

        int vertex_shader = ShaderHelper.compileVertexShader(vertex_shader_soruce);
        int frag_shader = ShaderHelper.compileFragmentShader(frag_shader_soruce);

        int program = ShaderHelper.linkProgram(vertex_shader,frag_shader);

        GLES20.glUseProgram(program);

        int aColorLocation = GLES20.glGetAttribLocation(program,"a_Color");

        int aPositionLocation = GLES20.glGetAttribLocation(program,"a_Position");

        vertexData.position(0);
        GLES20.glVertexAttribPointer(aPositionLocation,2,GLES20.GL_FLOAT,false,20,vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);

        vertexData.position(2);
        GLES20.glVertexAttribPointer(aColorLocation,3,GLES20.GL_FLOAT,false,20,vertexData);
        GLES20.glEnableVertexAttribArray(aColorLocation);

        GLES20.glClearColor(0.0f,0.0f,0.0f,0.0f);
    }

    @Override
    public void onDrawFrame(GL10 glUnused){
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        //GLES20.glUniform4f(uColorLocation,1.0f,1.0f,1.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN,0,6);

        //GLES20.glUniform4f(uColorLocation,1.0f,0.0f,0.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_LINES,6,2);

        //GLES20.glUniform4f(uColorLocation,0.0f,0.0f,1.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_POINTS,8,1);

        //GLES20.glUniform4f(uColorLocation,0.0f,0.0f,1.0f,1.0f);
        GLES20.glDrawArrays(GLES20.GL_POINTS,9,1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height){
        GLES20.glViewport(0,0,width,height);
    }


}
