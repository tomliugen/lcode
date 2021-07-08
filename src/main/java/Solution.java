import model.ListNode;

import java.util.*;

/**
 * Created by 14060614 on 2021/3/23.
 */
public class Solution {
    /**
     * 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
     你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     你可以按任意顺序返回答案。
     示例 1：
     输入：nums = [2,7,11,15], target = 9
     输出：[0,1]
     解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     示例 2：
     输入：nums = [3,2,4], target = 6
     输出：[1,2]
     示例 3：
     输入：nums = [3,3], target = 6
     输出：[0,1]
     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/two-sum
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                if((nums[i]+nums[j]) == target){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     *  * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     请你将两个数相加，并以相同形式返回一个表示和的链表。
     你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     输入：l1 = [2,4,3], l2 = [5,6,4]
     输出：[7,0,8]
     解释：342 + 465 = 807.

     输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     输出：[8,9,9,9,0,0,0,1]


     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/add-two-numbers
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode();
        ListNode lastNode = listNode;
        ListNode firstNode = listNode;
        int lastSumHigh = 0;
        while(l1 != null || l2 != null || lastSumHigh != 0){
            ListNode tmpListNode = new ListNode();
            listNode.val = (l1 == null? 0: l1.val) + (l2 == null ? 0: l2.val) + lastSumHigh;
            if(listNode.val > 9){
                listNode.val = listNode.val %10;
                lastSumHigh = 1;
            }else{
                lastSumHigh = 0;
            }
            listNode.next = tmpListNode;
            l1 = l1 == null ? null :l1.next;
            l2 = l2 == null ? null: l2.next;
            lastNode = listNode;
            listNode = tmpListNode;
        }
        lastNode.next = null;
        return firstNode;
    }

    /**
     *  * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
     * 示例 1:

     输入: s = "abcabcbb"
     输出: 3
     解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     示例 2:

     输入: s = "bbbbb"
     输出: 1
     解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     示例 3:

     输入: s = "pwwkew"
     输出: 3
     解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
          请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     示例 4:

     输入: s = ""
     输出: 0
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int posIndex = 0;
        int strMaxLength = 0;
        Map<Integer, Integer> subStrMap = new HashMap<Integer, Integer>();
        while(posIndex < s.length()){
            Integer value = (int)s.charAt(posIndex);
            if(subStrMap.containsKey(value) ){
                strMaxLength = strMaxLength > subStrMap.size() ? strMaxLength:subStrMap.size();
                posIndex = subStrMap.get(value) + 1;
                subStrMap.clear();
            }else{
                subStrMap.put(value, posIndex);
                posIndex++;
            }
        }
        strMaxLength = strMaxLength > subStrMap.size() ? strMaxLength:subStrMap.size();
        return strMaxLength;
    }

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 输入：nums1 = [1,3], nums2 = [2]
     输出：2.00000
     解释：合并数组 = [1,2,3] ，中位数 2

     输入：nums1 = [1,2], nums2 = [3,4]
     输出：2.50000
     解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

     输入：nums1 = [0,0], nums2 = [0,0]
     输出：0.00000

     * @param nums1
     * @param nums2
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2){
        // 首先对输入参数判空
        if(nums1.length == 0 && nums2.length == 0){
            return 0;
        }
        // 创建新的数组，长度是两个输入数组长度之和
        double[] result = new double[nums1.length+nums2.length];
        int maxLength = result.length;
        int nums1Index = 0;
        int nums2Index = 0;
        // 由于输入数组已经是排好序的，因此通过一次遍历完成两个数组的合并
        for(int i=0; i<maxLength; i++){
            // 优先处理已经到达边界的数组，再比较两个数组的元素
            if(nums1.length == nums1Index){
                result[i] = nums2[nums2Index++];
            }else if(nums2.length == nums2Index){
                result[i] = nums1[nums1Index++];
            }else if(nums1[nums1Index] > nums2[nums2Index]){
                result[i] = nums2[nums2Index++];
            }else{
                result[i] = nums1[nums1Index++];
            }
        }

        // 如果合并后长度为奇数，取中间元素值，否则取中间2个元素之和的平均值
        if(result.length % 2 != 0){
            return result[result.length/2];
        }else{
            return (result[result.length/2]+result[result.length/2-1])/2;
        }
    }


    /**
     *给你一个字符串 s，找到 s 中最长的回文子串。

     示例 1：
     输入：s = "babad"
     输出："bab"
     解释："aba" 同样是符合题意的答案。
     示例 2：
     输入：s = "cbbd"
     输出："bb"

     解法：首先给字符串的每个字符的左右插入一个#，然后循环每一个字符，以每个字符为中心计算回文的长度。

     */
    public String longestPalindrome(String s) {
        String newStr = "#";
        for(int i=0; i<s.length(); i++){
            newStr += s.charAt(i)+"#";
        }
        int pos = 0;
        int maxLength = 0;
        for(int i=1; i<newStr.length(); ){
            int length = getPalindromeLength(newStr, i);
            if(length > maxLength){
                pos = i;
                maxLength = length;
            }
            i +=1;
        }
        String result = newStr.substring(pos-maxLength+1, pos+maxLength);
        return result.replace("#", "");
    }

