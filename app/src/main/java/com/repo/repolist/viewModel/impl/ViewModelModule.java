package com.repo.repolist.viewModel.impl;

import com.repo.repolist.dataLayer.HomeAPI;
import com.repo.repolist.viewModel.HomeViewModel;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Doha on 09/07/19.
 */
@Module
public class ViewModelModule {

    @Provides
    @Singleton
    HomeViewModel provideHomeViewModel(HomeAPI api) {
        return new HomeViewModelImpl(api);
    }
}

