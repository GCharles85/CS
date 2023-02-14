/* Guyriano Charles
 * November 14, 2018
 * Project 10
 */

#include "Sensor.h"

Sensor::Sensor(void){
  anaPin = A0;
  lastvalue = 0;
  filter = 10;
  avg = 0;
}

Sensor::Sensor(int pin){
  anaPin = pin;
  lastvalue = 0;
  filter = 10;
  avg = 0;
}

Sensor::Sensor(int pin, int fsize){
  anaPin = pin;
  lastvalue = 0;
  filter = fsize;
  avg = 0;
}

int Sensor::update(void){
 lastvalue = analogRead(anaPin);
 float w = 1.0/filter;
 avg = (1-w)*avg + w*lastvalue;
}

int Sensor::getReading(void){
  return lastvalue;
}

float Sensor::getAvgReading(void){
 return avg;
}

float Sensor::getValue(void){
   return lastvalue;
}

float Sensor::getAvgValue(void){
  return avg;
}

void Sensor::print(void){
  Serial.println("LRD Recent Value: " + String(lastvalue));
  Serial.println("LRD Average Value: " + String(avg));
}
