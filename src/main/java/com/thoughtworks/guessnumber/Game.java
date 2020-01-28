package com.thoughtworks.guessnumber;

import java.util.*;

public class Game {
    public static final int INPUT_LENGTH = 4;

    private Integer[] answer;

    private List<Result> results;

    public Game(String answer) {
        this.answer = parseStringToArray(answer);
        results = new ArrayList<>();
    }

    public String check(String inputString) {
        Integer[] input = parseStringToArray(inputString);
        if (!checkParameters(input)) return "Wrong Input, input again";
        Result result = generateResult(inputString, input);
        results.add(result);
        return result.toPrintable();
    }

    private Result generateResult(String inputString, Integer[] input) {
        Result result = new Result();
        result.input = inputString;
        for (int i = 0; i < INPUT_LENGTH; i++) {
            if (Objects.equals(input[i], answer[i])) {
                result.A++;
                continue;
            }
            if (Arrays.asList(answer).contains(input[i])) {
                result.B++;
            }
        }
        return result;
    }

    private boolean checkParameters(Integer[] input) {
        return input.length == INPUT_LENGTH && new HashSet<>(Arrays.asList(input)).size() == INPUT_LENGTH;
    }

    public String generatePrompt() {
        StringBuilder prompt = new StringBuilder();
        for (Result r : results) {
            prompt.append(r.toString()).append("\n");
        }
        return prompt.toString();
    }

    private static Integer[] parseStringToArray(String answerString) {
        Integer[] answer = new Integer[answerString.length()];
        for (int i = 0; i < answerString.length(); i++) {
            answer[i] = (int) answerString.charAt(i);
        }
        return answer;
    }

    public static class Result {
        private String input;
        private int A = 0;
        private int B = 0;

        public String toPrintable() {
            return String.format("%dA%dB", A, B);
        }

        @Override
        public String toString() {
            return String.format("input=%s %s", input, toPrintable());
        }
    }

}
