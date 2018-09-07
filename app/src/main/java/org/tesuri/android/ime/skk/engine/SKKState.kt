package org.tesuri.android.ime.skk.engine

interface SKKState {
    val isTransient: Boolean
    val icon: Int
    fun handleKanaKey(context: SKKEngine)
    fun processKey(context: SKKEngine, pcode: Int)
    fun afterBackspace(context: SKKEngine)
    fun handleCancel(context: SKKEngine): Boolean
}
