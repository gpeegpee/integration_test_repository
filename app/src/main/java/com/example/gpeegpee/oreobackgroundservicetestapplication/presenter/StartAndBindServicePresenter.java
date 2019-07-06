package com.example.gpeegpee.oreobackgroundservicetestapplication.presenter;

import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.StartAndBindServicePresentation;

public class StartAndBindServicePresenter {

    private final StartAndBindServicePresentation presentation;


    public StartAndBindServicePresenter(StartAndBindServicePresentation presentation) {
        this.presentation = presentation;
    }

    public void onResume() {

    }

    public void onStartButtonClicked() {
        presentation.setStartAndBindServiceStartButtonEnabled(false);
        presentation.setStartAndBindServiceStopButtonEnabled(true);
        presentation.setStartAndBindServiceUnbindButtonEnabled(true);
        presentation.startStartAndBindService();
    }

    public void onStopButtonClicked() {
        presentation.setStartAndBindServiceStartButtonEnabled(true);
        presentation.setStartAndBindServiceStopButtonEnabled(false);
        presentation.setStartAndBindServiceUnbindButtonEnabled(false);
        presentation.stopStartAndBindService();
    }

    public void onUnBindButtonClicked() {
        presentation.setStartAndBindServiceStartButtonEnabled(true);
        presentation.setStartAndBindServiceStopButtonEnabled(false);
        presentation.setStartAndBindServiceUnbindButtonEnabled(false);
        presentation.unbindStartAndBindService();
    }
}
