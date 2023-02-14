

#include "FSR.h"




float FSR::adc2gm( int adc ){

  if( adc > 0 ){
    return .914*exp(.00725*adc); 
  }
  
  // if the adc was 0, raising it to a negative power will cause 
  // a divide by zero error, so approximate it as a 1
   return 0; //  
}

float FSR::FSRsample( ){
  adc = analogRead( pin );
  raw2 = adc2gm( adc );
  float meanLast = mean;
  mean = (mean*float(n-1) + float(raw2))/float(n);
  delta = mean-meanLast;
}

void FSR::print(void){
  Serial.println("Force OBJECT");
  Serial.println("\tpin:\t" + String(pin));
  Serial.println("\twindow:\t" + String(n));
  Serial.println("\tmean:\t" + String(mean) + " gm");
  Serial.println("\tdelta:\t" + String(delta) + " gm/sample");
}
