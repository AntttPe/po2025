package devices;

public class SmartLight extends SmartDevice implements Switchable, Adjustable {

    private int brightness; // 0–100

    public SmartLight(String name) {
        super(name);     // wywołanie konstruktora SmartDevice
        this.brightness = 50;  // domyślna jasność
    }

    @Override
    public void turnOn() {
        isOn = true;
    }

    @Override
    public void turnOff() {
        isOn = false;
    }

    @Override
    public void increase() {
        if (brightness < 100) {
            brightness += 10;
        }
    }

    @Override
    public void decrease() {
        if (brightness > 0) {
            brightness -= 10;
        }
    }

    @Override
    public void showDetails() {
        System.out.println(
                "SmartLight '" + name + "' | State: " + (isOn ? "ON" : "OFF") +
                        " | Brightness: " + brightness
        );
    }

    // getter do testów
    public int getBrightness() {
        return brightness;
    }

//    // Mini test
//    public static void main(String[] args) {
//        SmartLight light = new SmartLight("Lampa salon");
//
//        light.showDetails();
//        light.turnOn();
//        light.increase();
//        light.increase();
//        light.showDetails();
//        light.decrease();
//        light.turnOff();
//        light.showDetails();
//    }
}