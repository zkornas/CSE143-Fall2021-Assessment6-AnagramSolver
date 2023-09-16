// Zacharia Kornas
// TA: Kent Zeng
// The AnagramSolver class can produce a list of anagrams
// based on the phrase entered by the user.
import java.util.*;

public class AnagramSolver {
   private Map<String, LetterInventory> words;
   private List<String> dict;

   // Constructs an AnagramSolver which stores a dictionary
   // of possible anagrams.
   // Parameters:
   //    dictionary - a list of possible anagrams.
   public AnagramSolver(List<String> dictionary){
      words = new HashMap<String, LetterInventory>();
      for(String word : dictionary){
         LetterInventory wordsInventory = new LetterInventory(word);
         words.put(word, wordsInventory);
      }
      dict = dictionary;
   }
   
   // Prints a list of anagrams of the text entered by the user.
   // The anagrams will contain no more than a maximum number
   // of words indicated by the user if max is greater than 0.
   // If max is 0, there will be unlimited number of words is the anagram.
   // Throws:
   //    IllegalArgumentException - if max is less than 0
   // Parameters:
   //    text - the text that the user wants anagrams of
   //    max - the maximum number of words in the anagram when max is greater than 0
   //          if max is 0, there will be unlimited number of words in the anagram.
   public void print(String text, int max){
      if(max < 0){
         throw new IllegalArgumentException();
      }
      List<String> prunedWords = new ArrayList<>();
      LetterInventory textInventory = new LetterInventory(text);
      for(String word : dict){
         if(textInventory.subtract(words.get(word)) != null){
            prunedWords.add(word);
         }
      }
      if(max == 0){
         max = -1;
      }
      print(textInventory, max, new ArrayList<>(), prunedWords);
   }
   
   // Prints anagrams of the text passed in by the user in the public print method.
   // Anagrams will contain no more than a maximum number of words indicated by the user
   // if max is greater than 0. If max is 0, there will be an unlimited
   // number of words in the anagram.
   // Parameters:
   //    textInventory - the LetterInventory containing all letters 
   //                    in the phrase entered by the user.
   //    max - the maximum number of words that can be in the anagram when max is greater than 0
   //          if max is 0, there will be unlimited number of words sin the anagram.
   //    chosen - the list of chosen words that can be in the anagram
   //    prunedWords - A list of words who's 
   //                  letters all appear in the text entered by the user.
   
   private void print(LetterInventory textInventory, int max, List<String> chosen,
                      List<String> prunedWords){
                      
      if(textInventory.isEmpty()){
         System.out.println(chosen);
      } else if(chosen.size() != max){
         for(String word : prunedWords){
            LetterInventory newTextInventory = textInventory.subtract(words.get(word));
            if(newTextInventory != null){
               chosen.add(word);
               print(newTextInventory, max, chosen, prunedWords);
               chosen.remove(chosen.size() - 1);
            }
         }
      }
   }
}
