package com.example.gpeegpee.oreobackgroundservicetestapplication.presentation;

public interface AidlStartAndBindServicePresentation {
    void setAidlStartAndBindServiceStartButtonEnabled(boolean enabled);
    void setAidlStartAndBindServiceStopButtonEnabled(boolean enabled);
    void setAidlStartAndBindServiceUnbindButtonEnabled(boolean enabled);
    void startAidlStartAndBindService();
    void stopAidlStartAndBindService();
    void unbindAidlStartAndBindService();
}
