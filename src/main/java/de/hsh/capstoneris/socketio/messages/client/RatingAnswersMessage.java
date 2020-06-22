package de.hsh.capstoneris.socketio.messages.client;

import de.hsh.capstoneris.rest.json.JsonAnswer;

public class RatingAnswersMessage implements ClientMessage {
    public JsonAnswer[] answers;
    public String feedback;
}
