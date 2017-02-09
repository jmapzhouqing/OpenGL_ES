package opengles.graphic.jmap.opengles;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by zhouqing on 2017/2/9.
 */
public class ShaderHelper {
    public static int compileVertexShader(String code){
        return compileShader(GLES20.GL_VERTEX_SHADER,code);
    }

    public static int compileFragmentShader(String code){
        return compileShader(GLES20.GL_FRAGMENT_SHADER,code);
    }

    public static int linkProgram(int vertex_shader_id,int frag_shader_id){
        final int programId = GLES20.glCreateProgram();

        if(programId == 0){
            return 0;
        }

        GLES20.glAttachShader(programId,vertex_shader_id);
        GLES20.glAttachShader(programId,frag_shader_id);

        GLES20.glLinkProgram(programId);

        final  int[] linkStatus = new int[1];

        GLES20.glGetProgramiv(programId,GLES20.GL_LINK_STATUS,linkStatus,0);

        Log.v("ShaderHelp",GLES20.glGetProgramInfoLog(programId));

        if(linkStatus[0] == 0){
            GLES20.glDeleteProgram(programId);
            return 0;
        }

        return programId;
    }

    private static int compileShader(int type,String code){
        final  int shaderObjectId = GLES20.glCreateShader(type);
        if(shaderObjectId == 0){
            return  0;
        }
        GLES20.glShaderSource(shaderObjectId,code);
        GLES20.glCompileShader(shaderObjectId);

        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderObjectId,GLES20.GL_COMPILE_STATUS,compileStatus,0);

        Log.v("ShaderHelp",GLES20.glGetShaderInfoLog(shaderObjectId));

        if(compileStatus[0] == 0){
            GLES20.glDeleteShader(shaderObjectId);
            return 0;
        }
        return shaderObjectId;
    }

    private static boolean validateProgram(int programId){
        GLES20.glValidateProgram(programId);

        final int[] validateStatus = new int[1];
        GLES20.glGetProgramiv(programId,GLES20.GL_VALIDATE_STATUS,validateStatus,0);

        Log.v("ShaderHelp",GLES20.glGetProgramInfoLog(programId));

        return validateStatus[0]!=0;
    }
}
