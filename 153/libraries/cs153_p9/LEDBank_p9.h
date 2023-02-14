//Guyriano Charles
//October 31, 2018
//CS153 Project 08

/*
 * A useful class for handling a bank of LEDs
 */

#ifndef LEDBANK_P8_H
#define LEDBANK_P8_H

#include <Arduino.h>

#define LED_MAX_ACTIONS 32
#define LED_MAX_PINS    8

class LEDBank {
 private:
   int pins [LED_MAX_PINS];
   int num_pins=5;
   int lastvalue;
   int rec_active;
   int rec_num;
   int state[LED_MAX_ACTIONS]={};
   int times[LED_MAX_ACTIONS]={};
   void rec_add( int state, int time );
   // some function/method declarations for internal use only
  
 public:
  void set_pins( int pinNumbers[], int N);
  void init(void);
  LEDBank(void);
  LEDBank(int pinNumbers[], int N);
  void off( void );
  void off( int time );
  void on( void );
  int on( int time );
  int setAll( int state );
  int setAll( int state, int time );
  int set( int which, int value );
  int set( int which, int value, int time );
  int cur_status(void);
  int num_active_pins( void );
  int shift_left( void );
  int shift_left( int time );
  int shift_right( void );
  int shift_right( int time );
  int invert( void );
  int invert( int time );
  void record(void){
    this->rec_active=1;
   };
  void stop(void){
    this->rec_active=0;
   };
  void erase(void){
    this->rec_num=0;
   };
  void play( void );
  void play( float speed );
  void get(int index, int *state, int *time);
  void edit(int index, int state, int time);
  // all function/method declarations for external use

};

#endif
