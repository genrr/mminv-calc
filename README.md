# mminv-calc
Tool to calculate the Modular Multiplicative Inverse geometrically.

Using graphical method to compute roots for equation f(x) = (b*floor(x) mod m) - 1 (the inverses modulo m): by solving one linear equation, which is computed from the properties of the graph of f(x), no euclidean algorithm needed.

By measuring points of the function f(x), one can draw multiple perpendicular lines, which cross some subset of points of the f(x). Then if we find a point, that 
is divisible by the slope/rate of change of the line, then it means that
following along that line, there will be a root of the function. That root is 
the one of the inverses of b mod m. 

Option is given to choose between long arithmetic and BigInteger arbitrary precision arithmetic, the latter being suitable for any number and the former requiring
at least max(b,m)*b to be less than 2^63-1.

# The Algorithm


input: integers b, m
output: inverse of b modulo m

I. 	compute function f(x) = (bx mod m) -1 on x = 1 and x = 2:

II. 	computing the possible line, which crosses points of f(x): 
	iterate f(x) starting from 3, until abs(y - y1) < (some small constant, 	initially 12, signifying the slope of the line)


III.  setup more variables for steps 4 and 5:
	x1 = x;
   	l1 = y - y1;
   
IV. 	iterate f(x) mod l1 starting from 1, until y2 = 0, trying to find
	an x, such that the change of y coordinates(l_1) divides f(x),
	or how far along the line should one go before changing direction?
	

V. 	j - 1 is the multiplier or "distance to the root" computed in the last step, 
	x1 is the change along x axis, 
	l1 is the change along y axis, 
	x2 = j-1;
	
  	return x2 - x1(((b*x2-1) % m)/l1);
   


		 
		


# Running times

Benchmark on i5-4460 ~ 3.2 Ghz using long arithmetic:

| b | m | time(ms)|
|----|----|-------------|
| 71 | 105 | 0 |
| 65535 | 4571 | 4 |
| 2450981 | 345096  | 22 |
| 2459241 | 533518  | 19 |
| 4581059 | 7702134 | 61 |
| 9945821 | 1172055 | 51 |
| 22882211 | 34509113 | 226 |
| 37237941 | 99831233 | 499 |
| 172379411 | 298312355 | 2173 |
| 372328494 | 992831113 | 6534 |
| 245987451 | 117924382 | 1339 |
| 2370100341 | 1367133907 | 13117 |


