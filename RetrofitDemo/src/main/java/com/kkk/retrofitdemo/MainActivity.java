package com.kkk.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.kkk.retrofitdemo.bean.Repo;
import com.kkk.retrofitdemo.bean.SearchRepoResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);

        //默认CallAdapter
        mTextView.setText("search kylm53's repos:\n");
        final Call<List<Repo>> repoCall = APIUtils.service.listRepos("kylm53");
        repoCall.enqueue(listRepoCallback);
    }

    //查看user所有repo的callback
    Callback<List<Repo>> listRepoCallback = new Callback<List<Repo>>() {
        @Override
        public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
            List<Repo> repos = response.body();
            StringBuilder builder = new StringBuilder();
            for (Repo repo : repos) {
                Log.i(TAG, repo.getFull_name());
                builder.append(repo.getFull_name())
                        .append("\n")
                        .append(repo.getHtml_url())
                        .append("\n\n");
            }
            mTextView.setText(mTextView.getText() + builder.toString());

            //search rxjava
            searchRepo("rxandroid");
        }

        @Override
        public void onFailure(Call<List<Repo>> call, Throwable t) {
            mTextView.setText(mTextView.getText() + t.getLocalizedMessage());
            Log.i(TAG, t.getLocalizedMessage());
        }
    };

    private void searchRepo(String keyword) {
        //与rxjava结合使用
        mTextView.setText(mTextView.getText() + "\n" + "search rxandroid:");
        Observable<SearchRepoResult> searchRepos = APIUtils.service.
                searchRepos(keyword, "stars", "desc");

        searchRepos.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<SearchRepoResult, String>() {
                    @Override
                    public String call(SearchRepoResult repoResult) {
                        List<Repo> repos = repoResult.getItems();
                        mTextView.setText(mTextView.getText() + "total(" + repoResult.getTotal_count() + ")\n");
                        StringBuilder builder = new StringBuilder();
                        for (Repo repo : repos) {
                            Log.i(TAG, repo.getFull_name());
                            builder.append(repo.getFull_name())
                                    .append("\n")
                                    .append(repo.getHtml_url())
                                    .append("\n\n");
                        }
                        return builder.toString();
                    }
                })
                .subscribe(searchRepoResultSubscriber);
    }

    //搜索repo的subscriber
    Subscriber<String> searchRepoResultSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            mTextView.setText(mTextView.getText() + "\n" + "completed!!!");
            Toast.makeText(MainActivity.this, "complete", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            mTextView.setText(mTextView.getText() + "\n" + e.getLocalizedMessage());
            Log.e(TAG, e.getLocalizedMessage());
        }

        @Override
        public void onNext(String repoResult) {
            mTextView.setText(mTextView.getText() + repoResult);
        }
    };
}
