void dot(int unit,int pin){
  pinMode(pin,OUTPUT);
  digitalWrite(pin,HIGH);
  delay(unit);
  digitalWrite(pin,LOW);
  delay(unit);
}

void dash(int unit, int unit2,int pin){
  pinMode(pin,OUTPUT);
  digitalWrite(pin,HIGH);
  delay(unit2);
  digitalWrite(pin,LOW);
  delay(unit);
}

void sendH(int unit, int unit2, int pin){
  dot(unit, pin);
  dot(unit, pin);
  dot(unit, pin);
  dot(unit, pin);
  digitalWrite(pin,LOW);
  delay(unit2);
  Serial.print("H");
}

void sendE(int unit, int pin){
  dot(unit, pin);
  digitalWrite(pin,LOW);
  delay(3000);
  Serial.print("E");
}

void sendL(int unit,int unit2, int pin){
  dot(unit, pin);
  dash(unit, unit2, pin);
  dot(unit, pin);
  dot(unit, pin);
  digitalWrite(pin,LOW);
  delay(unit2);
  Serial.print("L");
}

void sendO(int unit, int unit2, int pin){
  dash(unit, unit2,pin);
  dash(unit, unit2,pin);
  dash(unit, unit2,pin);
  digitalWrite(pin,LOW);
  delay(unit2);
  Serial.print("O");
}

void sendW(int unit,int unit2, int pin){
  dot(unit, pin);
  dash(unit, unit2,pin);
  dash(unit, unit2,pin);
  digitalWrite(pin,LOW);
  delay(unit2);
  Serial.print("W");
}

void sendR(int unit,int unit2, int pin){
  dot(unit, pin);
  dash(unit, unit2,pin);
  dot(unit, pin);
  digitalWrite(pin,LOW);
  delay(unit2);
  Serial.print("R");
}

void sendD(int unit, int unit2, int pin){
  dot(unit, pin);
  dot(unit, pin);
  dot(unit, pin);
  digitalWrite(pin,LOW);
  delay(unit2);
  Serial.print("D");
}

void sendA(int unit, int unit2, int pin){
   dot(unit, pin);
   dash(unit, unit2, pin);
   digitalWrite(pin,LOW);
   delay(unit2);
   Serial.print("A");
}

void sendG(int unit, int unit2, int pin){
  dash(unit, unit2, pin);
  dash(unit, unit2, pin);
  dot(unit, pin);
  digitalWrite(pin,LOW);
  delay(unit2);
  Serial.print("G");
}

void sendT(int unit, int unit2, int pin){
  dash(unit, unit2, pin);
  digitalWrite(pin,LOW);
  delay(unit2);
  Serial.print("T");
}

void sendHELLO(int unit, int unit2, int pin){
  sendH(unit, unit2, pin);
  sendE(unit,pin);
  sendL(unit, unit2, pin);
  sendL(unit, unit2, pin);
  sendO(unit,unit2, pin);
  digitalWrite(13,LOW);
  delay(7000);
  Serial.print(" ");
}

void sendWORLD(int unit,int unit2, int pin){
  sendW(unit, unit2, pin);
  sendO(unit, unit2, pin);
  sendR(unit,unit2, pin);
  sendL(unit, unit2, pin);
  sendD(unit, unit2, pin);
  digitalWrite(13,LOW);
  delay(7000);
  Serial.print(" ");
}

void sendWE(int unit, int unit2, int pin){
   sendW(unit, unit2, pin);
   sendE(unit,pin);
   digitalWrite(13,LOW);
   delay(7000);
   Serial.print(" ");
}

void sendARE(int unit, int unit2, int pin){
  sendA(unit,unit2, pin);
  sendR(unit, unit2, pin);
  sendE(unit,pin);
  digitalWrite(13,LOW);
  delay(7000);
  Serial.print(" ");
}

void sendGROOT(int unit, int unit2, int pin){
  sendG(unit, unit2, pin);
  sendR(unit, unit2, pin);
  sendO(unit, unit2, pin);
  sendO(unit, unit2, pin);
  sendT(unit, unit2, pin);
  digitalWrite(13,LOW);
  delay(7000);
  Serial.print(" ");
}
