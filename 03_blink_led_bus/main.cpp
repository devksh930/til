#include "mbed.h"

BusOut ledBus(PA_13,PB_10,PA_4); //green, yellow, red

int ledValues[] = {
	0b00000000, //all led off
	0b00000001, //green led on
	0b00000010, //yellow led on
	0b00000100 //red led on
	};
int main(){/*
		int count=sizeof(ledValues)/sizeof(ledValues[0]); //ledvalues배열의 갯수 구하기
		while(1){
			for(int i=0; i<count; i++){
			ledBus.write(ledValues[i]);
				ledBus=ledValues[i];
				wait(0.5);*/
	
		
			}
		}
}
