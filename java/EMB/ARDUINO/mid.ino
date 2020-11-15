#include <SHT1x.h>

#define sensor A0 // Sharp IR GP2Y0A41SK0F (4-30cm, analog)
#define DUST_LED_PIN  A5
#define DUST_PIN  A6
#define dataPin  2
#define clockPin 3
SHT1x sht1x(dataPin, clockPin);


float Vo_Val=0;
float Voltage = 0;
float dustDensity = 0;

int samplingTime = 280;   // 0.28ms
int delayTime = 40;       // 0.32 - 0.28 = 0.04ms
int offTime = 9680;       // 10 - 0.32 = 9.68ms

int distance = 0;

//#define TESTING

// Specify data and clock connections and instantiate SHT1x object

#ifdef  TESTING
#define COMM_DEV    Serial
#else
#define COMM_DEV    Serial1
#endif

char* reqCmds[6] = {
  "TMP", "HMD", "DUST", "RANG" ,"ACK","DIMM"
};

char cmdBuf[32];

#define BUF_LENGTH  10

float tmpValues[BUF_LENGTH], tmpSum = 0, tmpAvg;
float hmdValues[BUF_LENGTH], hmdSum = 0, hmdAvg;
float dustValues[BUF_LENGTH], dustSum = 0, dustAvg;
float rangValues[BUF_LENGTH], rangSum = 0, rangAvg;

void setup()
{
  pinMode(7,OUTPUT);
  pinMode(9,OUTPUT);
   COMM_DEV.begin(115200);
#ifdef  TESTING   
   COMM_DEV.println("Starting temp/dist monitoring...");
   COMM_DEV.println("Initialize tmp/dist value buffer...");
#endif
   initSavedValues();
#ifdef  TESTING   
   COMM_DEV.print("Avg. temperature = "); COMM_DEV.println(tmpAvg);
   COMM_DEV.print("Avg. humidity = "); COMM_DEV.println(hmdAvg);
   COMM_DEV.print("Avg. dust = "); COMM_DEV.println(dustAvg);
   COMM_DEV.print("Avg. rang = "); COMM_DEV.println(rangAvg);
   
#endif   
}
void dustSensor(){
  
  digitalWrite(DUST_LED_PIN,HIGH);
  delayMicroseconds(samplingTime);
  
  Vo_Val=analogRead(DUST_PIN);
  
  delayMicroseconds(delayTime);
  digitalWrite(DUST_LED_PIN,LOW);
  delayMicroseconds(offTime); 
  
  Voltage = Vo_Val * 5.0 / 1024.0;
  dustDensity = abs((0.172*(Vo_Val*(5.0/1024.0))-0.0999)*1000);

}

void rangSensor(){
     float volts = analogRead(sensor)*0.0048828125;  // value from sensor * (5/1024)
    distance = 13*pow(volts, -1);  // worked out from datasheet graph
    if(distance >= 40){
      distance = 40;
    }
}
void initSavedValues() {
  float val = 0;
  for (int i=0; i<BUF_LENGTH; i++) {
    val = sht1x.readTemperatureC();
    tmpValues[i] = val;
    tmpSum += val;

     val = sht1x.readHumidity();
    hmdValues[i] = val;
    hmdSum += val;
    
    dustSensor();
    val = dustDensity;
    dustValues[i] = val;
    dustSum += val;

    rangSensor();
    val = distance;
    rangValues[i] = val;
    rangSum += val;
  }
   tmpAvg = tmpSum / BUF_LENGTH;
  hmdAvg = hmdSum / BUF_LENGTH;
  dustAvg = dustSum / BUF_LENGTH;
  rangAvg = rangSum / BUF_LENGTH;
}
void updateTemperature() {
  static int idx = 0;

  float val = sht1x.readTemperatureC();
  tmpSum -= tmpValues[idx];
  tmpValues[idx] = val;
  tmpSum += val;
  tmpAvg = tmpSum / BUF_LENGTH;
  idx = (idx + 1) % BUF_LENGTH;
}
void updateHumidity() {
  static int idx = 0;

  float val = sht1x.readHumidity();
  hmdSum -= hmdValues[idx];
  hmdValues[idx] = val;
  hmdSum += val;
  hmdAvg = hmdSum / BUF_LENGTH;
  idx = (idx + 1) % BUF_LENGTH;
}

void updateDust() {
  static int idx = 0;
   dustSensor();
  float val = dustDensity;
  dustSum -= dustValues[idx];
  dustValues[idx] = val;
  dustSum += val;
  dustAvg = dustSum / BUF_LENGTH;
  idx = (idx + 1) % BUF_LENGTH;
}

void updateRang() {
  static int idx = 0;
  rangSensor();
  float val = distance;
  rangSum -= rangValues[idx];
  rangValues[idx] = val;
  rangSum += val;
  rangAvg = rangSum / BUF_LENGTH;
  idx = (idx + 1) % BUF_LENGTH;
}

boolean getReqCmd(char* cmd) {
  boolean result = false;
  int ch, count = 0;
  
  if (COMM_DEV.available() >= 4) {
    do {
      ch = COMM_DEV.read();
    } while (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n');
    while (ch != -1 && ch != '\n') {
      *cmd++ = ch;
      count++;
      ch = COMM_DEV.read();
    }
    if (count >= 3)  result = true;
  } 
  *cmd = '\0';
    
  return result;
}

void processReqCmd(char *cmd) {
  int idx = 0;

  char *cp = cmd;
  if (strlen(cmd) > 4) {
    cp = strtok(cmd, "+");
  }

  for (idx=0; idx<5; idx++) {
    if (strcmp(reqCmds[idx], cmd) == 0)
      break;
  }

  switch (idx) {
  case 0:
    COMM_DEV.print("T="); COMM_DEV.print(tmpAvg); COMM_DEV.write('\n');
    break;
 
  case 1:
    COMM_DEV.print("H="); COMM_DEV.print(hmdAvg); COMM_DEV.write('\n');
    break;
 
  case 2:
    COMM_DEV.print("D="); COMM_DEV.print(dustAvg); COMM_DEV.write('\n');
    break;
 
  case 3:
    COMM_DEV.print("R="); COMM_DEV.print(rangAvg); COMM_DEV.write('\n');
    break;
  
  case 4:
    COMM_DEV.print("OK"); COMM_DEV.write('\n');   
    break;
  
  case 5:
    cp = strtok(NULL, "+");
    int value = atoi(cp);
    if(value < 100){
    value=value%100;
    int l1br = (int)(value * 2.55);
    analogWrite(7,l1br);
    
    }else if(value< 200){
      
    value= value -100;
    value = value %100;
    COMM_DEV.print(value);
    int l2br = int(value * 2.55);
     analogWrite(9,l2br);
    }else{
      COMM_DEV.print("NOT A NUMBER 0~99 led7, 100~199 led8");
    }
    break;
  }
}

void loop()
{
  if (getReqCmd(cmdBuf)) {
    processReqCmd(cmdBuf);
  }
  updateTemperature();
    updateHumidity();
  updateDust();
  updateRang();
  delay(100);
}
