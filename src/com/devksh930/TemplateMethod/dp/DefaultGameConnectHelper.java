package com.devksh930.TemplateMethod.dp;

public class DefaultGameConnectHelper extends AbstGameConnectHelper {
    @Override
    protected String doSecurity(String string) {
        System.out.println("강화된 알고리즘을 이용한 디코드");
//        System.out.println("디코드");
        return string;
    }

    @Override
    protected boolean authentication(String id, String password) {
        System.out.println("아이디/암호 확인 과정");
        return true;
    }

    @Override
    protected int authorization(String userName) {
        System.out.println("권한 확인");
        //서버에서 유저이름을 가지고 유저의 나이를 알수 있다
        //나이를 확인하고 지금시가능ㄹ 확인하고 성인아 아닐시 10시가 지났다면
        //권한이 없는 것을 간주
        return 0;
    }

    @Override
    protected String connection(String info) {
        System.out.println("마지막 접속 단계!");
        return info;
    }
}
