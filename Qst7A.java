
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class Qst7A {
    private static final int THREAD_POOL_SIZE = 4;
    public static void main(String[] args) throws InterruptedException {
        int[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] B = {{10, 11, 12}, {13, 14, 15}, {16, 17, 18}};
        int[][] C = new int[A.length][B[0].length];
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                executorService.execute(new MatrixMultiplicationTask(A, B, C, i, j));
            }
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}
class MatrixMultiplicationTask implements Runnable {
    private final int[][] A;
    private final int[][] B;
    private final int[][] C;
    private final int i;
    private final int j;
    public MatrixMultiplicationTask(int[][] A, int[][] B, int[][] C, int i, int j) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.i = i;
        this.j = j;
    }
    @Override
    public void run() {
        for (int k = 0; k < B.length; k++) {
            C[i][j] += A[i][k] * B[k][j];
        }
    }
}
