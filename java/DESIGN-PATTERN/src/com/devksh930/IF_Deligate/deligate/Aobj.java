package com.devksh930.IF_Deligate.deligate;

import com.devksh930.IF_Deligate.InterFace.Ainterface;
import com.devksh930.IF_Deligate.InterFace.AinterfaceImpl;

public class Aobj {

    Ainterface ainterface;

    public  Aobj(){
        ainterface = new AinterfaceImpl();
    }

   public void funcAA() {
       //ainteface에 위임한다
        ainterface.FuncA();
        ainterface.FuncA();
       }
}
