package com.devksh930.IF_Deligate;

import com.devksh930.IF_Deligate.InterFace.Ainterface;
import com.devksh930.IF_Deligate.InterFace.AinterfaceImpl;
import com.devksh930.IF_Deligate.deligate.Aobj;

public class InterfaceMain {
    public static void main(String[] args) {
        Ainterface ainterface = new AinterfaceImpl();

        //통로
//        ainterface.FuncA();

        Aobj aobj =new Aobj();
        aobj.funcAA();
    }
}
