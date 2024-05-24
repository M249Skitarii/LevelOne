package Game;
import com.example.lostcat.Controller;

public class Message {
    String mes;
    String author;

    public Message(String a, String m){
        author = a;
        this.mes = m;
    }
    public void setMessage(String m){

        mes =m;
    }

    public String getMessage() {
        return mes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void send(){
        Controller.messager(this);
    }
}
