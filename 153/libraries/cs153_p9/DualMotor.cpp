/* 
  Guyriano Charles
  November 7, 2018
  Project 09
*/

#include "DualMotor.h"

void DualMotor::init(void){
  // initialize the Mag and Accel objects, each with a unique identifier number
  this->mag = Adafruit_LSM303_Mag_Unified(12345);
  this->acc = Adafruit_LSM303_Accel_Unified(54321);

  // startup mag
  if( !this->mag.begin() )
    return;

  // startup acc
  if( !this->acc.begin() )
    return;

   mag_cur[2]={};
}

void DualMotor::update( void ){
  sensors_event_t event;
  this->mag.getEvent( &event );
  this->mag_cur[0]=event.magnetic.x;
  this->mag_cur[1]=event.magnetic.y;
}

float DualMotor::compass(void){
  update();
  float heading = 180*atan2(mag_y(), mag_x()) / 3.14159267;
  return heading;
}


DualMotor::DualMotor( int leftAnalogPin, int rightAnalogPin ){
  leftPin = leftAnalogPin;
  rightPin = rightAnalogPin;
  init();
  pinMode(leftAnalogPin, OUTPUT);
  pinMode(rightAnalogPin, OUTPUT);
}

void DualMotor::forward( int distance){
  float orient = compass();
  if(distance >= 6){
   Forward(3);
  }else{
   Forward(distance/10);
  }
  int count = 0;
  while(count<distance){
    align(orient);
    Forward(distance/10);
    count=count + (distance/10);
   }
}


void DualMotor::left( float angle ){
  setMotorPower(0,255);
  digitalWrite(4,HIGH);
  delay(angle*(1000/50));
  stop();
  digitalWrite(4,LOW);
}

void DualMotor::right( float angle ){
  setMotorPower(255,0);
  digitalWrite(2,HIGH);
  delay(angle*(1000/50));
  stop();
  digitalWrite(2,LOW);
  
}

void DualMotor::align(float orient){
  update();
  float actual = compass();
  int error = orient - actual;
  
  if(error<0 && abs(error)>5){
     right(error);
   }else if(error>0 && abs(error)>5){
     left(abs(error));
   }
}

void DualMotor::Forward(float distances){
  setMotorPower(255,255);
  digitalWrite(3,HIGH);
  delay(1000*(distances/6));
  stop();
  digitalWrite(3,LOW);
}

int DualMotor::setMotorPower(int pleft, int pright){
  if((pleft >= 0 && pleft <= 255) && (pright >= 0 && pright <= 255)){
    analogWrite(5, pleft );
    analogWrite(9, pright );
  }
  return 0;
}

int DualMotor::setSpeed(int speed){
  if (speed >= 0){
    setMotorPower(speed, speed);
  }else{
    analogWrite(5, 0 );
    analogWrite(5, 0 );
   }
   return 0;
}

int DualMotor::setRotation(int speed){
  if (speed <= 0 && abs(speed) <= 255){
    setMotorPower(abs(speed), 0);
  }else if(speed >= 0 && abs(speed) <= 255){
     setMotorPower(0, speed);
  }
  return 0;
}

int DualMotor::stop(void){
  setMotorPower(0,0);
  return 0;
}

int DualMotor::direction(int randoms){
  if(randoms==0 || randoms==2){
       return -1;
  }else if(randoms==1 || randoms==3){
       return 1;
   }
}
