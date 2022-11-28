import java.util.Stack;
import java.util.Scanner;

public class DSProject 
{
	static int Prec(char ch)
	{
		switch (ch) {
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

	static boolean isOperator(char x)
	{
		switch (x) {
		case '+':
		case '-':
		case '/':
		case '*':
			return true;
		}
		return false;
	}
	static boolean isOperand(char x)
	{
    return (x >= 'a' && x <= 'z') ||(x >= 'A' && x <= 'Z');
	}

static String infixToPrefix(String infix)
{
    Stack<Character> operators = new Stack<Character>();
    Stack<String> operands = new Stack<String>();
 
    for (int i = 0; i < infix.length(); i++)
    {

        if (infix.charAt(i) == '(')
        {
            operators.push(infix.charAt(i));
        }
        else if (infix.charAt(i) == ')')
        {
            while (!operators.empty() && operators.peek() != '(')
                {
 
                String op1 = operands.peek();
                operands.pop();
 
                String op2 = operands.peek();
                operands.pop();
 
                char op = operators.peek();
                operators.pop();
 
                String tmp = op + op2 + op1;
                operands.push(tmp);
            }

            operators.pop();
        }
 

        else if (!isOperator(infix.charAt(i)))
        {
            operands.push(infix.charAt(i) + "");
        }


        else
        {
            while (!operators.empty() &&
                Prec(infix.charAt(i)) <= Prec(operators.peek()))
                {
 
                String op1 = operands.peek();
                operands.pop();
 
                String op2 = operands.peek();
                operands.pop();
 
                char op = operators.peek();
                operators.pop();
 
                String tmp = op + op2 + op1;
                operands.push(tmp);
            }
 
            operators.push(infix.charAt(i));
        }
    }

    while (!operators.empty())
    {
        String op1 = operands.peek();
        operands.pop();
 
        String op2 = operands.peek();
        operands.pop();
 
        char op = operators.peek();
        operators.pop();
 
        String tmp = op + op2 + op1;
        operands.push(tmp);
    }
    return operands.peek();
}

	static String infixToPostfix(String exp)
	{
		String result = new String("");

		Stack<Character> stack= new Stack<Character>();

		for (int i = 0; i < exp.length(); ++i) {
			char c = exp.charAt(i);

			if (Character.isLetterOrDigit(c))
				result += c;

			else if (c == '(')
				stack.push(c);

			else if (c == ')') {
				while (!stack.isEmpty()
					&& stack.peek() != '(') {
					result += stack.peek();
					stack.pop();
				}

				stack.pop();
			}
			else 
			{
				while (!stack.isEmpty()
					&& Prec(c) <= Prec(stack.peek())) {
					result += stack.peek();
					stack.pop();
				}
				stack.push(c);
			}
		}

		while (!stack.isEmpty()) {
			if (stack.peek() == '(')
				return "Invalid Expression";
			result += stack.peek();
			stack.pop();
		}
		
		return result;
	}

	static String preToPost(String pre_exp)
	{

		Stack<String> s = new Stack<String>();

		int length = pre_exp.length();

		for (int i = length - 1; i >= 0; i--)
		{
			if (isOperator(pre_exp.charAt(i)))
			{
				String op1 = s.peek();
				s.pop();
				String op2 = s.peek();
				s.pop();

				String temp = op1 + op2 + pre_exp.charAt(i);

				s.push(temp);
			}

			else {
				s.push(pre_exp.charAt(i) + "");
			}
		}

		return s.peek();
	}
	static String postToPre(String post_exp)
    {
        Stack<String> s = new Stack<String>();
 
        int length = post_exp.length();
 
        for (int i = 0; i < length; i++) {
 
            if (isOperator(post_exp.charAt(i))) {
 
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();
 
                String temp
                    = post_exp.charAt(i) + op2 + op1;
                s.push(temp);
            }
 
            else { 
                s.push(post_exp.charAt(i) + "");
            }
        }
 
        String ans = "";
        for (String i : s)
            ans += i;
        return ans;
    }
    static String PreToIn(String str)
{
    Stack<String> stack = new Stack<>();
     
    int l = str.length();
     
    for(int i = l - 1; i >= 0; i--)
    {
        char c = str.charAt(i);
        if (isOperator(c))
        {
            String op1 = stack.pop();
            String op2 = stack.pop();
             
            String temp = "(" + op1 + c + op2 + ")";
            stack.push(temp);
        }
        else
        {
             
            stack.push(c + "");
        }
    }
    return stack.pop();
}
static String PostToInfix(String exp)
{
    Stack<String> s = new Stack<String>();
 
    for (int i = 0; i < exp.length(); i++)
    {
        if (isOperand(exp.charAt(i)))
        {
        s.push(exp.charAt(i) + "");
        }
        else
        {
            String op1 = s.peek();
            s.pop();
            String op2 = s.peek();
            s.pop();
            s.push("(" + op2 + exp.charAt(i) +
                    op1 + ")");
        }
    }

    return s.peek();
}

	public static void main(String[]args)
{
    Scanner sc=new Scanner(System.in);
    String s;
    System.out.print("Enter the expression you would like to convert: \n\n\t\t");
    s=sc.next();
    int l = s.length();
    l = l-1;
    if(s.charAt(0)=='+' ||s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0)=='/' || 
    s.charAt(0)=='^' || s.charAt(0)=='%')
    {

        System.out.print("\nIt is a Prefix Expression\n");
        System.out.println("..............Enter your choice.............");
        System.out.println("\nConvert the expression in: \n 1. INFIX \n 2. POSTFIX\n");
        int ch = sc.nextInt();
        switch(ch)
        {
            case 1:    System.out.println("You have chosen INFIX....");
					System.out.println("\nINFIX expression :\t " + PreToIn(s)+"\n");
            break;
            case 2:    System.out.println("You have chosen POSTFIX....");
					System.out.println("\nPOSTFIX expression :\t " + preToPost(s)+"\n");
			break;
        }
    }  

    
    else if(s.charAt(l)=='+' ||s.charAt(l) == '-' || s.charAt(l) == '*' || s.charAt(l)=='/' || 
    s.charAt(l)=='^' || s.charAt(l)=='%')
    {
        System.out.println("\nIt is a Postfix Expression\n");
        System.out.println("..............Enter your choice.............");
        System.out.println("\nConvert the expression in: \n 1. INFIX \n 2. PREFIX\n");
        int ch = sc.nextInt();
        switch(ch)
        {
            case 1:    System.out.println("You have chosen INFIX....");
					System.out.println("\nINFIX expression :\t " + PostToInfix(s)+"\n");
            break;
            case 2:    System.out.println("You have chosen PREFIX....");
			        System.out.println("\nPREFIX expression :\t " + postToPre(s)+"\n");
			break;
        }
    }  


    else
    {
        System.out.println("\nIt is an Infix Expression\n");
        System.out.println("..............Enter your choice.............");
        System.out.println("\nConvert the expression in: \n 1. PREFIX \n 2. POSTFIX\n");
        int ch = sc.nextInt();
        switch(ch)
        {
            case 1:    System.out.println("You have chosen PREFIX....");
					System.out.println("\nPREFIX expression :\t " + infixToPrefix(s)+"\n");
            break;
            case 2:    System.out.println("You have chosen POSTFIX....");
					System.out.println("\nPOSTFIX expression :\t " + infixToPostfix(s)+"\n");
			break;
        }
    }  
    sc.close();
     
}
}