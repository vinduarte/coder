class Solution {
    /*
    Applied rule: x ^ y ^ y = x ^ 0 = x
    [A, B, C, B, C]

    B ^ C ^ B ^ C = B ^ B ^ C ^ C = 0 ^ 0 = 0
    A ^ 0 = A

    So do a sequence of XORs. 
    If you do that, every repeated x will be "cancelled"
    by the other occurence of x. The only number that will
    remain is the one that's not repeated.
    */
    public int singleNumber(int[] nums) {
        int aux = nums[0];

        for (int i = 1; i < nums.length; i++) {
            aux = aux ^ nums[i];
        }

        return aux;
    }
}