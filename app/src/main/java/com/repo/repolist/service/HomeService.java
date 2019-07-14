package com.repo.repolist.service;

import com.repo.repolist.model.UserRepoData;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import java.util.List;

/**
 * Created by Doha on 09/07/19.
 */
public interface HomeService {
    @Headers("Content-Type: application/json")
    @GET("users/{name}/repos")
    Flowable<List<UserRepoData>> getRepoData(@Path("name") String userName);

}
