package com.smartdevservice.mystudiofactory.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.smartdevservice.mystudiofactory.R
import com.smartdevservice.mystudiofactory.domain.User
import kotlinx.android.synthetic.main.fragment_details_user.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM = "param_user"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsUserFragment : Fragment() {

    private val userViewModel: UserViewModel by sharedViewModel()
    private var userId: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.user.observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })

        userViewModel.findUserById(userId)

    }

    private fun updateUI(user: User?) {

        user_name.text = user?.name
        user_description.text = user?.description
        user_hired.text = user?.hired

        val circularProgressDrawable = CircularProgressDrawable(context!!)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide.with(context!!)
            .load(user?.picture)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(circularProgressDrawable)
            .into(user_picture)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param userId user id in parameter.
         * @return A new instance of fragment DetailsUserFragment.
         */
        @JvmStatic
        fun newInstance(userId: String?) =
            DetailsUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, userId)
                }
            }
    }
}
