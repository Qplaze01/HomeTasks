package deathrun;

import java.util.concurrent.Phaser;

public class Chairs {
    private Phaser phaser = new Phaser(0);
    private int waitTime = 1;
    private volatile int chairs = 4;
    private volatile int games = 4;

    public synchronized int check() {
        chairs--;
        return chairs;
    }

    public void waitNextStep() {
         try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phaser.arriveAndAwaitAdvance();
    }

    public void leaveGame() {
        phaser.arriveAndDeregister();
        nextGame();
    }

    public void iWantPlay() {
        phaser.register();
    }

    public int getGames() {
        return games;
    }

    private void nextGame() {
        chairs = --games;
    }

    public Chairs() {
        System.out.println("Go");
    }
}
