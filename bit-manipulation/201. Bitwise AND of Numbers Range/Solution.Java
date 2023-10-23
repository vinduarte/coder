class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        /*
        Idea: 
        Find the longest sequence of most significant bits that are equal 

        left = 5
        right = 7

        0101
        0111
        ----
        0100 -> longest equal sequence in the most significant bits (01)


        left =  000011101001000...
        right = 000011101110101...
        result= 000011101000000... -> longest equal sequence in the most significant bits (000011101)
        */

        int msb = 30; // most significant bit of 32-bit number, excluding the sign bit.
        int aux = 0;

        boolean msbLeft = getBitOfPos(left, msb);
        boolean msbRight = getBitOfPos(right, msb);


        while (msb > -1) {
            if (msbLeft && msbRight) {
                aux += 1 << msb;
            } else if (msbLeft != msbRight) {
                break;
            }

            msb--;
            msbLeft = getBitOfPos(left, msb);
            msbRight = getBitOfPos(right, msb);
        }

        return aux;
    }

    private boolean getBitOfPos(int n, int i) {
        int mask = 1 << i;
        return (n & mask) != 0;
    }
}