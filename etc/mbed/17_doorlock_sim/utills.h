#ifndef UTTILS_H
#define UTILLS_H

#include "mbed.h"
void initDevice();

void playNote(char note, double play_time);

uint8_t readButtonEdge();

uint8_t getButtonInput();

void displayLeds(int ledValue);

void startBlinkedled1();
void stopBlinkLed1();
void startBlinkedled3();
void stopBlinkLed3();

#endif //UTILLS_H
