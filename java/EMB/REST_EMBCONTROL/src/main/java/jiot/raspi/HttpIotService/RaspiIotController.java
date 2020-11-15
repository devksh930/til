/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.HttpIotService;

import java.util.Observable;
import java.util.Observer;
import javax.annotation.PreDestroy;
import jiot.raspi.things.ControlPoint;
import jiot.raspi.things.ControlPointContainer;
import jiot.raspi.things.FloatInputSupport;
import jiot.raspi.things.OutputControlPoint;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class RaspiIotController implements Observer{
    private ControlPointContainer cpContainer = null;

    public RaspiIotController() {
        cpContainer = ControlPointContainer.getInstance();
        cpContainer.start();
        
        cpContainer.getControlPoints().forEach((cp) -> {
            cp.addObserver(this);
        });
        
        System.out.println(">>> Start CP Container and register observer to each CP...");
    }
    
    @PreDestroy
    public void close() {
        if (cpContainer != null) {
            cpContainer.stop();
            System.out.println(">>> Stop CP Container...");
        }
    }
    
    private void write(String msg) {
        System.out.println(">>> " + msg);
    }

    
    
    @Override
    public void update(Observable obj, Object arg) {
        if (obj instanceof ControlPoint) {
            ControlPoint cp = (ControlPoint)obj;
            
            if (arg == null) {
                write("<Observer> changed value(" + cp.getName() + "):" + cp.getPresentValue());
            } else {
                if (arg.toString().equals("name")) {
                    write("<Observer> changed name(" + cp.getName() + "):" + cp.getName());
                } else {
                    write("<Observer> changed(" + cp.getName() + "):" + arg);
                }
            }
        }
    }
    
  
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(String args){
        return "<!DOCTYPE html>\n" +
               "<html lang=\"en\">\n" +
               "<head>\n" +
               "<meta charset=\"UTF-8\">\n" +
               "<title>http service</title>\n" +
               "</head>\n" +
               "<body>\n" +
                    "<p><a href='./fanon'>fan on</a></p>\n" +
                    "<p><a href='./fanoff'>fan off</a></p>\n" +
                    "<p><a href='./ledon/1'>led1 on</a> &nbsp <a href=\"./ledon/2\">led2 on</a>&nbsp <a href=\"./ledon/3\">led3 on</a> </p>\n" +
                    "<p><a href='./ledoff/1'>led1 off</a> &nbsp <a href=\"./ledoff/2\">led2 off</a>&nbsp <a href=\"./ledoff/3\">led3 off</a> </p>\n" +
                    "<p><a href='./getpir'>움직임감지</a></p>\n" +
                    "<p><a href='./gettmp'>현재 온도</a></p>\n" +
                    "<p><a href='./gethmd'>현재 습도</a></p>\n" +
                    "<p><a href='./getcds'>현재 밝기</a></p>\n" +                
               "</body>\n" +
               "</html>" ;

    }
      @RequestMapping(value="/ledon/{ledId}", method=RequestMethod.GET)
    public String ledOn(@PathVariable("ledId")String ledId) {   // ledId 매개 변수를 URL Path의 ledId에서 받아와란
        int ledNum = Integer.parseInt(ledId);
        
        if (ledNum < 1 || ledNum > 3) {
            return "LED ID value is 1~3 <p><a href=\"../\">홈으로</a></p>";
        } else {
            int ledCpId = ControlPointContainer.ControlPointID.LED1.getId() + (ledNum - 1);
            
            OutputControlPoint cp = (OutputControlPoint)cpContainer.getControlPoint(ledCpId);
            
            cp.setPresentValue(1);
            
            return "LED #" + ledId + " is turn on \n <p><a href=\"../\">홈으로</a></p>";
        }
    }
    
    @RequestMapping(value="/ledoff/{ledId}", method=RequestMethod.GET)
    public String ledOff(@PathVariable("ledId")String ledId) {   // ledId 매개 변수를 URL Path의 ledId에서 받아와란
        int ledNum = Integer.parseInt(ledId);
        
        if (ledNum < 1 || ledNum > 3) {
            return "LED ID value is 1~3";
        } else {
            int ledCpId = ControlPointContainer.ControlPointID.LED1.getId() + (ledNum - 1);
            
            OutputControlPoint cp = (OutputControlPoint)cpContainer.getControlPoint(ledCpId);
            
            cp.setPresentValue(0);
            
            return "LED #" + ledId + " is turn off \n <p><a href=\"../\">홈으로</a></p>";
        }
    }
    
    @RequestMapping(value="/getButton", method=RequestMethod.GET)
    public String getButton(@RequestParam("btn")String btn) {   
        int btnNum = Integer.parseInt(btn);
        
        if (btnNum < 1 || btnNum > 2) {
            return "Button ID value is 1~2";
        } else {
            int btnCpId = ControlPointContainer.ControlPointID.BTN1.getId() + (btnNum - 1);
            
            ControlPoint cp = cpContainer.getControlPoint(btnCpId);
            
            int value = cp.getPresentValue();
            
            return "Button #" + btn + " is = " + ((value == 0) ? " Press":" Released");
        }
    }
    
    @RequestMapping(value="/fanon", method=RequestMethod.GET)
    public String FanOn() {
        int fanCpId = ControlPointContainer.ControlPointID.FAN.getId();
            
        OutputControlPoint cp = (OutputControlPoint)cpContainer.getControlPoint(fanCpId);
            
        cp.setPresentValue(2);
            
        return "fan on \n  <p><a href=\"./\">홈으로</a></p>";
    }
    
    @RequestMapping(value="/fanoff", method=RequestMethod.GET)
    public String FanOff() {
        int fanCpId = ControlPointContainer.ControlPointID.FAN.getId();
            
        OutputControlPoint cp = (OutputControlPoint)cpContainer.getControlPoint(fanCpId);
            
        cp.setPresentValue(1);
            
        return "fan off \n <p><a href=\"./\">홈으로</a></p>";
    }
    
    @RequestMapping(value="/getpir", method=RequestMethod.GET)
    public String getPIR() {
        int pirCpId = ControlPointContainer.ControlPointID.PIR.getId();
            
        ControlPoint cp = cpContainer.getControlPoint(pirCpId);
            
        int value = cp.getPresentValue();
            
        return "움직임: " + ((value == 0) ? "not detected":"detected") + "\n <p><a href=\"./\">뒤로가기</a></p>";
    }
    
    @RequestMapping(value="/getcds", method=RequestMethod.GET)
    public String getCDR() {
        int cdrCpId = ControlPointContainer.ControlPointID.CDR.getId();
            
        ControlPoint cp = cpContainer.getControlPoint(cdrCpId);
            
        float value = cp instanceof FloatInputSupport ? ((FloatInputSupport)cp).getFloatValue() : cp.getPresentValue();
        
        return value + "";
    }
    
    @RequestMapping(value="/gettmp", method=RequestMethod.GET)
    public String getTMP() {
        int cdrCpId = ControlPointContainer.ControlPointID.TMP.getId();
            
        ControlPoint cp = cpContainer.getControlPoint(cdrCpId);
            
        float value = cp instanceof FloatInputSupport ? ((FloatInputSupport)cp).getFloatValue() : cp.getPresentValue();
        
        return value + "";
    }
    
    @RequestMapping(value="/gethmd", method=RequestMethod.GET)
    public String getHMD() {
        int cdrCpId = ControlPointContainer.ControlPointID.HMD.getId();
            
        ControlPoint cp = cpContainer.getControlPoint(cdrCpId);
            
        float value = cp instanceof FloatInputSupport ? ((FloatInputSupport)cp).getFloatValue() : cp.getPresentValue();
        
        return value +"";
        
    }
}
