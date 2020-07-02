package com.devksh930.Strategy;

public class StrategyMain {
    public static void main(String[] args) {
        GameCharacter character = new GameCharacter();

        character.attack();
        //교환
        character.setWeapone(new Knife());
        character.attack();
        //교환
        character.setWeapone(new Sword());
        character.attack();
        //교환
        character.setWeapone(new AX());
        character.attack();

    }
}
