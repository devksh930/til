#include "mbed.h"

DigitalIn btn1(PA_14);
DigitalOut led(PA_13);

typedef enum{
	NO_EDGE=0, RISING_EDGE, FALLING_EDGE}edge_t;
edge_t detectBtn1Edge(){
	static int prevState=1;
	edge_t edge=NO_EDGE;
	if(btn1 !=prevState){
		wait(0.05); //debounce delay
		if(btn1 != prevState){ //노이즈에 의해 값이 바뀔수도 있어서 한번더 비교한다
				if(btn1 ==1) edge = RISING_EDGE;
				else edge = FALLING_EDGE;
				prevState=btn1;
}
		}
	return edge;
	}

	int main() {
    while(1) {
			if(detectBtn1Edge()==FALLING_EDGE){
				
       led1=!led1;
    }
}
}
