package proj.sadna.mta.sadna_2017.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Einav on 26/08/2017.
 */
public class TripAppPreferences
{
    private static final String PREFS_NAME = "TripAppPreferences";
    private static final String KEY_USER_ID = "user_id";
    private static final String TAG = TripAppPreferences.class.getSimpleName();
    private static final String KEY_USER_LOGGED = "user_logged";
    private static SharedPreferences mPreferences;
    private static TripAppPreferences instance = new TripAppPreferences();
    private static final String KEY_FACEBOOK_ID = "facebook_id";
    private static final String KEY_GET_NOTIFICATION = "get_notification";

    private static final String defValue = "";
    private static final String KEY_USER_URL = "profile_pic_android";
    private static final String KEY_USER_DESCRIPTION = "description";
    private static final String FCM_TOKEN_KEY = "fcmtoken";
    private static final String UNREAD_COUNT = "unread_count";
    private static boolean userLogged;

    private TripAppPreferences()
    {
    }

    public static void init(Context context)
    {
        mPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static TripAppPreferences getInstance()
    {
        return instance;
    }


    public static boolean isUserRegistered()
    {
        return mPreferences.contains(KEY_USER_ID);
    }

    public static void setFacebookSocialId(String id)
    {
        mPreferences.edit().putString(KEY_FACEBOOK_ID, id);
    }

    public static String getFacebookUserId()
    {
        return mPreferences.getString(KEY_FACEBOOK_ID, defValue);
    }

    public static boolean showNotification()
    {
        return mPreferences.getBoolean(KEY_GET_NOTIFICATION, true);
    }

    public static void setNotification(boolean toShow)
    {
        mPreferences.edit().putBoolean(KEY_GET_NOTIFICATION, toShow).apply();
    }

    public static int getUserId()
    {
        return mPreferences.getInt(KEY_USER_ID, -1);
    }

    public static void saveUserId(int id)
    {
        mPreferences.edit().putInt(KEY_USER_ID, id).apply();
    }

    public static String getAndroidProfilePic()
    {
        return mPreferences.getString(KEY_USER_URL, " ");
    }

    public static void saveAndroidProfilePic(String url)
    {
        mPreferences.edit().putString(KEY_USER_URL, url).apply();
    }

    public static String getDescription()
    {
        return mPreferences.getString(KEY_USER_DESCRIPTION, " ");
    }

    public static void saveDescription(String description)
    {
        mPreferences.edit().putString(KEY_USER_DESCRIPTION, description).apply();
    }

    // Returns true if exists and deleted
    public static boolean clearUserId()
    {
        int savedId = mPreferences.getInt(KEY_USER_ID, -1);
        if (savedId < 0) return false;
        mPreferences.edit().remove(KEY_USER_ID).apply();
        Log.i(TAG, "User id deleted");
        return true;
    }


    public static void saveFCMToken(String refreshedToken)
    {
        mPreferences.edit().putString(FCM_TOKEN_KEY, refreshedToken).apply();
    }

    public static String loadFCMToken()
    {
        return mPreferences.getString(FCM_TOKEN_KEY, "");
    }

    public static void logout()
    {

        mPreferences.edit().remove(KEY_USER_ID).apply();
        mPreferences.edit().commit();

        mPreferences.edit().remove(KEY_FACEBOOK_ID).apply();
        mPreferences.edit().commit();

        mPreferences.edit().remove(KEY_USER_DESCRIPTION);
        mPreferences.edit().commit();

        mPreferences.edit().remove(KEY_USER_URL);
        mPreferences.edit().commit();


    }


    public static int getUnreadCount()
    {
        return mPreferences.getInt(UNREAD_COUNT, 0);
    }

    public static void incrementUnreadCount()
    {
        mPreferences.edit().putInt(UNREAD_COUNT, getUnreadCount() + 1).commit();
    }

    public static void resetUnreadCount()
    {
        mPreferences.edit().putInt(UNREAD_COUNT, 0).commit();
    }

    public static void setUserLogged(boolean userLogged)
    {
        mPreferences.edit().putBoolean(KEY_USER_LOGGED, userLogged).apply();
        mPreferences.edit().commit();
    }

    public static boolean isUserLogged()
    {
        return mPreferences.contains(KEY_USER_LOGGED);
    }
}
