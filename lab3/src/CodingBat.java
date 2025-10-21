public class CodingBat {
    // sleepIN
     boolean sleepIn(boolean weekday, boolean vacation) {
        if (!weekday || vacation) {
            return true;
        }
        return false;
    }

    // in 1020
    public boolean in1020(int a, int b) {
        return (a>=10 && a<=20) || (b>=10 && b<=20);
    }

    // sum3
    public int sum3(int[] nums) {
        return nums[0] + nums[1] + nums[2];
    }

    // theEnd
    public String theEnd(String str, boolean front) {
        if (front) {
            return str.substring(0, 1);
        } else {
            return str.substring(str.length() - 1);
        }
    }


    public static void main(String[] args) {
         CodingBat newObject = new CodingBat();

         //sleepIn result
         boolean result1 = newObject.sleepIn(true, true);
         boolean result2 = newObject.sleepIn(true, false);
         boolean result3 = newObject.sleepIn(false, true);
         boolean result4 = newObject.sleepIn(false, false);

         System.out.println(result1);
         System.out.println(result2);
         System.out.println(result3);
         System.out.println(result4);

         // in1020 result
        System.out.println("in1020: ");
        boolean in1020result1 = newObject.in1020(12, 233);
        System.out.println(in1020result1);

        // sum3 result
        System.out.println("sum3: ");
        int sum3result = newObject.sum3(new int[]{12, 14, 2}); // 28
        System.out.println(sum3result);

        // theEnd
        System.out.println("theEnd: ");
        String theEnd_result1 = newObject.theEnd("theEnd", true);
        System.out.println(theEnd_result1);
        String theEnd_result2 = newObject.theEnd("theEnd", false);
        System.out.println(theEnd_result2);

    }
}

// Testy jednostkowe JUnit4
// Po co robic testy jednostkowe:
// Testy dotyczą  jednej klasy - łatwo je pisac, są autoamtyczne, testy regresyjne ( czy system się nie pogorszył)
// czasami jak się dopisuje funkcje to cały program może się popsuć - do tego służą do wykrywania takich błedów
// JUnit4 dostarcza assert'y - umozliwjaą sprawdzanie metod w kalsie
