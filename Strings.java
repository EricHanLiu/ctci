import java.util.Arrays;
import java.util.HashMap;

public class Strings
{
    public Strings()
    {

    }

    // determines if a string s is made up of only unique characters
    public boolean isUnique(String s)
	{
		// if more than 255 characters, must be repetition (pigeonhole)
		// assumes no super special character outside of 255 bit representation
		if (s.length() > 255) { 
			return false;
		}

		int[] arr = new int[255]; // handles any 16 bit character
		Arrays.fill(arr, 0);

		for (char c : s.toCharArray()) 
		{
			arr[c]++;
		}

		for (int i = 0; i < arr.length; i++)
		{
			if (arr[i] > 1) {
				return false;
			}
		}
		return true;
	}	

	// counts number of substrings with exactly k unique characters 
    public int count(String str, int k) 
    { 
        int res = 0; // result and eventual return value
        int n = str.length(); 
  
        int count[] = new int[26]; // 'hashmap' which stores count of each character
  
        // consider all substrings beginning with str[i] 
        for (int i = 0; i < n; i++) 
        { 
            int distincts = 0; 
  
            // initialize count array with 0s
            Arrays.fill(count, 0); 
  
            // Consider all substrings between str[i..j] 
            for (int j = i; j < n; j++) 
            { 
                // If new character for this substring, increment number of distincts
                // (Post) increment character count simultaneously
                if (count[str.charAt(j) - 'a']++ == 0) 
                    distincts++; 
  
                // If distinct character count becomes k, then increment result 
                if (distincts == k) 
                    res++; 
            } 
        } 
  
        return res; 
    } 

    public boolean isPermutation(String s1, String s2)
    {
    	int n = s1.length();
    	// can't be permutation if diff lengths
    	if (n != s2.length()) {
    		return false;
    	}

    	HashMap<Character, Integer> h1 = new HashMap<Character, Integer>();
    	HashMap<Character, Integer> h2 = new HashMap<Character, Integer>();

    	// initialize both maps with 0s
    	for (int i = 0; i < n; i++)
    	{
    		h1.put(s1.charAt(i), 0);
    		h2.put(s2.charAt(i), 0);
    	}

    	// store char freqeuencies for both strings in hash maps
    	for (int i = 0; i < n; i++)
    	{
    		char c = s1.charAt(i);
    		h1.put(c, h1.get(c) + 1);
    	}
    	for (int i = 0; i < n; i++)
    	{
    		char c = s2.charAt(i);
    		h2.put(c, h2.get(c) + 1);
    	}

    	// compare value for each key, if one differs then return false
    	for (char k : h1.keySet()) 
    	{
    		if (h1.get(k) != h2.get(k)) {
    			return false;
    		}
    	}

    	return true;
    }

    

    // driver
    public static void main(String[] args)
    {
        Strings s = new Strings();
        
        // test isUnique function
        System.out.println("\nCONTAINS ONLY UNIQUE CHARACTERS");
        System.out.println("repetition: " + s.isUnique("repetition")); // false
		System.out.println("qwertyuiop: " + s.isUnique("qwertyuiop")); // true

		// test count function
		int k = 3;
		System.out.println("\nNUMBER OF SUBSTRINGS WITH EXACTLY " + k + " DISTINCT CHARACTERS");
		System.out.println("aabc: " + s.count("aabc", k)); 
		System.out.println("hello: " + s.count("hello", k));

		// test permutation function
		System.out.println("\nIS PERMUTATION");
		System.out.println("permute, ermupte: " + s.isPermutation("permute", "ermupte"));
		System.out.println("definitely not, surely not: " + s.isPermutation("definitely not", "surely not"));
    }
}
