package InterviewQuestions.SlidingWindow;

public class MaximumSumSubarrayWithK {
    /*
    Difficulty : Easy
        This problem requires us to find the maximum sum of a subArray with a size k
            we can solve this problem using a dynamic sliding window problem :
                we will consider each subArray a sliding window of size 'k'
                    remember that if we want to move to the next subarray, we will slide the window ahead
                    to achieve this :
                        we will subtract the first element from the window we are moving from
                        and add the new element from the window we are sliding into
                        Time complexity = O(n)
                        Space Complexity = O(1)
     */
    public static int findMax(int [] arr, int k){
        //create the needed variables
        int windowSum = 0, maxSum = Integer.MIN_VALUE;
        int windowStart = 0;
        //traversing through each subArray
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++){
            windowSum += arr[windowEnd];
            //since the subarray has to have a fixed size == k
            // then we will check if the end of the subarray >= the fixed size
            //at that point, we will update the maxSum
            //then slide the window forward
            if (windowEnd >= k - 1){
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart];
                //slide the window ahead
                windowStart++;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int [] arr = {2,1,5,1,3,2};
        int k = 3;
        int res =findMax(arr,k);
        System.out.println(res);
    }
}
