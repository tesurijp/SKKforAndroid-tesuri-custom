package org.tesuri.android.ime.skk.engine

import android.os.Build
import org.tesuri.android.ime.skk.R
import org.tesuri.android.ime.skk.hankaku2zenkaku

// 全角英数モード
object SKKZenkakuState : SKKState {
    override val isTransient = false
    override val icon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        R.drawable.ic_full_alphabet
    } else {
        R.drawable.immodeic_full_alphabet
    }

    override fun handleKanaKey(context: SKKEngine) {
        context.changeState(SKKHiraganaState)
    }

    override fun processKey(context: SKKEngine, pcode: Int) {
        context.commitTextSKK(hankaku2zenkaku(pcode).toChar().toString(), 1)
    }

    override fun afterBackspace(context: SKKEngine) {}

    override fun handleCancel(context: SKKEngine): Boolean {
        return SKKHiraganaState.handleCancel(context)
    }
}
