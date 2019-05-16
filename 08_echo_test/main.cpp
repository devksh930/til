#include "mbed.h"

Serial pc(USBTX,USBRX);

int main() {
		pc.printf("Strat Echo Test\n");
    while(1) {
			char c=pc.getc();
			pc.putc(c);
			
    }
}
