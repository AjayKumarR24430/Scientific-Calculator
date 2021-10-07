import java.util.*;
class Multiply
{
 
// Node structure containing powerer
// and coefficient of variable
static class Node {
    int coeff, power;
    String var;
    String var_pow;
    Node next;
    HashMap<Character , Integer> Variables = new HashMap<Character, Integer>();
};
 
// Function add a new node at the end of list
static Node addnode(Node start, int coeff, String var)
{
    // Create a new node
    Node newnode = new Node();
    newnode.coeff = coeff;
    // newnode.power = power;
    // newnode.var = var;
    for(int i=0;i<var.length()-1;i++){
        // System.out.print(var.charAt(i));
        if(var.charAt(i)=='^')
            newnode.Variables.put(var.charAt(i-1), Integer.parseInt(String.valueOf(var.charAt(i+1))) );
    }
    newnode.next = null;
 
    // If linked list is empty
    if (start == null)
        return newnode;
 
    // If linked list has nodes
    Node ptr = start;
    while (ptr.next != null)
        ptr = ptr.next;
    ptr.next = newnode;
 
    return start;
}
 
// Function To Display The Linked list
static void printList( Node ptr)
{
    while (ptr.next != null) {
        // System.out.print( ptr.coeff + ptr.var+ "^" + ptr.power); 
        if(ptr.coeff !=0){
            System.out.print( ptr.coeff); 
            for (Map.Entry<Character, Integer> entry : ptr.Variables.entrySet()) {
                Character key = entry.getKey();
                Integer value = entry.getValue();
                System.out.print(key+ "^" +value);
            }
        }
        ptr = ptr.next;
        if(ptr.next!=null)
            System.out.print(" + ");
    }
    System.out.println("\n");
}
 
// Function to add coefficients of
// two elements having same powerer
static void removeDuplicates(Node start)
{
    Node ptr1, ptr2, dup;
    ptr1 = start;
 
    /* Pick elements one by one */
    while (ptr1 != null && ptr1.next != null) {
        ptr2 = ptr1;
 
        // Compare the picked element
        // with rest of the elements
        while (ptr2.next != null) {
 
            // If powerer of two elements are same
            if (ptr1.power == ptr2.next.power) {
 
                // Add their coefficients and put it in 1st element
                ptr1.coeff = ptr1.coeff + ptr2.next.coeff;
                dup = ptr2.next;
                ptr2.next = ptr2.next.next;
 
            }
            else
                ptr2 = ptr2.next;
        }
        ptr1 = ptr1.next;
    }
}
 
// Function two Multiply two polynomial Numbers
static Node multiply(Node poly1, Node poly2,
            Node poly3)
{
 
    // Create two pointer and store the
    // address of 1st and 2nd polynomials
    Node ptr1, ptr2;
    ptr1 = poly1;
    ptr2 = poly2;
    while (ptr1 != null) {
        while (ptr2 != null) {
            int coeff, power;
            String var="";
            int pow;
 
            // Multiply the coefficient of both
            // polynomials and store it in coeff
            coeff = ptr1.coeff * ptr2.coeff;
 
            // Add the power of both polynomials
            // and store it in power
            for (Map.Entry<Character, Integer> entry : ptr1.Variables.entrySet()) {
                Character key1 = entry.getKey();
                Integer value1 = entry.getValue();
                int v1 = value1.intValue();
                char c1 = (char)v1;
                String s1 = Character.toString(key1);
                for (Map.Entry<Character, Integer> entry1 : ptr1.Variables.entrySet()) {
                    Character key2 = entry1.getKey();
                    Integer value2 = entry1.getValue();
                    int v2 = value2.intValue();
                    char c2 = (char)(v2 + '0');
                    String s2 = Character.toString(key2);
                    if(Character.compare(key1,key2) ==0){
                        // System.out.print("yes");
                        pow = v1 + v2;
                        char p = (char)(pow + '0');
                        var += s1+ "^"+ String.valueOf(p);
                    }
                    else if(Character.compare(key1,key2) !=0 && var == ""){
                        // System.out.print("no and no same");
                        var += s1 + "^" + String.valueOf(c1) + s2 + "^" + String.valueOf(c2);
                        // var.concat(s1+"^");
                        // var.concat(Character.toString(c1));
                        // var.concat(s2+"^");
                        // var.concat(Character.toString(c2));
                    }
                    else{
                        // System.out.print("no");
                        var += s2 + "^" + String.valueOf(c2);
                        // var.concat(s2+"^");
                        // var.concat(Character.toString(c2));
                    }
                }
            }
            poly3 = addnode(poly3, coeff, var);
 
            // move the pointer of 2nd polynomial
            // two get its next term
            ptr2 = ptr2.next;
        }
 
        // Move the 2nd pointer to the
        // starting point of 2nd polynomial
        ptr2 = poly2;
 
        // move the pointer of 1st polynomial
        ptr1 = ptr1.next;
    }
 
    // this function will be invoke to add
    // the coefficient of the elements
    // having same powerer from the resultant linked list
    // removeDuplicates(poly3);
    return poly3;
}
 
// Driver Code
public static void main(String args[])
{
 
    Node poly1 = null, poly2 = null, poly3 = null;
    Scanner scan = new Scanner(System.in);
    int coef = 0;
    int ex=0; 
    String var = "";
    System.out.println("Enter Coefficient and exponents of first polynomial: ");
    do {
        System.out.print("Enter Coefficient or 0 to end program: ");
        coef = scan.nextInt();
        if(coef != 0){
            // System.out.print("Enter Exponent: ");
            // ex = scan.nextInt();
            System.out.print("Enter Variable: ");
            var = scan.next();
        }
        // poly1 = addnode(poly1, coef, ex, var);
        poly1 = addnode(poly1, coef, var);
    } while (coef != 0);

    System.out.println("Enter Coefficient and exponents of second polynomial: ");

    do {
        System.out.print("Enter Coefficient or 0 to end program: ");
        coef = scan.nextInt();
        if(coef != 0){
            // System.out.print("Enter Exponent: ");
            // ex = scan.nextInt();
            System.out.print("Enter Variable: ");
            var = scan.next();
        }
        // poly2 = addnode(poly2, coef, ex,var);
        poly2 = addnode(poly2, coef ,var);
    } while (coef != 0);
 
    // Displaying 1st polynomial
    System.out.print("1st Polynomial:- ");
    printList(poly1);
 
    // Displaying 2nd polynomial
    System.out.print("2nd Polynomial:- ");
    printList(poly2);
 
    // calling multiply function
    poly3 = multiply(poly1, poly2, poly3);
 
    // Displaying Resultant Polynomial
    System.out.print( "Resultant Polynomial:- ");
    printList(poly3);
}
 
}

