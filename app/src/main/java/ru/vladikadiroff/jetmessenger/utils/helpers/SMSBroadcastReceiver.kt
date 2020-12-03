package ru.vladikadiroff.jetmessenger.utils.helpers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class SMSBroadcastReceiver: BroadcastReceiver() {

    private var onCodeReceive: ((String) -> Unit)? = null
    private var onTimeOutExpired: (() -> Unit)? = null

    override fun onReceive(context: Context, intent: Intent) {
        if(SmsRetriever.SMS_RETRIEVED_ACTION == intent.action){
            val extras = intent.extras
            val smsRetrieverStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
            when (smsRetrieverStatus.statusCode){
                CommonStatusCodes.SUCCESS -> {
                    var sms = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                    Log.e("SMS Text", sms)
                    onCodeReceive?.invoke(sms.substring(0,6).trim())
                }
                CommonStatusCodes.TIMEOUT -> onTimeOutExpired?.invoke()
            }
        }
    }

    fun addOnCodeReceiveListener(onCodeReceive: (String) -> Unit){
        this.onCodeReceive = onCodeReceive
    }

   fun addOnTimeOutExpiredListener(onTimeOutExpired: () -> Unit){
       this.onTimeOutExpired = onTimeOutExpired
   }

}