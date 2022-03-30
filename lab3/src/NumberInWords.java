import java.util.ArrayList;

public class NumberInWords {

    private int number;
    private final String SIGN = "minus";
    private final String HUNDRED = "sto";
    private final String ZERO = "zero";
    private final String[] DIGITS = {
        "",
        "jeden", 
        "dwa", 
        "trzy", 
        "cztery", 
        "pięć", 
        "sześć", 
        "siedem", 
        "osiem", 
        "dziewięć"
    };
    private final String[] TEENS = {
        "dziesięć",
        "jedenaście",
        "dwanaście",
        "trzynaście",
        "czternaście",
        "piętnaście",
        "szesnaście",
        "siedemnaście",
        "osiemnaście",
        "dziewętnaście"
    };
    private final String[] TENS = {
        "",
        "dziesięć",
        "dwadzieścia", 
        "trzydzieści", 
        "czterdzieści", 
        "pięćdziesiąt", 
        "sześćdziesiąt", 
        "siedemdziesiąt", 
        "osiemdziesiąt", 
        "dziewięćdziesiąt"
    };

    public NumberInWords(int number) {
        if(Math.abs(number) > 100)
            throw new IllegalArgumentException("Liczba musi być z zakresu -100 do 100");
        this.number = number;
    }

    @Override
    public String toString() {
        if(number == 0)
            return ZERO;

        ArrayList<String> wordedNumber = new ArrayList<>();

        int n = Math.abs(number);

        if(number < 0)
            wordedNumber.add(SIGN);

        if(n / 100 >= 1)
            wordedNumber.add(HUNDRED);

        int ten = n / 10 % 10;
        if(ten != 1) {
            wordedNumber.add(TENS[ten]);
            wordedNumber.add(DIGITS[n % 10]);
        } else {
            wordedNumber.add(TEENS[n % 10]);
        }

        for(int i = wordedNumber.size() - 1; i >= 0; i--)
            if(wordedNumber.get(i).equals(""))
                wordedNumber.remove(i);

        return String.join(" ", wordedNumber);
    }
    
}
