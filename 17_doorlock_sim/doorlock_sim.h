#ifndef	DOORLOCK_SIM_H
#define DOORLOCK_SIM_H

//
// State Values
//
typedef enum {
	DOOR_CLOSED = 0,
	PASSWD_INPUT,
	DOOR_OPEN,
	CLOSE_BUTTON_INPUT
} state_t;

typedef enum {
	FIRST_INPUT = 11,
	SECOND_INPUT,
	THIRD_INPUT,
	FOURTH_INPUT,
	PW_SUCCESS,
	PW_FAILURE
} pi_state_t;

//
// Button Input Values
//
#define NO_EDGE				0x00
#define BTN1_FALLING	0x11
#define BTN2_FALLING	0x12
#define BTN3_FALLING	0x14
#define BTN1_RISING		0x21
#define BTN2_RISING		0x22
#define BTN3_RISING		0x24

//
// LED Display Values
//
#define	ALL_OFF				0b00000000
#define	ALL_ON				0b00000111
#define	LED1_ON				0b00000001
#define	LED2_ON				0b00000010
#define	LED3_ON				0b00000100
#define	LED12_ON			0b00000011
#define	LED13_ON			0b00000101
#define	LED23_ON			0b00000110

//
// Return values
//
#define CONTINUE			0
#define	FAILED				-1
#define	SUCCEED				1


#define MODE_CHANGE_TIMEOUT			2.0f	// 3 sec
#define BUTTON_INPUT_TIMEOUT		30.0f	// 60 sec

//#define CHECK_BUTTON_INPUT  		readButtonEdge() & 0x10

#define DISPLAY_DOOR_OPEN()  		displayLeds(ALL_ON)
#define DISPLAY_DOOR_CLOSED()  	displayLeds(ALL_OFF)

#define BUTTON_BEEP()  					playNote('g', 0.3)
#define SUCCESS_BEEP()  				playNote('c', 0.3); wait(0.2); \
																playNote('d', 0.3); wait(0.2); \
																playNote('e', 0.3)
#define FAILURE_BEEP()  				playNote('b', 1.5); 

#endif	// DOORLOCK_SIM_H