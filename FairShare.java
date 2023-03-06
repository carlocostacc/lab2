import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FairShare implements Algorithm {
    List<Task> queue = new ArrayList<Task>();
    Integer quantum;
    List <Integer> wt;
    List <Integer> bt;

    public FairShare(List<Task> queue_list, Integer quant){
        queue = queue_list;   
        quantum = quant;
        wt = new ArrayList<Integer>(queue.size());
        bt = new ArrayList<Integer>(queue.size());
    }
    public void schedule(){ 
        Integer i,j;
        Integer n;
        Integer num_user = 0;
        Integer user_quantum;
        Integer time = 0 ;
        List<Integer> user_arr = new ArrayList<Integer>();
        n = queue.size();
        for(int y = 0; y < n; y++){
            wt.add(0);
        }
        for(int y = 0; y < n; y++){
            bt.add(queue.get(y).getBurst());
        }
        // find the number of users
        Write_To_File("fair share algorithm");
        for (i = 0; i < n - 1; i++){
            if(user_arr.size() > 0){
                for (j = 0; j < user_arr.size() ; j++){
                    if(user_arr.get(j) == queue.get(i).getTid()){
                        break;
                    }
                    if(j == user_arr.size() - 1 && user_arr.get(j) != queue.get(i).getTid()){
                        num_user += 1;
                        user_arr.add(queue.get(i).getTid());
                    }
                }
            }
            else {
                user_arr.add(queue.get(i).getTid());
                num_user += 1;
            }
        }
        user_quantum = quantum/num_user;
        // alocate burst time for each users
        for (j = 0; j < user_arr.size() ; j++){
            for (i = 0; i < n - 1; i++){
                if(user_arr.get(j) == queue.get(i).getTid()){
                    if(queue.get(i).getBurst() - user_quantum < 0 && queue.get(i).getBurst() != 0){
                        queue.get(i).setBurst(0);
                        j -= 1;
                        time += user_quantum;
                        wt.set(i, time - queue.get(i).getBurst());
                        Write_To_File("will run task: " + queue.get(i).getName());
                        Write_To_File("tid: " + queue.get(i).getTid());
                        Write_To_File("priority: " + queue.get(i).getPriority());
                        Write_To_File("burst: " + queue.get(i).getBurst());
                        break;
                    }
                    if(queue.get(i).getBurst() - user_quantum > 0){
                        queue.get(i).setBurst(queue.get(i).getBurst() - user_quantum);
                        time += time + queue.get(i).getBurst();
                        wt.set(i, time - queue.get(i).getBurst());
                        Write_To_File("will run task: " + queue.get(i).getName());
                        Write_To_File("tid: " + queue.get(i).getTid());
                        Write_To_File("priority: " + queue.get(i).getPriority());
                        Write_To_File("burst: " + queue.get(i).getBurst());
                        break;
                    }
                }
            }
        }
    }

    
    public Task pickNextTask(){
        Task task = new Task(null, 0, 0);
        return task;
    }
    
    public void Average_Times(){
        Integer i;
        Integer n = queue.size();
        double total_wait_time = 0;
        double total_burst_time = 0;

        for (i = 0; i < n; i++){
            total_wait_time = total_wait_time + wt.get(i);
            total_burst_time += bt.get(i);

        }
        
        Write_To_File("the average turnaround time is: " + (total_burst_time + total_wait_time)/n);
        Write_To_File("the average wait time time is: " + (total_wait_time)/n);


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
