package de.hsh.capstoneris.socketio.messages.server;

import de.hsh.capstoneris.rest.json.JsonQuestion;

import java.util.ArrayList;

public class RatingQuestionsMessage implements ServerMessage {
    public ArrayList<JsonQuestion> questions;

    public RatingQuestionsMessage(ArrayList<JsonQuestion> questions) {
        this.questions = questions;
    }
}
