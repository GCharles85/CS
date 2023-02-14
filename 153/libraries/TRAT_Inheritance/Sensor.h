/* This Sensor class makes it easy to for a user to 
 * calculate the online mean of many samples from an 
 * analog pin [A0, A5].
 *  
 * CS153 Fall 2018
 * Week 10: Inheritance
 * Caitrin Eaton
 */

#ifndef SENSOR_H
#define SENSOR_H
 
class Sensor {

  /* We want these members to be "protected" rather than "private"
   * so that child classes can inherit them. */
  protected:
    int pin;      // analog pin [A0, A5] to which the sensor is connected
    int n;        // width of the online filter's sliding window
    int adc;      // most recent unflitered analog reading
    float mean;   // online mean of (filtered) sensory data
    float delta;  // derivative of the online mean 
    
    
  public:
    Sensor( int p, int window );  // construct a new Sensor object with a given pin and window
    Sensor( int p );              // construct a new Sensor object a given pin and the default window
    void reset( );                // take a new reading and use it to reset the online mean
    int getPin();                 // return the value of pin
    int getWindow();              // return the value of the sliding window
    int getRaw();                 // return the latest unfiltered analog reading
    float getMean();              // return the online mean
    float getDerivative();        // return the derivative of the online mean
    void setPin( int p );         // set the value of pin
    void setWindow( int window ); // set the width of the sliding window
    float sample( );              // collect a new analog reading and return the new filtered mean
    void print( );                // print out a helpful description of the sensor
};

#endif 
