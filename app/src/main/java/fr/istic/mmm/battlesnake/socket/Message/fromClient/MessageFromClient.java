package fr.istic.mmm.battlesnake.socket.Message.fromClient;


public class MessageFromClient<T> {
    private TypeMessageClientToServer typeMsgFromClient;

    private T data;

    public MessageFromClient(TypeMessageClientToServer msgFromClient, T data) {
        this.typeMsgFromClient = msgFromClient;
        this.data = data;
    }

    public TypeMessageClientToServer getMsgFromClient() {
        return typeMsgFromClient;
    }

    public T getData() {
        return data;
    }
}
