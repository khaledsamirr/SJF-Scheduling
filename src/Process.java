public class Process {
    String Name;
    int BurstTime;
    int ArrivalTime;

    public Process(String s, int a, int b, int p){
        Name=s;
        ArrivalTime=a;
        BurstTime=b;
    }
    public Process(){}

    public int getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return BurstTime;
    }

    public String getName() {
        return Name;
    }


    public void setBurstTime(int burstTime) {
        BurstTime = burstTime;
    }

    public void setName(String name) {
        Name = name;
    }


}
