package com.hp.groceriapp.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {
    Context context;



    public static void showToast(String msg, Context context)
    {
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
    }


    public static String getDate() {


            Calendar c = Calendar.getInstance();
            System.out.println("Current time =&gt; "+c.getTime());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(c.getTime());
            Log.e("CURRENT DATE",formattedDate);
            String currrentDate=formattedDate.replaceAll(":","-");
            Log.e("CURRENT DATE",currrentDate);

// Now formattedDate have current date/time
            return currrentDate;




    }
}
