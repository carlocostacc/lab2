import java.util.*;


public class FCFS implements Algorithm{
    List<Task> queue = new ArrayList<Task>();

    public FCFS(List<Task> queue_list){
        queue = queue_list;   
    }
    public void schedule(){ 
        Integer i,j;
        Integer n;
        n = queue.size();
        for (i = 0; i < n - 1; i++){
            for (j = i + 1; j < n; j++){
                if(queue.get(i).getTid() > queue.get(j).getTid()){
                    replace_tagets(i,j);
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
    //void fcfs_sort(int arr[], int n) {
    //     int i, j, temp;
    //     for (i = 0; i < n - 1; i++) {
    //         for (j = i + 1; j < n; j++) {
    //             if (arr[i] > arr[j]) {
    //                 temp = arr[i];
    //                 arr[i] = arr[j];
    //                 arr[j] = temp;
    //             }
    //         }
    //     }
    // }

    }


