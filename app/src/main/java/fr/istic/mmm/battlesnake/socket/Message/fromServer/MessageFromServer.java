package fr.istic.mmm.battlesnake.socket.Message.fromServer;

public class MessageFromServer<T> {
    private TypeMessageServerToClient typeMsgFromServer;

    private T data;

    public MessageFromServer(TypeMessageServerToClient msgFromServer, T data) {
        this.typeMsgFromServer = msgFromServer;
        this.data = data;
    }

    public TypeMessageServerToClient getMsgFromServer() {
        return typeMsgFromServer;
    }

    public T getData() {
        return data;
    }
}
