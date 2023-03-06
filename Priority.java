import java.util.*;

public class Priority implements Algorithm {
    List<Task> queue = new ArrayList<Task>();
    List<Task> arr = new ArrayList<Task>();

    public Priority(List<Task> priority, List<Task> array){
        queue = priority; 
        arr = array;  
    }
    public void schedule(){ 
        Integer i,j;
        Integer n;
        n = queue.size();
        for (i = 0; i < n - 1; i++){
            Integer max_idx = i;
            for (j = i + 1; j < n; j++){
                if(queue.get(j).getTid() > queue.get(max_idx).getTid()){
                    max_idx = j;
                }
            }
            replace_tagets(i, max_idx);
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

// class PRIO
// {

//     void priority_sort(int arr[], int priority[], int n) {
//         int i, j, temp, temp_priority;
//         for (i = 0; i < n - 1; i++) {
//             int max_idx = i;
//             for (j = i + 1; j < n; j++) {
//                 if (priority[j] > priority[max_idx]) {
//                     max_idx = j;
//                 }
//             }

//             temp = arr[i];
//             arr[i] = arr[max_idx];
//             arr[max_idx] = temp;

//             temp_priority = priority[i];
//             priority[i] = priority[max_idx];
//             priority[max_idx] = temp_priority;

//         }
//     }
// };
