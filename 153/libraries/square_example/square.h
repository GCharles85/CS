//Guyriano Charles
//November 5, 2018
//Child class is defined. The class square uses the code from rectangle

#include <Arduino.h>
#include "rectangle.h"

class Square : public Rectangle{
  
  public: 
   Square( float sideLength): Rectangle (sideLength , sideLength){};
   Square(): Rectangle(){};
   void print();
};
