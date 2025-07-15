import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

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
        for (Character c : str2.toCharArray()) {
            String temp = prefixString + (c + "");
            if(str1.contains(temp)) prefixString = temp;
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


    //#region 283. Move Zeroes
    public void moveZeroes(int[] nums) {
        int[] temp = new int[nums.length];
        int index = 0;
        for(int i : nums) {
            if(i != 0) temp[index++] = i;
        }
        for(int i = 0; i < nums.length; ++i) {
            if(i < index) {
                nums[i] = temp[i];
            } else {
                nums[i] = 0;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
    //#endregion

    //#region 392. Is Subsequence
    public boolean isSubsequence(String s, String t) {
        if(s.isEmpty()) return true;
        if(s.length() == 1 && t.length() == 1) return s.equals(t);
        int index = 0;
        for (Character c : t.toCharArray()) {
            if(index >= s.length()) break;
            if(c == s.charAt(index)) ++index; 
        }
        return index == s.length();
    }
    //#endregion

    //#region 11. Container With Most Water
    public int maxArea(int[] height) {
        int maxAmount = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            maxAmount = Math.max(maxAmount, Math.min(height[left], height[right]) * (right - left));
            if(height[left] < height[right]) ++left;
            else --right;
        }
        return maxAmount;
    }
    //#endregion

    //#region 1679. Max Number of K-Sum Pairs
    public int maxOperations(int[] nums, int k) {
        // duyệt 2 con trỏ   *sai
        // int left = 0, right = nums.length - 1;
        // int count = 0;
        // while(left < right) {
        //     if(nums[left] + nums[right] == k) {
        //         ++count;
        //     }
        //     ++left;
        //     --right;
        // }
        // return count;

        // hashmap
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i : nums) {
            if(!mp.containsKey(i)) mp.put(i, 1);
            else mp.put(i, mp.get(i) + 1);
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            int x = entry.getKey();
            int y = k - x;
            if(mp.containsKey(y)) {
                count += (x == y) ? entry.getValue() / 2 : Math.min(entry.getValue(), mp.get(y));
                mp.put(x, 0);
                mp.put(y, 0);
            }
        }
        return count;
    }
    //#endregion

    
}

public class App {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        System.out.println(s.maxOperations(new int[] {3,1,3,4,3}, 6));
    }
}