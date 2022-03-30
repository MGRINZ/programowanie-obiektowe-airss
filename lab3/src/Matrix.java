public class Matrix {

    private final double[][] mat;
    private int m;
    private int n;

    public Matrix(double[][] mat) {
        this.mat = mat;
        m = mat.length;
        n = mat[0].length;
    }

    public double get(int m, int n) {
        return mat[m][n];
    }

    public Matrix transpose() {
        double[][] tMat = new double[this.n][this.m];
        for(int m = 0; m < this.m; m++)
            for(int n = 0; n < this.n; n++)
                tMat[n][m] = mat[m][n];
        return new Matrix(tMat);
    }

    public Matrix product(double a) {
        double[][] pMat = new double[this.m][this.n];
        for(int m = 0; m < this.m; m++)
            for(int n = 0; n < this.n; n++)
                pMat[m][n] = mat[m][n] * a;
        return new Matrix(pMat);
    }

    public Matrix product(Matrix A) {
        if(this.n != A.m)
            throw new IllegalArgumentException("Nieprawidłowe wymiary macierzy");

        double[][] pMat = new double[this.m][A.n];
        
        for(int m = 0; m < pMat.length; m++) {
            for(int n = 0; n < pMat[m].length; n++) {
                double sum = 0;
                for(int i = 0; i < this.n; i++)
                    sum += mat[m][i] * A.get(i, n);
                pMat[m][n] = sum;
            }
        }

        return new Matrix(pMat);
    }

    @Override
    public String toString() {
        String str = "";

        for(int m = 0; m < this.m; m++) {
            for(int n = 0; n < this.n; n++) {
                str += mat[m][n] + "\t";
            }
            str += "\n";
        }

        return str;
    }
}
