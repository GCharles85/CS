/* The IRDistance class inherits its data members and functions 
 * from the Sensor class, and adds some IR Distance-specific 
 * functionalities, like the characterized sensor's inverse mapping
 * from ADC codes back to distances (in cm), and custom printing.
 *  
 * CS153 Fall 2018
 * Week 10: Inheritance
 * Caitrin Eaton
 */

#include "Sensor.h"

class IRDistance: public Sensor {
  
  private:
    float raw;                // the unfiltered distance in cm
    float adc2cm( int adc );  // convert an adc code 

  public:
    IRDistance( int p, int window ): Sensor( p, window ){}; // construct a new IRDistance object with the given pin and sliding window width
    IRDistance( int p ): Sensor( p ){};                     // construct a new IRDistance object with the given pin and the default sliding window width
    float getRaw();           // return the latest unfiltered analog reading
    float sample( );          // collect a new analog reading and return the new filtered mean
    void print( );            // print out a helpful description of the sensor
};
