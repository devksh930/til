#include "mbed.h"
#include "BMP180.h"
I2C i2c(I2C_SDA, I2C_SCL);
BMP180 bmp180(&i2c);
Serial pc(USBTX, USBRX);
int main(void) {
pc.baud(115200);
while(1) {
if (bmp180.init() != 0) {
pc.printf("Error communicating with BMP180\n");
} else {
pc.printf("Initialized BMP180\n");
break;
}
wait(1);
}
while(1) {
bmp180.startTemperature();
wait_ms(5); // Wait for conversion to complete
float temp;
if(bmp180.getTemperature(&temp) != 0) {
pc.printf("Error getting temperature\n");
continue;
}
bmp180.startPressure(BMP180::ULTRA_LOW_POWER);
wait_ms(10); // Wait for conversion to complete
int pressure;
if(bmp180.getPressure(&pressure) != 0) {
pc.printf("Error getting pressure\n");
continue;
}
pc.printf("Pressure = %d Pa, Temperature = %.1f C\n",
pressure, temp);
// Calculate altitude in meters
float altitude;
altitude = 44330.0f*( 1.0f -
pow((pressure/102710.0f), (1.0f/5.255f)));
pc.printf("Altitude is %.1f m\n\r", altitude);
wait(1);
}
}