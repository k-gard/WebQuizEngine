package com.gardikiotis.WebQuizEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HelperClass {
  //  private static File file = new File("C:\\Users\\x0r\\Desktop\\log.txt");
    static int i=0;
    private static FileWriter writer;



    ArrayList<Quiz> quizzes;
    private static HelperClass instance = null;

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public static HelperClass getInstance() throws IOException {
        if (instance == null) {
            instance = new HelperClass();
        }
        return instance;
    }

    public HelperClass() throws IOException {

        this.quizzes = new ArrayList<>();
    }

    public void addQuiz(Quiz quiz){
        quizzes.add(quiz);
    }

    public Quiz getQuizById(int id){
        return quizzes.stream().filter(x -> x.getId() == id).findFirst().get();
    }


    public static void log(String s) throws IOException {
        i++;
        File file = new File("C:\\Users\\x0r\\Desktop\\log"+i+".txt");
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write("\n");
        writer.write(s);
        writer.close();
    }


}
