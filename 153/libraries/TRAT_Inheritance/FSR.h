/*TRAT Inheritance
 * 11/9/2018
 */

#ifndef FSR_H
#define FSR_H

#include <Arduino.h>
#include "Sensor.h"

class FSR : public Sensor{

  private:
  float raw2;
  float mean2;

  public:
  FSR( int p, int window):Sensor( p,  window ){};  // construct a new Sensor object with a given pin and window
  FSR( int p):Sensor( p ){};              // construct a new Sensor object a given pin and the default window
  int FSRgetWindow();              // return the value of the sliding window
  int FSRgetRaw();                 // return the latest unfiltered analog reading
  void FSRsetPin(int p);         // set the value of pin
  void FSRsetWindow(int window); // set the width of the sliding window
  float FSRsample();              // collect a new analog reading and return the new filtered mean
  void print(void);
  float adc2gm(int acd);
  
};

#endif 