    private int getPalindromeLength(String str, int index){
        int length = 1;
        while((index-length)>=0 && (index+length) < str.length()){
            if(str.charAt(index-length) == str.charAt(index+length)){
                length++;
            }else{
                return length;
            }
        }
        return length;
    }

    /**将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

     比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：

     P   A   H   N
     A P L S I I G
     Y   I   R
     之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

     示例 1：
     输入：s = "PAYPALISHIRING", numRows = 3
     输出："PAHNAPLSIIGYIR"
     示例 2：
     输入：s = "PAYPALISHIRING", numRows = 4
     输出："PINALSIGYAHRPI"
     解释：
     P     I    N
     A   L S  I G
     Y A   H R
     P     I

     思路：对于给定长度为L的字符串，行数n，在对字符串排列时，每个循环需要消耗n+n-2个字符，预先创建包含n个buffer的list，
     然后按循环次数，开始赋值

     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if(numRows == 1){
            return s;
        }
        int times =  s.length()/(numRows*2-2);
        if( s.length()%(numRows*2-2) != 0){
            times++;
        }
        // 创建存储Z字形字符的list
        List<StringBuffer> valueList = new ArrayList<StringBuffer>(numRows);
        for(int i=0;i<numRows; i++){
            valueList.add(new StringBuffer());
        }
        int index = 0;
        int stepLength = 2*numRows-2;
        // 按循环次数，逐个循环进行赋值
        for(int i=0; i<times; i++){
            for(int j=0; j<stepLength && index < s.length(); j++){
                // 每个循环的第一列要填满
                if(j<numRows){
                    valueList.get(j).append(s.charAt(index++));
                }else{ // 后面的（n-2）列，每列填一个字符
                    valueList.get(numRows*2 - j - 2).append(s.charAt(index++));
                }
            }
        }
        // 把排列好的字符串组装成一个完整的字符串并返回
        StringBuffer result = new StringBuffer();
        for(int i=0;i<valueList.size(); i++){
            result.append(valueList.get(i).toString());
        }
        return result.toString();
    }

    /** 整数翻转
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 示例 1：

     输入：x = 123
     输出：321
     示例 2：

     输入：x = -123
     输出：-321
     示例 3：

     输入：x = 120
     输出：21
     示例 4：

     输入：x = 0
     输出：0

     * @param x
     * @return
     */
    public int reverse(int x){
        long result = 0;
        long input = x;
        if(x == 0){
            result = 0;
        }else if(x>0){
            result = Long.parseLong(new StringBuffer(String.valueOf(input)).reverse().toString());
        }else{
            result = -Long.parseLong(new StringBuffer(String.valueOf(-input)).reverse().toString());
        }
        if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            return 0;
        }else{
            return (int)result;
        }
    }

    /**
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。

     函数 myAtoi(string s) 的算法如下：

     读入字符串并丢弃无用的前导空格
     检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     返回整数作为最终结果。
     注意：

     本题中的空白字符只包括空格字符 ' ' 。
     除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
      

     示例 1：

     输入：s = "42"
     输出：42
     解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
     第 1 步："42"（当前没有读入字符，因为没有前导空格）
     ^
     第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
     ^
     第 3 步："42"（读入 "42"）
     ^
     解析得到整数 42 。
     由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。
     示例 2：

     输入：s = "   -42"
     输出：-42
     解释：
     第 1 步："   -42"（读入前导空格，但忽视掉）
     ^
     第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
     ^
     第 3 步："   -42"（读入 "42"）
     ^
     解析得到整数 -42 。
     由于 "-42" 在范围 [-231, 231 - 1] 内，最终结果为 -42 。
     示例 3：

     输入：s = "4193 with words"
     输出：4193
     解释：
     第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
     ^
     第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
     ^
     第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
     ^
     解析得到整数 4193 。
     由于 "4193" 在范围 [-231, 231 - 1] 内，最终结果为 4193 。
     示例 4：

     输入：s = "words and 987"
     输出：0
     解释：
     第 1 步："words and 987"（当前没有读入字符，因为没有前导空格）
     ^
     第 2 步："words and 987"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
     ^
     第 3 步："words and 987"（由于当前字符 'w' 不是一个数字，所以读入停止）
     ^
     解析得到整数 0 ，因为没有读入任何数字。
     由于 0 在范围 [-231, 231 - 1] 内，最终结果为 0 。
     示例 5：

     输入：s = "-91283472332"
     输出：-2147483648
     解释：
     第 1 步："-91283472332"（当前没有读入字符，因为没有前导空格）
     ^
     第 2 步："-91283472332"（读入 '-' 字符，所以结果应该是负数）
     ^
     第 3 步："-91283472332"（读入 "91283472332"）
     ^
     解析得到整数 -91283472332 。
     由于 -91283472332 小于范围 [-231, 231 - 1] 的下界，最终结果被截断为 -231 = -2147483648 。
      

     提示：

     0 <= s.length <= 200
     s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/string-to-integer-atoi
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        if(s.length() == 0){
            return 0;
        }
        long result = 0L;
        boolean nagative = false;
        int length = s.length();
        int start = 0;
        // 排除空格
        for(int i=0; i<length; i++){
            if(s.charAt(i) == ' '){
                continue;
            }else{
                start = i;
                break;
            }
        }
       // 找到正负符号，且正负符号只能出现一次
        if(s.charAt(start) == '-' || s.charAt(start) == '+'){
            nagative = s.charAt(start) == '-';
            start = start+1;
        }
        for(int i=start; i<length; i++){
            char currentChar = s.charAt(i);
            if(currentChar <= '9' && currentChar >= '0' ){
                result = result*10 + (currentChar - '0');
                // 判断数字边界
                if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
                    break;
                }else {
                    continue;
                }
            }else {
                break;
            }
        }
        if(nagative){
            result = -result;
        }
        if(result > Integer.MAX_VALUE){
            result = Integer.MAX_VALUE;
        }else if(result < Integer.MIN_VALUE){
            result = Integer.MIN_VALUE;
        }
        return (int)result;
    }

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

     说明：你不能倾斜容器。
     输入：[1,8,6,2,5,4,8,3,7]
     输出：49
     解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     示例 2：

     输入：height = [1,1]
     输出：1
     示例 3：

     输入：height = [4,3,2,1,4]
     输出：16
     示例 4：

     输入：height = [1,2,1]
     输出：2
      

     提示：

     n = height.length
     2 <= n <= 3 * 104
     0 <= height[i] <= 3 * 104

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/container-with-most-water
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int maxValue = 0;
        int head = 0;
        int tail = height.length-1;
        while (head < tail){
            if(height[head] > height[tail]){
                if(height[tail]*(tail - head) > maxValue) {
                    maxValue = height[tail]*(tail - head);
                }
                tail--;
            }else {
                if(height[head]*(tail - head) > maxValue){
                    maxValue = height[head]*(tail - head);
                }
                head++;
            }
        }
        return maxValue;
    }

    /**
     给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

     注意：答案中不可以包含重复的三元组。

      

     示例 1：

     输入：nums = [-1,0,1,2,-1,-4]
     输出：[[-1,-1,2],[-1,0,1]]
     示例 2：

     输入：nums = []
     输出：[]
     示例 3：

     输入：nums = [0]
     输出：[]
      

     提示：

     0 <= nums.length <= 3000
     -105 <= nums[i] <= 105

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/3sum
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        return null;
    }

    /**
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

     '.' 匹配任意单个字符
     '*' 匹配零个或多个前面的那一个元素
     所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

      
     示例 1：

     输入：s = "aa" p = "a"
     输出：false
     解释："a" 无法匹配 "aa" 整个字符串。
     示例 2:

     输入：s = "aa" p = "a*"
     输出：true
     解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     示例 3：

     输入：s = "ab" p = ".*"
     输出：true
     解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     示例 4：

     输入：s = "aab" p = "c*a*b"
     输出：true
     解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     示例 5：

     输入：s = "mississippi" p = "mis*is*p*."
     输出：false
      

     提示：

     0 <= s.length <= 20
     0 <= p.length <= 30
     s 可能为空，且只包含从 a-z 的小写字母。
     p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     保证每次出现字符 * 时，前面都匹配到有效的字符

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/regular-expression-matching
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        // 先处理任意一个参数为空的问题
        if ((s == null || s.length() == 0)) {
            return true;
        }
        if(p == null || p.length() == 0){
            return false;
        }
        final char dot = '.';
        final char star = '*';
        // 贪婪算法，先进来向右匹配，并记录下最大可回溯元素个数，在下一个字符无法匹配时，再回溯匹配
        int sPos = 0;
        int pPos = 0;
        char lastChar = ' ';
        int returnCharCount = 0;
        boolean lastCharIsDot = false;
        for(; pPos<p.length(); pPos++){

            if(pPos>0){
                lastChar = p.charAt(pPos-1);
            }
            // 当匹配到"."后，更新标志位
            if(dot == p.charAt(pPos)){
                lastCharIsDot = true;
                sPos++;
                continue;
            }

            if(star == p.charAt(pPos)){
                // 如果上一次字符是"",本次字符是"*", 则匹配到s的结尾
                if(lastCharIsDot){
                    returnCharCount = s.length() - sPos;
                    sPos = s.length();
                    continue;
                }else{
                    // '*' 匹配零个或多个前面的那一个元素
                    while(sPos < s.length() && lastChar == s.charAt(sPos) && (s.length()-sPos)>=(p.length()-pPos)){
                        sPos++;
                        returnCharCount++;
                    }
                    continue;
                }
            }else if(p.charAt(pPos) == s.charAt(sPos)){
                sPos++;
                continue;
            }else{
                // 当不匹配时，且可回退时，sPos和pPos一起回退，且
                if (returnCharCount>0) {
                    pPos++;
                }
            }
        }
        return true;
    }

    private boolean matchSubString(String s, String p, int startPos, int backupSize){
        int sPos = startPos;
        int pPos = 0;
        char lastChar = ' ';
        final char dot = '.';
        final char star = '*';
        boolean lastCharIsDot = false;
        int newBackupSize = 0;
        for(;pPos<p.length(); pPos++){
            if(sPos > s.length()){
                return false;
            }
            if(pPos>0){
                lastChar = p.charAt(pPos-1);
            }
            // 当匹配到"."后，更新标志位
            if(dot == p.charAt(pPos)){
                lastCharIsDot = true;
                sPos++;
                if(sPos >= s.length()){
                    return false;
                }
                continue;
            }

            if(star == p.charAt(pPos)){
                // 如果上一次字符是".",本次字符是"*", 则匹配到s的结尾
                if(lastCharIsDot){
                    // 匹配到结尾，即结束
                    if((pPos+1) == p.length()){
                        return true;
                    }else {// 递归匹配
                        return matchSubString(s.substring(sPos), p.substring(pPos + 1), 0, s.substring(sPos).length());
                    }
                }else{
                    // '*' 匹配零个或多个前面的那一个元素
                    while(sPos < s.length() && lastChar == s.charAt(sPos) && (s.length()-sPos)>=(p.length()-pPos)){
                        sPos++;
                        newBackupSize++;
                    }
                    // 都匹配到结尾，则成功
                    if((pPos+1) == p.length() && (sPos+1) == s.length()){
                        return true;
                    }
                    // 有一个未匹配到结尾，则失败
                    if((pPos+1) == p.length() || (sPos+1) == s.length()){
                        return false;
                    }
                    // 否则继续递归
                    return matchSubString(s.substring(sPos), p.substring(pPos), 0, newBackupSize);
                }
            }else if(p.charAt(pPos) == s.charAt(sPos)){
                if((sPos+1) == s.length() ){
                    return true;
                }
                sPos++;
                continue;
            }else{

            }
        }
        return true;
    }
//    {
//        // 先处理任意一个参数为空的问题
//        if ((s == null || s.length() == 0)) {
//            return true;
//        }
//        if(p == null || p.length() == 0){
//            return false;
//        }
//        final char dot = '.';
//        final char star = '*';
//
//        // 首先对p进行分段，按".*"和"字符*"作为分隔字符串
//        int segmentCount = 0;
//        String[] pSegments = parseP(p);
//        segmentCount = pSegments.length;
//        // 开始处理，由于上面已经分割为多个字符串，且有一定的规律，即普通字符串和带星字符串间隔出现
//        // 因此普通字符串匹配结束后，要先匹配下一个普通字符串，再匹配中间的带星字符串
//        int sPos = 0; // s字符串的游标
//        for(int i=0; i<segmentCount && sPos<s.length(); i++){
//            String subP = pSegments[i];
//            if(!subP.contains("*")){
//                int nextPos = 0;
//                nextPos = matchSubString(s.substring(sPos), subP);
//                if(nextPos == -1){ // 未匹配到
//                    return false;
//                }
//                sPos = nextPos+subP.length();
//                continue;
//            }else{
//                int nextPos = s.length();
//                if((i+1) < segmentCount){
//                    // 先匹配下一个
//                    nextPos = matchSubString(s.substring(sPos), pSegments[i+1]);
//                    if(nextPos == -1){ // 未匹配到
//                        return false;
//                    }
//                    // 再匹配当前segment
//                    if(matchSubString(s.substring(sPos, nextPos+sPos), subP) == -1){ // 未匹配到
//                        return false;
//                    }
//                    sPos = nextPos+pSegments[i+1].length();
//                }else{
//                    if(matchSubString(s.substring(sPos, nextPos), subP) == -1){ // 未匹配到
//                        return false;
//                    }else{
//                        return true;
//                    }
//                }
//
//            }
//        }
//        if(sPos == s.length()){
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//
//    /**
//     *  对两个字符串进行匹配，判断s中是否包含p，条件如下：
//     *  1. 如果p中包含星号，则要求p要全匹配s， 匹配返回0，不匹配返回-1。
//     *  2. 如果p中不包含星号，只要求s中包含p， 匹配返回起始值，不匹配返回-1。
//     * @param s
//     * @param p
//     * @return
//     */
//    public int matchSubString(String s, String p){
//        int sPos = 0;
//        char dot = '.';
//        char star = '*';
//        char lastChar = ' ';
//        boolean lastCharIsDot = false;
//        if(!p.contains("*")){
//            return s.indexOf(p);
//        }
//        if(s.length() == 0){
//            return 0;
//        }
//        int pPos=0;
//        for(; pPos<p.length(); pPos++){
//            if(sPos == s.length()){
//                return -1;
//            }
//            if(pPos>0){
//                lastChar = p.charAt(pPos-1);
//            }
//            // 当匹配到"."后，更新标志位
//            if(dot == p.charAt(pPos)){
//                lastCharIsDot = true;
//                sPos++;
//                continue;
//            }
//
//            if(star == p.charAt(pPos)){
//                // 如果上一次字符是"",本次字符是"*", 表示p会匹配整个s
//                if(lastCharIsDot){
//                    return s.length()-1;
//                }else{
//                    // '*' 匹配零个或多个前面的那一个元素
//                    while(sPos < s.length() && lastChar == s.charAt(sPos) && (s.length()-sPos)>=(p.length()-pPos)){
//                        sPos++;
//                    }
//                    continue;
//                }
//            }else if(p.charAt(pPos) == s.charAt(sPos)){
//                sPos++;
//                continue;
//            }else{
//                // 当不匹配时，需要检查下一个字符是否是"*"，如果是则可以跳过当前字符和下一个"*",
//                // 如果不是，则直接返回false
//                if ((pPos+1)<p.length() && star == p.charAt(pPos+1)) {
//                    pPos++;
//                }else{
//                    return -1;
//                }
//            }
//        }
//        if(sPos == s.length() && pPos == p.length()){
//            return 0;
//        }else {
//            return -1;
//        }
//    }
//
//    /**
//     *  对输入字符串进行分割，按".*"和"字符*"作为分隔字符串
//     * @param p
//     * @return
//     */
//    public String[] parseP(String p){
//        int segmentCount = 0;
//        char star = '*';
//        String[] pSegments = new String[p.length()];
//        int lastPos = -1;
//        boolean lastSegmentHasChar = false;
//        for(int i=1; i<=p.length(); i++){
//            if(i == p.length()){
//                if(lastPos < i){
//                    pSegments[segmentCount++] = p.substring(lastPos>-1? lastPos:0, i);
//                }
//                break;
//            }
//            if(p.charAt(i) == star){
//                if(i>lastPos+1){
//                    if(i>1) {
//                        pSegments[segmentCount++] = p.substring(lastPos > -1 ? lastPos : 0, i - 1);
//                    }
//                    pSegments[segmentCount++] = p.substring(i-1, i+1);
//                }else{
//                    if(lastSegmentHasChar){
//                        pSegments[segmentCount-1] = pSegments[segmentCount-1]+p.substring(lastPos, i+1);
//                    }else {
//                        pSegments[segmentCount++] = p.substring(lastPos, i + 1);
//                    }
//                }
//                lastSegmentHasChar = true;
//                lastPos = i+1;
//            }
//        }
//        String[] newSegments = new String[segmentCount];
//        for(int i=0; i<segmentCount; i++){
//            newSegments[i] = pSegments[i];
//        }
//        return newSegments;
//    }
}
