//
package devices;

public abstract class SmartDevice {
    protected String name;          // nazwa urzadzenia
    protected boolean isOn;         // Stan urzadzenia

    public SmartDevice(String name) {
        this.name = name;
        this.isOn = false;
    }

    public String getName() {
        return name;
    }
    public boolean isOn() {
        return isOn;
    }

    public String getStatus() {
        return name + " is " + (isOn() ? "ON" : "OFF");
    }

    abstract void showDetails();
}
