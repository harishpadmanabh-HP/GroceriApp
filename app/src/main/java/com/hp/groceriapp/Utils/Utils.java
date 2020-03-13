package com.hp.groceriapp.Utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Utils {
    Context context;


    public static void showToast(String msg, Context context) {
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
    }


    public static String getDate() {


        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        Log.e("CURRENT DATE", formattedDate);
        String currrentDate = formattedDate.replaceAll(":", "-");
        Log.e("CURRENT DATE", currrentDate);

// Now formattedDate have current date/time
        return currrentDate;

    }

    public static <T extends View> List<T> findViewsWithType(View root, Class<T> type) {
        List<T> views = new ArrayList<>();
        findViewsWithType(root, type, views);
        return views;
    }

    private static <T extends View> void findViewsWithType(View view, Class<T> type, List<T> views) {
        if (type.isInstance(view)) {
            views.add(type.cast(view));
        }

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                findViewsWithType(viewGroup.getChildAt(i), type, views);
            }
        }
    }
}
