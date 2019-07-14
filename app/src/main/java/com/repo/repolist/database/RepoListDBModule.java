package com.repo.repolist.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.repo.repolist.repository.RepoListDataSource;
import com.repo.repolist.repository.RepoListRepository;
import com.repo.repolist.viewModel.HomeViewModel;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Doha on 13/07/19.
 */
@Module
public class RepoListDBModule {

    @Singleton
    @Provides
     RepoListDatabase provideRepoListDatabase(Context context) {
        return Room.databaseBuilder(context,
                RepoListDatabase.class, "db_name")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
     RepoListDao provideRepoListDao(RepoListDatabase repoListDatabase)
    {
        return repoListDatabase.repoListDao();
    }

    @Singleton
    @Provides
    RepoListRepository provideRepoListRepository(RepoListDao repoListDao, HomeViewModel viewModel)
    {
        return new RepoListDataSource(repoListDao,viewModel);
    }
}
