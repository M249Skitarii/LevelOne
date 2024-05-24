package Game.Thing;

import com.example.lostcat.Controller;

public class Talk {

    private String question;
    public String Talk(){
        return Controller.talker(this);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
