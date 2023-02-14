//Guyriano Charles
//November 5, 2018
//Example using parent rec. class to define child class

#include "rectangle.h"

Rectangle::Rectangle(float l, float w){  //Constructs rec. with a given length and width
  length=1.0;
  width=1.0;
  setLength(l);
  setWidth(w);
}

Rectangle::Rectangle(){   //Constructs rec. with a default length and width
   length=1.0;
   width=1.0;
}

float Rectangle::getLength(){  //returns value of lenght data member
  return length;
} 

float Rectangle::getWidth(){  //returns value of width data member
  return width;
} 

void Rectangle::setLength(float l){  //updates length data member
  if(l>0){
    length=l;
  }
} 

void Rectangle::setWidth(float w){  //updates width data member
  if(w>0){
    width=w;
  }
} 

void Rectangle::print(){
  Serial.println("RECTANGLE OBJECT");
}

float Rectangle::calcArea(){
  return length*width;
}
