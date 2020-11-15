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
//  edge 의 반환 값들이다.
#define NO_EDGE				0x00
#define BTN1_FALLING	0x11
#define BTN2_FALLING	0x12
#define BTN3_FALLING	0x14
#define BTN1_RISING		0x21
#define BTN2_RISING		0x22
#define BTN3_RISING		0x24

//
// LED 디스플레이 값.
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
// 반환 값을 상수로 정의
//
#define CONTINUE			0  //계속 수행중일경우
#define	FAILED				-1  //실패 할경우
#define	SUCCEED				1   //성공한경우


#define MODE_CHANGE_TIMEOUT			2.0f	// 3 sec
#define BUTTON_INPUT_TIMEOUT		10.0f	// 30 sec-

//#define CHECK_BUTTON_INPUT  		readButtonEdge() & 0x10

#define DISPLAY_DOOR_OPEN()  		displayLeds(ALL_ON)   //all led on
#define DISPLAY_DOOR_CLOSED()  	displayLeds(ALL_OFF)

#define BUTTON_BEEP()  					playNote('g', 0.3)
																	  // (\ = 의미는 메크로 다음 줄 연결)
#define SUCCESS_BEEP()  				playNote('c', 0.3); wait(0.2); \
																playNote('d', 0.3); wait(0.2); \
																playNote('e', 0.3)
																
#define FAILURE_BEEP()  				playNote('b', 1.5); 

#endif	// DOORLOCK_SIM_H

