public class AA_Sorting {
    public static void bubble(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - i - 1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int min = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    public static void insertionSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int j = i;
            while(j > 0 && arr[j-1] > arr[j]){
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                j--;
            }
        }
    }

    public static void conquer(int[] arr, int start, int mid, int end){
        int[] sort = new int[end-start+1];
        int index1 = start;
        int index2 = mid + 1;
        int j = 0;
        while(index1 <= mid && index2 <= end){
            if(arr[index1] < arr[index2]){
                sort[j++] = arr[index1++];
            }else{
                sort[j++] = arr[index2++];
            }
        }
        while(index1 <= mid){
            sort[j++] = arr[index1++];
        }
        while(index2 <= end){
            sort[j++] = arr[index2++];
        }
        for(int i = 0; i < sort.length; i++){
            arr[start + i] = sort[i];
        }
    }

    public static void divide(int[] arr, int start, int end){
        if(start >= end) return;
        int mid = start + ((end - start)/2);
        divide(arr,start,mid);
        divide(arr,mid+1,end);
        conquer(arr,start,mid,end);
    }

    public static void divide(int[] arr){
        int start = 0;
        int end = arr.length - 1;
        divide(arr, start, end);
    }

    public static void bubble_recursive(int []arr,int n){
        if(n==1) return;
        for(int i = 0; i < n-1; i++){
            if(arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        bubble_recursive(arr,n-1);
    }

    public static void bubble_recursive(int []arr){
        int n = arr.length;
        bubble_recursive(arr,n);
    }

    public static void insertion_recursive(int []arr){
        int n = arr.length;
        insertion_recursive(arr,0,n);
    }

    public static void insertion_recursive(int[] arr,int i, int n){
       if(i == n)return;
       int j = i;
       while(j > 0 && arr[j-1] > arr[j]){
           int temp = arr[j-1];
           arr[j-1] = arr[j];
           arr[j] = temp;
           j--;
       }
       insertion_recursive(arr,i+1,n);
    }

    public static void main(String[] args) {
        int[] arr = {12,14,1,2,4};
        insertion_recursive(arr);
        for(int i : arr){
            System.out.print(i + " ");
        }
    }
}
