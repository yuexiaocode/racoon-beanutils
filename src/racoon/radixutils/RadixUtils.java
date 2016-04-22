package racoon.radixutils;

public class RadixUtils {
	
	/**
	 * �ж�һ����������λÿһλ�����ڵ�����һ�����Ķ����Ƶ�ÿһλ
	 * @param int1 ����1
	 * @param int2 ����2
	 * @return true|false
	 */
	public static boolean allBitsGteq(int int1,int int2){
		
		for(int i=31 ;i >= 0 ;i --){
			byte bit1 = (byte) (((1<<i)&int1)>>i);
			byte bit2 = (byte) (((1<<i)&int2)>>i);
			if(bit1 - bit2 <0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		
		System.out.println(allBitsGteq(21,5));
		
	}
}

