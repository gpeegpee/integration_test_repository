package com.example.gpeegpee.oreobackgroundservicetestapplication.presenter;

import com.example.gpeegpee.oreobackgroundservicetestapplication.presentation.StartForegroundWithoutChannelServicePresentation;
public class StartForegroundWithoutChannelServicePresenter {

    private final StartForegroundWithoutChannelServicePresentation presentation;

    public StartForegroundWithoutChannelServicePresenter(StartForegroundWithoutChannelServicePresentation presentation) {
        this.presentation = presentation;
    }

    public void onResume() {

    }

    public void onStartButtonClicked() {
        presentation.setStartForegroundWithoutChannelStartButtonEnabled(false);
        presentation.setStartForegroundWithoutChannelStopButtonEnabled(true);
        presentation.startStartForegroundWithoutChannelService();
    }

    public void onStopButtonClicked() {
        presentation.setStartForegroundWithoutChannelStartButtonEnabled(true);
        presentation.setStartForegroundWithoutChannelStopButtonEnabled(false);
        presentation.stopStartForegroundWithoutChannelService();
    }
}
