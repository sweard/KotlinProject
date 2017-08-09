package com.jeff.kotlinproject.mvp.nfc

import android.content.Intent
import android.nfc.NfcAdapter
import com.jeff.kotlinproject.R
import com.jeff.kotlinproject.base.BaseActivity

/**
 * Created by jeff on 17-8-9.
 */
class NFCActivity : BaseActivity() {
    private var nfcAdapter: NfcAdapter? = null

    override val layoutId: Int
        get() = R.layout.activity_nfc

    override fun init() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {

        }
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
}