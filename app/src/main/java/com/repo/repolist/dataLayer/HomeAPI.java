package com.repo.repolist.dataLayer;

import com.repo.repolist.model.UserRepoData;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by Doha on 09/07/19.
 */
public interface HomeAPI {
    Flowable<List<UserRepoData>> getRepoData(String userName);
}
