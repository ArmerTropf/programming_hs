public class Funktion2 {
public static int[] strange(int[] i) {
if (i.length > 3)
return i;
else
return new int[4];
}
public static void store(int[] j) {
j[2] = 13;
}
public static void main(String[] args) {
int[] i1 = new int[4];
int[] i2 = new int[3];
i1[2] = 42;
i2[2] = 42;
store(strange(i1));
store(strange(i2));
System.out.println(i1.length);
System.out.println(i2.length);
System.out.println(i1[2]);
System.out.println(i2[2]);
}
}