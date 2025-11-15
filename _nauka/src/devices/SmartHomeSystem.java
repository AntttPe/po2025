package devices;

import java.util.ArrayList;
import java.util.List;

public class SmartHomeSystem {

    private List<SmartDevice> devices;

    public SmartHomeSystem() {
        this.devices = new ArrayList<>();
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
        System.out.println("Added device: " + device.getName());
    }

    public void showAllDevices() {
        System.out.println("=== ALL DEVICES ===");
        for (int i = 0; i < devices.size(); i++) {
            System.out.println((i + 1) + ". " + devices.get(i).getName());
        }
        System.out.println("====================");
    }

    public SmartDevice findDeviceByName(String name) {
        for (SmartDevice device : devices) {
            if (device.getName().equalsIgnoreCase(name)) {
                return device;
            }
        }
        return null;
    }

    public void showDeviceDetails(String name) {
        SmartDevice device = findDeviceByName(name);
        if (device != null) {
            device.showDetails();
        } else {
            System.out.println("Device not found.");
        }
    }

    public void switchDevice(String name, boolean on) {
        SmartDevice device = findDeviceByName(name);

        if (device == null) {
            System.out.println("Device not found!");
            return;
        }

        if (device instanceof Switchable) {
            Switchable sw = (Switchable) device;
            if (on) sw.turnOn();
            else sw.turnOff();
        } else {
            System.out.println("This device cannot be switched on/off.");
        }
    }
}