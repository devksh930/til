#include "mbed.h"

DigitalIn btn1(PA_14);
DigitalIn btn2(PB_7);
BusOut ledBus(PA_13,PB_10,PA_4);

typedef enum{
	NO_EDGE=0, RISING_EDGE, FALLING_EDGE}edge_t;
edge_t detectBtn1Edge(){
	static int prevState=1;
	edge_t edge=NO_EDGE;
	
	if(btn1 !=prevState){
		wait(0.05); //debounce delay
		if(btn1 != prevState){
				if(btn1 ==1) edge = RISING_EDGE;
				else edge = FALLING_EDGE;
				prevState=btn1;
}
		}
	}
edge_t detectBtn2Edge(){
	static int prevState=1;
	edge_t edge=NO_EDGE;
	
	if(btn2 !=prevState){
		wait(0.05); //debounce delay
		if(btn2 != prevState){
				if(btn2 ==1) edge = RISING_EDGE;
				else edge = FALLING_EDGE;
				prevState=btn2;
			}
		}
	return edge;
	}

	int main() {
    while(1) {
			if(detectBtn1Edge()==FALLING_EDGE){
				
       ledBus= 0b00000111;
				
				
    }
			else if(detectBtn2Edge()== FALLING_EDGE){
				ledBus=0;
}
}
		}
	
