package fr.istic.mmm.battlesnake.model;

public enum Direction {
    TOP, BOT, LEFT, RIGHT;

    public static Direction getRealDirection(Direction actualDirection, Direction directionToGo){
        switch (actualDirection){
            case TOP:
                switch (directionToGo){
                    case TOP:
                        return TOP;
                    case BOT:
                        return TOP;
                    case LEFT:
                        return LEFT;
                    case RIGHT:
                        return RIGHT;
                    default:
                        throw new RuntimeException("actual direction of snake is not implemented : "+directionToGo);
                }
            case BOT:
                switch (directionToGo){
                    case TOP:
                        return BOT;
                    case BOT:
                        return BOT;
                    case LEFT:
                        return LEFT;
                    case RIGHT:
                        return RIGHT;
                    default:
                        throw new RuntimeException("actual direction of snake is not implemented : "+directionToGo);
                }
            case LEFT:
                switch (directionToGo){
                    case TOP:
                        return TOP;
                    case BOT:
                        return BOT;
                    case LEFT:
                        return LEFT;
                    case RIGHT:
                        return LEFT;
                    default:
                        throw new RuntimeException("actual direction of snake is not implemented : "+directionToGo);
                }
            case RIGHT:
                switch (directionToGo){
                    case TOP:
                        return TOP;
                    case BOT:
                        return BOT;
                    case LEFT:
                        return RIGHT;
                    case RIGHT:
                        return RIGHT;
                    default:
                        throw new RuntimeException("actual direction of snake is not implemented : "+directionToGo);
                }
            default:
                throw new RuntimeException("actual direction of snake is not implemented : "+actualDirection);
        }
    }
}
