#include "mbed.h"

#define RANGE_CON	58.0
#define LIMIT_ECHO_T 30000

DigitalOut trig(PB_6); //triger out
DigitalIn echo(PA_8); //echo in
Serial pc(USBTX, USBRX);

Timer t;
int dt;

int main() {
		pc.baud(11520);
		pc.printf("Start distance measurement\n");
		
		t.start(); //start timer
    
	while(1) {
			//trigger ultrasonic sensor
			trig=1;
			wait_us(40);
			trig=0;
			
			//measure echo signal
			while(!echo);
			t.reset();
			while(echo);
			dt=t.read_us();
			if(dt<LIMIT_ECHO_T){
			pc.printf("Measured echo time= %d\n",dt);
			pc.printf("Distance = %6.2f\n\n",dt/RANGE_CON);
		
			wait(0.5);
	}
			else{
			pc.printf("NO object detected.\n\n");
				wait(0.5);
			}
}
}
