#include "mbed.h"

DigitalOut led1(PA_13);
DigitalOut led2(PB_10);

InterruptIn btn(PA_14);

Timeout blink;



void led2_off(){led2=0;}
	void led2_on(){
		led2=1;
		blink.attach(&led2_off,2.0);}
void btnisr(){
	blink.attach(&led2_on,2.0);
}
int main() {
	btn.fall(&btnisr);
	
	while(1){
		
			led1 = !led1;
			wait(0.5);
	
}
	}

