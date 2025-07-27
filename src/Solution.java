import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


class ListNode {
    int val;
    ListNode next;

    public ListNode() {
        super();
    }

    ListNode(int x) {
        val = x;
        next = null;
    }
}

@SuppressWarnings("unused")
public class Solution {

    // 1 Two sum
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int sub = target - nums[i];
            if (mp.containsKey(sub)) {
                return new int[] { mp.get(sub), i };
            }
            mp.put(nums[i], i);
        }
        return null;
    }

    // miss number
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 1; i <= nums.length; ++i) {
            res ^= i ^ nums[i - 1];
        }

        return res;
    }

    // 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null) {
            slow = head.next;
            fast = head.next.next;

            if (slow == fast)
                return true;
        }
        return false;
    }


    // 19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        while(n-- > 0) {
            fast = fast.next;
        }
        if(fast == null) {
            return fast;
        }
        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    // 20. Valid Parentheses
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case ')':
                    if (st.empty() || st.peek() != '(')
                        return false;
                    else st.pop();
                    break;
                case ']':
                    if (st.empty() || st.peek() != '[')
                        return false;
                    else st.pop();
                    break;
                case '}':
                    if (st.empty() || st.peek() != '{')
                        return false;
                    else st.pop();
                    break;
                default:
                    st.push(c);
                    break;
            }
        }
        return st.empty();
    }

    // 21. merge two sort list
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode list = new ListNode();
        ListNode head = list;
        while(list1 != null || list2 != null) {
            if(list1.val < list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            // head = head.next;
        }

        if(list1 == null) {
            while(list2 != null) {
                head.next = list2.next;
            }
        }

        if(list2 == null) {
            while(list1 != null) {
                head.next = list1.next;
            }
        }

        return list;
    }

    // 82. Remove Duplicates from Sorted List II
    public ListNode deleteDuplicatesII(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean isDuplicate = false;
        while(fast.next != null) {
            if(fast.val == fast.next.val) {
                if(fast == head) slow = fast.next;
                else slow.next = fast.next;
                fast = fast.next;
                isDuplicate = true;
                continue;
            } else {
                if(isDuplicate) {
                    slow.next = fast.next;
                    fast = fast.next;
                    isDuplicate = false;
                    continue;
                }
            }

            slow = fast;
            fast = fast.next;
        }
        return head;
    }

    // 83. Remove Duplicates from Sorted List
    public ListNode deleteDuplicates(ListNode head) {
        int prevValue = -101;
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null) {
            if(prevValue == fast.val) {
                slow.next = fast.next;
                fast = fast.next;
                continue;
            }
            prevValue = fast.val;
            slow = fast;
            fast = fast.next;
        }
        return head;
    }



    // 190 reverse bits
    public int reverseBits(int n) {
        // String s = Integer.toString(n);
        System.out.println(Integer.toBinaryString(n));
        String s = "00000010100101000001111010011100";
        StringBuilder sb = new StringBuilder(s);
        return Integer.parseInt(sb.reverse().toString(), 2);
    }

    public String addBinary(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a) + Integer.parseInt(b));
    }

    // 1768. Merge Strings Alternately
    public String mergeAlternately(String word1, String word2) {
        String res = "";
        int i = 0;
        int maxLength = Math.max(word1.length(), word2.length());
        while(i < maxLength) {
            res += (i < word1.length() ? (word1.charAt(i) + "") : "") + (i < word1.length() ? (word2.charAt(i) + "") : "");
            ++i;
        }
        return res;
    }

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

    //#region 643. Maximum Average Subarray I
    public double findMaxAverage(int[] nums, int k) {
        if(nums.length == 1) return nums[0];

        double sumk = 0;
        for(int i = 0; i < k; ++i) 
            sumk += nums[i];
        
        double maxAvg = sumk / k;
        for(int i = k; i < nums.length; ++i) {
            sumk += nums[i] - nums[i - k];
            maxAvg = Math.max(maxAvg, sumk / k);
        }
        return maxAvg;
    }
    //#endregion

    //#region 1456. Maximum Number of Vowels in a Substring of Given Length
    public int maxVowels(String s, int k) {
        int length = s.length();
        boolean[] isWovel = new boolean[length];
        for(int i = 0; i < length; ++i) {
            char c = s.charAt(i);
            isWovel[i] = (c == 'a' || c == 'o' || c == 'e' || c == 'u' || c == 'i');
        }

        int count = 0;
        for(int i = 0; i < k; ++i) {
            if(isWovel[i]) ++count;
        }
         
        int maxwovel = count;
        for(int i = k; i < length; ++i) {
            if(isWovel[i]) ++count;
            if(isWovel[i - k]) --count;
            maxwovel = Math.max(maxwovel, count);
        }

        return maxwovel;    
    }
    //#endregion

    //#region 1004. Max Consecutive Ones III
    public int longestOnes(int[] nums, int k) {
        int maxOneLength = 0;


        return maxOneLength;
    }
    //#endregion


    //#region 724. Find Pivot Index
    public int pivotIndex(int[] nums) {
        int length = nums.length;
        int[] leftSum = new int[length];
        leftSum[0] = nums[0];
        for(int i = 1; i < length; ++i) {
            leftSum[i] = leftSum[i - 1] + nums[i];
        }

        int[] rightSum = new int[length];
        rightSum[length - 1] = nums[length - 1];
        for(int i = length - 2; i >= 0; --i) {
            rightSum[i] = rightSum[i + 1] + nums[i];
        }

        for(int i = 0; i < length; ++i) {
            if(leftSum[i] == rightSum[i]) return i;
        }

        return -1;
    }
    //#endregion


    //#region 1732. Find the Highest Altitude
    public int largestAltitude(int[] gain) {
        // dùng biến tạm để lưu max và chi phí đỉnh trước
        int highestAltitude = 0;
        int lastAltitude = 0;
        for(int i = 0; i < gain.length; ++i) {
            lastAltitude = gain[i] + lastAltitude;
            highestAltitude = Math.max(highestAltitude,lastAltitude);
        }
        return highestAltitude;


        // dùng mảng để lưu xong duyệt tìm max
        // int highestAltitude = 0;
        // int[] a = new int[gain.length];
        // a[0] = gain[0] + 0;
        // for(int i = 1; i < gain.length; ++i) {
        //     a[i] = gain[i] + a[i - 1];
        // }
        // for(int i : a) {
        //     highestAltitude = Math.max(highestAltitude, i);
        // }
        // return highestAltitude;
    }
    //#endregion


    //#region 2215. Find the Difference of Two Arrays
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> l = new ArrayList<>(); 
        Map<Integer, Integer> mp = new HashMap<>();
        Set<Map<Integer, Integer>> s = new TreeSet<>();
        for(int i : nums1) {
            mp.put(i, 1);    
        }

        for(int i : nums2) {
            mp.put(i, 2);    
        }

        
        
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        return l;
    }
    //#endregion


}