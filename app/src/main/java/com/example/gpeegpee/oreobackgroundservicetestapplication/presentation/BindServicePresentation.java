package com.example.gpeegpee.oreobackgroundservicetestapplication.presentation;

public interface BindServicePresentation {
    void setBindServiceStartButtonEnabled(boolean enabled);
    void setBindServiceStopButtonEnabled(boolean enabled);
    void startBindService();
    void stopBindService();
}
