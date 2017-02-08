package opengles.graphic.jmap.opengles;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OpenGL extends AppCompatActivity {
    private GLSurfaceView glSurfaceView;
    private boolean rendererSet = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_open_gl);

        glSurfaceView = new GLSurfaceView(this);

        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();

        final boolean supportEs = configurationInfo.reqGlEsVersion >= 0x20000;

        if(supportEs){
            glSurfaceView.setEGLContextClientVersion(2);
            glSurfaceView.setRenderer(new OpenGLRenderer());
            rendererSet = true;
        }
        setContentView(glSurfaceView);
    }

    protected void OnPause(){
        super.onPause();
        if(rendererSet){
            glSurfaceView.onPause();
        }
    }

    protected void OnResume(){
        super.onResume();
        if(rendererSet){
            glSurfaceView.onResume();
        }
    }
}
