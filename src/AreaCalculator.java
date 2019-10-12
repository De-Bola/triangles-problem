public class AreaCalculator {
    public double findTriangleArea(Triangle triangle){
        //using heron's formula
        int sum = addSidesOfTriangle(triangle.getSideA(), triangle.getSideB(), triangle.getSideC());
        //triangle.setSum(sum);

        double sumFactor = getHeronFactor((float) sum);
        double diffA = triangle.getSideA();
        double diffB = triangle.getSideB();
        double diffC = triangle.getSideC();

        diffA = sumFactor - diffA;
        diffB = sumFactor - diffB;
        diffC = sumFactor - diffC;

        sumFactor = sumFactor * diffA * diffB * diffC;
        return Math.sqrt(sumFactor);
    }

    private int addSidesOfTriangle(int a, int b, int c){
        return a + b + c;
    }

    private float getHeronFactor(float sum){
        return sum/2 ;
    }
}
