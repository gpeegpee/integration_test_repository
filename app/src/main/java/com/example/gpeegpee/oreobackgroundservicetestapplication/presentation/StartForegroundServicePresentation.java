package com.example.gpeegpee.oreobackgroundservicetestapplication.presentation;

public interface StartForegroundServicePresentation {
    void setStartForegroundStartButtonEnabled(boolean enabled);
    void setStartForegroundStopButtonEnabled(boolean enabled);
    void startStartForegroundService();
    void stopStartForegroundService();
}
