package com.Icar05.githubsearch.presentation.util

import android.app.AlertDialog
import android.content.Context
import com.Icar05.githubsearch.R

class DialogUtil {
    
    fun showErrorOnDialog(text: String, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(text)
        builder.setTitle(context.getString(R.string.error_title))
        builder.setPositiveButton(context.getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }
    
}