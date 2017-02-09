package opengles.graphic.jmap.opengles;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zhouqing on 2017/2/9.
 */

public class TextResourceReader {
    public static String readTextFileFromResource(Context context, int resourceId){
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader buffer_reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resourceId)));

            String nextLine;
            while((nextLine = buffer_reader.readLine())!=null){
                builder.append(nextLine);
                builder.append("\n");
            }
        }catch (Resources.NotFoundException e){

        }catch(IOException e){

        }

        return builder.toString();
    }
}
