#include "mbed.h"

//DigitalOut led(PA_5);
DigitalOut led(PB_10);

int main(){
		// setup-initialrized

	// main loop
		while(1){
/*			led.write(1);//led on
			wait(0.5); //delay to 500ms

			led.write(0);//led off
			wait(0.5); //delay to 500ms
	*/
			
			//mbed api
			led=1;
			wait(0.5);
				
			led=0;
			wait(0.5);
		}
}
