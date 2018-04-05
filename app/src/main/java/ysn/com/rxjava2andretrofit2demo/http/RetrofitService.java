package ysn.com.rxjava2andretrofit2demo.http;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ysn.com.rxjava2andretrofit2demo.bean.Joke;

public interface RetrofitService {

    @GET("xiaohua.json")
    Observable<List<Joke>> getJokes();
}
