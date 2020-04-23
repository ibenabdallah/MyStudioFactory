package com.smartdevservice.mystudiofactory.ui.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.smartdevservice.mystudiofactory.R
import kotlinx.android.synthetic.main.view_loading_dialog.view.*

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

class MySFDialogHelper (private val context: Context) : BaseDialogHelper() {

    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.view_loading_dialog, null)
    }

    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)


    fun setTitle(title: CharSequence?): AlertDialog.Builder {
        dialogView.dialog_title.text = title
        return builder
    }

    fun setTitle(@StringRes titleRes: Int): AlertDialog.Builder =
        setTitle(context.getString(titleRes))

    fun setDescription(description: CharSequence?): AlertDialog.Builder {
        dialogView.dialog_description.text = description
        return builder
    }

    fun setDescription(@StringRes titleRes: Int): AlertDialog.Builder =
        setDescription(context.getString(titleRes))


    fun setPositiveButton(title: CharSequence?, func: (() -> Unit)? = null) {
        if (title != null) dialogView.dialog_positive_btn?.text = title
        dialogView.dialog_positive_btn?.setOnClickPositiveDialogButton(func)
        dialogView.dialog_positive_btn?.visibility = View.VISIBLE
    }

    fun setPositiveButton(@StringRes titleRes: Int, func: (() -> Unit)? = null) = setPositiveButton(context.getString(titleRes), func)

    fun setNegativeButton(title: CharSequence? = null, func: (() -> Unit)? = null) {
        if (title != null) dialogView.dialog_negative_btn?.text = title
        dialogView.dialog_negative_btn?.setOnClickNegativeDialogButton(func)
        dialogView.dialog_negative_btn?.visibility = View.VISIBLE
    }

    fun setNegativeButton(@StringRes titleRes: Int, func: (() -> Unit)? = null) = setNegativeButton(context.getString(titleRes), func)

    private fun Button.setOnClickPositiveDialogButton(func: (() -> Unit)?) {
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
    }

    private fun Button.setOnClickNegativeDialogButton(func: (() -> Unit)?) {
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
    }
    fun showLoading(showLoading: Boolean): AlertDialog.Builder {
        dialogView.dialog_progress_bar.visibility = when (showLoading) {
            true -> View.VISIBLE
            else -> View.GONE
        }
        return builder
    }
}


abstract class BaseDialogHelper {

    abstract val dialogView: View
    abstract val builder: AlertDialog.Builder

    //  required bools
    private var cancelable: Boolean = true

    //  dialog
    open var dialog: AlertDialog? = null

    //  dialog create
    open fun create(): AlertDialog {
        dialog = builder
            .setCancelable(cancelable)
            .create()

        return dialog as AlertDialog
    }

    open fun setCancelable(isCancelable: Boolean): AlertDialog.Builder? {
        cancelable = isCancelable
        return builder
    }
}