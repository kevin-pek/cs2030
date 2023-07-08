class RomanToInt {
    public static void main(String[] args) {
        int sum = 0, n = 0, prev = 0;
        for (int i = args[0].length() - 1; i >= 0; i--) {
            switch (args[0].charAt(i)) {
                case 'M':
                    n = 1000;
                    break;
                case 'D':
                    n = 500;
                    break;
                case 'C':
                    n = 100;
                    break;
                case 'L':
                    n = 50;
                    break;
                case 'X':
                    n = 10;
                    break;
                case 'V':
                    n = 5;
                    break;
                case 'I':
                    n = 1;
                    break;
            }
            if (n < prev) {
                sum += n;
            } else {
                sum -= n;
            }
            prev = n;
        }
        System.out.println(sum);
    }
}
