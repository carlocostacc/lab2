import java.util.*;

public class FairShare {
    List<Task> queue = new ArrayList<Task>();
    Integer quantum;

    public FairShare(List<Task> queue_list, Integer quant){
        queue = queue_list;   
        quantum = quant;
    }
    public void schedule(){ 
        Integer i,j;
        Integer n;
        Integer num_user = 0;
        Integer user_quantum;
        List<Integer> user_arr = new ArrayList<Integer>();
        n = queue.size();

        // find the number of users
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
        for (j = 0; j < user_arr.size() ; j++){
            for (i = 0; i < n - 1; i++){
                if(user_arr.get(j) == queue.get(i).getTid()){
                    if(queue.get(i).getBurst() - user_quantum < 0 && queue.get(i).getBurst() != 0){
                        queue.get(i).setBurst(0);
                        j -= 1;
                        break;
                    }
                    if(queue.get(i).getBurst() - user_quantum > 0){
                        queue.get(i).setBurst(queue.get(i).getBurst() - user_quantum);
                        break;
                    }
                }
            }
        }
    }


    public void replace_tagets( Integer n, Integer m){
        Integer size =  queue.size();
        Integer index = n;
        Integer index2 = m;
        Task temp_i = new Task(null, 0, 0);
        Task temp_j = new Task(null, 0, 0);
        List<Task> temp_list = new ArrayList<Task>();
        
        
        for (int i = 0; i < size; i++) { // queue list to temp list 
            if (i == index) {
                temp_i = queue.remove(0);
                temp_list.add(temp_i);
            } 
            if (i == index2){
                temp_j = queue.remove(0);
                temp_list.add(temp_j);
            }
            else {
                temp_list.add(queue.remove(0));
            }
        }

        for (int i = 0; i < size; i++) {  // temp list to queue list
            if (i == index) {
                queue.add(temp_j);
            } 
            if (i == index2){
                queue.add(temp_i);
            }
            else {
                queue.add(temp_list.remove(0));
            }
        }
       

    }
    
    public Task pickNextTask(){
        Task task = new Task(null, 0, 0);
        return task;
    }
}
