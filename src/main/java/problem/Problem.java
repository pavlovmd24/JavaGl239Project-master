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
    private ArrayList<Triangle> resultTriangles;
    private ArrayList<Point> resultPoints;

    /**
     * Конструктор класса задачи
     */
    public Problem() {
        points = new ArrayList<>();
        triangles = new ArrayList<>();
        resultTriangles = new ArrayList<>();
        resultPoints = new ArrayList<>();
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

        resultTriangles.clear();
        resultPoints.clear();
        // перебираем пары точек
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                for (int k = j + 1; k < points.size(); k++) {
                    Point p1 = points.get(i);
                    Point p2 = points.get(j);
                    Point p3 = points.get(k);
                    Triangle t = new Triangle(p1, p2, p3);
                    if (t.regular()) {
                        resultTriangles.add(t);
                        resultPoints.add(p1);
                        resultPoints.add(p2);
                        resultPoints.add(p3);
                    }
                }
            }
        }
    }

    /**
     * Загрузить задачу из файла
     */
    public void loadFromFile() {
        resultTriangles.clear();
        resultPoints.clear();
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
        }
    }

    /**
     * Очистить задачу
     */
    public void clear() {
        points.clear();
        triangles.clear();
        resultTriangles.clear();
        resultPoints.clear();
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
        for (Triangle triangle : resultTriangles) {
            triangle.render(gl);
        }
        gl.glColor3d(0.3, 0.1, 0.5);
        for (Point point : resultPoints) {
            point.render(gl);
        }
    }
}
