package impl;
import java.math.BigInteger;


public class APCalc {




	private static BigInteger b;
	private static BigInteger modulus;
	private static BigInteger r1;
	private static BigInteger x1;
	private static BigInteger x2;
	private static BigInteger l1;
	
	
	public static BigInteger calculate_mminv(BigInteger p1, BigInteger p2){
		long starttime = System.currentTimeMillis();
		System.out.println("using BI");
		b = p1;
		modulus = p2;
		r1 = BigInteger.TWO;//(long)Math.log10(Math.max(p1, p2));
		BigInteger result;
		
		if(p2 == BigInteger.ONE) {
			throw new IllegalArgumentException("mod=1");
		}
		
		result = function(b,modulus);
		
		while(result.max(BigInteger.ZERO) == BigInteger.ZERO) {
			result.add(p2);
		}
		
		long endtime = System.currentTimeMillis();
		System.out.println("time elapsed: "+(endtime-starttime));
		return result;
	}
	
	private static BigInteger function(BigInteger b, BigInteger m){
		
		BigInteger y_1 = (b.mod(m)).subtract(BigInteger.ONE);
		BigInteger y_2 = BigInteger.ONE;
		BigInteger y = ((BigInteger.TWO.multiply(b)).mod(m)).subtract(BigInteger.ONE);
		BigInteger i;
		BigInteger j;

		
		for(BigInteger sb = BigInteger.ONE; sb.min(((b.max(m)).divide(BigInteger.TWO)).add(BigInteger.ONE)) == sb; sb = sb.add(BigInteger.ONE)) {
			if(((b.multiply(sb)).mod(m) == BigInteger.ZERO) && sb.mod(m) != BigInteger.ZERO) {
				throw new IllegalArgumentException("gcd(b,m)!=1");
			}
		}
		


		for(i = new BigInteger("3"); y.subtract(y_1).abs().max(r1) == r1; i = i.add(BigInteger.ONE)) { // &&  y != -1
			y = ((i.multiply(b)).mod(m)).subtract(BigInteger.ONE);
		}
		x1 = i.subtract(BigInteger.TWO);
		l1 = y.subtract(y_1);
		
		for(j = new BigInteger("1"); y_2 != BigInteger.ZERO; j = j.add(BigInteger.ONE)) {
			y_2 = (((b.multiply(j)).mod(m)).subtract(BigInteger.ONE)).remainder(l1);
		}
		
		x2 = j.subtract(BigInteger.ONE);
		
		
		
		return x2.subtract(
					(x1.multiply(
							(
								(b.multiply(x2).subtract(BigInteger.ONE))
							.mod(m))
						    .divide(l1)
					)
				   ));
		
		
	}
	


	
}
