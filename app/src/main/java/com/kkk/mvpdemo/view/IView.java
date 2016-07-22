package com.kkk.mvpdemo.view;

import com.kkk.mvpdemo.model.Article;

import java.util.List;

/**
 * Created by CTC on 2016/7/21.
 */
public interface IView {
    void showArcticle(List<Article> articles);
}
