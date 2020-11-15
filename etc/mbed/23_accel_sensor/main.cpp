#include "mbed.h"


Serial pc(USBTX,USBRX);

SPI accel(PB_15,PB_14,PB_13); //mosi miso sclr

DigitalOut cs1(PB_12); //csel line for accel sensor

char buffer[6]; //raw data
int16_t data[3]; //16bit values
float x, y, z; // for rea g values

int main() {
	//init
	pc.baud(115200);
	// SPI conn init
	
	cs1 =1;
	accel.format(8,3); //8bit mod3
	accel.frequency(1000000);//clocker sync
	
	//accel.sensor init
	cs1= 0;
	accel.write(0x31); //send dataformat
	accel.write(0x01); //
	cs1=1;
	
	//accel.sensor init
	cs1= 0;
	accel.write(0x2D); //send pwcontrol
	accel.write(0x08); //send a value for stting
	cs1=1;
	
	cs1=0;
	accel.write(0x80 | 0x00);
	int id=accel.write(0x00);
	cs1 =1;
	pc.printf("dev id %x2x\n",id);
	wait(5);
	while(1) {
		wait(0.5);
		
		cs1=0;
		accel.write(0x80 | 0x40 | 0x32);
		for (int i=0; i<6; i++){
		accel.write(0x00); //shift register write dummy byte
			}
		cs1 =1;
		data[0] = (buffer[1] << 8) + buffer[0];
		data[1] = (buffer[3] << 8) + buffer[2];
		data[2] = (buffer[5] << 8) + buffer[4];
			
		x=0.0078*data[0];
			pc.printf("X accel = %.1f , Y accel= %.1f , Z accel =%.1f\n",x,y,z);
	}
}
