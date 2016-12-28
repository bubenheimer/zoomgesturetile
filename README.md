# Zoom Gesture Tile

Zoom Gesture Tile provides an Android Quick Settings Tile to enable and disable the Android magnification gesture. The magnification gesture is an accessibility feature to temporarily zoom up the screen by tapping it three times. It's very useful if you have a phone with a small screen, an app that really expects a 10 inch screen instead of your 4 inch, or if your vision is not 20/20. Many games are especially bad about smaller screen form factors.

Zoom Gesture Tile addresses a shortcoming of the Android magnification gesture implementation. It often can be necessary to disable the gesture, as some apps and many games do not work well otherwise. A series of regular clicks may be accidentally interpreted as a triple click and the screen zooms up unexpectedly. Clicks also take longer to be recognized, which can make games unplayable, even if they would benefit from screen magnification at other times. The tile makes it easy to switch gesture recognition on and off.

It is necessary to manually give the app the permission to read secure system settings. The permission will survive reboots. This is needed to access the magnification gesture setting. The app does not require a rooted phone.

To provide the permissions, use the adb command as below after the app has installed.

    adb shell grant org.bubenheimer.zoomgesturetile android.permission.SECURE_SETTINGS

This app is forked from another quick settings tile app from Francesco Franco, Demo Mode Tile, which he kindly offered as open source. Many thanks to him for showing how it's done.

Quick settings tiles are available in Android Nougat (7.0) and later. This app will not work in earlier Android versions.

All images are derived from Apache-licensed clip art in Android Studio 2.3.

This project is licensed under the terms of the Apache License Version 2.0.
