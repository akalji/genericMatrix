package com.akalji.genericmatrix;

import com.akalji.genericmatrix.exceptions.IncompatibleDimensions;


/**
 * @author Nikolai Tikhonov <akalji@ya.ru> akalji
 * Yet another class of matrix
 */
public class Matrix<T extends Number> {
    private int vsize, hsize;
    private Number M[][];


    /**
     * @param vsize - number of rows
     * @param hsize - number of columns
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Constructor of any dimension Matrix
     * @author akalji (Nikolai Tikhonov)
     */
    public Matrix(int vsize, int hsize) {
        this.vsize = vsize;
        this.hsize = hsize;
        this.M = new Number[vsize][hsize];

        for (int i = 0; i < this.vsize; i++) {
            for (int j = 0; j < this.hsize; j++) {
                this.M[i][j] = 0;
            }
        }
    }


    /**
     * @param A - The copied matrix
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * A copy constructor of the Matrix
     */
    public Matrix(Matrix A) {
        vsize = A.getVsize();
        hsize = A.getHsize();
        this.M = new Number[vsize][hsize];

        for (int i = 0; i < this.vsize; i++) {
            for (int j = 0; j < this.hsize; j++) {
                this.M[i][j] = A.getElement(i, j);
            }
        }
    }


    /**
     * @param N
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Constructor of square matrix
     */
    public Matrix(int N) {
        this.vsize = N;
        this.hsize = N;
        this.M = new Number[this.vsize][this.hsize];

        for (int i = 0; i < this.vsize; i++) {
            for (int j = 0; j < this.hsize; j++) {
                this.M[i][j] = 0;
            }
        }
    }


    /**
     * @param i     - row
     * @param j     - column
     * @param value
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Setter of value into cell of matrix
     * @author akalji (Nikolai Tikhonov)
     */
    public void set(int i, int j, T value) {
        if (i <= vsize && j <= hsize) {
            this.M[i][j] = value;
        } else {
            throw new IncompatibleDimensions();
        }

    }


    /**
     * @return - number of rows
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     */
    public int getVsize() {
        return this.vsize;
    }


    /**
     * @return - number of columns
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     */
    public int getHsize() {
        return this.hsize;
    }


    /**
     * @param i - row
     * @param j - column
     * @return - value of cell
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     */
    public T getElement(int i, int j) {
        return (T) this.M[i][j];
    }


    /**
     * @param a
     * @param b
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Swap elements in rows a & b
     */
    public void swapRows(int a, int b) {
        if (a == b) return;
        Number[] tmp = M[a];
        M[a] = M[b];
        M[b] = tmp;
    }


    /**
     * @param a
     * @param b
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Swap elements in columns a & b
     */
    public void swapColumns(int a, int b) {
        if (a == b) {
            return;
        }
        int N = this.vsize;
        if (a > N || b > N) {
            throw new IncompatibleDimensions();
        }
        Number tmp;
        for (int i = 0; i < N; ++i) {
            tmp = M[i][a];
            M[i][a] = M[i][b];
            M[i][b] = tmp;
        }
    }


    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Set 0.0 in all elements of the matrix
     */
    public void clear() {
        for (int i = 0; i < vsize; i++)
            for (int j = 0; j < hsize; j++)
                M[i][j] = 0;
    }

    /**
     * @param value
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     * Insert value in diagonal
     */
    public void insertDiag(T value) {
        for (int i = 0; i < vsize; ++i)
            for (int j = 0; j < hsize; ++j) {
                if (i == j) {
                    M[i][j] = value;
                }
            }
    }


    /**
     * @param M
     * @return Transposed Matrix M
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     */
    public static Matrix transpose(Matrix M) {
        int n = M.getVsize();
        int m = M.getHsize();
        Matrix A = new Matrix(m, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A.set(j, i, M.getElement(i, j));
            }
        }
        return A;
    }


    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     */
    public void shiftUp() {
        Number tmp[] = new Number[hsize];
        System.arraycopy(M[0], 0, tmp, 0, hsize);
        for (int i = 1; i < vsize; i++) {
            M[i - 1] = M[i];
        }
        M[hsize] = tmp;
    }


    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     */
    public void shiftDown() {
        Number tmp[] = new Number[hsize];
        System.arraycopy(M[hsize - 1], 0, tmp, 0, hsize);
        for (int i = hsize - 1; i >= 0; i--) {
            M[i] = M[i - 1];
        }
        M[hsize] = tmp;
    }


    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     */
    public void deleteRow(int rowToDelete) {
        Number newM[][] = new Number[this.vsize - 1][this.hsize];

        for (int i = 0, j = 0; i < this.vsize; i++, j++) {
            if (i != rowToDelete) {
                newM[j] = M[i];
            } else {
                j--;
            }
        }

        this.vsize--;
        this.M = newM;
    }


    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     */
    public void deleteColumn(int colToDelete) {
        Number newM[][] = new Number[this.vsize][this.hsize - 1];

        for (int i = 0; i < this.vsize; i++) {
            for (int j = 0, k = 0; j < this.hsize; j++, k++) {
                if (j != colToDelete) {
                    newM[i][k] = M[i][j];
                } else {
                    k--;
                }
            }
        }

        this.hsize--;
        this.M = newM;
    }


    /**
     * @author Nikolai Tikhonov <akalji@ya.ru> akalji
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Matrix {");
        stringBuilder.append(System.lineSeparator());
        for (int i = 0; i < vsize; i++) {
            for (int j = 0; j < hsize; j++) {
                stringBuilder.append(M[i][j]);
                if (j < hsize - 1) {
                    stringBuilder.append("\t");
                }
            }
            stringBuilder.append(System.lineSeparator());
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
