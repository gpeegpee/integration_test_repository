package com.example.gpeegpee.oreobackgroundservicetestapplication;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.AidlStartAndBindServicePresentation;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.BindServicePresentation;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.BootCompletePresentation;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.StartAndBindServicePresentation;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.StartForegroundServicePresentation;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.StartForegroundWithoutChannelServicePresentation;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.StartServicePresentation;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presenter.AidlStartAndBindServicePresenter;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presenter.BindServicePresenter;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presenter.BootCompletePresenter;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presenter.StartAndBindServicePresenter;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presenter.StartForegroundServicePresenter;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presenter.StartForegroundWithoutChannelServicePresenter;
import com.example.gpeegpee.oreobackgroundservicetestapplication.presenter.StartServicePresenter;
import com.example.gpeegpee.oreobackgroundservicetestapplication.service.AidlStartAndBindService;
import com.example.gpeegpee.oreobackgroundservicetestapplication.service.BindService;
import com.example.gpeegpee.oreobackgroundservicetestapplication.service.StartAndBindService;
import com.example.gpeegpee.oreobackgroundservicetestapplication.service.StartForegroundService;
import com.example.gpeegpee.oreobackgroundservicetestapplication.service.StartForegroundWithoutChannelService;
import com.example.gpeegpee.oreobackgroundservicetestapplication.service.StartService;
import com.example.gpeegpee.oreobackgroundservicetestapplication.util.ServiceUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements BindServicePresentation
        , StartServicePresentation
        , StartForegroundServicePresentation
        , BootCompletePresentation
        , StartAndBindServicePresentation
        , AidlStartAndBindServicePresentation
        , StartForegroundWithoutChannelServicePresentation {

    private static final String TAG = "BGTest";

    @BindView(R.id.boot_complete_service_status)
    TextView bootCompleteServiceStatus;

    @BindView(R.id.bind_service_status)
    TextView bindServiceStatus;

    @BindView(R.id.start_and_bind_service_status)
    TextView startAndbindServiceStatus;

    @BindView(R.id.aidl_start_and_bind_service_status)
    TextView ailStartAndBindServiceStatus;

    @BindView(R.id.start_service_status)
    TextView startServiceStatus;

    @BindView(R.id.start_foreground_status)
    TextView startForegroundStatus;

    @BindView(R.id.start_foreground_without_channel_status)
    TextView startForegroundWithoutChannelStatus;

    @BindView(R.id.bind_service_start_button)
    Button bindServiceStartButton;

    @BindView(R.id.bind_service_stop_button)
    Button bindServiceStopButton;


    @BindView(R.id.start_and_bind_service_start_button)
    Button startAndbindServiceStartButton;

    @BindView(R.id.start_and_bind_service_stop_button)
    Button startAndbindServiceStopButton;

    @BindView(R.id.start_and_bind_service_unbind_button)
    Button startAndbindServiceUnbindButton;


    @BindView(R.id.aidl_start_and_bind_service_start_button)
    Button aidlStartAndbindServiceStartButton;

    @BindView(R.id.aidl_start_and_bind_service_stop_button)
    Button aidlStartAndbindServiceStopButton;

    @BindView(R.id.aidl_start_and_bind_service_unbind_button)
    Button aidlStartAndbindServiceUnbindButton;


    @BindView(R.id.start_service_start_button)
    Button startServiceStartButton;

    @BindView(R.id.start_service_stop_button)
    Button startServiceStopButton;


    @BindView(R.id.start_foreground_start_button)
    Button startForegroundServiceStartButton;

    @BindView(R.id.start_foreground_stop_button)
    Button startForegroundServiceStopButton;


    @BindView(R.id.start_foreground_without_channel_start_button)
    Button startForegroundWithoutChannelServiceStartButton;

    @BindView(R.id.start_foreground_without_channel_stop_button)
    Button startForegroundWithoutChannelServiceStopButton;


    @BindView(R.id.setting_button)
    Button settingButton;

    private StartServicePresenter startServicePresenter;
    private StartForegroundServicePresenter startForegroundServicePresenter;
    private StartForegroundWithoutChannelServicePresenter
            startForegroundWithoutChannelServicePresenter;
    private BindServicePresenter bindServicePresenter;
    private StartAndBindServicePresenter startAndBindServicePresenter;
    private AidlStartAndBindServicePresenter aidlStartAndBindServicePresenter;
    private BootCompletePresenter bootCompletePresenter;


    BindService ms;
    boolean isService = false;

    final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ms = ((BindService.BindServiceBinder) service).getService();
            isService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ms = null;
            isService = false;
        }
    };

    StartAndBindService startAndBindService;
    boolean isStartAndBindServiceConnected = false;
    final ServiceConnection startAndBindServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            StartAndBindService.StartAndBindServiceBinder binder =
                    (StartAndBindService.StartAndBindServiceBinder) service;
            MainActivity.this.startAndBindService = (StartAndBindService) binder.getService();
            isStartAndBindServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
            MainActivity.this.startAndBindService = null;
            isStartAndBindServiceConnected = false;
        }
    };

    AidlStartAndBindService aidlStartAndBindService;
    boolean isAidlStartAndBindServiceConnected = false;
    final ServiceConnection aidlStartAndBindServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            IStartAndBindService.Stub.asInterface(service);
