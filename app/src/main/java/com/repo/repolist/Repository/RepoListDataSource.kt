package com.repo.repolist.Repository

import com.repo.repolist.database.RepoListDao
import com.repo.repolist.model.UserRepoData
import com.repo.repolist.viewModel.HomeViewModel
import io.reactivex.Flowable
import org.intellij.lang.annotations.Flow
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Doha on 13/07/19.
 */
class RepoListDataSource(
    private val repoListDao: RepoListDao,
    val viewModel: HomeViewModel
) : RepoListRepository {

    override fun getData(userName: String): Flowable<List<UserRepoData>> {
        return getNetworkData(userName).publish { networkResponse ->
            Flowable.merge(
                networkResponse,
                repoListDao.getAll().takeUntil(networkResponse)
            )
        }
    }

    private fun getNetworkData(userName: String): Flowable<List<UserRepoData>> {
        return viewModel.getRepoData(userName).doOnNext { response -> repoListDao.insertAll(response) }
    }
}