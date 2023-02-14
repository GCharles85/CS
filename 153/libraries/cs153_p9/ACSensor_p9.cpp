//Guyriano Charles
//October 31, 2018
//Project 08

#include "ACSensor_p9.h"


void ACSensor::clearPrior(void){
  for(int row=0; row<ACS_MEMORY_CAP; row++){
    for(int col=0; col<3; col++){
      this -> prior[row][col]=0.0;
     }
   }
 this -> next = 0;
}

void ACSensor::init( void ){
  // initialize the Mag and Accel objects, each with a unique identifier number
  this->mag = Adafruit_LSM303_Mag_Unified(12345);
  this->acc = Adafruit_LSM303_Accel_Unified(54321);

  // startup mag
  if( !this->mag.begin() )
    return;

  // startup acc
  if( !this->acc.begin() )
    return;

  this->clearPrior();
  this->acc_cur[3]={};
  this->mag_cur[2]={};
  this->G[3]={};
  this->next=0;
  avgacc[3]={};
  dacc[3]={};
  avgmag[3]={};
  dmag[3]={};
  filterSize=10;
}

void ACSensor::update( void ){
  sensors_event_t event;
  this->acc.getEvent( &event );
  this->acc_cur[0]=event.acceleration.x;
  this->acc_cur[1]=event.acceleration.y;
  this->acc_cur[2]=event.acceleration.z;
  this->mag.getEvent( &event );
  this->mag_cur[0]=event.magnetic.x;
  this->mag_cur[1]=event.magnetic.y;
  this->next = (this->next + 1) % ACS_MEMORY_CAP;
  float weight = 1.0/filterSize;
  for(int i=0; i<3; i++){
    float preavg = avgacc[i];
    avgacc[i] = ((1-weight)*preavg) + (weight*acc_cur[i]);
    dacc[i] = avgacc[i] - preavg  ;
  }
  float power = 1.0/filterSize;
  for(int i=0; i<2; i++){
    float preavg2 = avgmag[i];
    avgmag[i] = ((1-power)*preavg2) + (power*mag_cur[i]);
    dmag[i] = avgmag[i] - preavg2;
  }
}

void ACSensor::calibrate( int N ){
 float x=0;
 float y=0;
 float z=0;
 
 for(int i=0;i<N;i++){
    update();
    float xNew=this->acc_cur[0];
    x+=xNew;
    float yNew=this->acc_cur[1];
    y+=yNew;
    float zNew=this->acc_cur[2];
    z+=zNew;
  }
  this->G[0]=x/N;
  this->G[1]=y/N;
  this->G[2]=z/N; 
}

ACSensor::ACSensor(void){
  init();
}

ACSensor::ACSensor(int N){
 init();
 calibrate(N);
}

void ACSensor::acc1( float arr[]){
  arr[0]=this->acc_cur[0];
  arr[1]=this->acc_cur[1];
  arr[2]=this->acc_cur[2];
}

void ACSensor::mag1( float arr[]){
   arr[3]=this->mag_cur[0];
   arr[4]=this->mag_cur[1];
}

ACSensor::activity(void){
  update();
  float x=0;
  float yAvg=0;
  float y=0;
  float z=0;
  float lastz = 0;
  for(int i=0; i<this->next;i++){
   float xNew=this->acc_cur[0];
   x+=pow(xNew,2);
   float yNew=this->acc_cur[1];
   y+=pow(yNew,2);
   yAvg+=yNew;
   float zNew=this->acc_cur[2];
   z=pow(zNew,2);
   if(z<lastz){
     z=lastz;
    }
   lastz=z;
   }
  float x2avg=x/(this->next);
  float y2avg=y/(this->next);
  yAvg= yAvg/(this->next);
  
  if(z>380){
    clearPrior();
    return ACSensor::Tap;
  }else if(x2avg>8 && y2avg>8){
    clearPrior();
    return ACSensor::Shake;
  }else if(yAvg<-3){
    clearPrior();
    return ACSensor::LeftTilt;
   }else if(yAvg>3){
    clearPrior();
    return ACSensor::RightTilt;
  }else{
    return ACSensor::None;
  }
}

float ACSensor::compass(void){
  float heading = 180 * atan2(mag_y(), mag_x()) / 3.14159267;
  return heading;
}
