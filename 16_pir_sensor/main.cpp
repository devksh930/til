#include "mbed.h"

DigitalIn PIRsensor(D5);
Serial pc(USBTX,USBRX);
DigitalOut led(PA_13);

int motion = 0;

int main() {

	pc.baud(115200);
	printf("Start..\n");
	while(1) {
	motion = PIRsensor;
		if (motion){
			led=1;
			printf("Motion detected..\n");
			wait(3.0);
		}else{
			led=0;
		}
		wait(0.3);
			
}
}