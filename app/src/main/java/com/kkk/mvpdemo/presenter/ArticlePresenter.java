package com.kkk.mvpdemo.presenter;

import com.kkk.mvpdemo.model.Article;
import com.kkk.mvpdemo.view.IView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by CTC on 2016/7/22.
 */
public class ArticlePresenter implements IPresenter {
    public static final String Url = "http://www.jcodecraeer.com/essence/";
    public static final String Main_Url = "http://www.jcodecraeer.com";
    private IView mArticleView;
    private OkHttpClient mClient;

    public ArticlePresenter(IView view) {
        mClient = new OkHttpClient.Builder()
                .build();
        mArticleView = view;
    }

    @Override
    public void loadArticle() {
        mArticleView.showProgressbar();

        get(Url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    byte[] bytes = response.body().bytes(); //获取数据的bytes
                    String result = new String(bytes, "gb2312");

                    Document document = Jsoup.parse(result);
                    if (call != null) {
                        Elements elements = document.select("ul.archive-list li.archive-item");
                        ArrayList<Article> articles = new ArrayList<Article>();
                        for (Element ele : elements) {
                            Element item = ele.select("div.archive-detail h3 a").first();
                            String link = Main_Url + item.attr("href");
                            String title = item.text();

                            Article article = new Article(title, link);
                            articles.add(article);

                            onLoadComplete(articles);

                            System.out.println(title + "\n" + link);
                            System.out.println("====================================");
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onLoadComplete(final List<Article> articles) {
        mArticleView.dismissProgressbar();
        mArticleView.showArcticle(articles);
    }

    private Call executeRequest(final Request request, final Callback callback) {
        Call call = mClient.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public void get(String url, Callback callback) {
        Request request = new Request.Builder().url(url).get().build();
        executeRequest(request, callback);
    }
}
