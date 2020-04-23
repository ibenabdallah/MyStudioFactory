package com.smartdevservice.mystudiofactory.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.smartdevservice.mystudiofactory.R
import com.smartdevservice.mystudiofactory.domain.User
import com.smartdevservice.mystudiofactory.ui.utils.MySFDialogHelper
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() , OnUserListener {

    private val userViewModel: UserViewModel by viewModel()

    private lateinit var myDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container_fragment, UserListFragment.newInstance()).commit()

        myDialog = createDialog {
            setCancelable(false)
            setTitle(R.string.progress_dialog_title)
            setDescription(R.string.progress_dialog_content)
            showLoading(true)
        }

        userViewModel.usersState.observe(this, Observer {
            Timber.d("Dismiss dialog")
            myDialog.dismiss()
        })

        userViewModel.isNetworking.observe(this, Observer {
            Timber.d("isNetworking = $it")
            myDialog.dismiss()
        })

        myDialog.show()

    }

    override fun onUserClick(user: User) {
        Timber.d("user = $user")
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container_fragment, DetailsUserFragment.newInstance(user.id))
            .addToBackStack("DetailsUserFragment")
            .commit()
    }

    private inline fun createDialog(func: MySFDialogHelper.() -> Unit): AlertDialog {
        return MySFDialogHelper(
                this
        ).apply { func() }.create()
    }

}

interface OnUserListener {
    fun onUserClick(user: User)
}
