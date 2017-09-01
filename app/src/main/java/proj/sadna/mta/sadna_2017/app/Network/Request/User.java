package proj.sadna.mta.sadna_2017.app.Network.Request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Einav on 01/09/2017.
 */
public class User
{
    public static final String APP_USER_ID = "uid";
    public static final String EMAIL = "username";
    public static final String PASSWORD = "password";
    public static final String DEVICE_TOKEN = "deviceToken";

    private String userId;

    @SerializedName(EMAIL)
    private String email;

    @SerializedName(PASSWORD)
    private String password;

    @SerializedName(DEVICE_TOKEN)
    private String deviceToken;

    public User(String userId, String email, String password, String deviceToken)
    {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.deviceToken = deviceToken;
    }
}
