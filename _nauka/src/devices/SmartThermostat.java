package devices;

public class SmartThermostat extends SmartDevice implements Switchable {

    private double temperature; // bieżąca temperatura

    public SmartThermostat(String name) {
        super(name);
        this.temperature = 20.0; // domyślna temperatura
    }

    @Override
    public void turnOn() {
        isOn = true;
    }

    @Override
    public void turnOff() {
        isOn = false;
    }

    public void increaseTemp() {
        if (temperature < 30.0) {
            temperature += 0.5;
        }
    }

    public void decreaseTemp() {
        if (temperature > 15.0) {
            temperature -= 0.5;
        }
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public void showDetails() {
        System.out.println(
                "SmartThermostat '" + name + "' | State: " + (isOn ? "ON" : "OFF") +
                        " | Temperature: " + temperature + "°C"
        );
    }

//    // Mini-test
//    public static void main(String[] args) {
//        SmartThermostat thermostat = new SmartThermostat("Termostat kuchnia");
//
//        thermostat.showDetails();
//        thermostat.turnOn();
//        thermostat.increaseTemp();
//        thermostat.increaseTemp();
//        thermostat.showDetails();
//        thermostat.decreaseTemp();
//        thermostat.turnOff();
//        thermostat.showDetails();
//    }
}