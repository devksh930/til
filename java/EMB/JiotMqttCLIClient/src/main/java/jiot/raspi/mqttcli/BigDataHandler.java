/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jiot.raspi.mqttcli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author yjkim
 */
public class BigDataHandler {
    private List<ChangeOfValue> storage = 
            Collections.synchronizedList(new ArrayList<ChangeOfValue>());
    
    public void saveBigData(ChangeOfValue cov) {
        storage.add(cov);
    }
    
    public void displayBigData(){
        for (ChangeOfValue cov : storage) {
            System.out.println(cov);
        }
    }
    
    public void clearBigData(){
        storage.clear();
    }    
}
