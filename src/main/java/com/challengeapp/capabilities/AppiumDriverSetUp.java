package com.challengeapp.capabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import com.challengeapp.utils.PropertyReader;

public class AppiumDriverSetUp {

    private final PropertyReader configReader = PropertyReader.getInstance();

    public AppiumDriver initializeDriver(String platform) {
        try {
            if (platform.equalsIgnoreCase("android")) {
                return new AndroidDriver(new URL(configReader.getProperty("appium.server.url")), getAndroidOptions());
            } else if (platform.equalsIgnoreCase("ios")) {
                return new IOSDriver(new URL(configReader.getProperty("appium.server.url")), getIOSOptions());
            } else {
                throw new IllegalArgumentException("Unsupported platform: " + platform);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to initialize Appium Driver", e);
        }
    }

    private UiAutomator2Options getAndroidOptions() {
        return new UiAutomator2Options()
                .setUdid(configReader.getProperty("android.udid"))
                .setPlatformName("Android")
                .setDeviceName(configReader.getProperty("android.device.name"))
                .setAutomationName(configReader.getProperty("android.automation.name"))
                .setAppPackage(configReader.getProperty("android.app.package"))
                .setAppActivity(configReader.getProperty("android.app.activity"))
                .setPlatformVersion(configReader.getProperty("android.platform.version"))
                .setAutoGrantPermissions(true);
    }


    private XCUITestOptions getIOSOptions() {
        return new XCUITestOptions()
                .setUdid(configReader.getProperty("ios.udid"))
                .setDeviceName(configReader.getProperty("ios.device.name"))
                .setBundleId(configReader.getProperty("ios.bundle.id"))
                .setPlatformVersion(configReader.getProperty("ios.platform.version"))
                .setAutomationName(configReader.getProperty("ios.automation.name"))
                .setNewCommandTimeout(Duration.ofSeconds(60));
    }
}
