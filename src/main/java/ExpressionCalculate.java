package main.java;

public class ExpressionCalculate {
    static int Prec(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    public String infixToPostfix(String exp)
    {

        String result = new String("");

        // initializing empty stack
        LinkedListStack<Character> stack = new LinkedListStack<>();

        for (int i = 0; i<exp.length(); ++i)
        {
            char c = exp.charAt(i);
            if (Character.isLetterOrDigit(c))
                result += c;
            else if (c == '(')
                stack.push(c);
            else if (c == ')')
            {
                while (!stack.isEmpty() &&
                        stack.val != '(')
                    result += stack.pop();

                stack.pop();
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty() && Prec(c)
                        <= Prec(stack.val)){

                    result += stack.pop();
                }
                stack.push(c);
            }

        }

        // pop all the operators from the stack
        while (!stack.isEmpty()){
            if(stack.val == '(')
                return "Invalid Expression";
            result += stack.pop();
        }
        return result;
    }
    public int calc(String str){
        LinkedListStack<Integer> stack=new LinkedListStack<>();
        for (int i = 0; i<str.length(); ++i)
        {
            char c=str.charAt(i);
            if(Character.isLetterOrDigit(c)){
                int x=c-'0';
                stack.push(x);
            }
            else{
                int b=stack.pop();
                int a=stack.pop();
                int s=0;
                switch (c){
                    case '+':s=a+b; break;

                    case '-':s=a-b;break;

                    case '^':s=a^b; break;

                    case '/':s=a/b; break;

                    case '*':s=a*b; break;
                }
                stack.push(s);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        ExpressionCalculate expressionCalculate=new ExpressionCalculate();
        System.out.println(expressionCalculate.infixToPostfix("5*8*(2+9)+(7-5+8-9*(5*5)+5)"));
        System.out.println(expressionCalculate.calc(expressionCalculate.infixToPostfix("5*8*(2+9)+(7-5+8-9*(5*5)+5)")));


    }
}
