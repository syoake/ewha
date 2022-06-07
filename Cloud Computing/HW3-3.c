#include <omp.h>
#include <stdio.h>
#include <stdlib.h>
#define N 100

int main(int argc, char *argv) {
	omp_set_num_threads(8); // set number of threads here
	int i, j, k;
	double sum;
	double start, end; // used for timing
	double A[N][N], B[N][N], C[N][N];

	#pragma omp parallel for collapse(2)
	for (i = 0; i < N; i ++) {
		for (j = 0; j < N; j++){
			A[i][j] = j*1;
			B[i][j] = i*j+2;
			C[i][j] = j-i*2;
		}
	}

	start = omp_get_wtime(); // start time measurement

	#pragma omp parallel for collapse(2)
	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			sum = 0;
			for (k = 0; k < N; k++) {
				sum += A[i][k] * B[k][j];
			}
			C[i][j] = sum;
		}
	}
	
	end = omp_get_wtime(); // end time measurement
	printf("Time of computation: %f seconds\n", end-start);

	return(0);
}

/* average computation time
 * 1 thread: 0.005319 seconds
 * 2 threads: 0.029802 seconds
 * 4 threads: 0.024909 seconds
 * 8 threads: 0.020268 seconds */
