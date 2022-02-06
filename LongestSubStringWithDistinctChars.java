package InterviewQuestions.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStringWithDistinctChars {

    /*
        Difficulty : Medium
        This problem requires us to return the LongestSubstring with only unique characters
            we can solve this using a dynamic sliding window approach:
                for every subString, we implement a Hahsmap to store the frequency of the characters
                    so at any point where we have a character that has already been included into the HashMap
                        we simply begin a new window and begin to slide the window forward
                        Time Complexity = O(n)
                        Space Complexity = O(1)

     */
    public static int findLength(String s) {
        //create variables windowStart, maxLength
        int windowStart = 0, maxLength = Integer.MIN_VALUE;
        //implement HashMap to store the frequency of Chars
        Map<Character, Integer> map = new HashMap<>();
        //traversing through the string
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightChar = s.charAt(windowEnd);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);
            //update the windowStart
            if (map.get(rightChar) > 1)
                windowStart = Math.max(windowStart, map.get(rightChar) + 1  );

            //then we will keep track of the maxLengthFound so far
            //update the maxLength
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);

        }
        return maxLength;
    }

    /*
        Here is another way to write it after line 26:
        //Now we check iof the Map already contains the character
            if (map.containsKey(rightChar)) {
                //then at that char, a new Window should begin
                //so we will update the windowStart with an increment in value of the character
                windowStart = Math.max(windowStart, map.get(rightChar) + 1);
            }
            //else if the char is not in the map,
            //we will hash it to the map
            else {
                map.put(rightChar, windowEnd);
            }
     */

    public static void main(String[] args) {
        String s = "aabccbb";
        int res = findLength(s);
        System.out.println(res);
    }
}
