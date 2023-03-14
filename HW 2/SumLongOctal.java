public class SumLongOctal{
    public static void main(String[] args) {
        long summ = 0L;
        for (int i = 0; i < args.length; i++) {
            int beg = -1;
            for (int j = 0; j <= args[i].length(); j++) {
                if (j == args[i].length() || Character.isWhitespace(args[i].charAt(j))) {
                    if (beg != -1) {
                        summ += Long.parseLong(args[i].substring(beg, j));
                        beg = -1;
                    }
                } else if (Character.toLowerCase(args[i].charAt(j)) == 'o') {
                    summ += Long.parseLong(args[i].substring(beg, j), 8);
                    beg = -1;
                } else if (beg == -1) {
                    beg = j;
                }                    
            }
        }
        System.out.println(summ);
    }
}
