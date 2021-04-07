package pack;

import java.util.HashMap;

public class TwoSum {

  public static void main(String[] args) {
    
    
  }
  
  public int[] twoSum(int[] nums, int target) {
    //return indices of the two numbers such that they add up to target
    int[] ret = new int[2];
    HashMap<Integer,Integer> numbers = new HashMap<Integer,Integer>(); //value = index in nums
    
    for (int i = 0; i < nums.length; i++) {
      //for each i, create hashmap entry (key = int); check if map contains key = target - nums[i]; if so, return
      int diff = target - nums[i];
      
      if (numbers.containsKey(diff)) {
        ret[0] = i;
        ret[1] = numbers.get(diff);
        
        break;
        
      }
      
      numbers.put(nums[i], i);
      
    }
    
    return ret;
  }

}
