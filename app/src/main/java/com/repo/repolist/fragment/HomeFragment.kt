package com.repo.repolist.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nostra13.universalimageloader.core.ImageLoader
import com.repo.repolist.ReposListApplication
import com.repo.repolist.adapter.RepoDataAdapter
import com.repo.repolist.R
import com.repo.repolist.viewModel.HomeViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import com.repo.repolist.Repository.RepoListRepository
import com.repo.repolist.model.UserRepoData


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : RepoListBaseFragment() {

    @Inject
    lateinit var dataRepository: RepoListRepository
    private val compositeDisposable: CompositeDisposable? = CompositeDisposable()
    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = activity?.application as ReposListApplication
        application.getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarWithoutBackBtn(getString(R.string.home_fragment_title))
        subscribeToGetData()
    }

    private fun subscribeToGetData() {
        //TODO:take user name as input
        val userName = "johnsundell"
        showProgressDialog()
        compositeDisposable?.add(dataRepository.getData(userName).subscribe(
            {
                dismissProgressDialog()
                displayRepoData(it)

            }, {
                dismissProgressDialog()
            })
        )
    }

    private fun displayRepoData(it: List<UserRepoData>) {
        rv_repo_data.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_repo_data.adapter = RepoDataAdapter(context, imageLoader, it) { repoLink ->

            openRepoInBrowser(repoLink)
        }
    }

    private fun openRepoInBrowser(repoLink: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(repoLink))
        startActivity(browserIntent)
    }

    override fun onDestroy() {
        super.onDestroy();
        compositeDisposable?.dispose()
    }

}
