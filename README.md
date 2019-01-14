Jonathan Lipps Automation Guild 2019 Code Samples
===

This is the sample code that goes along with Jonathan Lipps's 2019 Automation Guild presentation (link TBD).

It is a full Gradle-based Java project. You should be able to import it and have it work as long as you know what you are doing with your IDE and gradle.

## Setup

The only dependency which is not included is the Angry Birds APK, since I am not Rovio. You need to get an Angry Birds APK and put it at this location in the project:

```
src/test/java/resources/angrybirds.apk
```

Secondly, you should install [ScreenStream](https://apkpure.com/screen-stream-over-http/info.dvkr.screenstream) onto your Android device, and adjust its settings to stream over all interfaces, not just wifi.

If your Android device is an emulator, you'll need to run `adb forward tcp:8080 tcp:8080` to allow getting screenshots from ScreenStream. If you have a real device, you'll need to update the `mjpegScreenshotUrl` capability to use the appropriate network address for your device (instead of localhost).
