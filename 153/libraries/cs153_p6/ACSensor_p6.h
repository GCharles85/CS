//Guyriano Charles
//October 17, 2018
//Project 6

#ifndef ACSENSOR_H
#define ACSENSOR_H

#include <Wire.h>
#include "Adafruit_Sensor.h"
#include "Adafruit_LSM303_U.h"

typedef struct{
 Adafruit_LSM303_Accel_Unified acc;
 Adafruit_LSM303_Mag_Unified mag;
 float acc_cur[3];
 float mag_cur[2];
 float G[3];

}ACSensor;

int acs_init(ACSensor *acs);
void acs_calibrate( ACSensor *acs, int N );
void acs_update( ACSensor *acs );
float acs_gravityCosAngleX( ACSensor *acs );
float acs_accx( ACSensor *acs );
float acs_accy( ACSensor *acs );
float acs_accz( ACSensor *acs );
void acs_gravity( ACSensor *acs, float grav[] );
float acs_compass( ACSensor *acs );
void acs_mag( ACSensor *acs, float mag[] );
int levelPattern(float cosine, float yOrient, float xOrient );

#endif
