/* Guyriano Charles
 * November 14, 2018
 * Project 10
 */

#include "Sensor.h"

int LightSensor::dark(void){
  update();
  if (lastvalue < 40){
    return 1;
 }else{
  return 0;
 }
}

int LightSensor::bright(void){
  update();
  if (lastvalue > 240){
    return 1;
 }else{
  return 0;
 }
}
