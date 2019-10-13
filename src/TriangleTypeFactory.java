import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TriangleTypeFactory {

    String getTypeFactory(Triangle triangle) {

        List<Integer> sides = new ArrayList<>();
        Collections.addAll(sides, triangle.getSideA(), triangle.getSideB(), triangle.getSideC());
        List<Double> resultList = getAngles(triangle.getSideA(), triangle.getSideB(), triangle.getSideC());

        if (isValidBySides(sides)) {
            return null;
        }

        if (isIsosceles(triangle.getSideA(), triangle.getSideB(), triangle.getSideC())){
            return String.valueOf(TriangleType.ISOSCELES).toLowerCase();
        }

        if (!isValidByAngles(resultList)) {
            return null;
        }

        if (checkForRightAngle(resultList)) {
            return String.valueOf(TriangleType.RIGHT_ANGLE).toLowerCase();
        } else {
            return String.valueOf(TriangleType.SCALENE).toLowerCase();
        }
    }

    private boolean isIsosceles(int a, int b, int c) {

        boolean isEqual1 = (a == b && a != c);
        boolean isEqual2 = (a == c && a != b);
        boolean isEqual3 = (c == b && b != a);

        return (isEqual1 || isEqual2 || isEqual3);
    }

    public List<Double> getAngles(float a, float b, float c) {

        float sideA = a * a;
        float sideB = b * b;
        float sideC = c * c;

        double angleA = Math.acos((sideB + sideC - sideA) / (2 * b * c));
        double angleB = Math.acos((sideA + sideC - sideB) / (2 * a * c));
        double angleC = Math.acos((sideA + sideB - sideC) / (2 * b * a));

        List<Double> angles = new ArrayList<>();
        Collections.addAll(angles, Math.floor((angleA * 180) / Math.PI),
                Math.floor((angleB * 180) / Math.PI), Math.floor((angleC * 180) / Math.PI));
        return angles;
    }

    private boolean checkForRightAngle(List<Double> anglesList) {
        Double rightAngle = 90.0;
        return anglesList.contains(rightAngle);
    }

    private boolean isValidByAngles(List<Double> anglesList) {
        return anglesList.stream().reduce(1.0, Double::sum) == 180.0;
    }

    private boolean isValidBySides(List<Integer> sides){
        return sides.contains(0);
    }
}
