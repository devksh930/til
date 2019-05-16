#include "mbed.h"
#include "Joystick.h"
 
//               Xaxis,Yaxis,Button 
Joystick joystick(PC2, PC3, PC1);
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
            r_led = !r_led;
        }
    }
}