# HC-05 Bluetooth Controller App

An Android app for wirelessly controlling **three LEDs on a microcontroller board** through an **HC-05 Bluetooth module**. The app provides individual LED controls as well as a master control for switching all three LEDs on or off.

## App Preview

<p align="center">
  <img src="screenshots/app-screenshot.jpg" alt="HC-05 Bluetooth Controller app interface" width="320" />
</p>

## Features

- Connect to an HC-05 Bluetooth module.
- View the Bluetooth connection status.
- Turn LED 1, LED 2, and LED 3 on or off independently.
- Use **All On** and **All Off** to control all three LEDs together.
- Use Android's **Nearby devices** permission for Bluetooth access when requested by the system.

## Hardware

- Android phone
- HC-05 Bluetooth module
- A microcontroller board compatible with the HC-05 serial connection
- 3 LEDs and suitable current-limiting resistors
- Jumper wires and an appropriate power supply

## How It Works

The Android app sends control data to the HC-05 over Bluetooth. The HC-05 passes the received data to the microcontroller board, and the board's firmware switches the corresponding LED outputs.

> **Important:** The microcontroller firmware must understand the command format sent by the app. Make sure the board firmware and app use the same commands. Check the HC-05 board's power and logic-level requirements before wiring it to your controller.

## Getting Started

1. Assemble the circuit and connect the HC-05 to the microcontroller's serial interface (TX/RX) and common GND. Follow the documentation for your specific board and HC-05 breakout.
2. Upload firmware to the microcontroller that handles the app's LED control commands.
3. Power on the board and HC-05.
4. Enable Bluetooth on your Android phone and pair with the HC-05 in the Android Bluetooth settings if it has not been paired already. Use the pairing PIN configured for your module.
5. Open the app and grant the **Nearby devices** permission when Android requests it.
6. Tap **Connect**. Once connected, use the ON/OFF buttons for individual LEDs or **All On / All Off** for master control.

## Notes

- This repository contains the Android controller app; the microcontroller firmware and circuit may need to be prepared separately.
- The exact LED pin numbers and Bluetooth command values depend on the firmware and should be kept consistent with the app.

---

Made for learning and experimenting with Android Bluetooth communication and embedded systems.
