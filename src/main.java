import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String args[]){
        int NumOfProcesses=0;
        Process newProcess;
        int context=0;
        Scanner s=new Scanner(System.in);
        Scanner m=new Scanner(System.in);
        System.out.println("============== SJF SCHEDULING ==============");
        System.out.println("Enter number of processes..");
        NumOfProcesses=s.nextInt();
        List<Process> prs=new ArrayList<>();
        for(int i=0;i<NumOfProcesses;i++){
            newProcess=new Process();
            System.out.println("Enter name of process "+(i+1)+" : ..");
            newProcess.setName(m.nextLine());
            System.out.println("Enter arrival time of process "+(i+1)+" : ..");
            newProcess.setArrivalTime(s.nextInt());
            System.out.println("Enter burst time of process "+(i+1)+" : ..");
            newProcess.setBurstTime(s.nextInt());
            prs.add(newProcess);
        }
        System.out.println("Enter Context switch : ..");
        context=s.nextInt();
        SJFScheduling p=new SJFScheduling(context);

        List<Integer>tempbusrt=new ArrayList<>();
        for(int i=0;i<prs.size();i++){
            tempbusrt.add(prs.get(i).getBurstTime());
        }
        p.setProcesses(prs);
        p.setBurst(tempbusrt);
        p.run();
        p.print();


    }
}
