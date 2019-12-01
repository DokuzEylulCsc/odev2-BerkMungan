package bbg2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("Seçenekler:");
		System.out.println("1.Onluktan Roma Rakamýna(Lütfen Onluk sistemde bir sayý giriniz.)");
		System.out.println("2.Roma Rakamýndan Onluða(Lütfen Roma rakamlarýyla bir sayý giriniz.)");

		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();

		try {
			if (choice == 1) { // Convert int to roman
				System.out.print("Ondalýk sistemde bir sayý giriniz: ");
				int givenVal = in.nextInt();
				if(givenVal > 3999 || givenVal < 1) {
					System.out.println("Girdiðiniz sayý 1 ile 3999 arasýnda olmalýdýr.");
				}else {
					convertIntToRoman(givenVal);
				}
			} else if (choice == 2) { // Convert roman to int
				System.out.print("Roma rakamlarýyla bir sayý giriniz: ");
				String givenVal = in.next();
				int finalResult = convertRomanToInt(givenVal);
				if(finalResult == -1)
					System.out.println("Girdiðiniz roma rakamý I ile MMMCMXCIX arasýnda olmalýdýr.");
				else{
					System.out.println("Sonuç: " + finalResult);
				}
			} 
		}catch(InputMismatchException e) {
			System.exit(0);
		}


		in.close();
		System.exit(0);
	}
	public static void convertIntToRoman(int number) {
		char c[] = new char[10001]; 
		int i = 0;         
		if (number <= 0) { 
			System.out.printf("Lütfen 1 ile 3999 arasý bir sayý giriniz."); 
			return; 
		}         
		while (number != 0) {             
			if (number >= 1000) {                
				i = digit('M', number / 1000, i, c); 
				number = number % 1000; 
			}
			else if (number >= 500) {                 
				if (number < 900) {                   
					i = digit('D', number / 500, i, c); 
					number = number % 500; 
				}
				else {                     
					i = sub_digit('C', 'M', i, c); 
					number = number % 100; 
				} 
			}
			else if (number >= 100) { 
				if (number < 400) { 
					i = digit('C', number / 100, i, c); 
					number = number % 100; 
				} 
				else { 
					i = sub_digit('C', 'D', i, c); 
					number = number % 100; 
				} 
			}
			else if (number >= 50) {                 
				if (number < 90) { 
					i = digit('L', number / 50, i, c); 
					number = number % 50; 
				}
				else { 
					i = sub_digit('X', 'C', i, c); 
					number = number % 10; 
				} 
			}
			else if (number >= 10) {               
				if (number < 40) { 
					i = digit('X', number / 10, i, c); 
					number = number % 10; 
				}
				else { 
					i = sub_digit('X', 'L', i, c); 
					number = number % 10; 
				} 
			}
			else if (number >= 5) { 
				if (number < 9) { 
					i = digit('V', number / 5, i, c); 
					number = number % 5; 
				}
				else { 
					i = sub_digit('I', 'X', i, c); 
					number = 0; 
				} 
			} 
			else if (number >= 1) { 
				if (number < 4) { 
					i = digit('I', number, i, c); 
					number = 0; 
				}
				else { 
					i = sub_digit('I', 'V', i, c); 
					number = 0; 
				} 
			} 
		}

		System.out.printf("Roma rakamýnýz: "); 
		for (int j = 0; j < i; j++) { 
			System.out.printf("%c", c[j]); 
		} 
	}

	static int sub_digit(char num1, char num2, int i, char[] c) {
		c[i++] = num1;
		c[i++] = num2;
		return i;
	}
	static int digit(char ch, int n, int i, char[] c) {
		for (int j = 0; j < n; j++) {
			c[i++] = ch;
		}
		return i;
	}
	public static int convertRomanToInt(String str) {
		int res = 0;

		for (int i = 0; i < str.length(); i++) {			
			int s1 = value(str.charAt(i));			
			if (i + 1 < str.length()) {
				int s2 = value(str.charAt(i + 1));
				if (s1 >= s2) {					
					res = res + s1;
				} else {
					res = res + s2 - s1;
					i++; 
				}
			} else {
				res = res + s1;
				i++;
			}
		}
		if(res >= 3999) {
			return -1;
		}
		else{
			return res;
		}

	}
	static int value(char r) {
		if (r == 'I')
			return 1;
		if (r == 'V')
			return 5;
		if (r == 'X')
			return 10;
		if (r == 'L')
			return 50;
		if (r == 'C')
			return 100;
		if (r == 'D')
			return 500;
		if (r == 'M')
			return 1000;
		return -1;
	}
}
