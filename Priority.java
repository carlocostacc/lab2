import java.util.*;
import java.util.Collections;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Priority implements Algorithm {
    List<Task> queue = new ArrayList<Task>();
    

    public Priority(List<Task> priority){
        queue = priority;  
    }
    public void schedule(){ 
        Integer i,j;
        Integer n;
        n = queue.size();
        Write_To_File("Priority algrithm");
        for (i = 0; i < n - 1; i++){
            Integer max_idx = i;
            for (j = i + 1; j < n; j++){
                if(queue.get(j).getPriority() > queue.get(max_idx).getPriority()){
                    max_idx = j;
                }
            }
            Collections.swap(queue, i, max_idx);
            Write_To_File("will run task: " + queue.get(i).getName());
            Write_To_File("tid: " + queue.get(i).getTid());
            Write_To_File("priority: " + queue.get(i).getPriority());
            Write_To_File("burst: " + queue.get(i).getBurst());
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

