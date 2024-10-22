public class A_Bit_manipulation {
    public static void main(String[] args) {
        System.out.println();
    }
    public static int getBit(int a, int pos){
        int bitmask = 1 << pos;
        if((a & bitmask) == 0)
            return 0;
        else
            return 1;
    }


    public static int setBit(int a, int n){
        // let assume a = 10 which means in binary 1010  we want 1110
//        here the n is the which bit to set as 1
        int z = 1;
        z = z << n;
        return z | a;
    }
    public static int clearBit(int a, int n){
        int z = 1;
        z = z << n;
        return ~z & a;
    }
    public static int ones_compliment(int a){
        return ~a;
    }


    public static String even_r_odd(int a){
        if((a & 1) == 1)
            return "odd";
        else
            return "even";
    }


    public static int updateIthBit(int n,int i, int newBit){
        if(newBit == 0)
            return clearBit(n,i);
        else
            return setBit(n,i);
    }


    public static int clearLastIBits(int i, int pos){
        int a = ~0 << pos;
        return i & a;
    }


    public static int clearRangeofBits(int n, int i , int j){
        int a = ((~0) << (j + 1));
        // here the value of a makes not to change the right side of range (before the j val)
        // the b is used for the not to change the values after the i
        int b = (1 << i) - 1;
        return (n & (a | b));
    }


    public static boolean isPowerOf2(int n){
        return (n & (n - 1)) == 0;
    }


    public static int numberOfBits(int n){
        return (int)(Math.log(n) / Math.log(2) + 1);
    }


    public static int numberOfSetBits(int n){
        int count = 0;
        while(n > 0){
            if((n & 1) == 1)
                count++;
            n = n >> 1;
        }
        return count;
    }
    public static int powOfNumber(int base, int power){
        int val = 1;
        while(power > 0){
            if((power & 1) == 1){
                val = val * base;
            }
            base = base * base;
            power = power >> 1;
        }
        return val;
    }
    public static void swapNumbersWithoutExtraVar(int a, int b){
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a is "  + a + " b is "  + b);
    }
    // without bit manipulation
    public static void swap(int a, int b){
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a is " + a  +" b is " + b);
    }
    public static void toLower(){
        for(char ch = 'A'; ch <= 'Z'; ch++){
            System.out.print((char)(ch | ' '));
        }
    }

}
