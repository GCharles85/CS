//Guyriano Charles
//October 17, 2018
//Project 6

#include "led_p6.h"

int led_init(LEDBank *lb, int pins[], int nPins){
  for(int i=0; i<nPins; i++){
     lb->pins[i]=pins[i];
     pinMode(pins[i], OUTPUT);
     digitalWrite(pins[i], LOW);
   }
   (*lb).nPins = nPins;
   (*lb).lastvalue=0;

  return 0;
}

int led_off(LEDBank *lb){
  for(int i=0; i<lb->nPins; i++){
    digitalWrite(lb->pins[i],LOW);
   }
  (*lb).lastvalue=0;
  return  (*lb).lastvalue;
}

int led_on(LEDBank *lb){
  for(int i=0; i<lb->nPins; i++){
    digitalWrite(lb->pins[i],HIGH);
   }
  (*lb).lastvalue=1;
  return  (*lb).lastvalue;
}

int led_setAll(LEDBank *lb, int state){
  for(int i=0; i<lb->nPins; i++){
    digitalWrite(lb->pins[i],state & _BV(i));
  }
  (*lb).lastvalue=state;
  return (*lb).lastvalue;
}

int led_status(LEDBank *lb){
  return (*lb).lastvalue;
}

int led_set(LEDBank *lb, int which, int value){
  digitalWrite(which,value);
  (*lb).lastvalue=value;
  return (*lb).lastvalue;
}
