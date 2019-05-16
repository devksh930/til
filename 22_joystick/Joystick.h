/**
@file Joystick.h
@brief Joysitck header file containing member functions and variables
*/
//Joystick class - Header file
//Define guards
#ifndef JOYSTICK_H
#define JOYSTICK_H
 
#include "mbed.h"
 
/**
@brief Library for interfacing with a Joystick (http://proto-pic.co.uk/thumb-joystick-analogue/)
@brief Joystick is just 2 potentiometers and a button so can be interfaced with AnalogIn and DigitalIn/InterruptIn
@brief The library contains a method to prevent button debounce with a Timeout
 
@author Avinash Patel
@date April 2016
 * Example
 * @code
 
#include "mbed.h"
#include "Joystick.h"
 
//               Xaxis,Yaxis,Button 
Joystick joystick(PTB3, PTB2, PTB11);
DigitalOut r_led(LED_RED);
Serial pc(USBTX, USBRX);
 
int main()
{
    //First initalise joystick
    joystick.init();
 
    while (true) {
        //Stores x and y output values
        float x, y;
 
        //Calls the "GetXValue" and "GetYValue" and stores it in x and y
        x = joystick.GetXValue();
        y = joystick.GetYValue();
 
        //Prints the values to the terminal
        pc.printf("X: %f, Y: %f\n", x, y);
 
        //If the button flag is pressed switch the led
        if (joystick.get_button_flag()) {
            r_led = !r_led
        }
    }
}
 * @endcode
*/
 

class Joystick
{
public:
    /** Creates a Joystick object connected to the given pins
    * Dynamically allocates AnalogIn for input potentiometers, InterruptIn for the joystick buton and the debounce Timeout
    *
    * @param x_axis_pin connected to the Joystick's x potentiometer output
    * @param y_axis_pin connected to the Joystick's y potentiometer output
    * @param button_pin connected to the Joystick's button
    *
    */
    Joystick(PinName x_axis_pin, PinName y_axis_pin, PinName button_pin);
 
    /** Destroys the Joystick object
    * Clears the AnalogIn's, InterruptIn and Timeout from memory
    */
    ~Joystick();
 
    /** Initalises the Joystick
    * Sets up the InterruptIn Mode ISR
    * Initalises the offset vairables and ISR flags
    * Samples the AnalogIn's 5 times and takes an average to get the offset
    */
    void init();
 
    /** Gets the value of the x potentiometer
    * Takes 5 readings from the potentiometer 
    * Calculates the average measurement, accounting for joystick offset
    * Caps the average between 0 and 1
    * 
    * @returns the average value of the x potentiometer
    */
    float GetXValue();
 
    /** Gets the value of the y potentiometer
    * Takes 5 readings from the potentiometer 
    * Calculates the average measurement, accounting for joystick offset
    * Caps the average between 0 and 1
    * 
    * @returns the average value of the y potentiometer
    */
    float GetYValue();
 
    /** Reads the state of the button flag
    * @returns the button flag
    */
    int get_button_flag();
 
    /** Sets the button flag
    * @param value The value the flag will be set to
    */
    void set_button_flag(bool value);
 
private:
    void button_isr();
    void button_fall_isr();
    void button_debounce_isr();
private:
    //Pin inputs
    AnalogIn* x_axis_;
    AnalogIn* y_axis_;
    InterruptIn* button_;
 
    //Ticker to prevent joystick button bounce
    Timeout* button_debounce_;
 
    //Stores X and Y offsets
    float x_offset_;
    float y_offset_;
 
    //Stores interrupt flags
    volatile bool g_button_flag_;
    volatile bool g_button_debounce_flag_;
};
 
#endif