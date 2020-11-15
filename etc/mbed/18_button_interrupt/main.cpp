#include "mbed.h"

DigitalOut led1(PA_13);
DigitalOut led2(PB_10);

//DigitalIn btn(PA_14);
InterruptIn btnIRQ(PA_14);
void btnisr(){
	led2=!led2;
}
int main() {
			btnIRQ.fall(&btnisr);
		while(1){
		
		
		led1=!led1;
		wait(0.5);
	}
}
	

