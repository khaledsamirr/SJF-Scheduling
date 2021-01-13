import java.util.ArrayList;
import java.util.List;

public class SJFScheduling {
    List<Process> Processes=new ArrayList<>();
    List<Integer> burst=new ArrayList<>();
    int currentTime=0;
    int contextswitching=0;

    public void setBurst(List<Integer> burst) {
        this.burst = burst;
    }

    public SJFScheduling(int context){
        contextswitching=context;
    }

    public void setProcesses(List<Process> processes) {
        Processes = processes;
    }

    public List<Process> getProcesses() {
        return Processes;
    }

    private List<Integer>waiting=new ArrayList<Integer>();
    private List<Integer>turnaround=new ArrayList<Integer>();

    public void run(){

        int index=0;
        currentTime=Processes.get(0).getArrivalTime();
        for(int i=1;i<Processes.size();i++) {
            if (Processes.get(i).getArrivalTime() < currentTime) {
                index = i;
                currentTime = Processes.get(i).getArrivalTime();
            }
        }
        int endtime=0;
        while(!Processes.isEmpty()){
            endtime=currentTime;
            System.out.print("Start time :"+currentTime+" "+Processes.get(index).getName()+" ");
            while(processIsHere(index,currentTime,Processes)==false){
                currentTime++;
                this.Processes.get(index).setBurstTime(Processes.get(index).getBurstTime()-1);
            }
            System.out.println("End time :" + currentTime);
            if(endtime==currentTime){
             // do noting
            }else {
                currentTime += contextswitching;
                System.out.println("Time is added by : " + contextswitching + " as context switching");
            }
            if(Processes.get(index).getBurstTime()!=0){
                for(int i=0;i<Processes.size();i++){
                    if(Processes.get(i).getArrivalTime()<=currentTime&&Processes.get(i).getBurstTime()<Processes.get(index).getBurstTime()
                    ){
                        index=i;
                    }
                }
            }

            int burstt=0;
            if(Processes.get(index).getBurstTime()==0){
                System.out.println(Processes.get(index).getName()+" Waiting time is : "+ CalcWaiting(Processes.get(index),this.burst,index));
                System.out.println(Processes.get(index).getName()+" Turn Round time is : "+ CalcTurn(Processes.get(index)));
                this.Processes.remove(Processes.get(index));
                this.burst.remove(burst.get(index));
                if(!Processes.isEmpty()){
                burstt=Processes.get(0).getBurstTime();
                for(int i=0;i<Processes.size();i++){
                    if(Processes.get(i).getBurstTime()<burstt&&Processes.get(i).getArrivalTime()<=currentTime){
                        index=i;
                        burstt=Processes.get(i).getBurstTime();
                    }else
                        index=0;

                }}
            }


        }
    }

    boolean processIsHere(int index, int curr, List<Process>m) {
        if (m.get(index).getBurstTime() == 0)
            return true;
        else {
            if (index < m.size() - 1) {
                if (curr < m.get(index + 1).getArrivalTime()) {
                    return false;
            }else if(curr==m.get(index+1).getArrivalTime()) {
                    if (m.get(index + 1).getBurstTime() < m.get(index).getBurstTime())
                        return true;
                    else
                        return false;
                }
               else if (curr>m.get(index+1).getArrivalTime()){
                    if(m.get(index+1).getBurstTime()<m.get(index).getBurstTime()){
                        return true;
                    }else{
                       return false;
                  }
                }
                else
                    return false;
            } else {
                if (m.get(index).getBurstTime() == 0)
                    return true;
                else
                    return false;
            }
        }
    }

    public int CalcTurn(Process p){
        int turn=currentTime-p.getArrivalTime();
        turnaround.add(turn);
        return(turn);
    }
    public int CalcWaiting(Process p,List<Integer>m,int index){
        int wait=currentTime-p.getArrivalTime()-m.get(index);
        waiting.add(wait);
        return (wait);
    }
    public double AvgWaiting(){
        int sum=0;
        for(int i=0;i<waiting.size();i++){
            sum+=waiting.get(i);
        }
        return((double) sum/waiting.size());
    }
    public double AvgTurntime(){
        int sum=0;
        for(int i=0;i<turnaround.size();i++){
            sum+=turnaround.get(i);
        }
        return((double) sum/turnaround.size());
    }

    public void print() {
        System.out.println("----------------------------------------");
        System.out.println("Avg Waiting time : "+AvgWaiting());
        System.out.println("Avg Turn around time : "+AvgTurntime());
    }

}
