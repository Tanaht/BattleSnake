package fr.istic.mmm.battlesnake.socket.Message.fromClient;

import fr.istic.mmm.battlesnake.model.Direction;


public class MessageSendDirection extends MessageFromClient<Direction> {
    public MessageSendDirection(TypeMessageClientToServer msgFromClient, Direction data) {
        super(msgFromClient, data);
    }
}
