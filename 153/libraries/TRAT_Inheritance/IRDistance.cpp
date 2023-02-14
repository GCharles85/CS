/* This IRDistance class inherits from the Sensor class to
 * make it easy for the user to take filtered distance readings.
 *  
 * CS153 Fall 2018
 * Week 10: Inheritance
 * Caitrin Eaton
 */

#include "IRDistance.h"
#include <Arduino.h>

// return the latest unfiltered analog reading
float IRDistance::getRaw(){
  return raw;
}

/* Use the IRDistance sensor's characterized inverse mapping
   to convert an adc reading into a distance in cm. */
float IRDistance::adc2cm( int adc ){

  if( adc > 0 ){
    return 18243*pow( adc, -1.09 );
  }
  
  // if the adc was 0, raising it to a negative power will cause 
  // a divide by zero error, so approximate it as a 1
  return 18243*pow( 1, -1.09 ); //  
}

/* Collect a new analog sample and update the online mean, 
 * as well as the derivative of the online mean. */
float IRDistance::sample( ){
  adc = analogRead( pin );
  raw = adc2cm( adc );
  float meanLast = mean;
  mean = (mean*float(n-1) + float(raw))/float(n);
  delta = mean-meanLast;
}

/* Print out a helpful description of the IRDistance object. */
void IRDistance::print( ){
  Serial.println("IRDistance OBJECT");
  Serial.println("\tpin:\t" + String(pin));
  Serial.println("\twindow:\t" + String(n));
  Serial.println("\tmean:\t" + String(mean) + " cm");
  Serial.println("\tdelta:\t" + String(delta) + " cm/sample");
}
