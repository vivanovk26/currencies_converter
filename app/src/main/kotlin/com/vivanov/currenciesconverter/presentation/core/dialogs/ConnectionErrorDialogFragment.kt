package com.vivanov.currenciesconverter.presentation.core.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatDialogFragment
import com.example.currenciesconverter.R

class ConnectionErrorDialogFragment : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle(R.string.error)
            .setMessage(R.string.error_connection)
            .setPositiveButton(R.string.b_ok) { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton(R.string.b_settings) { _, _ ->
                startActivity(Intent(Settings.ACTION_SETTINGS))
            }
            .create()
    }
}