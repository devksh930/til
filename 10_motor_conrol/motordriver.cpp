#include "motordriver.h"


Motor::Motor(PinName pwm, PinName dir)
	: _pwm(pwm), _dir(dir)
	
	{
	_pwm.period(0.001);	//Pulse freq. 1khz
	_pwm = 0;
	
	_dir=0;
	status = STOP;
}
void Motor::forward(double speed){
	if (status == BACKWARD){
	_pwm = 0;
		wait(0.2);
	}
	_dir = 1;
	_pwm = speed;
	status = FORWARD;
}
void Motor::backward(double speed){
	if (status == FORWARD){
	_pwm = 1;
		wait(0.2);
	}
	_dir = 0;
	_pwm = speed;
	status = FORWARD;
}


void Motor::stop(){
	_pwm=0;
	status =STOP;
}
