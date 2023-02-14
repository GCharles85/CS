/* Guyriano Charles
 * November 14, 2018
 * Project 10
 */

#include "Sensor.h"

float IRSensor::getValue(void){
 update();
 float dist = (-.0359*lastvalue + 27.1) - 4;
 return dist;
}

float IRSensor::getAvgValue(void){
  update();
  float dist = (-.0359*avg + 27.1) - 4;
  return dist;
}

void IRSensor::print(void){
  Serial.println("IR Recent Value: " + String(lastvalue));
  Serial.println("Actual distance: " + String(getValue()));
}
