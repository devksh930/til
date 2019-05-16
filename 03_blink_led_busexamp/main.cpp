#include "mbed.h"

BusOut ledBus(PA_13,PB_10,PA_4); //green, yellow, red

int ledValues[] = {
	0b00000000, //0 led off
	0b00000001, //1 led on
	0b00000010, //2 led on
	0b00000011, //3 led on
	0b00000100,	//4
	0b00000101, //5
	0b00100110, //6
	0b01000111  //7
	
};
int main(){
		int count=sizeof(ledValues)/sizeof(ledValues[0]); //ledvalues배열의 갯수 구하기
		while(1){
			for(int i=0; i<count; i++){
			ledBus.write(i);
				wait(0.5);
	
		
			}
		}
}
