package com.kkk.mvpdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kkk.mvpdemo.R;
import com.kkk.mvpdemo.model.Article;
import com.kkk.mvpdemo.presenter.ArticlePresenter;
import com.kkk.mvpdemo.presenter.IPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private IPresenter mPresenter;
    private ArrayList<Article> mArticles;
    private ArticleAdapter mArticleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        mArticles = new ArrayList<>();
        mArticleAdapter = new ArticleAdapter();
        mRecyclerView.setAdapter(mArticleAdapter);

        mPresenter = new ArticlePresenter(this);
        mPresenter.loadArticle();
    }

    @Override
    public void showProgressbar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void dismissProgressbar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void showArcticle(final List<Article> articles) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mArticles.clear();
                mArticles.addAll(articles);
                mArticleAdapter.notifyDataSetChanged();
            }
        });
    }

    class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final Article article = mArticles.get(position);
            holder.title.setText(article.title);
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("article", article);

                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mArticles.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public MyViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
