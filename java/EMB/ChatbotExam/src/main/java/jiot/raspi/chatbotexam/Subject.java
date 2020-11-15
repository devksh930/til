package jiot.raspi.chatbotexam;

public class Subject {
    private static Subject instance;
    private Subject(){}
    public static Subject getInstance(){
        if(instance==null)
            instance=new Subject();
        return instance;
    }
}
