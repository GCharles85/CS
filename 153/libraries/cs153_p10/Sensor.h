/* Guyriano Charles
 * November 14, 2018
 * Project 10
 */

 #include <Arduino.h>
 #include <string.h>

class Sensor {
  protected:
  int anaPin;
  float lastvalue;
  float filter;
  float avg;
  char name[32];

  public:
  Sensor(void);
  Sensor( int pin);
  Sensor(int pin, int fsize);
  int update(void);
  int getReading(void);
  float getAvgReading(void);
  float getValue(void);
  float getAvgValue(void);
  void print(void);
};

class IRSensor : public Sensor {
  public: 
  IRSensor(int pin): Sensor(pin) {strcpy(name, "IR Distance Sensor: ");}
  IRSensor(int pin, int fsize): Sensor(pin, fsize) {strcpy(name, "IR Distance Sensor: ");}
  float getValue(void);
  float getAvgValue(void);
  void print(void);
};


class LightSensor : public Sensor {
  public: 
  LightSensor(int pin) : Sensor(pin) {strcpy(name, "Light Sensor: ");}
  LightSensor(int pin, int fsize) : Sensor(pin, fsize) {strcpy(name, "Light Sensor: ");}
  int dark(void);
  int bright(void);
};
