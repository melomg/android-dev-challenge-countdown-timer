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

private const val UPDATE_INTERVAL_MILLIS = 1_000L

internal class OneSecondCountdownTimer(
    startTimeInMillis: Long,
    val updateTime: (Long) -> Unit,
    val finishTime: () -> Unit,
) : CountDownTimer(startTimeInMillis, UPDATE_INTERVAL_MILLIS) {

    override fun onTick(millisUntilFinished: Long) {
        updateTime(millisUntilFinished)
        Log.d("melo", "CountDownTimer$this: updateTime $millisUntilFinished") // TODO remove this
    }

    override fun onFinish() {
        finishTime()
        Log.d("melo", "CountDownTimer$this: onFinish") // TODO remove this
    }
}