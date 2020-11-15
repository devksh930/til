#include "DHT22.h"

#define timeout 200

DHT22::DHT22(PinName pin) {
    _data_pin = pin;
    DigitalInOut dht(_data_pin);
    dht.output();
    dht.write(1);
}

int DHT22::getTemperature() {
    return _temperature;
}

int DHT22::getHumidity() {
    return _humidity;
}

bool DHT22::sample() {
    __disable_irq();    // Disable Interrupts
    Timer t;
    DigitalInOut dht(_data_pin);
    int dht22_dat [5];
    dht.output();
    dht.write(0);
    wait_ms(1);
    dht.write(1);
    dht.input();
    wait_us(40);
    wait_us(80);
    int i,j,result=0;
    for (i=0; i<5; i++) {
        result=0; 
        for (j=0; j<8; j++) {
            while(dht.read());
            while(!dht.read());
            t.reset();
            t.start();
            while(dht.read());
            t.stop();
            int p;
            if(t.read_us()>50)
                p = 1;
            else
                p = 0;
            p=p <<(7-j);
            result=result|p;
        }
        dht22_dat[i] = result;
    }
    dht.output();
    dht.write(1);
    int dht22_check_sum;
    dht22_check_sum=dht22_dat[0]+dht22_dat[1]+dht22_dat[2]+dht22_dat[3];
    dht22_check_sum= dht22_check_sum%256;
    if (dht22_check_sum==dht22_dat[4]) {
        _humidity=dht22_dat[0]*256+dht22_dat[1];
        _temperature=dht22_dat[2]*256+dht22_dat[3];
        __enable_irq();
        return true;
    }
    __enable_irq();
    return false;
}
