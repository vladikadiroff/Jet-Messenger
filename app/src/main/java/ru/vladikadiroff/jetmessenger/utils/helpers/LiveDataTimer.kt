package ru.vladikadiroff.jetmessenger.utils.helpers

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.vladikadiroff.jetmessenger.utils.extensions.default
import ru.vladikadiroff.jetmessenger.utils.extensions.set

class LiveDataTimer {

    private var count = 60
    private var timer: CountDownTimer? = null
    private val status =
            MutableLiveData<LiveDataTimerStatus>().default(LiveDataTimerStatus.Default)

    private fun startTimer(){
        timer = object: CountDownTimer(60000, 1000){
            override fun onTick(counter: Long) {
                status.set(LiveDataTimerStatus.OnTick(count))
                count--
            }
            override fun onFinish() {
                status.set(LiveDataTimerStatus.OnFinish)
            }
        }.start()
    }

    fun getTimerStatus(): LiveData<LiveDataTimerStatus> = status

    fun start(){
        status.set(LiveDataTimerStatus.Default)
        timer = null
        count = 60
        startTimer()
    }

    fun stop(){
        status.set(LiveDataTimerStatus.Default)
        timer = null
    }


    sealed class LiveDataTimerStatus {
        class OnTick(val count: Int) : LiveDataTimerStatus()
        object OnFinish : LiveDataTimerStatus()
        object Default : LiveDataTimerStatus()
    }

}