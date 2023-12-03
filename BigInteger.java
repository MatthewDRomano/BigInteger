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

    public BigInteger multiply(BigInteger mult) {
        BigInteger smaller = (mult.digitList.size() <= this.digitList.size()) ? mult : this;
        BigInteger larger = (mult.digitList.size() > this.digitList.size()) ? mult : this;

        LinkedList<String> sumRows = new LinkedList<>();
        int carry = 0;

        Iterator<Byte> iterLarge;
        Iterator<Byte> iterSmall = (smaller.digitList).descendingIterator();
        
        for (int i = 0; i < smaller.digitList.size(); i++) {
            iterLarge = (larger.digitList).descendingIterator();
            int dig1 = iterSmall.next() & 0xff;
            String sum  = "";
            for (int a = 0; a < i; a++) sum += "0";
            for (int q = larger.digitList.size()-1; q >= 0; q--) {
                int dig2 = iterLarge.next() & 0xff;
                sum = ((dig1 * dig2 + carry) % 10) + sum;
                carry = (dig1 * dig2 + carry) / 10;
            }

            System.out.println(Integer.parseInt(sum));
            
            sumRows.add(sum);
        }

        BigInteger ans = new BigInteger(sumRows.get(0));
        for (int z = 1; z < sumRows.size(); z++) { //sumation doesnt work properly
            ans = ans.add(new BigInteger(sumRows.get(z)));
        }

        return ans;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < digitList.size(); i++)
            str = (digitList.get(i) & 0xff) + str;
        return str;
    }
}