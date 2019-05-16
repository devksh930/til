#include "mbed.h"

DigitalOut led1(PA_13);
DigitalIn btn1(PA_13);

int main(){
		while(1){
	if(!btn1){
		led1 = 1;
		wait(2.0);
		led1 = 0;
	}
}
}

