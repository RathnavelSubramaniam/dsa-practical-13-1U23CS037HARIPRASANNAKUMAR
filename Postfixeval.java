import java.util.*;

class Stack {
    int size;
    int item[];
    int top;

    public Stack() {
        size = 100;
        item = new int[size];
        top = -1;
    }

    public void push(int ele) {
        if (top == (size - 1)) {
            System.out.println("Stack Overflow");
        } else {
            top++;
            item[top] = ele;
        }
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Invalid Postfix String: More operators than operands.");
            return -1;
        } else {
            int x = item[top];
            top--;
            return x;
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

class EvalPostfix {
    Stack st = new Stack();
    String postfix;

    public EvalPostfix(String str) {
        postfix = str;
    }

    public boolean isOperand(char ch) {
        return Character.isDigit(ch);
    }

    public int eval() {
        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);

            if (isOperand(ch)) {
                st.push(ch - '0'); // Convert char to integer
            } else {
                int val2 = st.pop();
                int val1 = st.pop();
                int result = 0;

                switch (ch) {
                    case '+':
                        result = val1 + val2;
                        break;
                    case '-':
                        result = val1 - val2;
                        break;
                    case '*':
                        result = val1 * val2;
                        break;
                    case '/':
                        if (val2 == 0) {
                            System.out.println("Division by zero error");
                            return -1;
                        }
                        result = val1 / val2;
                        break;
                    default:
                        System.out.println("Invalid operator");
                        return -1;
                }
                st.push(result);
            }
        }
        return st.pop();
    }
}

public class Postfixeval {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter postfix string:");
        String str = sc.nextLine();
        sc.close();

        EvalPostfix epf = new EvalPostfix(str);
        int res = epf.eval();
        if (res != -1)
            System.out.println("Result: " + res);
        else
            System.out.println("Invalid Postfix Expression");
    }
}