#ifndef UTILS_H
#define UTILS_H
#include "mbed.h"
  //다른모듈에서 사용하기위해 프로토 타입을 선언해준것이다.
	
void initDevs();
	
void playNote(char note, double play_time) ;


uint8_t readButtonEdge();


uint8_t getButtonInput();
	

void displayLeds(int ledValue);


void startBlinkLed1();

void stopBlinkLed1();

void startBlinkLed3();

void stopBlinkLed3();

#endif   ///