package de.hsh.capstoneris.socket.messages.server;


/* Statuscodes

400 - InvalidInput -> Client sends message with invalid input data
403 - IllegalOperation -> Client sends message which is not allowed in it's current state

 */

public class ErrorMessage implements ServerMessage {
    public int status;
    public String message;

    public ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorMessage() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*TODO
    *  error
Fehlercode
Fehlermeldung
→ Client zeigt Fehler als Notification oder so an
*/
}
