package com.example.gpeegpee.oreobackgroundservicetestapplication.presenter;

import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.BootCompletePresentation;

public class BootCompletePresenter {

    private BootCompletePresentation presentation;

    public BootCompletePresenter(BootCompletePresentation presentation) {
        this.presentation = presentation;
    }

    public void onResume() {
        presentation.updateIsServiceRunning();
    }
}
