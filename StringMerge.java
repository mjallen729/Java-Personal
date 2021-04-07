package pack;

public class StringMerge {
  public static void main(String[] args) {
    String word1 = "matt";
    String word2 = "liam";
    
    String result = mergeAlternately(word1,word2);
    
    System.out.println(result);
    
  }
  
  //word1 = "abc"; word2 = "def"
  //final = "adbecf"
  public static String mergeAlternately(String word1, String word2) {
    StringBuilder finalWord = new StringBuilder();
    
    if (word1.length() == 0 | word2.length() == 0) {
      return null;
      
    }
    
    for (int i = 0; i < word1.length() + word2.length(); i++) {
      char letter1 = '0';
      char letter2 = '0';
      
      try { //word1
        letter1 = word1.charAt(i);
        
      } catch (IndexOutOfBoundsException e) { //if word1 is shorter
        String leftover = word2.substring(i, word2.length());
        
        finalWord.append(leftover);
        
        break;
        
      }
      
      try { //word2
        letter2 = word2.charAt(i);
        
      } catch (IndexOutOfBoundsException e) { //if word2 is shorter
        String leftover = word1.substring(i, word1.length());
        
        finalWord.append(leftover);
        
        break;
        
      }
      
      finalWord.append(letter1);
      finalWord.append(letter2);
      
    }
    
    return finalWord.toString();
    
  }
  
}
