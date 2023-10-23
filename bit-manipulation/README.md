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
- x ^ y ^ y = x

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