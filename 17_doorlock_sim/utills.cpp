#include "mbed.h"
#include "doorlock_sim.h"
DigitalOut buzzer(PC_9);
BusIn buttons(PA_14,PB_7,PC_4);
BusOut leds(PA_13,PB_10,PA_4);

Timer t;
Ticker ticker;

void initDevice(){
	t.start();
	}

	

void playNote(char note, double play_time) {
	static int notePeriods[] = {
		1136, 1014, 1915, 1700, 1519, 1432, 1275, 956
	};

  int half_period = (note == 'C') ? notePeriods[7] : notePeriods[note-'a'];

	t.reset();
	while (t.read() < play_time) {
		buzzer = 1;
		wait_us(half_period);
		buzzer = 0;
		wait_us(half_period);
	}  
}	

uint8_t readButtonEdge() {
	static int prevState = 0b00000111;
	uint8_t edge = NO_EDGE;
  int currState = buttons;
	
	int edges = currState ^ prevState;
	if (edges != 0) {
		wait(0.1);			// debouncing
		if (edges & 0x01) {
			edge = (currState & 0x01) ? BTN1_RISING : BTN1_FALLING;
		}
		else if (edges & 0x02) {
			edge = (currState & 0x02) ? BTN2_RISING : BTN2_FALLING;
		}
		else if (edges & 0x04) {
			edge = (currState & 0x04) ? BTN3_RISING : BTN3_FALLING;
		}
		prevState = currState;
	}
	
	return edge;
}

uint8_t getButtonInput() {
	uint8_t edge = readButtonEdge();
	if (edge & 0x10) {
		BUTTON_BEEP();
	}
	return edge;
}

void displayLeds(int ledValue) {
	leds = ledValue;
}

void blink_led1() {
	leds = leds ^ LED1_ON;
}

void blink_led3() {
	leds = leds ^ LED3_ON;
}
void startBlinkled1(){
ticker.attach(&blink_led1, 0.5);
}
void stopBlinkled1(){
ticker.detach();
}

void startBlinkled3(){
ticker.attach(&blink_led3, 0.5);
}
void stopBlinkled3(){
ticker.detach();}
