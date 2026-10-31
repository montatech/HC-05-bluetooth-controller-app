package com.example.alipor

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.core.app.ActivityCompat
import com.example.alipor.ui.BluetoothControlScreen
import java.io.OutputStream
import java.util.UUID

class MainActivity : ComponentActivity() {

    private var socket: BluetoothSocket? = null
    private var output: OutputStream? = null

    // وضعیت اتصال برای Compose
    private var isConnected by mutableStateOf(false)

    private val bluetoothAdapter: BluetoothAdapter? =
        BluetoothAdapter.getDefaultAdapter()

    private val uuid =
        UUID.fromString(
            "00001101-0000-1000-8000-00805F9B34FB"
        )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        requestBluetoothPermissions()

        setContent {

            BluetoothControlScreen(

                isConnected = isConnected,

                onConnectClick = {
                    connectBluetooth()
                },

                onLed1On = { send("A") },
                onLed1Off = { send("a") },

                onLed2On = { send("B") },
                onLed2Off = { send("b") },

                onLed3On = { send("C") },
                onLed3Off = { send("c") },

                onAllOn = { send("X") },
                onAllOff = { send("x") }
            )
        }
    }

    /**
     * درخواست مجوزهای بلوتوث
     */
    private fun requestBluetoothPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.BLUETOOTH_SCAN
                ),
                100
            )
        }
    }

    /**
     * اتصال به HC-05
     */
    private fun connectBluetooth() {

        try {

            if (bluetoothAdapter == null) {

                Toast.makeText(
                    this,
                    "Bluetooth پشتیبانی نمی‌شود",
                    Toast.LENGTH_LONG
                ).show()

                return
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

                if (
                    ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
            }

            val device: BluetoothDevice =
                bluetoothAdapter.bondedDevices.first {
                    it.name == "HC-05"
                }

            socket =
                device.createRfcommSocketToServiceRecord(uuid)

            socket?.connect()

            output = socket?.outputStream

            isConnected = true

            Toast.makeText(
                this,
                "Connected",
                Toast.LENGTH_SHORT
            ).show()

        } catch (e: Exception) {

            isConnected = false

            Toast.makeText(
                this,
                e.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /**
     * ارسال فرمان به آردوینو
     */
    private fun send(command: String) {

        try {

            output?.write(command.toByteArray())

        } catch (e: Exception) {

            Toast.makeText(
                this,
                "ابتدا اتصال را برقرار کن",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroy() {

        try {
            socket?.close()
        } catch (_: Exception) {
        }

        super.onDestroy()
    }
}