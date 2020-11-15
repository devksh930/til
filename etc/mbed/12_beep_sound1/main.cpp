#include "mbed.h"

Serial pc(USBTX,USBRX);
AnalogIn sound(A5);

int mian(){
pc.baud(115200);
	while(1){
		pc.printf("laudness : %d\n",sound.read_u16());
		wait(0.3);
	}
}