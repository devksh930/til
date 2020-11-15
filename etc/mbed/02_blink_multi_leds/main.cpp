#include "mbed.h"

DigitalOut led1(PA_13); //green led
DigitalOut led2(PB_10); //yellow led
DigitalOut led3(PA_4);  //red led

int main() {
    while(1) {
			led1=!led1;
			wait(0.5);
			led1=0;
			
			led2=!led2;
			wait(0.5);
			led2=0;
			
			led3=!led3;
			wait(0.5);
			led3=0;
    }
}
