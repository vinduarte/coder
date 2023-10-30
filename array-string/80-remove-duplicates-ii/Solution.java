class Solution {
    public int removeDuplicates(int[] nums) {
        int insertionPos;
        int currentPos;
        final int MAX_REPETITION = 2;
        int currentRepetition;

        insertionPos = currentPos = currentRepetition = 1;

        for (; currentPos < nums.length; currentPos++) {
            if (nums[currentPos] == nums[currentPos - 1]) {
                currentRepetition++;
                if (currentRepetition <= MAX_REPETITION) {
                    nums[insertionPos] = nums[currentPos];
                    insertionPos++;
                }
            } else {
                currentRepetition = 1;
                nums[insertionPos] = nums[currentPos];
                insertionPos++;
            }                        
        }

        return insertionPos;
    }
}