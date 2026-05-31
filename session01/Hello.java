public class Hello { 
    public static void main(String[] args) { 
        System.out.println("Hallo, Java!"); 
        int zahl = 42; 
        System.out.println("Die Zahl ist: " + zahl); 
        double temp = 19.3; 
        System.out.println("Temperatur: " + temp); 
        System.err.println("Temperatur: " + temp);
        SensorReading reading = new SensorReading(-300);
        // System.out.println("Sensor-Temperatur: " + reading.temperatureC);   
        reading.setTemperatureC(1000);
        // System.out.println("Sensor-Temperatur end: " + reading.temperatureC);
        System.out.println("Sensor-Temperatur getter: " + reading.getTemperatureF());
    } 
} 