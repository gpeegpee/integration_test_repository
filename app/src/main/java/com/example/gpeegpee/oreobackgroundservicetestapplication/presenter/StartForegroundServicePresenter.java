package com.example.gpeegpee.oreobackgroundservicetestapplication.presenter;

import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.StartForegroundServicePresentation;

public class StartForegroundServicePresenter {

    private final StartForegroundServicePresentation presentation;

    public StartForegroundServicePresenter(StartForegroundServicePresentation presentation) {
        this.presentation = presentation;
    }

    public void onResume() {

    }

    public void onStartButtonClicked() {
        presentation.setStartForegroundStartButtonEnabled(false);
        presentation.setStartForegroundStopButtonEnabled(true);
        presentation.startStartForegroundService();
    }

    public void onStopButtonClicked() {
        presentation.setStartForegroundStartButtonEnabled(true);
        presentation.setStartForegroundStopButtonEnabled(false);
        presentation.stopStartForegroundService();
    }

}
