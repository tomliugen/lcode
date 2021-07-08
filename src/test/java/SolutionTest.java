import model.ListNode;
import org.testng.*;
import org.testng.annotations.*;

import java.math.BigDecimal;

/**
 * Created by 14060614 on 2021/3/23.
 */
public class SolutionTest {
    @Test
    public void twoSumTest(){
        int[] nums = new int[4];
        nums[0] = 2;
        nums[1] = 7;
        nums[2] = 11;
        nums[3] = 15;
        int target = 13;

        Solution solution = new Solution();
        int[] result = solution.twoSum(nums, target);
        Assert.assertTrue(result.length == 2);
        Assert.assertTrue(result[0] == 0);
        Assert.assertTrue(result[1] == 2);
    }

    /**
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     输出：[7,0,8]
     解释：342 + 465 = 807.
     */
    @Test
    public void addTwoNumbersTest1(){
        ListNode listNode1 = genListNode(new int[]{2,4,3});
        ListNode listNode2 = genListNode(new int[]{5,6,4});
        Solution solution = new Solution();
        ListNode result = solution.addTwoNumbers(listNode1, listNode2);
        Assert.assertTrue(result.val == 7);
        Assert.assertTrue(result.next.val == 0);
        Assert.assertTrue(result.next.next.val == 8);
        Assert.assertTrue(result.next.next.next == null);
    }

    /**
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     输出：[8,9,9,9,0,0,0,1]
     */
    @Test
    public void addTwoNumbersTest2(){
        ListNode listNode1 = genListNode(new int[]{9,9,9,9,9,9,9});
        ListNode listNode2 = genListNode(new int[]{9,9,9,9});
        Solution solution = new Solution();
        ListNode result = solution.addTwoNumbers(listNode1, listNode2);
        Assert.assertTrue(result.val == 8);
        Assert.assertTrue(result.next.val == 9);
        Assert.assertTrue(result.next.next.val == 9);
        Assert.assertTrue(result.next.next.next.val == 9);
        Assert.assertTrue(result.next.next.next.next.val == 0);
        Assert.assertTrue(result.next.next.next.next.next.val == 0);
        Assert.assertTrue(result.next.next.next.next.next.next.val == 0);
        Assert.assertTrue(result.next.next.next.next.next.next.next.val == 1);
        Assert.assertTrue(result.next.next.next.next.next.next.next.next == null);
    }

    ListNode genListNode(int[] nums){
        ListNode listNode = new ListNode();
        ListNode firstNode = listNode;
        ListNode lastNode = listNode;
        for(int i=0; i<nums.length; i++){
            listNode.val = nums[i];
            listNode.next = new ListNode();
            lastNode = listNode;
            listNode = listNode.next;
        }
        lastNode.next = null;
        return firstNode;
    }

    @Test
    public void lengthOfLongestSubstringTest(){
        Solution solution = new Solution();
        int length = solution.lengthOfLongestSubstring("abcabcbb");
        int length2 = solution.lengthOfLongestSubstring("bbbb");
        int length3 = solution.lengthOfLongestSubstring("pwwkew");
        int length4 = solution.lengthOfLongestSubstring("aabbccddef");
        int length5 = solution.lengthOfLongestSubstring("dvdf");
        Assert.assertTrue(length == 3);
        Assert.assertTrue(length2 == 1);
        Assert.assertTrue(length3 == 3);
        Assert.assertTrue(length4 == 3);
        Assert.assertTrue(length5 == 3);
    }

    @Test
    public void findMedianSortedArraysTest(){
        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{2};
        double result = 2;
        Solution solution = new Solution();
        Assert.assertTrue(new BigDecimal(result).compareTo(new BigDecimal(solution.findMedianSortedArrays(nums1, nums2))) == 0);

        nums1 = new int[]{1,2,3};
        nums2 = new int[]{4};
        result = 2.5;
        double result2 = solution.findMedianSortedArrays(nums1, nums2);
        Assert.assertTrue(new BigDecimal(result).compareTo(new BigDecimal(solution.findMedianSortedArrays(nums1, nums2))) == 0);

        nums1 = new int[]{1};
        nums2 = new int[]{};
        result = 1;
        Assert.assertTrue(new BigDecimal(result).compareTo(new BigDecimal(solution.findMedianSortedArrays(nums1, nums2))) == 0);

        nums1 = new int[]{};
        nums2 = new int[]{2};
        result = 2;
        Assert.assertTrue(new BigDecimal(result).compareTo(new BigDecimal(solution.findMedianSortedArrays(nums1, nums2))) == 0);
    }

    @Test
    public void longestPalindromeTest(){
        Solution solution = new Solution();
        String s1 = "abc";
        String s2 = "babad";
        String s3 = "cbbd";
        String result1 = solution.longestPalindrome(s1);
        String result2 = solution.longestPalindrome(s2);
        String result3 = solution.longestPalindrome(s3);
        Assert.assertTrue(result1.equals("a"));
        Assert.assertTrue(result2.equals("bab"));
        Assert.assertTrue(result3.equals("bb"));
    }

    @Test
    public void convertTest(){
        String s = "ABC";
        int numRows = 2;
        String expectResult = "ACB";
        Solution solution = new Solution();
        String result = solution.convert(s, numRows);
        Assert.assertEquals(expectResult, result);

        s = "PAYPALISHIRING";
        expectResult = "PINALSIGYAHRPI";
        numRows = 4;
        result = solution.convert(s, numRows);
        Assert.assertEquals(expectResult, result);

        s = "A";
        expectResult = "A";
        numRows = 1;
        result = solution.convert(s, numRows);
        Assert.assertEquals(expectResult, result);
    }

