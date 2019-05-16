#include "mbed.h"

DigitalIn btn1(PA_14);
DigitalOut led1(PA_13);

DigitalIn btn2(PB_7);
DigitalOut led2(PB_10);

DigitalIn btn3(PC_4);
DigitalOut led3(PA_4);

BusOut leds(PA_13, PB_10, PA_4);


typedef enum {
 NO_EDGE = 0, RISING_EDGE, FALLING_EDGE
} edge_t;


edge_t detectBtn1Edge() {
 static int prevState = 1;
 edge_t edge = NO_EDGE;
 
 if (btn1 != prevState) {
  wait(0.05);
  if(btn1 != prevState) {
   if(btn1 == 0) edge = FALLING_EDGE;
   else edge = RISING_EDGE;
   prevState = btn1;
  }
 }
 return edge;
}


edge_t detectBtn2Edge() {
 static int prevState = 1;
 edge_t edge = NO_EDGE;
 
 if (btn2 != prevState) {
  wait(0.05);
  if(btn2 != prevState) {
   if(btn2 == 0) edge = FALLING_EDGE;
   else edge = RISING_EDGE;
   prevState = btn2;
  }
 }
 return edge;
}


edge_t detectBtn3Edge() {
 static int prevState = 1;
 edge_t edge = NO_EDGE;
 
 if (btn3 != prevState) {
  wait(0.05);
  if(btn3 != prevState) {
   if(btn3 == 0) edge = FALLING_EDGE;
   else edge = RISING_EDGE;
   prevState = btn3;
  }
 }
 return edge;
}

 


int state = 0; // 0 = lock, 1 = unlock
Timer t;
int dt = 0;
int password[] = {0, 0, 0, 0};


int main() {
 t.start();
 
 while(1) {
  if(state == 0) { // state = lock
   int click = 0;
   
   while(click < 4) {
    if(detectBtn1Edge() == FALLING_EDGE) {
     led1 = 1; wait(0.2); led1 = 0;
     password[click] = 1;
     click++;
    }
    else if(detectBtn2Edge() == FALLING_EDGE) {
     led2 = 1; wait(0.2); led2 = 0;
     password[click] = 2;
     click++;
    }
    else if(detectBtn3Edge() == FALLING_EDGE) {
     led3 = 1; wait(0.2); led3 = 0;
     password[click] = 3;
     click++;
    }
   }
   
   if(password[0] == 1 && password[1] == 2 && password[2] == 3 && password[3] == 1) {
    state = 1;
    
    // led event
    led1 = 1; wait(0.3); led1 = 0;
    led2 = 1; wait(0.3); led2 = 0;
    led3 = 1; wait(0.3); led3 = 0;
    wait(0.3);
    leds = 0b00000111; wait(0.3);
    leds = 0b00000000; wait(0.3);
    leds = 0b00000111;
    
    for(int i = 0; i < 4; i++)
     password[i] = 0;
   }
   else {
    for(int i = 0; i < 4; i++)
     password[i] = 0;
    
    // led event
    leds = 0b00000111; wait(0.3);
    leds = 0b00000000; wait(0.3);
    leds = 0b00000111; wait(0.3);
    leds = 0b00000000; wait(0.3);
   }
  }
  else { // state = unlock
   while(state == 1) {
    if(detectBtn1Edge() == FALLING_EDGE) {
     t.reset();
     while(!btn1) wait(0.1);
     dt = t.read();
     
     if(dt > 3) {
      state = 0;
      
      //led event
      led3 = 1; wait(0.3); led3 = 0;
      led2 = 1; wait(0.3); led2 = 0;
      led1 = 1; wait(0.3); led1 = 0;
      wait(0.3);
      leds = 0b00000111; wait(0.3);
      leds = 0b00000000;
      
      break;
     }
    }
   }
  }
  
 }
}