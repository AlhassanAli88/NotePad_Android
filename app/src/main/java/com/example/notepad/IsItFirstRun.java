package com.example.notepad;

public class IsItFirstRun {

    private static IsItFirstRun instance;
    private boolean firstRun = true;

    private IsItFirstRun() {

    }

    public static synchronized IsItFirstRun getInstance() {
        if (instance == null) {
            instance = new IsItFirstRun();
        }
        return instance;
    }

    public Boolean getFirstRun() {
        return firstRun;
    }

    public void setFirstRun(boolean value) {
        firstRun = value;
    }

}
