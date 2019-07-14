package com.repo.repolist.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.repo.repolist.model.UserRepoData

/**
 * Created by Doha on 12/07/19.
 */

@Database(entities = [UserRepoData::class], version = 1, exportSchema = false)
abstract class RepoListDatabase : RoomDatabase() {
    abstract fun repoListDao(): RepoListDao
}