package ru.vladikadiroff.jetmessenger.ui.fragments.auth

import android.content.IntentFilter
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.phone.SmsRetriever
import kotlinx.android.synthetic.main.fragment_enter_code.*
import ru.vladikadiroff.jetmessenger.R
import ru.vladikadiroff.jetmessenger.domain.auth.keys.AuthenticationStatus
import ru.vladikadiroff.jetmessenger.ui.abstracts.BaseLoginFragment
import ru.vladikadiroff.jetmessenger.utils.extensions.navigateToMessengerActivity
import ru.vladikadiroff.jetmessenger.utils.extensions.navigateUp
import ru.vladikadiroff.jetmessenger.utils.helpers.LiveDataTimer
import ru.vladikadiroff.jetmessenger.utils.helpers.OTPEditTextHelper
import ru.vladikadiroff.jetmessenger.utils.helpers.SMSBroadcastReceiver
import ru.vladikadiroff.jetmessenger.viewmodels.EnterCodeViewModel

class EnterCodeFragment: BaseLoginFragment<EnterCodeViewModel>(R.layout.fragment_enter_code) {

    private val otpHelper = OTPEditTextHelper()
    private val receiver = SMSBroadcastReceiver()

    override val viewModelClass = EnterCodeViewModel::class.java

    override fun initLoginFragment() {
        initView()
        initViewModel()
        startSMSListener()
    }

    private fun initView(){
        textViewNumber.text = viewModel.getPhone()
        imageEnterCodeBack.setOnClickListener{ onBackClick() }
        textResendSMS.setOnClickListener { viewModel.resendVerificationCode() }
        otpHelper.bind(otpEditText1, otpEditText2, otpEditText3, otpEditText4, otpEditText5, otpEditText6)
        otpHelper.setOnInputOTPComplete { code -> viewModel.verifyVerificationCode(code) }
    }

    private fun initViewModel(){
        viewModel.status.observe(this, Observer { status ->
            when(status) {
                is AuthenticationStatus.isSuccessfull -> navigateToMessengerActivity()
                is AuthenticationStatus.Exception -> otpHelper.showVerificationException()
            }
        })
        viewModel.timerResendSMS.observe(this, Observer { timer ->
            when(timer){
                is LiveDataTimer.LiveDataTimerStatus.OnTick -> enableResendSms(count = timer.count)
                is LiveDataTimer.LiveDataTimerStatus.OnFinish -> enableResendSms(false)
            }
        })
    }

    private fun startSMSListener(){
        receiver.addOnCodeReceiveListener { code -> otpHelper.setVerificationCode(code) }
        activity?.registerReceiver(receiver, IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION))
        SmsRetriever.getClient(requireActivity()).startSmsRetriever()
        //val client = SmsRetriever.getClient(requireActivity())
        //val task = client.startSmsRetriever()
        //task.addOnSuccessListener { showToast("Waiting SMS Code") }
        //task.addOnFailureListener{ showToast("Cannot Start SMS Retriever")}
    }

    private fun enableResendSms(enable: Boolean = true, count: Int = 0){
        textResendSMS.isEnabled = !enable
        if(enable) textResendSMS.text = getString(R.string.resend_sms_timer, count.toString())
        else textResendSMS.text = getString(R.string.resend_sms)
    }

    private fun onBackClick(){
        viewModel.onBack()
        navigateUp()
    }

}