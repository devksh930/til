#include "mbed.h"

DigitalOut led1(PA_13);
DigitalOut led2(PB_10);
InterruptIn btn(PA_14);
Ticker ticker_f;
Ticker ticker_s;

Timeout blink;
int act=1;

void slow(){
		led2=!led2;
	}
void fast(){
	led1=!led1;
}
void btnISR()
{
	if(act){
	ticker_f.detach();
	ticker_s.detach();
		act=0;
		
	}
	else{
ticker_f.attach(&fast,0.2);
ticker_s.attach(&slow,1.0);
	act=1;
	}
}

int main() {

ticker_f.attach(&fast,0.2);
ticker_s.attach(&slow,1.0);
btn.fall(&btnISR);
	while(1){
		
		wait(1.0);
}
	}

