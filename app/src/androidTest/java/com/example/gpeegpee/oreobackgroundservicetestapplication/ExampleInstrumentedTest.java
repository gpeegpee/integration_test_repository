package com.example.gpeegpee.oreobackgroundservicetestapplication;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.core.internal.deps.guava.collect.Iterables;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;
import android.util.Log;
import android.view.View;

import com.facebook.litho.config.ComponentsConfiguration;
import com.facebook.testing.screenshot.Screenshot;
import com.facebook.testing.screenshot.layouthierarchy.LayoutHierarchyDumper;
import com.facebook.testing.screenshot.layouthierarchy.common.TextViewAttributePlugin;
import com.facebook.testing.screenshot.layouthierarchy.litho.LithoAttributePlugin;
import com.facebook.testing.screenshot.layouthierarchy.litho.LithoHierarchyPlugin;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Objects;

import tools.fastlane.screengrab.Screengrab;
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

//    @Rule
//    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    private UiDevice mDevice;

    @Before
    public void setUp() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        try {
            mDevice.wakeUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
//    private UiDevice mDevice;

    private static final String BASIC_SAMPLE_PACKAGE =
            "com.example.gpeegpee.oreobackgroundservicetestapplication";


    @Test
    @Ignore
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals(
                "com.example.gpeegpee.oreobackgroundservicetestapplication",
                appContext.getPackageName());
        mDevice.waitForIdle(1000);

        File fileCacheItem = new File("/sdcard/2.png");
        try {
            fileCacheItem.createNewFile();
//            out = new FileOutputStream(fileCacheItem);
//            mDevice.dumpWindowHierarchy(out);
            Log.i("TEST", "Launcher: " + mDevice.getLauncherPackageName());
            mDevice.takeScreenshot(fileCacheItem);

        } catch (Exception e) {
            e.printStackTrace();
        }

        mDevice.waitForIdle(1000);

        Activity activity;
        try {
            activity = getCurrentActivity();
        } catch (Throwable e) {
            e.printStackTrace();
        }
            Bitmap bitmap = android.support.test.runner.screenshot.Screenshot.capture(activity).getBitmap();
        File fileCacheItemOrg = new File("/sdcard/1.png");
        OutputStream out2 = null;

        try {
            fileCacheItemOrg.createNewFile();
            out2 = new FileOutputStream(fileCacheItemOrg);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out2);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Bitmap image1 = BitmapFactory.decodeFile(fileCacheItemOrg.getAbsolutePath());
        Bitmap image2 = BitmapFactory.decodeFile(fileCacheItem.getAbsolutePath());
        Bitmap image3 =
                Bitmap.createBitmap(image1.getWidth(), image1.getHeight(), Bitmap.Config.ARGB_8888);

        for (int x = 0; x < image1.getWidth(); x++) {
            for (int y = 0; y < image1.getHeight(); y++) {
                int argb1 = image1.getPixel(x, y);
                int argb2 = image2.getPixel(x, y);

                //int a1 = (argb1 >> 24) & 0xFF;
                int r1 = (argb1 >> 16) & 0xFF;
                int g1 = (argb1 >> 8) & 0xFF;
                int b1 = argb1 & 0xFF;

                //int a2 = (argb2 >> 24) & 0xFF;
                int r2 = (argb2 >> 16) & 0xFF;
                int g2 = (argb2 >> 8) & 0xFF;
                int b2 = argb2 & 0xFF;

                //int aDiff = Math.abs(a2 - a1);
                int rDiff = Math.abs(r2 - r1);
                int gDiff = Math.abs(g2 - g1);
                int bDiff = Math.abs(b2 - b1);

                int diff = (255 << 24) | (rDiff << 16) | (gDiff << 8) | bDiff;

                image3.setPixel(x, y, diff);
            }
        }

        File diff = new File("/sdcard/3.png");
        try {
            diff.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileOutputStream out = new FileOutputStream(diff)) {
            image3.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = InstrumentationRegistry.getTargetContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }

    @Before
    public void startMainActivityFromHomeScreen() {
        LayoutHierarchyDumper.addGlobalAttributePlugin(TextViewAttributePlugin.getInstance());
        // Litho plugins
        ComponentsConfiguration.isDebugModeEnabled = true;
        LayoutHierarchyDumper.addGlobalHierarchyPlugin(LithoHierarchyPlugin.getInstance());
        LayoutHierarchyDumper.addGlobalAttributePlugin(LithoAttributePlugin.getInstance());

        Screengrab.setDefaultScreenshotStrategy(new UiAutomatorScreenshotStrategy());
//        Screengrab.setDefaultScreenshotStrategy(new FalconScreenshotStrategy(mActivityRule.getActivity()));

        getInstrumentation().getUiAutomation().executeShellCommand(
                "pm grant " + getInstrumentation().getTargetContext().getPackageName()
                        + " android.permission.WRITE_EXTERNAL_STORAGE");

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the blueprint app
        Context context = InstrumentationRegistry.getTargetContext();
        final Intent intent =
                context.getPackageManager().getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        Objects.requireNonNull(intent).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void test() {
        getInstrumentation().waitForIdleSync();
        Screengrab.screenshot("mainactivity");
        try {
            Screenshot.snapActivity(getCurrentActivity()).record();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        mDevice.waitForIdle();
    }

    Activity getCurrentActivity() throws Throwable {
        getInstrumentation().waitForIdleSync();
        final Activity[] activity = new Activity[1];
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                java.util.Collection<Activity> activities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                activity[0] = Iterables.getOnlyElement(activities);
            }});
        return activity[0];
    }
}
