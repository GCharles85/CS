//Guyriano Charles
//October 31, 2018
//Project 08

#ifndef ACSENSOR_P7_H
#define ACSENSOR_P7_H

#include <Arduino.h>
#include <Wire.h>
#include "Adafruit_Sensor.h"
#include "Adafruit_LSM303_U.h"

#define  ACS_MEMORY_CAP 20

class ACSensor{
  private:
   Adafruit_LSM303_Accel_Unified acc;
   Adafruit_LSM303_Mag_Unified mag;
   float acc_cur[3]={};
   float mag_cur[2]={};
   float G[3]={};
   float prior[ACS_MEMORY_CAP][3];
   int next;
   int avgacc[3];
   int dacc[3];
   int avgmag[2]={};
   int dmag[2]={};
   int filterSize;

   public:
    enum Activity {None, Shake, Tap, LeftTilt, RightTilt};
    activity(void);
    void clearPrior(void);
    void init( void );
    void update( void );
    void calibrate( int N );
    ACSensor(void);
    ACSensor(int N);
    acc_x(void){
      return this->acc_cur[0];
    }
    acc_y(void){
      return this->acc_cur[1];
    }
    acc_z(void){
     return this->acc_cur[2]; 
    }
    mag_x(void){
      return this->mag_cur[0];
    }
    mag_y(void){
     return this->mag_cur[1];
    }
    void acc1( float arr[]);
    void mag1( float arr[] );
    void setFilterSize( int N ){
       this->filterSize = N;
     };
    int getFilterSize(void){
      return filterSize;
    }
    float avgacc_x(void){
      return avgacc[0];
    }
    float avgacc_y(void){
      return avgacc[1];
    }
    float avgacc_z(void){
      return avgacc[2];
    }
    float avgmag_x(void){
      return avgmag[0];
    }
    float avgmag_y(void){
      return avgmag[1];
    }
    float dacc_x(void){
      return dacc[0];
    }
    float dacc_y(void){
      return dacc[1];
    }
    float dacc_z(void){
      return dacc[2];
    }
    float dmag_x(void){
      return dmag[0];
    }
    float dmag_y(void){
      return dmag[1];
    }
    float compass(void);
};

#endif
