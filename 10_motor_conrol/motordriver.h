#include "mbed.h"

#ifndef MOTORDRIVER_H 
#define MOTORDRIVER_H


typedef enum{
	BACKWARD = -1,
	STOP = 0,
	FORWARD = 1
} status_t;
class Motor{
	public:
		Motor(PinName pwm, PinName dir);
		void forward(double speed);
		void backward(double speed);
		void stop();
	
	protected:
		PwmOut _pwm;
		DigitalOut _dir;
		status_t status;
	
};

#endif