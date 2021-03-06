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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.mapper.toCountTimeHolder
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun CountdownTimerView(
    startTimeInMillis: Long,
    countdownState: CountdownState,
    onTimerStart: () -> Unit,
    onTimerPause: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colors.surface,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            val timerSeparator = stringResource(id = R.string.timer_separator)
            val (hours, minutes, seconds) = toCountTimeHolder(startTimeInMillis)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = hours,
                    style = typography.h3,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = timerSeparator,
                    style = typography.h3,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = minutes,
                    style = typography.h3,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = timerSeparator,
                    style = typography.h3,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = seconds,
                    style = typography.h3,
                    textAlign = TextAlign.Center,
                )
            }

            Button(
                shape = CircleShape,
                onClick = {
                    when (countdownState) {
                        CountdownState.STARTED -> {
                            onTimerPause()
                        }
                        CountdownState.PAUSED, CountdownState.FINISHED -> {
                            onTimerStart()
                        }
                    }
                },
                modifier = Modifier
                    .size(60.dp)
                    .shadow(elevation = 8.dp, shape = CircleShape),
            ) {
                when (countdownState) {
                    CountdownState.STARTED -> {
                        Image(
                            painter = painterResource(R.drawable.ic_pause),
                            contentDescription = "Pause"
                        )
                    }
                    CountdownState.PAUSED -> {
                        Image(
                            painter = painterResource(R.drawable.ic_play),
                            contentDescription = "Play",
                        )
                    }
                    CountdownState.FINISHED -> {
                        Image(
                            painter = painterResource(R.drawable.ic_replay),
                            contentDescription = "RePlay",
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview("Light Theme CountDown Timer", widthDp = 360, heightDp = 640)
@Composable
fun LightPreviewCountDownTimerView() {
    MyTheme {
        CountdownTimerView(
            INITIAL_START_MILLIS,
            CountdownState.PAUSED,
            {},
            {},
        )
    }
}
