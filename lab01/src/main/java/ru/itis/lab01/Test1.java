package ru.itis.lab01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EmptyStackException;
import java.util.Stack;
public class Test1 {
    public static void main(String[] args) {
        String str = read("transport.json");
        System.out.println();
        Stack stack = new Stack();
        int len = str.length();
        int i;
        boolean isGood = true;
        boolean isCount = false;
        char quotes = '"';
        int count = 0;
        int index = 0;
        for(i = 0; i < len; i++){
            char bracket = str.charAt(i);

            if(bracket == quotes){
                count += 1;
                index += 1;
            }
            else {
                index += 1;
            }
            if (count == 1 && count != 2){
                isCount = true;
            }
            if(count == 2){
                count = 0;
                isCount = false;
                continue;
            }
            if (isCount){
                continue;
            }


            if((bracket == '{') || (bracket == '[') || (bracket == '(')){
                stack.push(bracket);
            }

            if((bracket == '}') || (bracket == ']') || (bracket == ')')){
                try{
                    char openBracket = (char)stack.peek();
                    if (openBracket == '(' && bracket == ')'){
                        stack.pop();
                        continue;
                    }
                    if (openBracket == '[' && bracket == ']'){
                        stack.pop();
                        continue;

                    }
                    if (openBracket == '{' && bracket == '}'){
                        stack.pop();
                        continue;
                    }
                } catch(EmptyStackException e){
                    isGood = false;
                    System.out.println("Problem in - " + i);
                    index = i;
                }
            }


        }
        System.out.println(isGood && stack.empty() ? "JSON is valid":"JSON is invalid");

    }

    public static String read(String fileName) {
        String str = null;
        try {
            str = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            System.out.println("Ошибка ввода вывода");
            e.printStackTrace();
        }
        return str;
    }
}