    @Test
    public void reverseTest(){
        Solution solution = new Solution();
        int input1 = -2147483648;
        int input2 = -321;
        int input3 = 0;
        int expectOutput1 = 0;
        int expectOutput2 = -123;
        int expectOutput3 = 0;
        int output1 = solution.reverse(input1);
        int output2 = solution.reverse(input2);
        int output3 = solution.reverse(input3);
        Assert.assertEquals(expectOutput1, output1);
        Assert.assertEquals(expectOutput2, output2);
        Assert.assertEquals(expectOutput3, output3);
    }

    @Test
    public void myAtoiTest(){
        Solution solution = new Solution();
        String input1 = "42";
        int expectOutput1 = 42;
        String input2 = "    -42";
        int expectOutput2 = -42;
        String input3 = " 4193 with words";
        int expectOutput3 = 4193;
        String input4 = " words and 987";
        int expectOutput4 = 0;
        String input5 = " -91283472332";
        int expectOutput5 = Integer.MIN_VALUE;
        String input6 = " 9223372036854775808";
        int expectOutput6 = Integer.MAX_VALUE;
        String input7 = "+-42";
        int expectOutput7 = 0;
        String input8 = "00+-42";
        int expectOutput8 = 0;
        String input9 = "+0 123";
        int expectOutput9 = 0;
        String input10 = " -9223372036854775808";
        int expectOutput10 = Integer.MIN_VALUE;
        String input11 = "18446744073709551617";
        int expectOutput11 = Integer.MAX_VALUE;
        String input12 = "-18446744073709551617";
        int expectOutput12 = Integer.MIN_VALUE;
        String input13 = "00000-42a1234";
        int expectOutput13 = 0;
        int output1 = solution.myAtoi(input1);
        int output2 = solution.myAtoi(input2);
        int output3 = solution.myAtoi(input3);
        int output4 = solution.myAtoi(input4);
        int output5 = solution.myAtoi(input5);
        int output6 = solution.myAtoi(input6);
        int output7 = solution.myAtoi(input7);
        int output8 = solution.myAtoi(input8);
        int output9 = solution.myAtoi(input9);
        int output10 = solution.myAtoi(input10);
        int output11 = solution.myAtoi(input11);
        int output12 = solution.myAtoi(input12);
        int output13 = solution.myAtoi(input13);
        Assert.assertEquals(expectOutput1, output1);
        Assert.assertEquals(expectOutput2, output2);
        Assert.assertEquals(expectOutput3, output3);
        Assert.assertEquals(expectOutput4, output4);
        Assert.assertEquals(expectOutput5, output5);
        Assert.assertEquals(expectOutput6, output6);
        Assert.assertEquals(expectOutput7, output7);
        Assert.assertEquals(expectOutput8, output8);
        Assert.assertEquals(expectOutput9, output9);
        Assert.assertEquals(expectOutput10, output10);
        Assert.assertEquals(expectOutput11, output11);
        Assert.assertEquals(expectOutput12, output12);
        Assert.assertEquals(expectOutput13, output13);
    }

    @Test
    public void isMatchTest(){
          Solution solution = new Solution();
//        Assert.assertEquals(solution.isMatch("", ""), true);
//        Assert.assertEquals(solution.isMatch(null, ""), true);
//        Assert.assertEquals(solution.isMatch(null, null), true);
//        Assert.assertEquals(solution.isMatch("", "1"), true);
//        Assert.assertEquals(solution.isMatch("1", ""), false);
//        Assert.assertEquals(solution.isMatch("aa", "a"), false);

//        Assert.assertEquals(solution.isMatch("a", "ab*"), true);
//        Assert.assertEquals(solution.isMatch("aa", "aa"), true);
//        Assert.assertEquals(solution.isMatch("aa", "a*"), true);
//        Assert.assertEquals(solution.isMatch("aaa", "a*a"), true);
//        Assert.assertEquals(solution.isMatch("aaaaab", "a*b"), true);
//        Assert.assertEquals(solution.isMatch("babaaaaabacd", ".*aa*ab*ac*d"), true);
//
//
//        Assert.assertEquals(solution.isMatch("ab", ".*c"), false);
//        Assert.assertEquals(solution.isMatch("abcedf", ".*"), true);
//        Assert.assertEquals(solution.isMatch("aaaabc", "a.*c*.*.*bc"), true);
//        Assert.assertEquals(solution.isMatch("aab", "c*a*b"), true);
//        Assert.assertEquals(solution.isMatch("mississippi", "mis*is*p*."), false);

    }

    @Test
    public void maxAreaTest(){
        Solution solution = new Solution();
        int[] height1 = new int[]{1,8,6,2,5,4,8,3,7};
        int[] height2 = new int[]{1,1};
        int[] height3 = new int[]{4,3,2,1,4};
        int[] height4 = new int[]{1,2,1};
        int[] height5 = new int[]{2,0};
        Assert.assertEquals(solution.maxArea(height1), 49);
        Assert.assertEquals(solution.maxArea(height2), 1);
        Assert.assertEquals(solution.maxArea(height3), 16);
        Assert.assertEquals(solution.maxArea(height4), 2);
        Assert.assertEquals(solution.maxArea(height5), 0);
    }
}
