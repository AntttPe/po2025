package devices;

public class SmartCamera extends SmartDevice implements Switchable{

    protected Boolean recording;
    protected int motionsDetecions;

    public SmartCamera(String name) {
        super(name);
        this.recording = false;
        this.motionsDetecions = 0;
    }

    @Override
    public void turnOn() {
        isOn = true;
    }
    @Override
    public void turnOff() {
        isOn = false;
        recording = false; // OFF -----> przestaje nagrywac
    }

    public void startRecording(){
        if (isOn){
            recording = true;
        } else {
            System.out.println(name + " ERROR: " +"Recording failed. Camera is OFF");
        }
    }
    public void stopRecording(){
        recording = false;
    }

    // Symulacja wykrysia ruchu
    public void detectMotions(){
        if (isOn) {
            motionsDetecions++;
            System.out.println(name + ": Motion detected!");
        } else {
            System.out.println(name + ": Device OFF, cannot detect motion.");
        }
    }

    public boolean isRecording() {
        return recording;
    }
    public int getMotionsDetecions() {
        return motionsDetecions;
    }

    @Override
    public void showDetails() {
        System.out.println(
                "SmartCamera '" + name + "' | State: " + (isOn ? "ON" : "OFF") +
                        " | Recording: " + (recording ? "YES" : "NO") +
                        " | Motion detections: " + motionsDetecions
        );
    }

//    // mini test:
//    public static void main(String[] args) {
//        SmartCamera cam = new SmartCamera("SmartCamera wejscie 1");
//        cam.showDetails();
//        cam.turnOn();
//        cam.startRecording();
//        cam.detectMotions();
//        cam.detectMotions();
//        cam.showDetails();
//        cam.turnOff();
//        cam.showDetails();
//
//
//    }

}
