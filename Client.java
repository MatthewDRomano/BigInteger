public class Client {
    public static void main(String[] args) {
        BigInteger one = new BigInteger("123");
        BigInteger two = new BigInteger("345");

        //BigInteger mistake = new BigInteger("bb3-j");

        BigInteger sum = one.add(two);

        System.out.println(sum);
    }
}
