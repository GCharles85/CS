//Guyriano Charles
//November 5, 2018
//Example using parent rec. class to define child class

#include <Arduino.h>
#ifndef RECTANGLE_H
#define RECTANGLE_H

class Rectangle{
  protected: //A child class can't use a private thing from parent but it can use a protected thing,  still can't access protected from main()
   float length;
   float width;

  public:
   Rectangle(float l, float w); //Constructs rec. with a given length and width
   Rectangle(); //Constructs rec. with a default length and width
   float getLength(); //returns value of lenght data member
   float getWidth(); //returns value of width data member
   void setLength(float l); //updates length data member
   void setWidth(float w); //updates width data member
   void print();
   float calcArea();
};

#endif
