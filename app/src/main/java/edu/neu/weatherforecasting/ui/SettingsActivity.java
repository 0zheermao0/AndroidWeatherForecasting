package edu.neu.weatherforecasting.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import edu.neu.weatherforecasting.R;
import edu.neu.weatherforecasting.config.Config;
import edu.neu.weatherforecasting.data.model.User;
import edu.neu.weatherforecasting.http.HttpUtil;
import edu.neu.weatherforecasting.utils.ToastUtil;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SettingsActivity extends AppCompatActivity {
    private EditText et_old_pwd, et_new_pwd, et_again, et_ch_name, et_username, et_age;
    private Button btn_submit;
    private RadioGroup rg_sex;
    private RadioButton rb_male, rb_female;
    private Integer sex = 0;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        et_username = findViewById(R.id.et_username);
        et_old_pwd = findViewById(R.id.et_old_pwd);
        et_new_pwd = findViewById(R.id.et_new_pwd);
        et_again = findViewById(R.id.et_again);
        et_ch_name = findViewById(R.id.et_ch_name);
        et_age = findViewById(R.id.et_age);
        rg_sex = findViewById(R.id.rg_sex);
        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);
        btn_submit = findViewById(R.id.btn_submit);

        mSharedPreferences = this.getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                System.out.println(checkId);
//                ToastUtil.showMsg(SettingsActivity.this, checkId + "被点了");
                sex = checkId;
            }
        });

        Bundle bundle = this.getIntent().getExtras();
        user = (User) bundle.getSerializable("user");

        if(!user.getUsername().equals(null)){
            et_username.setText(user.getUsername());
        }
        if(!user.getchName().equals(null)){
            et_ch_name.setText(user.getchName());
        }
        if(!mSharedPreferences.getString("age", "default").equals("")){
            String age = mSharedPreferences.getString("age", "0");
            et_age.setText(age);
//            ToastUtil.showMsg(SettingsActivity.this, "年龄是"+ mSharedPreferences.getString("age", "default"));
        }
        if(mSharedPreferences.getInt("sex", -1) == 2131296652 || mSharedPreferences.getInt("sex", -1) == 2131296651){
            sex = mSharedPreferences.getInt("sex", -1);
            rg_sex.check(sex);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    private void submit(){
        //为了演示（
        if(et_old_pwd.getText().toString().equals("")){
            user.setUsername(et_username.getText().toString());
            user.setchName(et_ch_name.getText().toString());
            user.setAge(et_age.getText().toString());
            user.setSex(sex);
            System.out.println(user);
            if(!user.getAge().equals("") && !user.getSex().equals(-1)){
                mEditor.putString("age", user.getAge());
                mEditor.putInt("sex", user.getSex());
                mEditor.commit();
                mEditor.apply();
            }
            this.finish();
            return;
        }
        if(!et_new_pwd.getText().toString().equals(et_again.getText().toString())){
            ToastUtil.showMsg(this, "两次输入的新密码不一致！");
            return;
        }
        if(!et_old_pwd.getText().toString().equals(user.getPwd())){
            ToastUtil.showMsg(this, "旧密码输入错误");
            return;
        }

        user.setUsername(et_username.getText().toString());
        user.setPwd(et_again.getText().toString());
        user.setchName(et_ch_name.getText().toString());

        user.setAge(et_age.getText().toString());
        user.setSex(sex);

        mEditor.putString("age", user.getAge());
        mEditor.putInt("sex", user.getSex());
        mEditor.commit();
        mEditor.apply();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getPwd());
            jsonObject.put("ch_name", user.getchName());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), JSON); // new
        HttpUtil.post(Config.domain + "/user/update", body, new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }
}