package fr.istic.mmm.battlesnake.model;


import java.util.List;

public class RoutineMessageContent {

    private List<Direction> newPlayerDirection;
    private int applePositionX;
    private int ApplePositionY;

    public RoutineMessageContent(List<Direction> newPlayerDirection, int applePositionX, int applePositionY) {
        this.newPlayerDirection = newPlayerDirection;
        this.applePositionX = applePositionX;
        ApplePositionY = applePositionY;
    }

    public List<Direction> getNewPlayerDirection() {
        return newPlayerDirection;
    }

    public int getApplePositionX() {
        return applePositionX;
    }

    public int getApplePositionY() {
        return ApplePositionY;
    }
}
