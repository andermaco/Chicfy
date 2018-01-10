package com.andermaco.test.networking;

import com.andermaco.test.BuildConfig;
import com.andermaco.test.di.scopes.PerApplication;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by andermaco@gmail.com on 26/12/17.
 *
 * Define network providers
 */
@Module
public class NetworkModule {
    File cacheFile;

    public NetworkModule(File cacheFile) {
        this.cacheFile = cacheFile;
    }

    @Provides
    @PerApplication
    public Retrofit provideCall() {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpLoggingInterceptor logginInterceptor = new HttpLoggingInterceptor();
        logginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Customize the request
                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .removeHeader("Pragma")
                            .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                            .build();
                    okhttp3.Response response = null;
                    try {
                        response = chain.proceed(request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    response.cacheResponse();
                    // Customize or return the response
                    return response;
                }
            })
            .addInterceptor(logginInterceptor)
            .cache(cache)
            .build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                // Use an specific RxJava2 IO scheduler to avoid NetworkOnMainThreadException exception
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @PerApplication
    @SuppressWarnings("unused")
    public NetworkService providesNetworkService(
             Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }
    @Provides
    @PerApplication
    @SuppressWarnings("unused")
    public Service providesService(
            NetworkService networkService) {
        return new Service(networkService);
    }

}
