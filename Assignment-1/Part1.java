import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Coca-Vargas
 */
public class Part1
{  
    /**
     * Determines if s2 is a permutation of s1 by checking if every
     * character in s1 is in s2 and checking index is not in an
     * Array List called indexes. Adding the indexes of the characters
     * if a match was found and increasing the value of matches by 1.
     * Then checking if the value of matches matches the length of s1.
     */
    public static boolean isStringPermutation(String s1, String s2) {
        boolean isPerm = false;
        int matches = 0;
        ArrayList<Integer> indexes = new ArrayList<>();
        
        // they are not the same length can assume
        // they can not be permutations
        if (s1.length() != s2.length()) {
            return isPerm;
        }
        
        // O(n^3)
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                // if the letters equal each other and the index is not inside 
                // the ArrayList indexes
                if (s1.charAt(i) == s2.charAt(j) && !indexes.contains(j)) {
                    // a match was found
                    matches += 1;
                    
                    // as to not repeat the same characters twice when comparing
                    indexes.add(j);
                    break;
                }
            }
        }
        
        // if matches is equivalent to the length of s1
        if (matches == s1.length()) {
            isPerm = true;
        }
        
        return isPerm;
    }
    
    /**
     * Goes through the input Array finds the target Addend of every number
     * in the input array then checks if the target Addend is in the input Array
     * if it is then it is added to the addends Array List. Then it goes through
     * the Array List with all the possible addends and creates a pair of every
     * possible combination checking if the pair adds up to the target Sum and
     * making sure it is not a duplicate pair.
     */
    private static List<List<Integer>> pairsThatEqualSum(List<Integer> inputArray, Integer targetSum) {
        List<List<Integer>> sums = new ArrayList<>();
        List<Integer> addends = new ArrayList<>();
        List<Integer> pair;
        int targetAddend;
            
        // O(n^2)
        for (int i: inputArray) {
            // the targetSum is equal to i + targetAddend
            targetAddend = targetSum - i;
            
            // only include numbers less than the targetSum
            // only include numbers that have the targetAddend in the input Array
            if (i < targetSum && inputArray.contains(targetAddend)) {
                addends.add(i);
            }
        }
        
        // O(n^3)
        for (int i: addends) {
            for (int j: addends) {
                // resets the pair Array List
                pair = new ArrayList<>();
                
                // create a pair of numbers
                pair.add(i);
                pair.add(j);
                
                // checks if the numbers sum is equal to targetSum
                if (i + j == targetSum && !duplicatePair(sums, pair)) {
                    sums.add(pair);
                }
            }
        }
                
        return sums;
    }
    
    /**
     * Checks if an Array List, a pair of numbers, is inside the 
     * the nested Array List, as well as the reverse pair.
     */
    public static boolean duplicatePair(List<List<Integer>> array, List<Integer> pair) {
        boolean isDuplicate = false;
        List<Integer> reversePair = new ArrayList();
        
        // adds the pair in reverse 
        reversePair.add(pair.get(1));
        reversePair.add(pair.get(0));
        
        for (List<Integer> i: array) {
            // if i is equal to the pair or reverse Pair
            if (i.equals(pair) || i.equals(reversePair)) {
                isDuplicate = true;
            }
        }
        
        return isDuplicate;
    }
    
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);list1.add(2);list1.add(3);list1.add(4);list1.add(5);
        System.out.println(pairsThatEqualSum(list1, 5));
        System.out.println(pairsThatEqualSum(list1, 1));
        System.out.println(pairsThatEqualSum(list1, 7));
        
        
        /*
         == [(1, 4), (2, 3)]
         pairsThatEqualSum(inputArray: [1, 2, 3, 4, 5], targetSum: 1) == []
         pairsThatEqualSum(inputArray: [1, 2, 3, 4, 5], targetSum: 7) == [(2, 5), (3, 4)]
         */
    }
}
