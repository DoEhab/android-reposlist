package com.repo.repolist.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.repo.repolist.model.UserRepoData
import io.reactivex.Flowable

/**
 * Created by Doha on 12/07/19.
 */
@Dao
interface RepoListDao {
    @Query("SELECT * FROM UserRepoData")
    fun getAll(): Flowable<List<UserRepoData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( userRepoData: List<UserRepoData>)

    @Query("DELETE FROM UserRepoData")
    fun nukeTable()

}