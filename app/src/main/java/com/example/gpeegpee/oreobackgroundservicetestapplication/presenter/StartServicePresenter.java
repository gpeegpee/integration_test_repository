package com.example.gpeegpee.oreobackgroundservicetestapplication.presenter;

import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.StartServicePresentation;

public class StartServicePresenter {

    private final StartServicePresentation presentation;

    public StartServicePresenter(StartServicePresentation presentation) {
        this.presentation = presentation;
    }

    public void onResume() {

    }

    public void onStartButtonClicked() {
        presentation.setStartServiceStartButtonEnabled(false);
        presentation.setStartServiceStopButtonEnabled(true);
        presentation.startStartService();
    }

    public void onStopButtonClicked() {
        presentation.setStartServiceStartButtonEnabled(true);
        presentation.setStartServiceStopButtonEnabled(false);
        presentation.stopStartService();
    }

}
