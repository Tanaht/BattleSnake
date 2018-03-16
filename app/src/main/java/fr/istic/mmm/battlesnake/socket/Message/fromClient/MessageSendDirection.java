package fr.istic.mmm.battlesnake.socket.Message.fromClient;

import java.util.List;

import fr.istic.mmm.battlesnake.model.Direction;

import static fr.istic.mmm.battlesnake.socket.Message.fromClient.TypeMessageClientToServer.NEXT_DIRECTION_PLAYER;


public class MessageSendDirection extends MessageFromClient<Direction> {
    public MessageSendDirection(Direction data) {
        super(NEXT_DIRECTION_PLAYER, data);
    }
}
