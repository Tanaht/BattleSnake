package fr.istic.mmm.battlesnake.socket.Message.fromServer;

import static fr.istic.mmm.battlesnake.socket.Message.fromServer.TypeMessageServerToClient.NUMBER_OF_PLAYER;


public class MessageSendNumberOfPlayer extends MessageFromServer<Integer> {
    public MessageSendNumberOfPlayer(Integer data) {
        super(NUMBER_OF_PLAYER, data);
    }
}
