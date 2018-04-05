package ysn.com.rxjava2andretrofit2demo.adpter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import ysn.com.rxjava2andretrofit2demo.R;
import ysn.com.rxjava2andretrofit2demo.bean.Joke;

public class JokeAdapter extends BaseQuickAdapter<Joke, BaseViewHolder> {

    public JokeAdapter() {
        super(R.layout.item_joke);
    }

    @Override
    protected void convert(BaseViewHolder helper, Joke joke) {
        helper.setText(R.id.title, joke.getTitle());
        helper.setText(R.id.content, joke.getContent());
    }
}
