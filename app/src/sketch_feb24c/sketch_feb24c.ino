#include<Servo.h>;
Servo myservo;
char charln;
String message = "";
void setup() {
  // put your setup code here, to run once:
  myservo.attach(9);
  Serial.begin(9600);
  while(!Serial)
  {
    ;
  }
}

void loop() {
  // put your main code here, to run repeatedly:
  while(Serial.available()>0)
  {
    charln = Serial.read();
    message += charln;
  }
  myservo.write(0);
  if(message.length()>0)
  {
    message.trim();
    if(message.equalsIgnoreCase("1"))
    {
      Serial.println("Servo 1");
      for(int pos = 0; pos <= 90; pos +=1)
      {
        myservo.write(pos);
        delay(10);
      }
      myservo.write(0);
      delay(20);
    }
    else if (message.equalsIgnoreCase("2"))
    {
      Serial.println("Servo 2");
      for(int pos = 0; pos<=90; pos +=1)
      {
        myservo.write(pos);
        delay(10);
      }
      myservo.write(0);
      delay(20);
    } 
    else if(message.equalsIgnoreCase("3"))
    {
      Serial.println("Servo 3");
      for(int pos = 90; pos>=50; pos -=1)
      {
        myservo.write(pos);
        delay(10);
      }
      myservo.write(140);
      delay(20);
    }
        else if(message.equalsIgnoreCase("4"))
    {
      Serial.println("Servo 4");
      for(int pos = 90; pos>=50; pos -=1)
      {
        myservo.write(pos);
        delay(10);
      }
      myservo.write(120);
      delay(20);
    }
    message ="";
  }
}
