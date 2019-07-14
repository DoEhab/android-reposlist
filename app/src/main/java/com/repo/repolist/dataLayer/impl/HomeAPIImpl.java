package com.repo.repolist.dataLayer.impl;

import com.repo.repolist.dataLayer.HomeAPI;
import com.repo.repolist.model.UserRepoData;
import com.repo.repolist.service.HomeService;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by Doha on 09/07/19.
 */
public class HomeAPIImpl implements HomeAPI {
    private final HomeService service;

    HomeAPIImpl(HomeService service){
        this.service = service;
    }

    @Override
    public Flowable<List<UserRepoData>> getRepoData(String userName) {
        return service.getRepoData(userName);
    }
}
