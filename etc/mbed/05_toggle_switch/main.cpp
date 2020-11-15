#include "mbed.h"

DigitalOut led1(PA_13);
DigitalIn btn1(PA_14);

int main() {
    while(1) {
			if(!btn1){
				
				//int value=led1;
				//led1=!value;
       led1=!led1;
			 wait(0.3); //디바운스 딜레이

			}
	}
}
