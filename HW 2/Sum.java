public class Sum {
    public Sum() {
    }

    public static void main(String[] var0) {
        int var1 = 0;

        for(int var2 = 0; var2 < var0.length; ++var2) {
            byte var3 = 1;
            int var4 = 0;

            for(int var5 = 0; var5 < var0[var2].length(); ++var5) {
                if (var0[var2].charAt(var5) == '+') {
                    var3 = 1;
                } else if (var0[var2].charAt(var5) == '-') {
                    var3 = -1;
                } else if (Character.isDigit(var0[var2].charAt(var5))) {
                    var4 *= 10;
                    var4 += var0[var2].charAt(var5) - 48;
                } else {
                    var1 += var4 * var3;
                    var4 = 0;
                    var3 = 1;
                }
            }

            var1 += var4 * var3;
        }

        System.out.println(var1);
    }
}
