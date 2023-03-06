import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FCFS implements Algorithm{
    List<Task> queue = new ArrayList<Task>();

    public FCFS(List<Task> queue_list){
        queue = queue_list;   
    }
    public void schedule(){ 
        Integer i;
        Integer n;
        n = queue.size();
        Write_To_File("FCFS algorithm");
        for (i = 0; i < n ; i++){
            
                Write_To_File("will run task: " + queue.get(i).getName());
                Write_To_File("tid: " + queue.get(i).getTid());
                Write_To_File("priority: " + queue.get(i).getPriority());
                Write_To_File("burst: " + queue.get(i).getBurst());
            
            }
            Average_Times();
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

    public void Average_Times(){
        Integer i;
        Integer n = queue.size();
        double final_time = 0;
        double turnaruondtime = 0;

        for (i = 0; i < n; i++){
            final_time += queue.get(i).getBurst();
            turnaruondtime += final_time;
        }
        Write_To_File("the average turnaround time is: " + turnaruondtime/n);
        Write_To_File("the average wait time time is: " + (turnaruondtime - final_time)/n);


    }
    public Task pickNextTask(){
        Task task = new Task(null, 0, 0);
        return task;
    }
    }


