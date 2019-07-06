package com.example.gpeegpee.oreobackgroundservicetestapplication.presenter;

import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.BindServicePresentation;

public class BindServicePresenter {

    private final BindServicePresentation presentation;

    public BindServicePresenter(BindServicePresentation presentation) {
        this.presentation = presentation;
    }

    public void onResume() {

    }

    public void onStartButtonClicked() {
        presentation.setBindServiceStartButtonEnabled(false);
        presentation.setBindServiceStopButtonEnabled(true);
        presentation.startBindService();
    }

    public void onStopButtonClicked() {
        presentation.setBindServiceStartButtonEnabled(true);
        presentation.setBindServiceStopButtonEnabled(false);
        presentation.stopBindService();
    }
}
