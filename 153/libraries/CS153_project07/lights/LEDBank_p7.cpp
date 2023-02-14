//Guyriano Charles
//October 24, 2018
//CS153 Project 07

#include <Arduino.h>
#include <stdlib.h>
#include <string.h>
#include "LEDBank_p7.h"

LEDBank::set_pins( int pinNumbers[], int N){
 for(int i=0; i<N; i++){
      this->pins[i]=pinNumbers[i];
  }
  this->num_pins=N;
}

LEDBank::init(void){
  for(int i=0; i<this->num_pins; i++){
     pinMode(this->pins[i],OUTPUT);
     digitalWrite(this->pins[i],LOW);
  }
  this->lastvalue=0;
}

LEDBank:: LEDBank(void){
  this->num_pins=0;
  this->lastvalue=0;
}

LEDBank::LEDBank(int pinNumbers[], int N){
  set_pins(pinNumbers,N);
  init();
}

LEDBank::off( void ){
  for(int i=0; i<this->num_pins; i++){
     digitalWrite(this->pins[i],LOW);
  }
  this->lastvalue=0;
  return this->lastvalue;
}

LEDBank:: off( int time ){
  off();
  delay(time);
  return this->lastvalue;
}

LEDBank::on( void ){
   this->lastvalue=0;
   for(int i=0; i<this->num_pins; i++){
     this->lastvalue |= 1 << i;
     digitalWrite(this->pins[i],HIGH);
  }
  return this->lastvalue;
}

int LEDBank::on( int time ){
  on();
  delay(time);
  return this->lastvalue;
 
}

int LEDBank::setAll( int state ){
  this->lastvalue=0;
  for(int i=0; i<this->num_pins; i++){
    digitalWrite(this->pins[i],state & _BV(i));
    if(state & _BV(i)){
    this->lastvalue |= 1 << i;
    }
  }
  return this->lastvalue;
}

int LEDBank::setAll( int state, int time ){
  setAll(state);
  delay(time);
  return this->lastvalue;
}

int LEDBank::set( int which, int value ){
  digitalWrite(which, value);
  this->lastvalue=value;
  return this->lastvalue;
}

int LEDBank::set( int which, int value, int time ){
  set(which,value );
  delay(time);
  return this->lastvalue;
}

int LEDBank::cur_status( void ){
  return this->lastvalue;
}

int LEDBank::num_active_pins( void ){
  return this->num_pins;
}

int LEDBank::shift_left( void ){
  setAll(lastvalue<<1);
}

int LEDBank::shift_left( int time ){
  shift_left();
  delay(time);
  return this->lastvalue;
}

int LEDBank::shift_right( void ){
  setAll( lastvalue>>1);
}

int LEDBank::shift_right( int time ){
  shift_right();
  delay(time);
  return this->lastvalue;
}

int LEDBank::invert(void){
  setAll(~lastvalue);
  return this->lastvalue;
}

int LEDBank::invert( int time ){
  invert();
  delay(time);
  return this->lastvalue;
}
