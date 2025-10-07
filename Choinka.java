public class ChristmasTree { 
	public static void main(String[] args) { 
		if (args.length == 0) {
			System.out.println("Podaj wartosc!");
			return;
		}

		int height = Integer.parseInt(args[0]);

		for (int i = 0; i < height; i++) {
			for (int k = 1; k < i; k++) {
			System.out.print("*");
			}
			System.out.println("");

		}
	} 
} 