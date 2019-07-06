package com.example.gpeegpee.oreobackgroundservicetestapplication.presenter;

import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.AidlStartAndBindServicePresentation;

public class AidlStartAndBindServicePresenter {
    private final AidlStartAndBindServicePresentation presentation;

    public AidlStartAndBindServicePresenter(AidlStartAndBindServicePresentation presentation) {
        this.presentation = presentation;
    }

    public void onResume() {

    }

    public void onStartButtonClicked() {
        presentation.setAidlStartAndBindServiceStartButtonEnabled(false);
        presentation.setAidlStartAndBindServiceStopButtonEnabled(true);
        presentation.setAidlStartAndBindServiceUnbindButtonEnabled(true);
        presentation.startAidlStartAndBindService();
    }

    public void onStopButtonClicked() {
        presentation.setAidlStartAndBindServiceStartButtonEnabled(true);
        presentation.setAidlStartAndBindServiceStopButtonEnabled(false);
        presentation.setAidlStartAndBindServiceUnbindButtonEnabled(false);
        presentation.stopAidlStartAndBindService();
    }

    public void onUnBindButtonClicked() {
        presentation.setAidlStartAndBindServiceStartButtonEnabled(true);
        presentation.setAidlStartAndBindServiceStopButtonEnabled(false);
        presentation.setAidlStartAndBindServiceUnbindButtonEnabled(false);
        presentation.unbindAidlStartAndBindService();
    }
}
