package edu.neu.weatherforecasting.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import edu.neu.weatherforecasting.R;
import edu.neu.weatherforecasting.data.model.User;
import edu.neu.weatherforecasting.http.HttpUtil;
import edu.neu.weatherforecasting.http.ParseResult;
import edu.neu.weatherforecasting.http.RequestTask;
import edu.neu.weatherforecasting.ui.MainActivity;
import edu.neu.weatherforecasting.utils.ToastUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private EditText et_username, et_pwd;
    private AppCompatButton btn_login;
    private TextView tv_login_2_register;
    private User user;
    private RegisterFragment registerFragment;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        et_username = view.findViewById(R.id.et_1);
        et_pwd = view.findViewById(R.id.et_2);
        btn_login = view.findViewById(R.id.btn_save_notice);
        tv_login_2_register = view.findViewById(R.id.tv_2_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = new User(et_username.getText().toString(), et_pwd.getText().toString());
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username", user.getUsername());
                    jsonObject.put("password", user.getPwd());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody body = RequestBody.create(jsonObject.toString(), JSON); // new
                HttpUtil.post("http://69d9-210-30-193-0.ngrok.io/user/login", body, new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String token = parseJsonWithJsonObject(response);
                        if(token != null){
                            Intent intent = new Intent(view.getContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });

        tv_login_2_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(registerFragment == null){
                    registerFragment = new RegisterFragment();
                }
                assert getFragmentManager() != null;
                Fragment fragment = getFragmentManager().findFragmentByTag("login");
                if(fragment != null){
                    getFragmentManager().beginTransaction().replace(R.id.fl_container, registerFragment).addToBackStack(null).commit();
//                    getFragmentManager().beginTransaction().hide(fragment).add(R.id.fl_container, registerFragment).addToBackStack(null).commitAllowingStateLoss();
                }else {
                    getFragmentManager().beginTransaction().replace(R.id.fl_container, registerFragment).addToBackStack(null).commit();
                }
            }
        });
    }

    class LoginRequest extends RequestTask {

    }

    private String parseJsonWithJsonObject(Response response) throws IOException {
        String responseData=response.body().string();
        try{
            JSONObject jsonObject = new JSONObject(responseData);
            return jsonObject.getJSONObject("data").getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}