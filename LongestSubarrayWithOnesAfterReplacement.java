package InterviewQuestions.SlidingWindow;

public class LongestSubarrayWithOnesAfterReplacement {
    /*
        Difficulty : Hard
            This problem wants us to return the maximum Length of subarray after k '0s' have been replaced with ones
                we can solve this using a dynamic sliding window approach :
                    for every subarray in the array:
                        we have to count the max number of ones that we have
                        mathematically SubarraySize = maxOnesCount + maxZerosCount
                            so we have to justify the window ,
                            then if we have the SubarraySize - maxOneCount > k
                                then we will begin to shrink the window
                                    by reducing the maxOnesCount until we are left with <= k Os to be replaced

                                    Time Complexity = O(n)
                                    Space Complexity = O(1)
     */
    public static int findMaxLength(int [] arr, int k){
        //create variables windowStart, maxOnesCount, maxLength
        int windowStart = 0, maxOnesCount =0, maxLength = Integer.MIN_VALUE;
        //traversing through the arr
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++){
            //keep track of the number of ones in the arr
            if (arr[windowEnd] == 1){
                maxOnesCount++;
            }
            //then we check if the number of zeroes in the arr > k
            //then we will begin to shrink the arr until we are left with <= k
            if (windowEnd - windowStart + 1 - maxOnesCount > k){
                //we will subtract the element farthest left
                if (arr[windowStart] == 1){
                    maxOnesCount--;
                }
                windowStart++;
            }
            //return maxLength
            maxLength =Math.max(maxLength, windowEnd -windowStart + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int [] arr ={0,1,1,0,0,0,1,1,0,1,1};
        int k = 2;
        int res = findMaxLength(arr,k);
        System.out.println(res);
    }
}
