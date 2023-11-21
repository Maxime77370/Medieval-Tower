package com.medievaltower.levels;

public class Chronometre {
    private long startTime;
    private long endTime;
    private boolean isRunning;

    public Chronometre() {
        this.startTime = 0;
        this.endTime = 0;
        this.isRunning = false;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.isRunning = true;
    }

    public void stop() {
        this.endTime = System.currentTimeMillis();
        this.isRunning = false;
    }

    public long getElapsedTime() {
        if (isRunning) {
            return System.currentTimeMillis() - startTime;
        } else {
            return endTime - startTime;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void reset() {
        this.startTime = 0;
        this.endTime = 0;
        this.isRunning = false;
    }
}
