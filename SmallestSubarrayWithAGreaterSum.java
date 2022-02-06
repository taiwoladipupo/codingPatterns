package InterviewQuestions.SlidingWindow;

public class SmallestSubarrayWithAGreaterSum {
    /*
        This problem requires us to find the smallest subarray with
        a sum greater or equal to a given sum
        we could solve this using a dynamic sliding window approach
        where each subarray is a sliding window,
        so for the window to be unique, this condition has to be satisfied
        at any point where the sum of the subarray > than the given sum ,
        this is the point where we want to keep the length of such subarray
        then we will begin to shrink the window :
            by subtracting the first element of the curr window
            and adding the next element of the next window
            Time Complexity = O(n)
            Space Complexity = O(1)
     */
    public static int findMinSubArray(int [] arr, int S){
        //create variables for windowStart, minLength, windowSum
        int windowStart = 0, minLength = Integer.MAX_VALUE;
        int windowSum = 0;
        //traversing through the arr
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++){
            //add the next element to the windowSum
            windowSum +=arr[windowEnd];
            //the condition is to have windowSum >=  Given sum
            //then we have to keep the minlength  of the subArray whose sum >= GIVEN_SUM
            //after we achieve this, we have to move to the next window
            //this is then achieved by shrinking the window
            while (windowSum >= S){
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                //shrink the window by deleting the first element of the current window
                windowSum -= arr[windowStart];
                //then, we add the next element(windowStart) of the next window
                windowStart++;
            }
        }
        //return the minLength;
        return minLength;
    }

    public static void main(String[] args) {
        int [] arr = {2,1,5,2,8};
        int S = 7;
        int res = findMinSubArray(arr, S);
        System.out.println(res);
    }
}
