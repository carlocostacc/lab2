import java.util.*;

import javax.xml.transform.Templates;

import java.io.*;

public class FCFS {
    List<Task> queue = new ArrayList<Task>();
    public void FCFS(List<Task> queue_list){
        List<Task> queue = queue_list;
        Integer i,j;
        Task temp;
        Integer n;
        n = queue.size();
        for (i = 0; i < n - 1; i++){
            for (j = i + 1; j < n; j++){
                if(queue.get(i).getTid() > queue.get(j).getTid()){
                    temp = queue.get(i);
                    
                }
            }
        }
    }


    public List<Task> replace_Task(List<Task> queue_list, Integer n, Integer m){
        Integer size =  queue_list.size();
        Integer index = n;
        Integer index2 = m;
        Task temp_i = new Task(null, 0, 0);
        Task temp_j = new Task(null, 0, 0);
        List<Task> temp_list = new ArrayList<Task>();
        
        
        for (int i = 0; i < size; i++) { // queue list to temp list 
            if (i == index) {
                temp_i = queue_list.remove(0);
                temp_list.add(temp_i);
            } 
            if (i == index2){
                temp_j = queue_list.remove(0);
                temp_list.add(temp_j);
            }
            else {
                temp_list.add(queue_list.remove(0));
            }
        }

        for (int i = 0; i < size; i++) {  // temp list to queue list
            if (i == index) {
                queue_list.add(temp_j);
            } 
            if (i == index2){
                queue_list.add(temp_i);
            }
            else {
                queue_list.add(temp_list.remove(0));
            }
        }
       
        return queue_list;
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


