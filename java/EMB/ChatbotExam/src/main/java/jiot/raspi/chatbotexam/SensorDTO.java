package jiot.raspi.chatbotexam;

public class SensorDTO {
    private String temp;
    private String hmd;
    private String cdr;

    public String getCdr() {
        return cdr;
    }

    public void setCdr(String cdr) {
        this.cdr = cdr;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHmd() {
        return hmd;
    }

    public void setHmd(String hmd) {
        this.hmd = hmd;
    }
}
