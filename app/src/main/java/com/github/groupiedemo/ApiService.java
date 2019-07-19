package com.github.groupiedemo;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.ApiResult;
import com.zhouyou.http.request.PostRequest;

import io.reactivex.Observable;

/**
 * @author nightkyb created at 2019/7/1 18:17
 */
public class ApiService {
    private static volatile ApiService apiService;
    private Gson gson;

    private ApiService() {
        gson = new GsonBuilder().create();
    }

    public static ApiService getInstance() {
        if (apiService == null) {
            synchronized (ApiService.class) {
                if (apiService == null) {
                    apiService = new ApiService();
                }
            }
        }

        return apiService;
    }

    public <T> Observable<T> request(@NonNull String url, @NonNull Object object, Class<T> clazz) {
        return request(url, object, clazz, null);
    }

    public <T> Observable<T> request(@NonNull String url, @NonNull Object object, Class<T> clazz, CacheMode cacheMode) {
        PostRequest postRequest = EasyHttp.post(url);

        if (cacheMode != null) {
            postRequest.cacheMode(cacheMode);
        }

        postRequest.cacheKey(url).upJson(gson.toJson(object)).execute(new SimpleCallBack<ApiResult<String>>() {

            @Override
            public void onError(ApiException e) {

            }

            @Override
            public void onSuccess(ApiResult<String> apiResult) {
                if (apiResult.getCode() == 1) {
                    // 业务成功
                }else{
                    // 业务失败
                }
            }
        });

        return postRequest.cacheKey(url).upJson(gson.toJson(object)).execute(String.class)
                .map(json -> gson.fromJson(json, clazz));



    }
}
