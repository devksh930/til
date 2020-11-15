package com.devksh930.TemplateMethod.dp;

public abstract class AbstGameConnectHelper {

    protected abstract String doSecurity(String string);

    protected abstract boolean authentication(String id, String password);

    protected abstract int authorization(String userName);

    protected abstract String connection(String info);

    //템플릿메소드
    public String requestConnection(String encodedInfo) {

        //보안 작업 -> 암호화 된 문자열 decode
        String decodedInfo = doSecurity(encodedInfo);

        //반환 된것을 가지고 아이디, 암호를 할
        String id = "aaa";
        String password = "bbb";

        if (!authentication(id, password)) {
            throw new Error("아이디 암호 불일치");
        }

        String userName = "";
        int i = authorization(userName);
        switch (i) {
            case -1:
                throw new Error("셧다운");
             case 0:
                System.out.println("게임매니저");
                break;
            case 1:
                System.out.println("유료회원");
                break;
            case 2:
                System.out.println("무료회원");
                break;
            case 3:
                System.out.println("무료회원");
                break;
            default:
                break;
        }
        return connection(decodedInfo);
    }
}
