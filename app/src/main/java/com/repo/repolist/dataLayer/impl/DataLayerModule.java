package com.repo.repolist.dataLayer.impl;

import com.repo.repolist.dataLayer.HomeAPI;
import com.repo.repolist.service.HomeService;
import com.repo.repolist.util.RetrofitFactory;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Doha on 09/07/19.
 */
@Module
public class DataLayerModule {

    @Singleton
    @Provides
    HomeService providesHomeService() {
        return RetrofitFactory.createService(HomeService.class);
    }

    @Singleton
    @Provides
    HomeAPI providesHomeAPI(HomeService service) {
        return new HomeAPIImpl(service);
    }
}
