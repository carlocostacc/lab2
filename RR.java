import java.util.*;
import java.util.Collections;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RR implements Algorithm {
    List<Task> queue = new ArrayList<Task>();
    Integer quantum;

    public RR(List<Task> queue_list, Integer quant){
        queue = queue_list;   
        quantum = quant;
    }
    public void schedule(){ 
        Integer i,j;
        Integer n;
        n = queue.size();
        Write_To_File("Round robin algorithm");
        for (i = 0; i < n - 1; i++){
            for (j = i + 1; j < n; j++){
                if(queue.get(i).getTid() > queue.get(j).getTid()){
                    Collections.swap(queue,i, j);
                    
                }
            }
            
        }
        Integer time = 0;
        while (true){
            Integer flag = 0;
            for(i = 0; i<n; i++){
                if (queue.get(i).getBurst() != -1){
                    flag = 1;
                    if(queue.get(i).getBurst() <= quantum){
                        time += queue.get(i).getBurst();
                        System.out.println(i);
                        queue.get(i).setBurst(-1);
                        Write_To_File("will run task: " + queue.get(i).getName());
                        Write_To_File("tid: " + queue.get(i).getTid());
                        Write_To_File("priority: " + queue.get(i).getPriority());
                        Write_To_File("burst: " + queue.get(i).getBurst());
                    }
                    else{
                        time += quantum;
                        queue.get(i).setBurst(queue.get(i).getBurst()-quantum);
                        Write_To_File("will run task: " + queue.get(i).getName());
                        Write_To_File("tid: " + queue.get(i).getTid());
                        Write_To_File("priority: " + queue.get(i).getPriority());
                        Write_To_File("burst: " + queue.get(i).getBurst());
                    }
                }
            }
            if (flag == 0){
                break;
            }
        }
    }
    public void Average_Times(){
        Integer i;
        Integer n = queue.size();
        Integer final_time = 0;
        Integer turnaruondtime = 0;
        Integer remaining_quantum = 0;
        

        for (i = 0; i < n ; i++){
            if(i < n - 2){
                if(queue.get(i).getPriority() != queue.get(i + 1).getPriority()){
                    if(queue.get(i).getBurst() - quantum < 0 && queue.get(i).getBurst() != 0){
                        if(remaining_quantum == 0){
                            queue.get(i).setBurst(0);
                            remaining_quantum =quantum- queue.get(i).getBurst();
                            i -= 1;
                            break;
                    }
                    }
                    if(queue.get(i).getBurst() - quantum > 0){
                        queue.get(i).setBurst(queue.get(i).getBurst() - quantum);
                        break;
                    }
                    if(remaining_quantum > 0)
                }
            }

        }
        System.out.println("the average turnaround time is: " + turnaruondtime/n);
        System.out.println("the average wait time time is: " + (turnaruondtime - final_time)/n);


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

