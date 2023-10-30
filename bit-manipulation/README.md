# Bit manipulation


## Basic tricks
```
- x & 0s = 0
- x & 1s = x
- x & x = x
- x | 0s = x
- x | 1s = 1s
- x | x = x
- x ^ 0s = x
- x ^ 1s = ~x
- x ^ x = 0

- x * 2 = x << 1
- x * 4 = x << 2
- x * 8 = x << 3
...

- x ^ (~x) = 1s
- ~0 = 1s
- ~0 << 2 == 11111...00
- x is a power of 2 if x & (x - 1) == 0
- x is a power of 4 if it is a power of 2 and (x - 1) % 3 == 0 (x - 1 is multiple of 3)
- x ^ y ^ y = x ^ 0 = x

- to get negative of x: (~x) + 1. 
Example: 
5 = 000...0101
-5 will be: 111...1010 + 1: 111...1011
```

## Logical Right Shift

Shift the bits and put a 0 in the most significant bit, including the sign bit. If the number is negative, the result will be positive.

On an 8-bit integer:
```
-75 >>> 1 ==> 90
10110101 >>> 1 ==> 01011010
<1011010>1 >>> 1 ==> 0<1011010>
```

## Arithmetic Right Shift

Shift the bits and put a 0 in the most significant bit, excluding the sign bit. The result will always keep the sign bit. Equivalent to division by 2.

On an 8-bit integer:
```
-75 >> 1 ==> -38
10110101 >> 1 ==> 11011010
<1011010>1 >> 1 ==> 1<1011010>
```

Also, -75 / 2 = -38.

## Getting and setting bits

### Get bit
```
boolean getBitOfPos(int n, int i) {
    int mask = 1 << i;
    return (n & mask) != 0;
}

/*
n = 00101101
i = 3

mask => 1000 (1 followed by 3 zeros)
n & mask ==>
00101101
00001000
--------
00001000 (different from 0, so ith bit is 1)


n = 00101101
i = 1

mask => 10
n & mask ==>
00101101
00000010
--------
00000000 (equals to 0, so ith bit is 0)
*/
```

### Set bit
```
boolean setBitOfPos(int n, int i) {
    int mask = 1 << i;
    return n | mask;
}

/*
n = 00101101
i = 1

mask => 10
n & mask ==>
00101101
00000010
--------
00101111 (ith bit is set)


n = 00101101
i = 3

mask => 1000 (1 followed by 3 zeros)
n | mask ==>
00101101
00001000
--------
00101101 (ith bit is set, no difference in n in this case)
*/
``````

### Clear bit

Clear a single bit in a specified position.
```
boolean clearBitOfPos(int n, int i) {
    int mask = ~(1 << i);
    return n & mask;
}

/*
n = 00101101
i = 3

mask => 00001000 => 11110111
n & mask ==>
00101101
11110111
--------
00100101 (ith bit is set to zero)


n = 00101101
i = 1

mask => 00000010 => 11111101
n & mask ==> 
00101101
11111101
--------
00101101 (ith bit is set, no difference in this case)
*/
``````

Clear all bits from the most significant to an specified position (inclusive).
```
boolean clearBitFromMostSignificantToPos(int n, int i) {
    int mask = (1 << i) - 1;
    return n & mask;
}

/*
n = 00101101
i = 3

mask => 00001000 => 00000111
n & mask ==>
00101101
00000111
--------
00000101


n = 00101101
i = 1

mask => 00000010 => 00000001
n & mask ==> 
00101101
00000001
--------
00000001
*/
``````

Clear all bits from i through 0 (inclusive).
```
boolean clearBitFromPosToZero(int n, int i) {
    int mask = (-1 << (i + 1));
    return n & mask;
}

/*
n = 00101101
i = 3

mask => 11111111 => 11110000
n & mask ==>
00101101
11110000
--------
00100000


n = 00101101
i = 1

mask => 11111111 => 11111100
n & mask ==> 
00101101
11111100
--------
00101100
*/
``````

### Update bit

1. Clear the bit in the position.
2. Perform an OR with the desired bit shifted to the position.

```
boolean updateBitInPos(int n, int i, boolean bitIs1) {
    int value = bitIs1 ? 1 : 0;
    int mask = ~(1 << i);
    return (n & mask) | (value << i);
}

/*
n = 00101101
i = 3
value = 0

mask => 00001000 => 11110111
n & mask ==>
00101101
11110111
--------
00100101 (cleared the ith bit)
00000000 (value << i)
--------
00100101 (bit set to 0)


n = 00101101
i = 3
value = 1

mask => 00001000 => 11110111
n & mask ==>
00101101
11110111
--------
00100101 (cleared the ith bit)
00001000 (value << i)
--------
00101101 (bit set to 1)
*/
```

### Get number of 1's

This will basically look at the less significant bit at every call. Is it 1 (by comparing n & 1)? Then, sum + 1 and call the shifted n (to disconsider the current bit and move to the next).
```
int getNumberOf1sBits(int n) {
    return n == 0 ? 0 : (n & 1) + getNumberOf1sBits(n >>> 1);
}
```
