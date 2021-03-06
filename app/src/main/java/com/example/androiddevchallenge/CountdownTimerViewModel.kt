/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

const val INITIAL_START_MILLIS = 1 * 60 * 60 * 1_000L

class CountdownTimerViewModel : ViewModel() {

    private val _startTimeInMillis: MutableStateFlow<Long> =
        MutableStateFlow(INITIAL_START_MILLIS)

    val startTimeInMillis: StateFlow<Long> get() = _startTimeInMillis

    private val _countdownState: MutableStateFlow<CountdownState> =
        MutableStateFlow(CountdownState.PAUSED)

    val countdownState: StateFlow<CountdownState> get() = _countdownState

    private var countdownTimer: CountDownTimer? = null

    fun updateStartTimeInMillis(timeInMillis: Long) {
        _startTimeInMillis.value = timeInMillis
    }

    fun updateCountdownState(countdownState: CountdownState) {
        when (countdownState) {
            CountdownState.STARTED -> {
                countdownTimer?.cancel()
                countdownTimer = OneSecondCountdownTimer(
                    _startTimeInMillis.value,
                    updateTime = ::updateStartTimeInMillis,
                    finishTime = {
                        updateCountdownState(CountdownState.FINISHED)
                    },
                )
                countdownTimer?.start()
                Log.d("melo", "onTimerStart: countdownTimer started $countdownTimer") // TODO remove this
            }
            CountdownState.PAUSED -> {
                countdownTimer?.cancel()
                Log.d("melo", "onTimerStart: countdownTimer cancelled $countdownTimer") // TODO remove this
            }
            CountdownState.FINISHED -> {
                updateStartTimeInMillis(INITIAL_START_MILLIS)
            }
        }
        _countdownState.value = countdownState
    }
}
