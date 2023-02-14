/* 
  Guyriano Charles
  November 7, 2018
  Project 09
*/

#include <Arduino.h>
#include <Wire.h>
#include "Adafruit_Sensor.h"
#include "Adafruit_LSM303_U.h"

class DualMotor{
  
  private:
  Adafruit_LSM303_Accel_Unified acc;
  Adafruit_LSM303_Mag_Unified mag;
  int leftPin;
  int rightPin;
  float mag_cur[2];

  public:
  DualMotor( int leftAnalogPin, int rightAnalogPin );
  void forward(int distance);
  void left( float angle );
  void right( float angle );
  void align( float orient );
  void update(void);
  void init(void);
  float compass(void);
  float mag_y(void){
    return mag_cur[1];
  }
  float mag_x(void){
    return mag_cur[0];
  }
  void Forward(float distances);
  int setMotorPower(int pleft, int pright);
  int setSpeed(int speed);
  int setRotation(int speed);
  int stop(void);
  int direction(int randoms);
};
