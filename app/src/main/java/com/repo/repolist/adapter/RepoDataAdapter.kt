package com.repo.repolist.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nostra13.universalimageloader.core.ImageLoader
import com.repo.repolist.R
import com.repo.repolist.model.UserRepoData
import kotlinx.android.synthetic.main.repo_list_item.view.*


/**
 * Created by Doha on 10/07/19.
 */
class RepoDataAdapter(
    val context: Context?,
    private val imageLoader: ImageLoader,
    private val userRepoData: List<UserRepoData>,
    private val openRepoLink: (String) -> Unit
) :
    RecyclerView.Adapter<RepoDataAdapter.ItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.repo_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {

        imageLoader.displayImage(userRepoData[position].user.userImg, holder.items.img_user_avatar)

        holder.items.txt_repo_title.text = context?.getString(R.string.repo_title).plus(userRepoData[position].repoName)

        holder.items.txt_repo_description.text =
            context?.getString(R.string.repo_description).plus(userRepoData[position].description)

        holder.items.txt_fork_count.text =
            context?.getString(R.string.forks_count).plus(userRepoData[position].forkCount.toString())

        if (userRepoData[position].language != null) {
            holder.items.txt_language.text = context?.getString(R.string.language).plus(userRepoData[position].language)
        } else {
            holder.items.txt_language.visibility = View.GONE
        }

        holder.items.setOnClickListener {
            openRepoLink.invoke(userRepoData[position].projectUrl)
        }

        holder.items.txt_repo_creation_date.text =
            context?.getString(R.string.repo_creation_date).plus(userRepoData[position].repoCreationDate)
    }

    override fun getItemCount(): Int {
        return userRepoData.size
    }

    class ItemsViewHolder(val items: View) : RecyclerView.ViewHolder(items) {

    }
}

