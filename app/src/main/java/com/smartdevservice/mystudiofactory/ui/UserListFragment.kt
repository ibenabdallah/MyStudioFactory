package com.smartdevservice.mystudiofactory.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.smartdevservice.mystudiofactory.R
import kotlinx.android.synthetic.main.fragment_user.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [OnUserListener] interface.
 */
class UserListFragment : Fragment() {

    private val userViewModel: UserViewModel by sharedViewModel()
    private lateinit var listener: OnUserListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleView.apply {
            layoutManager = LinearLayoutManager(this@UserListFragment.context)
        }


        userViewModel.usersState.observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })


        swipeRefresh.setOnRefreshListener{
            Timber.d("Start Swipe Refresh")
            userViewModel.syncFullUser()
        }

    }

    private fun updateUI(state: MyViewModelStateSealed?) {
        Timber.d("Users = ${state?.users}")
        swipeRefresh?.isRefreshing = false
        return when(state){
            is MyViewModelStateSuccess -> {
                Timber.d("My View Model State Success")
                recycleView.adapter =
                    context?.let {
                        MyUserRecyclerViewAdapter(
                            it,
                            state.users,
                            listener
                        )
                    }
            }
            MyViewModelStateNotFound -> {
                Timber.d("My View Model State Not Found")
            }
            else -> {
                Timber.d("My View Model State si null")
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnUserListener) {
            listener = context
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UserListFragment()
    }
}
