package com.putragandad.urbanfarm.fragments.dialogfragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.putragandad.urbanfarm.R

class InDevelopmentLoginFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.indevelopment_login_text)
                .setPositiveButton("MENGERTI") { dialog, id ->
                    // START THE GAME!
                }
            // Create the AlertDialog object and return it.
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}