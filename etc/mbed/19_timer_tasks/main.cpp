#include "mbed.h"

DigitalOut led1(PA_13);
DigitalOut led2(PB_10);

Timer timer_fast;
Timer timer_slow;

void task_fast(){
led1=!led1;
}
void task_slow(){
led2=!led2;
}

int main() {
	timer_fast.start();
	timer_slow.start();
	
	
	while(1){
		if(timer_fast.read()>=0.2){
		task_fast();
			timer_fast.reset();
		}
		if(timer_slow.read()>=1.0){
		task_slow();
			timer_slow.reset();
		
		}
		
	}
}
	

