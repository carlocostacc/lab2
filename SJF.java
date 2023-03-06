import java.util.*;

public class SJF implements Algorithm {
    List<Task> queue = new ArrayList<Task>();

    public SJF(List<Task> queue_list){
        queue = queue_list;   
    }
    public void schedule(){ 
        Integer i,j;
        Integer n;
        n = queue.size();
        for (i = 0; i < n - 1; i++){
            Integer min_idx = i;
            for (j = i + 1; j < n; j++){
                if(queue.get(min_idx).getTid() < queue.get(j).getTid()){
                    min_idx = j;
                }
            }
            replace_tagets(i, min_idx);
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


// class SJF
// {
//     void sjf_sort(int arr[], int n) {
//         int i, j, temp;
//         for (i = 0; i < n - 1; i++) {
//             int min_idx = i;
//             for (j = i + 1; j < n; j++) {
//                 if (arr[j] < arr[min_idx]) {
//                     min_idx = j;
//                 }
//             }
//             temp = arr[i];
//             arr[i] = arr[min_idx];
//             arr[min_idx] = temp;
//         }
//     }
// };