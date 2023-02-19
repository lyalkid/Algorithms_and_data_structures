package ru.itis.lab01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EmptyStackException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String str = read("transport.json");
        System.out.println();
        Stack stack = new Stack();
        int len = str.length();
        int i;
        boolean isGood = true;
        for(i = 0; i < len; i++){
            char bracket = str.charAt(i);
            if((bracket == '{') || (bracket == '[') || (bracket == '(')){
                stack.push(bracket);
            }

            if((bracket == '}') || (bracket == ']') || (bracket == ')')){
                try{
                char openBracket = (char)stack.pop();
                    if (openBracket == '(' && bracket == ')'){
                        continue;
                    }
                    if (openBracket == '[' && bracket == ']'){
                        continue;
                    }
                    if (openBracket == '{' && bracket == '}'){
                        continue;
                    }
                } catch(EmptyStackException e){
                    isGood = false;
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
