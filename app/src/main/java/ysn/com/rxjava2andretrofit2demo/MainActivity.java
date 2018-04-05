package ysn.com.rxjava2andretrofit2demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ysn.com.rxjava2andretrofit2demo.adpter.JokeAdapter;
import ysn.com.rxjava2andretrofit2demo.bean.Joke;
import ysn.com.rxjava2andretrofit2demo.http.BaseObserver;
import ysn.com.rxjava2andretrofit2demo.http.RetrofitFactory;

public class MainActivity extends BaseActivity {
    private JokeAdapter jokeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        jokeAdapter = new JokeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(jokeAdapter);

        MaterialRefreshLayout materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getJokes();
                        materialRefreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });

        getJokes();
    }

    public void getJokes() {
        Observable<List<Joke>> observable = RetrofitFactory.getInstance().getJokes();
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseObserver<List<Joke>>(MainActivity.this) {
            @Override
            protected void onHandleSuccess(List<Joke> jokes) {
                jokeAdapter.setNewData(jokes);
                Log.d("test", "获取数据完成" + jokes.size());
            }
        });
    }
}
