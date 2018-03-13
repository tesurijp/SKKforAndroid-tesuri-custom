package jp.deadend.noname.skk

import android.os.Bundle
import android.content.Context
import android.preference.CheckBoxPreference
import android.preference.Preference.OnPreferenceClickListener
import android.preference.PreferenceActivity
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatDelegate
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.skkprefs.*

class SKKPrefs : PreferenceActivity() {
    private val delegate: AppCompatDelegate by lazy {
        AppCompatDelegate.create(this, null)
    }

//    private var mDelegate: AppCompatDelegate? = null
//    private val delegate: AppCompatDelegate
//        get() {
//            if (mDelegate == null) {
//                mDelegate = AppCompatDelegate.create(this, null)
//            }
//            return requireNotNull(mDelegate) { "BUG: mDelegate is null!" }
//        }

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        delegate.installViewFactory()
        delegate.onCreate(icicle)
        delegate.setContentView(R.layout.skkprefs)
        delegate.setSupportActionBar(pref_toolbar)
        addPreferencesFromResource(R.xml.prefs)

        val stickyPr = findPreference(getString(R.string.prefkey_sticky_meta)) as CheckBoxPreference
        val sandsPr = findPreference(getString(R.string.prefkey_sands)) as CheckBoxPreference
        stickyPr.onPreferenceClickListener = OnPreferenceClickListener {
            sandsPr.isEnabled = !stickyPr.isChecked
            true
        }
        sandsPr.onPreferenceClickListener = OnPreferenceClickListener {
            stickyPr.isEnabled = !sandsPr.isChecked
            true
        }

        if (stickyPr.isChecked) {
            sandsPr.isEnabled = false
        } else if (sandsPr.isChecked) {
            stickyPr.isEnabled = false
        }
    }

    override fun onPause() {
        super.onPause()

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.sendAppPrivateCommand(null, SKKService.ACTION_READ_PREFS, null)
    }

    companion object {
        fun getKutoutenType(context: Context): String {
            return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.prefkey_kutouten_type), "en")
        }

        fun getUseCandidatesView(context: Context): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.prefkey_use_candidates_view), true)
        }

        fun getCandidatesSize(context: Context): Int {
            return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.prefkey_candidates_size), 18)
        }

        fun getKanaKey(context: Context): Int {
            return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.prefkey_kana_key), 612)
            // 612はCtrl+j
        }

        fun getCancelKey(context: Context): Int {
            return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.prefkey_cancel_key), 564)
            // 564はCtrl+g
        }

        fun getToggleKanaKey(context: Context): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.prefkey_toggle_kana_key), true)
        }

        fun getFlickSensitivity(context: Context): Int {
            return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.prefkey_flick_sensitivity), 30)
        }

        fun getCurveSensitivity(context: Context): String {
            return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.prefkey_curve_sensitivity), "high")
        }

        fun getUseSoftKey(context: Context): String {
            return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.prefkey_use_softkey), "auto")
        }

        fun getUsePopup(context: Context): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.prefkey_use_popup), true)
        }

        fun getFixedPopup(context: Context): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.prefkey_fixed_popup), true)
        }

        fun getUseSoftCancelKey(context: Context): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.prefkey_use_soft_cancel_key), false)
        }

        fun getKeyHeightPort(context: Context): Int {
            return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.prefkey_key_height_port), 30)
        }

        fun getKeyHeightLand(context: Context): Int {
            return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.prefkey_key_height_land), 30)
        }

        fun getKeyWidthPort(context: Context): Int {
            return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.prefkey_key_width_port), 100)
        }

        fun getKeyWidthLand(context: Context): Int {
            return PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.prefkey_key_width_land), 100)
        }

        fun getKeyPosition(context: Context): String {
            return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.prefkey_key_position), "center")
        }

        fun getStickyMeta(context: Context): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.prefkey_sticky_meta), false)
        }

        fun getSandS(context: Context): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.prefkey_sands), false)
        }
    }
}
