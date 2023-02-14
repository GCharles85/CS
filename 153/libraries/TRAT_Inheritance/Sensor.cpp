/* This Sensor class makes it easy to for a user to 
 * calculate the online mean of many samples from an 
 * analog pin [A0, A5].
 *  
 * CS153 Fall 2018
 * Week 10: Inheritance
 * Caitrin Eaton
 */

#include "Sensor.h"      // Contains class's prototypes
#include <Arduino.h>     // Contains analogRead()

/* Construct a new Sensor object that will collect analog
   readings from pin "p" and filter them within a sliding
   window that is "window" samples wide. */
Sensor::Sensor( int p, int window ) {
  setPin( p );
  setWindow( window );
  reset();
}

/* Construct a new Sensor object that will collect analog
   readings from pin "p" and filter them within the
   default sliding window: 15 samples wide. */
Sensor::Sensor( int p ) {
  setPin( p );
  setWindow( 15 );
  reset();
}

/* Take a new reading and use it to reset the online mean. */
void Sensor::reset( ) {
  adc = analogRead( pin );
  mean = float(adc);
  delta = 0.0;
}

/* Return the pin this Sensor can control. */
int Sensor::getPin( ) {
  return pin;
}

/* Return the width of the filter's sliding window. */
int Sensor::getWindow( ) {
  return n;
}

/* Return the most recent unfiltered analog reading. */
int Sensor::getRaw( ) {
  return adc;
}

/* Return the online mean: the most recent filtered value. */
float Sensor::getMean( ) {
  return mean;
}

/* Return the most recent change in the online mean. */
float Sensor::getDerivative( ) {
  return delta;
}


/* Choose the pin that this Sensor can read from. If the
   user passed in a pin that's not in the range [A0, A5], 
   then use A0 instead. */
void Sensor::setPin( int p ) {
  if( (A0 <= p) && (p<=A5)){
    pin = p;
  } else {
    pin = A0;
  }
}

/* Change the width of the filter's sliding window,
   making sure it's an integer greater than zero. */
void Sensor::setWindow( int window ) {
  if( window > 0 ){
    n = window;
  } else {
    n = 15;
  }
}

/* Collect a new analog sample and update the online mean, 
 * as well as the derivative of the online mean. */
float Sensor::sample( ){
  adc = analogRead( pin );
  float meanLast = mean;
  mean = (mean*float(n-1) + float(adc))/float(n);
  delta = mean-meanLast;
}

/* Print out a helpful description of the Sensor object. */
void Sensor::print( ){
  Serial.println("SENSOR OBJECT");
  Serial.println("\tpin:\t" + String(pin));
  Serial.println("\twindow:\t" + String(n));
  Serial.println("\tmean:\t" + String(mean));
  Serial.println("\tdelta:\t" + String(delta));
}
