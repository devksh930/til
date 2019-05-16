#include "mbed.h"

PwmOut red(A1);
PwmOut green(PC_6);
PwmOut blue(A3);

int main() {
		red.period(0.001);
		blue.period(0.001);
		green.period(0.001);
		
		red = 0;
		green = 0;
		blue = 0;
	
    while(1) {
			for(double p = 0.0; p < 1.0 ; p += 0.1) {
			green = 1.0 - ((p < 1.0) ? (1.0 - p) :( (p > 2.0) ? (p - 2.0) : 0));
			red = 1.0 - ((p < 1.0) ? p : ((p > 2.0) ? (2.0-p): 1.0)); 
			blue = 1.0 - ((p < 1.0) ? 1.0 : ((p > 2.0) ? (2.0-p) :1.0-p)); 
				wait (1); 
				} 
			} 
} 
			