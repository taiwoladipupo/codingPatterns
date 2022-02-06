package InterviewQuestions.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongeestSubStringWithMaximumKDinstinctChars {
    /*
        Difficulty : Medium
        This problem asked to find the Longest SubString with Maximum K unique characters
        we can solve this problem using a dynamic sliding window approach :
            the goal is to make the window justified,
            for that to happen, we should not have more than K distinct chars
                for each substring whenever we have more than K unique elements
                    we will shrink the window until we are left with K distinct chars
                        then we will keep track of the length of this window
                        we will implement a hashMap to keep count of the frequency of the characters
                        we will hash the characters from the beggining of the string
                        while the size of the map > K distinct elements
                            we will try to shrink the window until we are left with K Distinct elements
                                we will decrement the frequency of the char at farthest left
                                    and if when decrementing the frequency == 0
                                        we will remove it from the map
                                        Now, if the map size  <= K distinct chars
                                        we will add the next char to begin a new window
                                        At this point we will keep track of the maxLength so far
                                        Time Complexity = O(n)
                                        Space Complexity = O(1)
     */
    public static int findMaxLength(String s, int k){
        //create variables windowStart, maxLength
        int windowStart = 0, maxLength = Integer.MIN_VALUE;
        //implement a HashMap to keep track of the frequency
        Map<Character, Integer> map = new HashMap<>();
        //traversing through the string
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++){
            char rightChar = s.charAt(windowEnd);
            map.put(rightChar, map.getOrDefault(rightChar,0) + 1);
            //the condition is to have a substring with <= K distinct characters
            //so since the characters are stored in the map
            //at any point where the size of the Map does not satisfy this condition i.e
            //the size of the map > K distinct chars
            //then we will begin to shrink the window until we are left with K distinct elements
            while (map.size() > k){
                //shrink the sliding window until we are left with K Distinct chars
                char leftChar = s.charAt(windowStart);
                //here, we are reducing the character going out the window
                map.put(leftChar, map.get(leftChar) - 1);
                //if after reducing the freq of the char going out of the window, and we are left with 0
                //we then remove it from the map
                if (map.get(leftChar) == 0){
                    map.remove(leftChar);
                }
                //then we add the next charcater of the next window
                windowStart++;
            }
            //keep track of the max Length so far
            maxLength  = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "araaci";
        int k = 2;
        int res = findMaxLength(s, k);
        System.out.println(res);
    }
}
