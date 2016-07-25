package com.kkk.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.kkk.retrofitdemo.bean.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);

        Call<List<Repo>> repoCall = APIUtils.service.listRepos("kylm53");
        repoCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> repos = response.body();
                StringBuilder builder = new StringBuilder();
                for (Repo repo : repos) {
                    Log.i(TAG, repo.getFull_name());
                    builder.append(repo.getFull_name()).append("\n");
                }
                mTextView.setText(builder.toString());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.i(TAG, t.getLocalizedMessage());
            }
        });
    }
}
