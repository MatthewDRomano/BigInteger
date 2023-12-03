import java.util.LinkedList;
import java.util.Iterator;

public class BigInteger {
    private LinkedList<Byte> digitList;

    public BigInteger(Object input) { //string / Int / long (NO DECIMAL)
        digitList = new LinkedList<Byte>();

        char[] inputString = input.toString().toCharArray();

        for (int i = 0;  i < inputString.length; i++) 
            digitList.addLast((byte)(inputString[i] -'0')); //int to signed value? 
    }
    
    public BigInteger add(BigInteger adder) {
        BigInteger smaller = (adder.digitList.size() <= this.digitList.size()) ? adder : this;
        BigInteger larger =  (adder.digitList.size() > this.digitList.size()) ? adder : this;

        String sum = "";
        int carry = 0;

        Iterator<Byte> iterLarge = (larger.digitList).descendingIterator();
        Iterator<Byte> iterSmall = (smaller.digitList).descendingIterator();

        for (int i = larger.digitList.size()-1; i >= 0; i--) {
            int dig1 = 0, dig2 = 0;
            if (iterSmall.hasNext())
                dig1 = iterSmall.next() & 0xff; 
                
            if (iterLarge.hasNext())
                dig2 = iterLarge.next() & 0xff;
      
            else {
                sum = carry + sum;
                break;
            }

            sum = sum + (dig1 + dig2 + carry)%10;
            carry = (dig1 + dig2 + carry) / 10;
        }
        return new BigInteger(sum);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < digitList.size(); i++)
            str = (digitList.get(i) & 0xff) + str;
        return str;
    }
}