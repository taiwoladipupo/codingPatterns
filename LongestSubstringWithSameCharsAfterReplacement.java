package InterviewQuestions.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithSameCharsAfterReplacement {
    /*
    Difficulty : Hard
        This problem requires us to return the length of the longest substring with same chars after replacement
        we can solve this problem using a dynamic sliding window technique :
            for every substring, we hash the chars into the map
                now we have to look for the maximum no of repeated characters
                then mathematically, a SubstringSize  = no of maxRepeatedChars +  NoOfunique chars  + NoOfminRepeatedChars
                then lets say we are not supposed to replace more than 'k' chars
                where k = no of unRepeated chars(StringSize -(no of maxRepeatedChars)
                then how do we justify our window
                    so at any point if we have more 'K' elements to be replaced
                        we simply began to shrink the window until we are left with windowSize <= k
                        then we store the length of such window so far.
                        Tme Complexity = O(n

     */
    public static int findMaxLength(String s, int k){
        //create variables windowStart, maxLength, maxRepeatedCount
        int windowStart = 0, maxLength =Integer.MIN_VALUE;
        int maxRepeatedLetterCount = 0;
        //Implement a Hashmap to tore the frequency of repeated haracters
        Map<Character, Integer> map = new HashMap<>();
        //traversing through the string
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++ ){
            char rightChar = s.charAt(windowEnd);
            //hash the char to map
            map.put(rightChar,map.getOrDefault(rightChar,0) +1);
            //now get the value of maxRepeatedletterCount
            maxRepeatedLetterCount = Math.max(maxRepeatedLetterCount, map.get(rightChar));
            //since we are supposed to have (SubstringSize - No of max repeated char) <= k
            //so whenever we have it (SubstringSize - No of max repeated char) > k
            //we began to shrink the window by reducing the frequency of the chars from the farthest left
            //and add the next char of the next window
            //and keep track of the length of such substring so far
            if (windowEnd - windowStart + 1 - maxRepeatedLetterCount > k){
                char leftChar = s.charAt(windowStart);
                map.put(leftChar, map.get(leftChar) - 1);
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        //update the maxLength
        return maxLength;
        /*
            another way to write this after line 30 :
                if(map.containsKey(rightChar) {
                    //get the maxrepeatedCount
                    maxRepeatedLetterCount = Math.max(maxRepeatedLetterCount, map.get(rightChar);
                    }
                    else {
                        map.put(rightChar, windowEnd);
                        }
         */

    }

    public static void main(String[] args) {
        String s = "aabccbb";
        int k = 2;
        int res = findMaxLength(s, k);
        System.out.println(res);
    }
}
