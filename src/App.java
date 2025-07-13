import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@SuppressWarnings("unused")
class Solution {

    //#region 345
    public String reverseVowels(String s) {
        // cách 1
        // Map<Integer, Character> mp = new HashMap<>();
        // Stack<Integer> st = new Stack<>();
        // StringBuilder sb = new StringBuilder(s);
        // s = s.toLowerCase();
        // for(int i = 0; i < s.length(); ++i) {
        //    if(s.charAt(i) == 'a' ||
        //     s.charAt(i) == 'o' ||
        //     s.charAt(i) == 'e' || 
        //     s.charAt(i) == 'u' || 
        //     s.charAt(i) == 'i') {
        //         mp.put(i, sb.charAt(i));
        //         st.add(i);
        //    }
        // }

        // for(int i = 0; i < sb.length(); ++i) {
        //     if(mp.containsKey(i)) {
        //         sb.setCharAt(i, mp.get(st.pop()));
        //     }
        // }
        // return sb.toString();
        

        // cách 2
        // String res = "";
        // Stack<Character> st = new Stack<>();
        // String a = s.toLowerCase();
        // int i = 0;
        // for(Character c : a.toCharArray()) {
        //    if(c == 'a' ||
        //     c == 'o' ||
        //     c == 'e' || 
        //     c == 'u' || 
        //     c == 'i') {
        //         st.add(s.charAt(i));
        //    }
        //    ++i;
        // }

        // i = 0;
        // for(Character c : a.toCharArray()) {
        //     if(c == 'a' ||
        //     c == 'o' ||
        //     c == 'e' || 
        //     c == 'u' || 
        //     c == 'i') {
        //         res += st.pop();
        //    } else res += s.charAt(i);
        //    ++i;
        // }
        // return res;



        // cách 3
        StringBuilder sb = new StringBuilder(s);
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if(ci == 'a' ||
            ci == 'o' ||
            ci == 'e' || 
            ci == 'u' || 
            ci == 'i') {
                if(cj == 'a' ||
                cj == 'o' ||
                cj == 'e' || 
                cj == 'u' || 
                cj == 'i') {
                    char temp = sb.charAt(i);
                    sb.setCharAt(i, sb.charAt(j));
                    sb.setCharAt(j, temp);
                    ++i;
                    --j;
                } else --j;
            } else ++i;
        }

        return sb.toString();
    }
    //#endregion

    //#region 151
    public String reverseWords(String s) {
        s = s.trim();
        String[] arr = s.split(" ");    
        String res = "";
        for(int i = arr.length - 1; i >= 0; --i) {
            if(arr[i] == "") continue;
            res += arr[i] + (i != 0 ? " " : "");
        }
        return res;
    }
    //#endregion


    //#region 238
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        if(length == 1) return nums;
        int[] prefix = new int[length];
        prefix[0] = nums[0];
        for(int i = 1; i < length; ++i) {
            prefix[i] = prefix[i - 1]  * nums[i];
        }
        
        int[] postfix = new int[length];
        postfix[length - 1] = nums[length - 1];
        for(int i = length - 1 - 1; i >= 0; --i) {
            postfix[i] = postfix[i + 1] * nums[i];
        }

        int[] answer = new int[length];
        answer[0] = postfix[1];
        answer[length - 1] = prefix[length - 1 - 1];
        for(int i = 1; i < length - 1; ++i) {
            answer[i] = prefix[i - 1] * postfix[i + 1];
        }
        return answer;
    }
    //#endregion

    //#region 1431
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> l = new ArrayList<>();
        int greatest = Arrays.stream(candies).max().getAsInt();
        for (int i : candies) {
            if(i + extraCandies >= greatest) l.add(true);
            else l.add(false);
        }
        return l;
    }
    //#endregion

    // 1071
    public String gcdOfStrings(String str1, String str2) {
        String prefixString = "";
        for (Character c : str1.toCharArray()) {
            String temp = prefixString + (c + "");
            if(str2.contains(temp)) prefixString = temp;
            else break;
        }
        return prefixString;
    }

     
    //#region 443. String Compression
    public void addCountToStack(Stack<Character> st, int count) {
        char[] ch = Integer.toString(count).toCharArray();
        for (char c : ch) {
            st.add(c);
        }
    }

    public int compress(char[] chars) {
        char lastCharacter = ' ';
        int count = 0;
        Stack<Character> st = new Stack<>();
        for(Character c : chars) {
            if(lastCharacter == ' ') {
                lastCharacter = c;
                st.add(lastCharacter);
                ++count;
                continue;
            }
            if(c == lastCharacter) {
                ++count;
            } else {
                lastCharacter = c;
                if(count != 1)
                    addCountToStack(st, count);
                st.add(lastCharacter);
                count = 1;
            }

        }
        if(count != 1)
            addCountToStack(st, count);

        int index = 0;
        for (char c : st) {
            chars[index++] = c;
        }
        return index;
    }
    //#endregion

    //#region 334. Increasing Triplet Subsequence
    public boolean increasingTriplet(int[] nums) {
        int length = nums.length;
        if(length < 3) return false;

        int[] leftMin = new int[length];
        leftMin[0] = nums[0];
        for (int i = 1; i < length; ++i)
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);

        int[] rightMax = new int[length];
        rightMax[length - 1] = nums[length - 1];
        for (int i = length - 2; i >= 0; --i)
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);

        for (int i = 0; i < length; ++i) {
           if(leftMin[i] < nums[i] && nums[i] < rightMax[i]) return true; 
        }

        return false;
    }
    //#endregion

}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        //  System.out.println(s.reverseWords(str));
        // s.productExceptSelf(new int[]{1,2,3,4});
        // System.out.println((s.kidsWithCandies(new int[] {2,3,5,1,3}, 3)));
        // System.out.println(s.gcdOfStrings("LEET", "CODE"));
        // System.out.println(s.compress(new char[]{'a','a','b','b','c','c','c'}));
        // System.out.println(s.increasingTriplet(new int[]{1,2,3,4,5}));
    }
}
