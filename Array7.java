public class Array7 {
public static void main(String[] args) {
int a = 34;
int b;
b = (a += 12) -3;
System.out.println(a);
System.out.println(b);
if (b > 1023 & (a %= 23) == 45)
System.out.println("juhu");
System.out.println(a);
}
}