package Server;

public class SStringProcessing implements Runnable{

    private String message;

    //contructor

    public SStringProcessing(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
//getter


    @Override
    public void run() {

        if (message != null) {
            StringNormallize normallize = new StringNormallize();
            message = normallize.normalize();
            System.out.println("SStringProcessing: " + message);
        }
    }
}
