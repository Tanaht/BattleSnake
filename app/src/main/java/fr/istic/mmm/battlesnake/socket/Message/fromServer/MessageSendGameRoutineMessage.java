package fr.istic.mmm.battlesnake.socket.Message.fromServer;


import fr.istic.mmm.battlesnake.model.RoutineMessageContent;

import static fr.istic.mmm.battlesnake.socket.Message.fromServer.TypeMessageServerToClient.GAME_ROUTINE_MESSAGE;

public class MessageSendGameRoutineMessage extends MessageFromServer<RoutineMessageContent>{
    public MessageSendGameRoutineMessage(RoutineMessageContent data) {
        super(GAME_ROUTINE_MESSAGE, data);
    }
}
