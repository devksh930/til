#include "mbed.h"
#include "motordriver.h"

#define PWM_PIN			PA_7
#define DIR_PIN			PC_8

Motor m(PWM_PIN, DIR_PIN);


int main() {
	while(1) {
		for(double s=0; s<=1.0; s+=0.1){
			m.forward(s);
			wait(1);
	}	for(double s=1; s<=0; s-=0.1){
			m.forward(s);
			wait(1);
	}
			for(double s=0; s<=1.0; s+=0.1){
			m.backward(s);
			wait(1);
	}	for(double s=1; s<=0; s-=0.1){
			m.backward(s);
			wait(1);
	}
	m.stop();
}
}