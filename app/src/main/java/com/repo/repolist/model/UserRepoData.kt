package com.repo.repolist.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Doha on 12/07/19.
 */
data class User(

    @SerializedName("login")
    var userName: String,
    @SerializedName("avatar_url")
    var userImg: String
)

@Entity
data class UserRepoData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @Embedded
    @SerializedName("owner")
    var user: User,

    @SerializedName("name")
    var repoName: String,

    @SerializedName("forks_count")
    var forkCount: Int,

    var language: String?,

    var description: String,

    @SerializedName("html_url")
    var projectUrl: String,

    @SerializedName("created_at")
    var repoCreationDate: String



)