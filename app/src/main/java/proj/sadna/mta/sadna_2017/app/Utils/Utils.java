package proj.sadna.mta.sadna_2017.app.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * Created by Einav on 26/08/2017.
 */

public class Utils
{

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
        DisplayMetrics mDisplay = mContext.getResources().getDisplayMetrics();
    }
    public static void sendEmail(Context context, String to, String subject, String body)
    {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT, body);
        try
        {
            context.startActivity(Intent.createChooser(i, "Send mail..."));

        } catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
