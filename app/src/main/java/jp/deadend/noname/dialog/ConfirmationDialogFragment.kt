package jp.deadend.noname.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

import jp.deadend.noname.skk.R

class ConfirmationDialogFragment : DialogFragment() {
    private var mListener: Listener? = null

    interface Listener {
        fun onPositiveClick()
        fun onNegativeClick()
    }

    fun setListener(listener: Listener) {
        this.mListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
                .setMessage(arguments.getInt("message"))
                .setCancelable(true)
                .setPositiveButton(R.string.label_OK) { _, _ ->
                    mListener?.onPositiveClick()
                    dismiss()
                }
                .setNegativeButton(R.string.label_CANCEL) { _, _ ->
                    mListener?.onNegativeClick()
                    dismiss()
                }
                .create()
    }

    companion object {
        fun newInstance(message: Int): ConfirmationDialogFragment {
            val frag = ConfirmationDialogFragment()
            val args = Bundle()
            args.putInt("message", message)
            frag.arguments = args
            return frag
        }
    }
}
