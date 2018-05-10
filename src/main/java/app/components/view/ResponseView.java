package app.components.view;

public class ResponseView {
    public String message;

    public ResponseView() {
    }

    public ResponseView(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseView{" +
                "message='" + message + '\'' +
                '}';
    }
}
