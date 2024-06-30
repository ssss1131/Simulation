package main.java.ssss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameState {
    private static boolean paused = false;
    public static boolean finishRequested = false;

    public  void togglePause() {
        paused = !paused;
    }

    public  void requestFinish() {
        finishRequested = true;
    }

    public  boolean isPaused() {
        return paused;
    }

    public  boolean isFinishRequested() {
        return finishRequested;
    }

    public void waitForResume() {
        System.out.println("Simulation paused. Press [S] to resume.");
        while (isPaused()) {
            checkUserInput();
        }
    }

    public void checkUserInput() {
        try {
            if (System.in.available() > 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String input = reader.readLine().trim().toUpperCase();

                if ("S".equals(input)) {
                    togglePause();
                } else if ("F".equals(input)) {
                    requestFinish();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

