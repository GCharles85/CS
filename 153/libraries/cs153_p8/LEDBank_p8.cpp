//Guyriano Charles
//October 31, 2018
//CS153 Project 08

#include <Arduino.h>
#include <stdlib.h>
#include <string.h>
#include "LEDBank_p8.h"

void LEDBank::set_pins( int pinNumbers[], int N){
 for(int i=0; i<N; i++){
      this->pins[i]=pinNumbers[i];
  }
  this->num_pins=N;
}

void LEDBank::init(void){
  for(int i=0; i<this->num_pins; i++){
     pinMode(this->pins[i],OUTPUT);
     digitalWrite(this->pins[i],LOW);
  }
  this->lastvalue=0;
}

LEDBank:: LEDBank(void){
  this->num_pins=0;
  this->lastvalue=0;
  this->rec_num=0;
  this->rec_active=0;
}

LEDBank::LEDBank(int pinNumbers[], int N){
  this->rec_num=0;
  this->rec_active=0;
  set_pins(pinNumbers,N);
  init();
}

void LEDBank::off( void ){
  for(int i=0; i<this->num_pins; i++){
     digitalWrite(this->pins[i],LOW);
  }
  this->lastvalue=0;
  return this->lastvalue;
}

void LEDBank:: off( int time ){
  off();
  delay(time);
  this->rec_add(this->lastvalue, time);
}

void LEDBank::on( void ){
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
  this->rec_add(this->lastvalue, time);
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
  this->rec_add(this->lastvalue, time);
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
  this->rec_add(this->lastvalue, time);
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
  this->rec_add(this->lastvalue, time);
  return this->lastvalue;
}

int LEDBank::shift_right( void ){
  setAll( lastvalue>>1);
}

int LEDBank::shift_right( int time ){
  shift_right();
  delay(time);
  this->rec_add(this->lastvalue, time);
  return this->lastvalue;
}

int LEDBank::invert(void){
  setAll(~lastvalue);
  return this->lastvalue;
}

int LEDBank::invert( int time ){
  invert();
  delay(time);
  this->rec_add(this->lastvalue, time);
  return this->lastvalue;
}

void LEDBank::rec_add( int state, int time ){
  if(rec_active && rec_num < LED_MAX_ACTIONS){
    this->state[rec_num]=state;
    times[rec_num]=time;
   }
  rec_num++;
}

void LEDBank::play( void ){
  for(int i=0; i < rec_num; i++){
      setAll(state[i]);
      delay(times[i]);
   }
}

void LEDBank::play( float speed ){
 if(speed > 0){
  for(int i=0; i < rec_num; i++){
      setAll(state[i]);
      delay(times[i]/speed);
   }
 }else{
  return;
 }
}

void LEDBank::get(int index, int *state, int *time){
  state = state[index];
  time = times[index];
}

void LEDBank::edit(int index, int state, int time){
 if(index >= 0 && index < rec_num){
  this->state[index]= state;
  times[index]= time;  
 }else{
  return;
 }
}
