#include "mbed.h"

PwmOut red(A1);
PwmOut green(PC_6);
 PwmOut blue(A3);

int main() {
		green.period(0.001);
		green = 0;
    while(1) {
			for(double b=0; b<=1.0; b+=0.1){
				green = b;
				wait(1);
			 }
			for(double b=1;b>=0; b-=0.1){
				green = b;
				wait(1);
			}
}
}