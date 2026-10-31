package com.example.alipor.ui

import android.R.attr.background
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BackgroundColor = Color(0xFF0B0F1A)
private val GlassColor = Color.White.copy(alpha = 0.08f)
private val BorderColor = Color.White.copy(alpha = 0.15f)
private val AccentColor = Color(0xFF00D4FF)
private val SuccessColor = Color(0xFF00E676)

@Composable
fun BluetoothControlScreen(
    isConnected: Boolean,
    onConnectClick: () -> Unit,
    onLed1On: () -> Unit,
    onLed1Off: () -> Unit,
    onLed2On: () -> Unit,
    onLed2Off: () -> Unit,
    onLed3On: () -> Unit,
    onLed3Off: () -> Unit,
    onAllOn: () -> Unit,
    onAllOff: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF09090F),
                        Color(0xFF1A103C),
                        Color(0xFF182848),
                        Color(0xFF202040),
                        Color(0xFF111827)
                    )
                )
            )

            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // =========================
        // Header
        // =========================

        Text(
            text = "HC-05 Bluetooth Controller",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        // =========================
        // Connection Status
        // =========================

        GlassCard {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "Connection Status",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = if (isConnected)
                            "Connected"
                        else
                            "Disconnected",
                        color = if (isConnected)
                            SuccessColor
                        else
                            Color.Red
                    )
                }

                Button(
                    onClick = onConnectClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentColor,
                        contentColor = Color.Black
                    )
                ) {
                    Text("CONNECT")
                }
            }
        }

        // =========================
        // LED Controls
        // =========================

        LedControlCard(
            title = "LED 1",
            onOnClick = onLed1On,
            onOffClick = onLed1Off
        )

        LedControlCard(
            title = "LED 2",
            onOnClick = onLed2On,
            onOffClick = onLed2Off
        )

        LedControlCard(
            title = "LED 3",
            onOnClick = onLed3On,
            onOffClick = onLed3Off
        )

        // =========================
        // Master Control
        // =========================

        GlassCard {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "Master Control",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = onAllOn,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AccentColor,
                            contentColor = Color.Black
                        )
                    ) {
                        Text("ALL ON")
                    }

                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = onAllOff,
                        border = BorderStroke(
                            1.dp,
                            AccentColor
                        )
                    ) {
                        Text(
                            text = "ALL OFF",
                            color = AccentColor
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "built by Mohammad Amin Aalipour",
            fontSize = 15.sp,
            color = Color.LightGray
        )
    }
}

@Composable
fun LedControlCard(
    title: String,
    onOnClick: () -> Unit,
    onOffClick: () -> Unit
) {

    GlassCard {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onOnClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentColor,
                        contentColor = Color.Black
                    )
                ) {
                    Text("ON")
                }

                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = onOffClick,
                    border = BorderStroke(
                        1.dp,
                        AccentColor
                    )
                ) {
                    Text(
                        text = "OFF",
                        color = AccentColor
                    )
                }
            }
        }
    }
}

@Composable
fun GlassCard(
    content: @Composable ColumnScope.() -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = GlassColor
        ),
        border = BorderStroke(
            1.dp,
            BorderColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            content = content
        )
    }
}