//Guyriano Charles
//October 17, 2018
//Project 6

#include "ACSensor_p6.h"

int acs_init(ACSensor *acs){
// initialize the Mag and Accel objects, each with a unique identifier number
  acs->mag = Adafruit_LSM303_Mag_Unified(12345);
  acs->acc = Adafruit_LSM303_Accel_Unified(54321);

  // startup mag
  if( !acs->mag.begin() )
    return(-1);

  // startup acc
  if( !acs->acc.begin() )
    return(-2);  

  acs->G[2]={};
  acs->mag_cur[2]={};
  acs->acc_cur[3]={};
  
}

void acs_calibrate( ACSensor *acs, int N ){
  float x=0;
  float y=0;
  float z=0;
  sensors_event_t event;
  
  for(int i=0;i<N;i++){
    acs->acc.getEvent( &event );
    float xNew=event.acceleration.x;
    x+=xNew;
    float yNew=event.acceleration.y;
    y+=yNew;
    float zNew=event.acceleration.z;
    z+=zNew;
    float newt = xNew+yNew+zNew;
  }
  acs->G[0]=x/N;
  acs->G[1]=y/N;
  acs->G[2]=z/N;
}

void acs_update( ACSensor *acs ){
  sensors_event_t event1;
  sensors_event_t event2;
  acs->acc.getEvent( &event1 );
  acs->mag.getEvent( &event2 );
  acs->acc_cur[0]= event1.acceleration.x;
  acs->acc_cur[1]=event1.acceleration.y;
  acs->acc_cur[2]=event1.acceleration.z;
  acs->mag_cur[0]= event2.magnetic.x;
  acs->mag_cur[1]=event2.magnetic.y;
}

float acs_gravityCosAngleX( ACSensor *acs ){
  float gY=acs->G[1];
  float gZ=acs->G[2];
  float yNew=acs->acc_cur[1];
  float zNew=acs->acc_cur[2];

  float numer = (gY*yNew)+(gZ*zNew);
  float denom = sqrt((pow(gY,2)+pow(gZ,2))*(pow(yNew,2)+pow(zNew,2)));
  
  float Cos = numer/denom;
  return Cos;
}

float acs_accx( ACSensor *acs ){
 return acs->acc_cur[0]; 
}

float acs_accy( ACSensor *acs ){
 return acs->acc_cur[1]; 
}

float acs_accz( ACSensor *acs ){
  return acs->acc_cur[2];
}

void acs_gravity( ACSensor *acs, float grav[] ){
  grav[0]=acs->G[0];
  grav[1]=acs->G[1];
  grav[2]=acs->G[2];
}

float acs_compass( ACSensor *acs ){
  float heading = 180 * atan2(acs->mag_cur[1], acs->mag_cur[0]) / 3.14159267;
  return heading;
}

void acs_mag( ACSensor *acs, float mag[] ){
  mag[0]=acs->mag_cur[0];
  mag[1]=acs->mag_cur[1];
}
