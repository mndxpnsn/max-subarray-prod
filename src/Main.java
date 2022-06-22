import java.util.Random;

public class Main {

    static int max(int a, int b) {

        return Math.max(a, b);
    }

    static int max_crossing(int[] A, int low, int mid, int high) {

        int max_pl = Integer.MIN_VALUE;
        int max_nl = Integer.MAX_VALUE;
        int prod = 1;
        for(int i = mid; i >= low; --i) {
            prod = prod * A[i];
            if(prod > max_pl) {
                max_pl = prod;
            }
            if(prod < max_nl) {
                max_nl = prod;
            }
        }

        int max_pr = Integer.MIN_VALUE;
        int max_nr = Integer.MAX_VALUE;
        prod = 1;
        for(int i = mid + 1; i <= high; ++i) {
            prod = prod * A[i];
            if(prod > max_pr) {
                max_pr = prod;
            }
            if(prod < max_nr) {
                max_nr = prod;
            }
        }

        int max_prod = max(max_pr, max_pl);
        max_prod = max(max_prod, max_pr * max_pl);
        max_prod = max(max_prod, max_nr * max_nl);

        return max_prod;
    }

    static int max_sub_array(int[] A, int low, int high) {
        int res;
        if(low == high) {
            return A[low];
        }
        int mid = (low + high) / 2;
        int max_l = max_sub_array(A, low, mid);
        int max_r = max_sub_array(A, mid + 1, high);
        int max_cross =  max_crossing(A, low, mid, high);
        res = max(max_l, max_r);
        res = max(res, max_cross);

        return res;
    }

    static void init_array(int[] arr) {

        int n = arr.length;
        
        for(int i = 0; i < n; ++i) {
            Random r = new Random();
            int min = -10;
            int max = 20;

            int rand_val = min + r.nextInt(max);
            arr[i] = rand_val;
        }
    }

    static int max_product(int[] nums) {

        int n = nums.length;

        return max_sub_array(nums, 0, n - 1);
    }

    public static void main(String[] args) {

        // Size of array
        int n = 11;

        // Declare and initialize array A with random data
        int[] nums = new int[n];

        init_array(nums);

        // Compute maximum subarray product
        int max_prod = max_product(nums);

        // Print results
        System.out.println("maximum subarray product: " + max_prod);
    }
}
