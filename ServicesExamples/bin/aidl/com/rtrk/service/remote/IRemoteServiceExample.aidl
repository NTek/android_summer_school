package com.rtrk.service.remote;

interface IRemoteServiceExample {
    void setMusicFinishedCallback(in IBinder binder);
    void startMusic();
    void stopMusic();
}