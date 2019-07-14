package com.repo.repolist;

import com.repo.repolist.activity.HomeActivity;
import com.repo.repolist.fragment.HomeFragment;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by Doha on 09/07/19.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(ReposListApplication app);
    void inject(HomeFragment homeFragment);
}
