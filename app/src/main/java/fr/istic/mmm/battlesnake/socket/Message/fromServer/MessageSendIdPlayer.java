package fr.istic.mmm.battlesnake.socket.Message.fromServer;

import static fr.istic.mmm.battlesnake.socket.Message.fromServer.TypeMessageServerToClient.PLAYER_ID;

/**
 * Created by loic on 15/03/18.
 */

public class MessageSendIdPlayer extends MessageFromServer<Integer> {
    public MessageSendIdPlayer(Integer data) {
        super(PLAYER_ID, data);
    }
}
