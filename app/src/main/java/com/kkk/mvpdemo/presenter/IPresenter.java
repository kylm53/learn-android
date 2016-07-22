package com.kkk.mvpdemo.presenter;

import com.kkk.mvpdemo.model.Article;

import java.util.List;

/**
 * Created by CTC on 2016/7/21.
 */
public interface IPresenter {
    void loadArticle();
    void onLoadComplete(List<Article> articles);
}
