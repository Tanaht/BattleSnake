package fr.istic.mmm.battlesnake.socket.Message.fromServer;

/**
 * Created by loic on 24/03/18.
 */

public class MessagePlayerLose extends MessageFromServer<Integer>  {
    public MessagePlayerLose(Integer data) {
        super(TypeMessageServerToClient.PLAYER_LOSE, data);
    }
}
