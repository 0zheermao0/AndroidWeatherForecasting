package edu.neu.weatherforecasting.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import edu.neu.weatherforecasting.data.model.User;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class RequestTask extends AsyncTask<String, String, TaskOutput> {
    private Context context;
    private Activity activity;
    private ProgressDialog progressDialog;

    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    protected RequestTask() {
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected TaskOutput doInBackground(String... strings) {
        return null;
    }

    public String login(String url, User user, Callback callback) throws IOException {
        StringBuilder res = new StringBuilder();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getPwd());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), JSON); // new
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String resStr = Objects.requireNonNull(response.body()).string();
                    res.append(resStr);
                    if (response.isSuccessful()) {
                        Log.i("WY","打印响应的数据：" + resStr);
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return res.toString();
    }
}
