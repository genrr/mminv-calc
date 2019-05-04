package impl;


public class Calc {
	private static long b;
	private static long modulus;
	private static long r1;
	private static long x1;
	private static long x2;
	private static long l1;
	
	
	public static long calculate_mminv(long p1, long p2){
		long starttime = System.currentTimeMillis();
		
		b = p1;
		modulus = p2;
		r1 = 2;//(long)Math.log10(Math.max(p1, p2));
		long result;
		
		if(p2 == 1) {
			throw new IllegalArgumentException("mod=1");
		}
		
		result = function(b,modulus);
		
		while(result < 0) {
			result += p2;
		}
		
		long endtime = System.currentTimeMillis();
		System.out.println("time elapsed: "+(endtime-starttime));
		return result;
	}
	
	private static long function(long b, long m){
		
		long y_1 = (b % m) - 1;
		long y_2 = 1;
		long y = ((2*b) % m) - 1;
		long i;
		long j;

		//multiplyExact() throws exception if b*s overflows a long 
		for(long s = 1; s<=Math.max(b, m)/2; s++) {
			if((b*s) % m == 0L && s % m != 0L) {
				throw new IllegalArgumentException("gcd(b,m)!=1");
			}
			Math.multiplyExact(b, s);
		}

		
		for(i = 3; Math.abs(y - y_1) > r1; i++) { // &&  y != -1
			y = ((b*i) % m) - 1;
		}
		x1 = i-2;
		l1 = y-y_1;
		
		for(j = 1; y_2 != 0; j++) {
			y_2 = Math.floorMod(((b*j % m)-1), l1);
		}
		
		x2 = j-1;
		
		return x2 - x1*(((b*x2-1) % m)/l1);
		

	}
	
}
