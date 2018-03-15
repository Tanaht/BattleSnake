package fr.istic.mmm.battlesnake.socket.Message.fromServer;

import fr.istic.mmm.battlesnake.model.Cell;

public class MessageSendBoard extends MessageFromServer<Cell[][]>  {
    public MessageSendBoard(Cell[][] data) {
        super(TypeMessageServerToClient.BOARD_TO_DRAW, data);
    }
}
