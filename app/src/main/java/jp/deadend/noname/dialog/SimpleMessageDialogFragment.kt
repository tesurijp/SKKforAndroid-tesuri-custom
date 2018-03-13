package jp.deadend.noname.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

import jp.deadend.noname.skk.R

class SimpleMessageDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
                .setMessage(arguments.getString("message"))
                .setPositiveButton(R.string.label_OK) { _, _ -> dismiss() }
                .create()
    }

    companion object {
        fun newInstance(message: String): SimpleMessageDialogFragment {
            val frag = SimpleMessageDialogFragment()
            val args = Bundle()
            args.putString("message", message)
            frag.arguments = args
            return frag
        }
    }
}
