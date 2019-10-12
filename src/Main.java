import java.util.List;
import java.util.Scanner;

public class Main {

    private static TriangleTypeFactory triangleFactory = new TriangleTypeFactory();
    private static AreaCalculator calculator = new AreaCalculator();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Triangle triangle = new Triangle();

        System.out.println("Please enter the sides of your triangle:");
        System.out.println("All units will be in millimeters(for lengths) and degrees(for angles)!");

        do {
            System.out.println("Enter sideA: ");
            triangle.setSideA(scanner.nextInt());
        } while (triangle.getSideA() == null);

        do {
            System.out.println("Enter sideB: ");
            triangle.setSideB(scanner.nextInt());
        } while (triangle.getSideB() == null);

        do {
            System.out.println("Enter sideC: ");
            triangle.setSideC(scanner.nextInt());
        } while (triangle.getSideC() == null);

        if (triangle.getSideB() == 0 || triangle.getSideC() == 0 || triangle.getSideA() == 0) {
            System.out.println("This isn't a triangle");
        }

        if ((triangle.getSideA().intValue() == triangle.getSideB().intValue() && !triangle.getSideA().equals(triangle.getSideC())) ||
                (triangle.getSideA().intValue() == triangle.getSideC().intValue() && !triangle.getSideA().equals(triangle.getSideB())) ||
                (triangle.getSideC().intValue() == triangle.getSideB().intValue() && !triangle.getSideB().equals(triangle.getSideA()))) {
            System.out.println("This is an isosceles triangle");
            printAngles(triangle);
            printArea(triangle);
            return;
        }

        String output = triangleFactory.getTypeFactory(triangle);
        if(output != null){
            System.out.println("Your triangle type is " + output);
            printAngles(triangle);
            printArea(triangle);
        } else {
            System.out.println("This isn't a triangle");
        }
    }

    private static void printAngles(Triangle triangle){
        List<Double> angles = triangleFactory.getAngles(triangle.getSideA(), triangle.getSideB(),
                triangle.getSideC());
        System.out.println("Here are the angles: \n" +angles);
    }

    private static void printArea(Triangle triangle){
        System.out.println("The area of your triangle is "+calculator.findTriangleArea(triangle));
    }
}
