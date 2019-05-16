#include "mbed.h"

DigitalOut buzzer(PC_9);
#define NOTE_c_DELAY 1915 //261HZ
Timer t;

int notePlayDelay[] = { 
	1136, //a
	1014, //b
	1915, //c
	1700,
	1519,
	1432,
	1275,
	956
};

void playNote(char note,float playTime){
	int timeHigh = (note == 'C') ? notePlayDelay[7]:notePlayDelay[note-'a']; 
	
	t.reset();
	while (t.read() <(float)playTime){
	buzzer = 1;
	wait_us(timeHigh);
	buzzer = 0;
	wait_us(timeHigh);

	}
	
}

int main() {
	char song[]="cbabcccbbbcccbcacbbccbca";
		int size = sizeof(song)/sizeof(song[0]);
	
	t.start();
    while(1) {
			for(int i=0; i<size; i++){
				playNote(song[i],0.5);
				wait(0.1);
			}
			}
			wait(0.5);
    }

