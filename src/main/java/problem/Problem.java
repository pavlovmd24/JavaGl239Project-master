package problem;

import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс задачи
 */
public class Problem {
    /**
     * текст задачи
     */
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "Дано множество точек на плоскости.\n" +
            "Определить среди них множество точек\n" +
            "наибольшего размера такое, что каждая точка этого множества является вершиной\n" +
            "равностороннего треугольника, вершины которого принадлежат этому множеству.";

    /**
     * заголовок окна
     */
    public static final String PROBLEM_CAPTION = "Итоговый проект ученика 10-1 Павлова Михаила";

    /**
     * путь к файлу
     */
    private static final String FILE_NAME = "points.txt";

    /**
     * список точек
     */
    private ArrayList<Point> points;
    private ArrayList<Triangle> triangles;

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        points = new ArrayList<>();
        triangles = new ArrayList<>();
    }

    /**
     * Добавить точку
     *
     * @param x координата X точки
     * @param y координата Y точки
     */
    public void addPoint(double x, double y) {
        Point point = new Point(x, y);
        points.add(point);
    }

    /**
     * Решить задачу
     */
    public void solve() {
        triangles.clear();
        // перебираем пары точек
        int i=0;
        int j=0;
        int q=0;
        int[] mas = new int[10000];
        for(Point p1 : points ) {
            for (Point p2 : points) {
                if(j>i) {
                    for (Point p3 : points) {
                        if(q>j) {
                            Triangle t = new Triangle(p1, p2, p3);
                            if (t.regular()) {
                                triangles.add(t);
                            }
                            if(mas[i]==0){
                                mas[i]++;
                                points.add(p1);
                            }
                            if(mas[j]==0){
                                mas[j]++;
                                points.add(p2);
                            }
                            if(mas[q]==0){
                                mas[q]++;
                                points.add(p3);
                            }
                        }
                        q++;
                    }
                }
                j++;
            }
            i++;
        }
    }

    /**
     * Загрузить задачу из файла
     */
    public void loadFromFile() {
        points.clear();
        try {
            File file = new File(FILE_NAME);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                sc.nextLine();
                Point point = new Point(x, y);
                points.add(point);
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
    }

    /**
     * Сохранить задачу в файл
     */
    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME));
            for (Point point : points) {
                out.printf("%.2f %.2f\n", point.x, point.y);
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    /**
     * Добавить заданное число случайных точек
     *
     * @param n кол-во точек
     */
    public void addRandomPoints(int n) {
        for (int i = 0; i < n; i++) {
            Point p = Point.getRandomPoint();
            points.add(p);
            Triangle r = Triangle.getRandomTriangle();
            triangles.add(r);
        }
    }

    /**
     * Очистить задачу
     */
    public void clear() {
        points.clear();
        triangles.clear();
    }

    /**
     * Нарисовать задачу
     *
     * @param gl переменная OpenGL для рисования
     */
    public void render(GL2 gl) {
        gl.glColor3d(0.5, 0.7, 0.1);
        for (Point point : points) {
            point.render(gl);
        }
        gl.glColor3d(0.3, 0.1, 0.5);
        for (Triangle triangle : triangles) {
            triangle.render(gl);
        }
//      Triangle t = Triangle.getRandomTriangle();
//      t.render(gl);
    }
}
