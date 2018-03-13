package jp.deadend.noname.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.EditText

import jp.deadend.noname.skk.R

class TextInputDialogFragment : DialogFragment() {
    private var mListener: Listener? = null
    private lateinit var mEditText: EditText
    private var mSingleLine = false

    interface Listener {
        fun onPositiveClick(result: String)
        fun onNegativeClick()
    }

    fun setListener(listener: Listener) {
        this.mListener = listener
    }

    fun setSingleLine(value: Boolean) {
        mSingleLine = value
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mEditText = EditText(activity)
        if (mSingleLine) {
            mEditText.setSingleLine()
        }

        return AlertDialog.Builder(activity)
                .setMessage(arguments.getString("message"))
                .setView(mEditText)
                .setCancelable(true)
                .setPositiveButton(R.string.label_OK) { _, _ ->
                    mListener?.onPositiveClick(mEditText.text.toString())
                    dismiss()
                }
                .setNegativeButton(R.string.label_CANCEL) { _, _ ->
                    mListener?.onNegativeClick()
                    dismiss()
                }
                .create()
    }

    companion object {
        fun newInstance(message: String): TextInputDialogFragment {
            val frag = TextInputDialogFragment()
            val args = Bundle()
            args.putString("message", message)
            frag.arguments = args
            return frag
        }
    }
}
