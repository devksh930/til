#include "mbed.h"

AnalogIn IRsensor(PC_5);
Serial pc(USBTX,USBRX);
DigitalOut myled(LED1);

double map(double x,
	double in_min,double in_max,
		double out_min, double out_max){
		double scale =(out_max - out_min)/(in_max-in_min);
		return scale *x;
		}

int main() {
	double ir, volt,dist;
	pc.baud(115200);
    while(1) {
			ir=0;
			for(int i=0;i<5;i++){
				ir+=IRsensor;
			}
			ir/=5;
			
			ir=IRsensor.read();
			pc.printf("ir sensor value : %4.2f\n",ir);
			if(ir<0.1) ir=0 ;
			
			if(ir !=0){
				volt = map(ir,0.0,1.0,0,3200);
				dist =( (27.61f/(volt-0.1696f)) * 1000);
			}
			else{
				dist=0;
			}
			pc.printf("distance: %5.2f cm\n\n",dist);
			
			wait(0.5);
			
}
}