package proj.sadna.mta.sadna_2017.app.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import proj.sadna.mta.sadna_2017.app.Network.Request.User;
import proj.sadna.mta.sadna_2017.app.Network.Response.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Einav on 01/09/2017.
 */


public class NetworkManager
{
    private static NetworkManager instance;
    TripApi api;

    public static NetworkManager getInstance()
    {
        if (instance == null)
        {
            instance = new NetworkManager();
        }
        return instance;
    }

    public NetworkManager()
    {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(ApiConstants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                client(client).build();
        api = retrofit.create(TripApi.class);
    }


//    public void addUser(User user, Callback<LoginResponse> callback)
//    {
//        Call<LoginResponse> call = api.addUser(user);
//        call.enqueue(callback);
//    }
//
//    public void editUser(User user, Callback<LoginResponse> callback)
//    {
//        Call<LoginResponse> call = api.editUser(user);
//        call.enqueue(callback);
//    }

    public void loginUser(User user, Callback<LoginResponse> callback)
    {
        Call<LoginResponse> call = api.loginUser(user);
        call.enqueue(callback);
    }

//    public void getUser(int userId, Callback<GetUserResponse> callback)
//    {
//        Call<GetUserResponse> call = api.getUser(userId);
//        call.enqueue(callback);
//    }
//
//    public void findAllUsers(Callback<FindAllUsersResponse> callback)
//    {
//        Call<FindAllUsersResponse> call = api.findAllUsers();
//        call.enqueue(callback);
//    }


}
