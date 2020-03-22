//#include "DHT.h"
#include <FirebaseArduino.h>
//#include <DHT.h>
#include <ESP8266WiFi.h>
#define FIREBASE_HOST "nodemcu-c958b.firebaseio.com"
#define FIREBASE_AUTH "ZPijEQFcmmkbhVDvLXHDg35I4zilCiaFQbIGQ7cj"
#define WIFI_SSID "GlobeAtHome_47572_2.4"
#define WIFI_PASSWORD "bnA3aGUJ"
//#define DHTPIN 5
#define flex A0
//#define DHTTYPE DHT11
//DHT dht(DHTPIN, DHTTYPE);
int vbr = 4;


void setup() {
Serial.begin(9600);
//Serial.println(F("DHTxx test!"));
//dht.begin();
pinMode(vbr, OUTPUT);
pinMode(flex, INPUT);


// connect to wifi.
WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
Serial.print("connecting");
while (WiFi.status() != WL_CONNECTED) {
Serial.print(".");
delay(500);
}
Serial.println();
Serial.print("connected: ");
Serial.println(WiFi.localIP());
Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);

//Firebase.set("LED_STATUS", 0);
//Firebase.set("Values/Angle", 0);
//Firebase.set("Values/Status", "none");
delay(10000);
}
int n = 0;
int a=0;
int data=0;
void loop() {

//float t = dht.readTemperature();
data= analogRead(flex);
//float value = map(data, 700, 900, 0, 255);
//float voltage= (data*4.98)/1023.0;
//float r= 47500.0*(4.98/voltage)-1.0;
//Serial.print(t);
//Serial.println("Resistance: " + String(r)+ " ohms");
//Firebase.setFloat("value", t);
//float angle = map(r, 37300.0, 90000.0, 0, 90.0);
Serial.println("Bend: "+String(data) +" degrees");
Firebase.setInt("Values/Angle", data);
Serial.println();
//Firebase.pushInt("angle", data);
//Firebase.getFloat("value");
delay(10000);
/*n= Firebase.getInt("LED_STATUS");
if (n==1){
  Serial.println("LED ON");
  digitalWrite(led, HIGH);
  return;
  delay(500);
}
else{
  Serial.println("LED OFF");
  digitalWrite(led, LOW);
  return;
}*/

a= Firebase.getInt("Values/Angle");

if (a<=6){
  digitalWrite(vbr, HIGH);
  delay(2000);
 
  Firebase.setString("Values/Status", "bad");
  return;
}
else{
  digitalWrite(vbr, LOW);
  delay(600);
  Firebase.setString("Values/Status", "normal");
}
}
