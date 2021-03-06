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
package com.example.androiddevchallenge.ui.timer.mapper

import com.example.androiddevchallenge.ui.timer.INITIAL_START_MILLIS
import com.example.androiddevchallenge.ui.timer.model.TimeUIModel
import kotlin.math.floor

internal fun toTimeUIModel(startTimeInMillis: Long): TimeUIModel {
    val remainingSeconds = startTimeInMillis / 1000
    val initialSeconds = INITIAL_START_MILLIS / 1000
    var seconds = 0
    var minutes = 0
    var hours = 0

    val progress = (floor(remainingSeconds.toDouble()) / initialSeconds).toFloat()

    if (remainingSeconds > 0) {
        val secondsDay = remainingSeconds % (24 * 60 * 60)
        seconds = (secondsDay % 60).toInt()
        minutes = ((secondsDay / 60) % 60).toInt()
        hours = (secondsDay / 3600).toInt()
    }

    return TimeUIModel(
        formatTime(hours),
        formatTime(minutes),
        formatTime(seconds),
        progress,
    )
}

private fun formatTime(value: Int): String = value.toString().padStart(2, '0')
