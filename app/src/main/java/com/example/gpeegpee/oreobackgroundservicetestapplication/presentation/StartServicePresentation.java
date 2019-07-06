package com.example.gpeegpee.oreobackgroundservicetestapplication.presentation;

public interface StartServicePresentation {
    void setStartServiceStartButtonEnabled(boolean enabled);
    void setStartServiceStopButtonEnabled(boolean enabled);
    void startStartService();
    void stopStartService();
}
