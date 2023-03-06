import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RR implements Algorithm {
    List<Task> queue = new ArrayList<Task>();
    Integer quantum;
    List <Integer> wt;
    List <Integer> bt;

    public RR(List<Task> queue_list, Integer quant){
        queue = queue_list;   
        quantum = quant;
        wt = new ArrayList<Integer>(queue.size());
        bt = new ArrayList<Integer>(queue.size());
    }
    public void schedule(){ 
        Integer i,j;
        Integer n;
        n = queue.size();
        Integer time = 0 ;
        for(int y = 0; y < n; y++){
            wt.add(0);
        }
        for(int y = 0; y < n; y++){
            bt.add(queue.get(y).getBurst());
        }
        Write_To_File("Round robin algorithm");
        while(true)
        {
            boolean done = true;
      
            // Traverse all processes one by one repeatedly
            for (i = 0 ; i < n; i++)
            {
                // If burst time of a process is greater than 0
                // then only need to process further
                if (queue.get(i).getBurst() > 0)
                {
                    done = false; // There is a pending process
      
                    if (queue.get(i).getBurst() > quantum)
                    {
                        // Increase the value of time i.e. shows
                        // how much time a process has been processed
                        time += quantum;
      
                        // Decrease the burst_time of current process
                        // by quantum
                        queue.get(i).setBurst(queue.get(i).getBurst() - quantum);
                        Write_To_File("will run task: " + queue.get(i).getName());
                        Write_To_File("tid: " + queue.get(i).getTid());
                        Write_To_File("priority: " + queue.get(i).getPriority());
                        Write_To_File("burst: " + queue.get(i).getBurst());
                    }
      
                    // If burst time is smaller than or equal to
                    // quantum. Last cycle for this process
                    else
                    {
                        // Increase the value of time i.e. shows
                        // how much time a process has been processed
                        time = time + queue.get(i).getBurst();
      
                        // Waiting time is current time minus time
                        // used by this process
                        wt.set(i, time - queue.get(i).getBurst());
      
                        // As the process gets fully executed
                        // make its remaining burst time = 0
                        queue.get(i).setBurst(0);
                        Write_To_File("will run task: " + queue.get(i).getName());
                        Write_To_File("tid: " + queue.get(i).getTid());
                        Write_To_File("priority: " + queue.get(i).getPriority());
                        Write_To_File("burst: " + queue.get(i).getBurst());
                    }
                }
            }
      
            // If all processes are done
            if (done == true)
              break;
        }
        System.out.println(wt);
        Average_Times();
    }
    public void Average_Times(){
        Integer i;
        Integer n = queue.size();
        double total_wait_time = 0;
        double total_burst_time = 0;
        

        for (i = 0; i < n ; i++){
            total_wait_time = total_wait_time + wt.get(i);
            total_burst_time += bt.get(i);

        }
        Write_To_File("the average turnaround time is: " + (total_burst_time + total_wait_time)/n);
        Write_To_File("the average wait time time is: " + (total_wait_time)/n);


    }
    
    public Task pickNextTask(){
        Task task = new Task(null, 0, 0);
        return task;
    }

    public void Write_To_File(String string){

        try {
            FileWriter filewriter = new FileWriter("run.txt", true);
            PrintWriter pw = new PrintWriter(filewriter);
            System.out.println(" ");
            pw.println(string);
            pw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

