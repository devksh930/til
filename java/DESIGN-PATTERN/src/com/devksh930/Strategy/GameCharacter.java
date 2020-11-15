package com.devksh930.Strategy;

public class GameCharacter {

    //접근점
    private Weapone weapone;

    //교환 가능
    public void setWeapone(Weapone weapone) {
        this.weapone = weapone;
    }

    public void attack() {

        if (weapone == null) {
            System.out.println("맨손 공격");
        } else {
            //델리게이트
            weapone.attack();
        }

    }
}
