//Guyriano Charles
//October 24, 2018
//CS153 Project 07

/*
 * A useful class for handling a bank of LEDs
 */

#ifndef LEDBANK_P7_H
#define LEDBANK_P7_H

#include <Arduino.h>

#define LED_MAX_PINS 8


class LEDBank {
 private:
   int pins [LED_MAX_PINS];
   int num_pins=5;
   int lastvalue;
   // some function/method declarations for internal use only
  
 public:
  set_pins( int pinNumbers[], int N);
  init(void);
  LEDBank(void);
  LEDBank(int pinNumbers[], int N);
  off( void );
  off( int time );
  on( void );
  on( int time );
  setAll( int state );
  setAll( int state, int time );
  set( int which, int value );
  set( int which, int value, int time );
  cur_status(void);
  num_active_pins( void );
  shift_left( void );
  shift_left( int time );
  shift_right( void );
  shift_right( int time );
  invert( void );
  invert( int time );
  
   // all function/method declarations for external use

};

#endif
