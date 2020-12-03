package ru.vladikadiroff.jetmessenger.viewmodels

import androidx.lifecycle.LiveData
import ru.vladikadiroff.jetmessenger.domain.auth.keys.AuthenticationStatus
import ru.vladikadiroff.jetmessenger.ui.abstracts.BaseLoginViewModel
import ru.vladikadiroff.jetmessenger.utils.extensions.set
import ru.vladikadiroff.jetmessenger.utils.helpers.LiveDataTimer
import javax.inject.Inject

class EnterCodeViewModel @Inject constructor(private val liveDataTimer: LiveDataTimer): BaseLoginViewModel(){

    val timerResendSMS: LiveData<LiveDataTimer.LiveDataTimerStatus>

    init {
        liveDataTimer.start()
        timerResendSMS = liveDataTimer.getTimerStatus()
    }

    fun resendVerificationCode(){
        liveDataTimer.start()
        interactor.resendVerificationCode()
    }

    fun verifyVerificationCode(code: String) {
        interactor.verifyVerificationCode(code)
    }

    fun getPhone() = interactor.getPhone()

    fun onBack() {
        status.set(AuthenticationStatus.Default)
    }

    override fun onCleared() {
        liveDataTimer.stop()
        super.onCleared()
    }

}