//            AidlStartAndBindService.AidlStartAndBindServiceBinder binder = (AidlStartAndBindService.AidlStartAndBindServiceBinder) service;
//            MainActivity.this.aidlStartAndBindService = (AidlStartAndBindService) binder.getService();
            isAidlStartAndBindServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
            MainActivity.this.aidlStartAndBindService = null;
            isAidlStartAndBindServiceConnected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        startServicePresenter = new StartServicePresenter(this);
        startForegroundServicePresenter = new StartForegroundServicePresenter(this);
        bindServicePresenter = new BindServicePresenter(this);
        bootCompletePresenter = new BootCompletePresenter(this);
        startAndBindServicePresenter = new StartAndBindServicePresenter(this);
        aidlStartAndBindServicePresenter = new AidlStartAndBindServicePresenter(this);
        startForegroundWithoutChannelServicePresenter =
                new StartForegroundWithoutChannelServicePresenter(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        bootCompletePresenter.onResume();
        startServicePresenter.onResume();
        startForegroundServicePresenter.onResume();
        bindServicePresenter.onResume();
        startAndBindServicePresenter.onResume();
        aidlStartAndBindServicePresenter.onResume();
        startForegroundWithoutChannelServicePresenter.onResume();

        okhttpRequest();
    }

    private void okhttpRequest() {

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://www.google.co.kr").newBuilder();
        String url = urlBuilder.build().toString();

        HttpUrl.Builder urlBuilder2 = HttpUrl.parse("http://www.daum.net").newBuilder();
        String url2 = urlBuilder2.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient.Builder().build();

        Log.i(TAG, "1st google enqueue call");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();
                Log.i(TAG, "google build enqueue Response");
//                MainActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.i(TAG, "build enqueue Response");
//                    }
//                });

            }
        });

        Request request2 = new Request.Builder()
                .url(url2)
                .build();

        OkHttpClient client2 = new OkHttpClient();

        Log.i(TAG, "2nd daum enqueue call");
        client2.newCall(request2).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "enqueue Failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "daum enqueue Response");
            }
        });

        Request request3 = new Request.Builder()
                .url(url2)
                .build();

        OkHttpClient client3 = new OkHttpClient();
        try {
            client3.newCall(request3).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isService) {
            unbindService(conn);
            isService = false;
        }

        if (isStartAndBindServiceConnected) {
            unbindService(startAndBindServiceConnection);
            isStartAndBindServiceConnected = false;
        }

        if (isAidlStartAndBindServiceConnected) {
            unbindService(aidlStartAndBindServiceConnection);
            isAidlStartAndBindServiceConnected = false;
        }
    }

    @OnClick(R.id.bind_service_start_button)
    public void onBindServiceStartButtonClicked(View view) {
        Log.d(TAG, "onBindServiceStartButtonClicked");
        bindServicePresenter.onStartButtonClicked();
    }

    @OnClick(R.id.bind_service_stop_button)
    public void onBindServiceStopButtonClicked(View view) {
        Log.d(TAG, "onBindServiceStopButtonClicked");
        bindServicePresenter.onStopButtonClicked();
    }


    @OnClick(R.id.start_and_bind_service_start_button)
    public void onStartAndBindServiceStartButtonClicked(View view) {
        Log.d(TAG, "onStartAndBindServiceStartButtonClicked");
        startAndBindServicePresenter.onStartButtonClicked();
    }

    @OnClick(R.id.start_and_bind_service_stop_button)
    public void onStartAndBindServiceStopButtonClicked(View view) {
        Log.d(TAG, "onStartAndBindServiceStopButtonClicked");
        startAndBindServicePresenter.onStopButtonClicked();
    }

    @OnClick(R.id.start_and_bind_service_unbind_button)
    public void onStartAndBindServiceUnbindButtonCliced(View view) {
        Log.d(TAG, "onStartAndBindServiceUnbindButtonCliced");
        startAndBindServicePresenter.onUnBindButtonClicked();
    }


    @OnClick(R.id.aidl_start_and_bind_service_start_button)
    public void onAidlStartAndBindServiceStartButtonClicked(View view) {
        Log.d(TAG, "onAidlStartAndBindServiceStartButtonClicked");
        aidlStartAndBindServicePresenter.onStartButtonClicked();
    }

    @OnClick(R.id.aidl_start_and_bind_service_stop_button)
    public void onAidlStartAndBindServiceStopButtonClicked(View view) {
        Log.d(TAG, "onAidlStartAndBindServiceStopButtonClicked");
        aidlStartAndBindServicePresenter.onStopButtonClicked();
    }

    @OnClick(R.id.aidl_start_and_bind_service_unbind_button)
    public void onAidlStartAndBindServiceUnbindButtonCliced(View view) {
        Log.d(TAG, "onAidlStartAndBindServiceUnbindButtonCliced");
        aidlStartAndBindServicePresenter.onUnBindButtonClicked();
    }


    @OnClick(R.id.start_service_start_button)
    public void onStartServiceStartButtonClicked(View view) {
        Log.d(TAG, "onStartServiceStartButtonClicked");
        startServicePresenter.onStartButtonClicked();
    }

    @OnClick(R.id.start_service_stop_button)
    public void onStartServiceStopButtonClicked(View view) {
        Log.d(TAG, "onStartServiceStopButtonClicked");
        startServicePresenter.onStopButtonClicked();
    }


    @OnClick(R.id.start_foreground_start_button)
    public void onStartServiceForegroundStartButtonClicked(View view) {
        Log.d(TAG, "onStartServiceForegroundStartButtonClicked");
        startForegroundServicePresenter.onStartButtonClicked();
    }

    @OnClick(R.id.start_foreground_stop_button)
    public void onStartServiceForegroundStopButtonClicked(View view) {
        Log.d(TAG, "onStartServiceForegroundStopButtonClicked");
        startForegroundServicePresenter.onStopButtonClicked();
    }


    @OnClick(R.id.start_foreground_without_channel_start_button)
    public void onStartServiceForegroundWithoutChannelStartButtonClicked(View view) {
        Log.d(TAG, "onStartServiceForegroundStartButtonClicked");
        startForegroundWithoutChannelServicePresenter.onStartButtonClicked();
    }

    @OnClick(R.id.start_foreground_without_channel_stop_button)
    public void onStartServiceForegroundWithoutChannelStopButtonClicked(View view) {
        Log.d(TAG, "onStartServiceForegroundStopButtonClicked");
        startForegroundWithoutChannelServicePresenter.onStopButtonClicked();
    }

    @OnClick(R.id.setting_button)
    public void onSettingButtonClicked(View view) {
        Log.d(TAG, "onSettingButtonClicked");
        launchBatterySetting();
    }

    @TargetApi(26)
    public void launchBatterySetting() {
        Intent intent = new Intent();
        Context context = getApplicationContext();

        String packageName = context.getPackageName();
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

        if (packageName == null || packageName.isEmpty()) {
            return;
        }

        if (pm.isIgnoringBatteryOptimizations(packageName)) {
            Log.d(TAG, "Currently ignore Battery Optimization");
            intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
        } else {
            Log.d(TAG, "Requesting to ignore Battery Optimization");
            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            //intent.setData(Uri.parse(new StringBuilder().append("package:").append(packageName).toString()));
            intent.setData(Uri.parse("package:" + packageName));
        }
        context.startActivity(intent);
    }

    @Override
    public void updateIsServiceRunning() {
        if (ServiceUtil.isServiceRunning(this, StartService.class)) {
            bootCompleteServiceStatus.setText("Service is running");
        } else {
            bootCompleteServiceStatus.setText("Service is not running");
        }

        if (ServiceUtil.isServiceRunning(this, BindService.class)) {
            bindServiceStatus.setText("Service is running");
        } else {
            bindServiceStatus.setText("Service is not running");
        }

        if (ServiceUtil.isServiceRunning(this, StartAndBindService.class)) {
            startAndbindServiceStatus.setText("Service is running");
        } else {
            startAndbindServiceStatus.setText("Service is not running");
        }

        if (ServiceUtil.isServiceRunning(this, StartService.class)) {
            startServiceStatus.setText("Service is running");
        } else {
            startServiceStatus.setText("Service is not running");
        }

        if (ServiceUtil.isServiceRunning(this, StartForegroundService.class)) {
            startForegroundStatus.setText("Service is running");
        } else {
            startForegroundStatus.setText("Service is not running");
        }

        if (ServiceUtil.isServiceRunning(this, StartForegroundWithoutChannelService.class)) {
            startForegroundWithoutChannelStatus.setText("Service is running");
        } else {
            startForegroundWithoutChannelStatus.setText("Service is not running");
        }
    }

    @Override
    public void setBindServiceStartButtonEnabled(boolean enabled) {
        bindServiceStartButton.setEnabled(enabled);
    }

    @Override
    public void setBindServiceStopButtonEnabled(boolean enabled) {
        bindServiceStopButton.setEnabled(enabled);
    }

    @Override
    public void startBindService() {
        Intent intent = new Intent(this, BindService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void stopBindService() {
        if (isService) {
            unbindService(conn);
            isService = false;
        }
    }


    @Override
    public void setStartAndBindServiceStartButtonEnabled(boolean enabled) {
        startAndbindServiceStartButton.setEnabled(enabled);
    }

    @Override
    public void setStartAndBindServiceStopButtonEnabled(boolean enabled) {
        startAndbindServiceStopButton.setEnabled(enabled);
    }

    @Override
    public void setStartAndBindServiceUnbindButtonEnabled(boolean enabled) {
        startAndbindServiceUnbindButton.setEnabled(enabled);
    }

    @Override
    public void startStartAndBindService() {
        Intent intent = new Intent(this, StartAndBindService.class);
        try {
            startService(intent);
        } catch (SecurityException e) {
            Log.w(TAG, "exception : " + e);
        } catch (IllegalStateException e) {
            Log.e(TAG, "exception : " + e);
        }
        boolean connected =
                bindService(intent, startAndBindServiceConnection, Context.BIND_AUTO_CREATE);
        if (!connected) {
            Log.w(TAG, "bindService failed.");
        }
    }

    @Override
    public void stopStartAndBindService() {
        if (isStartAndBindServiceConnected) {
            unbindService(startAndBindServiceConnection);
            isStartAndBindServiceConnected = false;
        }

        try {
            Intent intent = new Intent(this, StartAndBindService.class);
            boolean stopped = stopService(intent);
            if (!stopped) {
                Log.e(TAG, "Failed to stop StartAndBindService");
            }
        } catch (SecurityException e) {
            Log.w(TAG, "exception : " + e);
        } catch (IllegalStateException e) {
            Log.e(TAG, "exception : " + e);
        }
    }

    @Override
    public void unbindStartAndBindService() {
        if (isStartAndBindServiceConnected) {
            unbindService(startAndBindServiceConnection);
            isStartAndBindServiceConnected = false;
        }
    }


    @Override
    public void setAidlStartAndBindServiceStartButtonEnabled(boolean enabled) {
        aidlStartAndbindServiceStartButton.setEnabled(enabled);
    }

    @Override
    public void setAidlStartAndBindServiceStopButtonEnabled(boolean enabled) {
        aidlStartAndbindServiceStopButton.setEnabled(enabled);
    }

    @Override
    public void setAidlStartAndBindServiceUnbindButtonEnabled(boolean enabled) {
        aidlStartAndbindServiceUnbindButton.setEnabled(enabled);
    }

    @Override
    public void startAidlStartAndBindService() {
        Intent intent = new Intent(this, AidlStartAndBindService.class);
        try {
            startService(intent);
        } catch (SecurityException e) {
            Log.w(TAG, "exception : " + e);
        } catch (IllegalStateException e) {
            Log.e(TAG, "exception : " + e);
        }
        boolean connected =
                bindService(intent, aidlStartAndBindServiceConnection, Context.BIND_AUTO_CREATE);
        if (!connected) {
            Log.w(TAG, "bindService failed.");
        }
    }

    @Override
    public void stopAidlStartAndBindService() {
        if (isAidlStartAndBindServiceConnected) {
            unbindService(aidlStartAndBindServiceConnection);
            isAidlStartAndBindServiceConnected = false;
        }

        try {
            Intent intent = new Intent(this, AidlStartAndBindService.class);
            boolean stopped = stopService(intent);
            if (!stopped) {
                Log.e(TAG, "Failed to stop AidlStartAndBindService");
            }
        } catch (SecurityException e) {
            Log.w(TAG, "exception : " + e);
        } catch (IllegalStateException e) {
            Log.e(TAG, "exception : " + e);
        }
    }

    @Override
    public void unbindAidlStartAndBindService() {
        if (isAidlStartAndBindServiceConnected) {
            unbindService(aidlStartAndBindServiceConnection);
            isAidlStartAndBindServiceConnected = false;
        }
    }


    @Override
    public void setStartServiceStartButtonEnabled(boolean enabled) {
        startServiceStartButton.setEnabled(enabled);
    }

    @Override
    public void setStartServiceStopButtonEnabled(boolean enabled) {
        startServiceStopButton.setEnabled(enabled);
    }

    @Override
    public void startStartService() {
        Intent intent = new Intent(getApplicationContext(), StartService.class);
        startService(intent);
    }

    @Override
    public void stopStartService() {
        Intent intent = new Intent(getApplicationContext(), StartService.class);
        stopService(intent);
    }

    @Override
    public void setStartForegroundStartButtonEnabled(boolean enabled) {
        startForegroundServiceStartButton.setEnabled(enabled);
    }

    @Override
    public void setStartForegroundStopButtonEnabled(boolean enabled) {
        startForegroundServiceStopButton.setEnabled(enabled);
    }

    @Override
    public void startStartForegroundService() {
        Intent intent = new Intent(getApplicationContext(), StartForegroundService.class);
        ContextCompat.startForegroundService(this, intent);
    }

    @Override
    public void stopStartForegroundService() {
        Intent intent = new Intent(getApplicationContext(), StartForegroundService.class);
        stopService(intent);
    }


    @Override
    public void setStartForegroundWithoutChannelStartButtonEnabled(boolean enabled) {
        startForegroundWithoutChannelServiceStartButton.setEnabled(enabled);
    }

    @Override
    public void setStartForegroundWithoutChannelStopButtonEnabled(boolean enabled) {
        startForegroundWithoutChannelServiceStopButton.setEnabled(enabled);
    }

    @Override
    public void startStartForegroundWithoutChannelService() {
        Intent intent =
                new Intent(getApplicationContext(), StartForegroundWithoutChannelService.class);
        ContextCompat.startForegroundService(this, intent);
    }

    @Override
    public void stopStartForegroundWithoutChannelService() {
        Intent intent =
                new Intent(getApplicationContext(), StartForegroundWithoutChannelService.class);
        stopService(intent);
    }
}
