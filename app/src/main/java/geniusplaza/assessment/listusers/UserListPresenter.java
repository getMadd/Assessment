package geniusplaza.assessment.listusers;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import geniusplaza.assessment.TinyDB;
import geniusplaza.assessment.models.Create;
import geniusplaza.assessment.models.User;
import geniusplaza.assessment.network.RetrofitAccess;
import geniusplaza.assessment.network.endpoints.ReqresEndpoints;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jamarkushodge on 4/4/19.
 */

public class UserListPresenter implements UserListContract.Presenter {
    private final int MAX_RETURN_USERS = 12;
    private ReqresEndpoints apiService;
    private RetrofitAccess retrofit;
    private User[] users;
    private Context context;

    @Override
    public void initialize(Context context) {
        retrofit = new RetrofitAccess();
        apiService = retrofit.getRetrofit().create(ReqresEndpoints.class);
        this.context = context;
    }

    @Override
    public User[] handleRefreshList() {

        Call<ResponseBody> call = apiService.getUsers(MAX_RETURN_USERS);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String apiResponse = response.body().string();


                    JSONObject data = new JSONObject(apiResponse);
                    JSONArray usersArr = data.getJSONArray("data");

                    // read from json string
                    ObjectMapper mapper = new ObjectMapper();
                    users = mapper.readValue(usersArr.toString(), User[].class);
                    saveUsersSharedPrefs(context,users);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return users;
    }

    @Override
    public void handleAddUser(Create user) {
        Call<ResponseBody> call = apiService.createUser(user);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    int apiResponse = response.code();
                    Toast.makeText(context,"User Created ~ Response :" + apiResponse,Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public User[] getUsers(){
        return users;
    }

    @Override
    public void saveUsersSharedPrefs(Context context,User[] users){
        TinyDB tinydb = new TinyDB(context);
        tinydb.putListObject("usersList", users);
    }
}
