public class SensorReading {
    private double temperatureC;

    public SensorReading(double temperatureC) {
        // sanity check: absolute zero is -273.15°C
        // this is part of the "specification"  
        if (temperatureC < -273.15) {
            System.err.println("Ungültige Temperatur: " + temperatureC);
            this.temperatureC = -273.15; // Fallback
        } else {
            this.temperatureC = temperatureC;
        }
    }

    public double getTemperatureF() {
        return temperatureC * 9 / 5 + 32;
    }

    public void setTemperatureC(double temperatureC) {
        if (temperatureC < -273.15) {
            System.err.println("Ungültige Temperatur: " + temperatureC);
            this.temperatureC = -273.15; // Fallback
        } else {
            this.temperatureC = temperatureC;
        }
    }
}