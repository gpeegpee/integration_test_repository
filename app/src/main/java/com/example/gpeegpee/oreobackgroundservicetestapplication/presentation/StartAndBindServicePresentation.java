package com.example.gpeegpee.oreobackgroundservicetestapplication.presentation;

public interface StartAndBindServicePresentation {

    void setStartAndBindServiceStartButtonEnabled(boolean enabled);
    void setStartAndBindServiceStopButtonEnabled(boolean enabled);
    void setStartAndBindServiceUnbindButtonEnabled(boolean enabled);
    void startStartAndBindService();
    void stopStartAndBindService();
    void unbindStartAndBindService();
}
