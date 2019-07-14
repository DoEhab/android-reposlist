package com.repo.repolist.activity

import android.os.Bundle
import com.repo.repolist.R
import com.repo.repolist.fragment.HomeFragment
import com.repo.repolist.fragment.RepoListBaseFragment


class HomeActivity : RepoListBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        launchFragment(HomeFragment(),true)
    }
    //TODO: replace with Nav controller
    private fun launchFragment(fragment: RepoListBaseFragment, addToBackStack: Boolean) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction = transaction.replace(R.id.sub_content, fragment)

        if (addToBackStack) {
            transaction = transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }

}
