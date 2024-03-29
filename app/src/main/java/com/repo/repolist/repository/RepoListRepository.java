package com.repo.repolist.repository;

import com.repo.repolist.model.UserRepoData;
import io.reactivex.Flowable;

import java.util.List;

/**
 * Created by Doha on 13/07/19.
 */
public interface RepoListRepository {
    Flowable<List<UserRepoData>> getData(String userName);
}
