package com.vivanov.currenciesconverter.presentation.core.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import com.example.currenciesconverter.R

const val EXTRA_DIALOG_TITLE: String = "EXTRA_DIALOG_TITLE"
const val EXTRA_DIALOG_MESSAGE: String = "EXTRA_DIALOG_MESSAGE"

class InfoDialogFragment : AppCompatDialogFragment() {

    companion object {

        fun createInstance(title: String, message: String): InfoDialogFragment =
            InfoDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_DIALOG_TITLE, title)
                    putString(EXTRA_DIALOG_MESSAGE, message)
                }
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments?.getString(EXTRA_DIALOG_TITLE)
        val message = arguments?.getString(EXTRA_DIALOG_MESSAGE)
        return AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.b_ok) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}