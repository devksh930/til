package com.devksh930.TemplateMethod;

import com.devksh930.TemplateMethod.dp.AbstGameConnectHelper;
import com.devksh930.TemplateMethod.dp.DefaultGameConnectHelper;

public class TemplateMain {
    public static void main(String[] args) {
        AbstGameConnectHelper helper = new DefaultGameConnectHelper();

        helper.requestConnection("아이디 암호 등 접속 정보");

    }
}
