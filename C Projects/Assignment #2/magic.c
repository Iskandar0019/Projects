/* This program reads integer values from standard input into
a matrix and checks whether the matrix is a basic magic square */

#include <stdio.h>

#define FALSE 0
#define TRUE 1
#define N 3

int checkInput (int mat[N][N]); /* Populate matrix if input is valid, otherwise return error signal. */
void printMatrix (int mat[N][N]); /* Print matrix created from input values. */
int checkMatrix (int mat[N][N]); /* Check if the matrix is a basic magic square. */

/* Main function */

int main (void)
{
  int mat [N][N];

  printf ("Please enter %d distinct integer values ranging from 1 to %d:\n\n", N * N, N * N);

  switch (checkInput (mat))
  {
    printf ("\n");

    case 0: /* Valid input - continue program. */
    {
      printf ("\n");
      printf ("The matrix to be tested is:\n\n");

      printMatrix (mat);

      if (checkMatrix (mat))
      {
        printf ("This matrix is a basic magic square.\n");
      }
      else
      {
        printf ("This matrix is not a basic magic square.\n");
      }

      break;
    }

    /* Invalid input - terminate program with error message. */

    case -1:
    {
      printf ("Error: Input contains non integer values.\n");
      break;
    }
    case -2:
    {
      printf ("Error: Input contains less than %d values.\n", N * N);
      break;
    }
    case -3:
    {
      printf ("Error: Input contains more than %d values.\n", N * N);
      break;
    }
    default:
    {
      break;
    }
  }

  return (1);
}

/* checkInput: Receives an N dimensional. If input is valid - populates it with input values and returns 0.
Otherwise returns error signal (-1, -2 or -3). */

int checkInput (int mat[N][N])
{
  int value, isInteger, counter = 0, *p = &mat[0][0];

  while ((isInteger = scanf ("%d", &value)) != EOF && isInteger != 0) /* Check input for validity. */
  {
    counter++; /* Count input values. */
    *p = value; /* Enter value into matrix. */
    p++;
  }

  if (isInteger == 0) /* Non integer value detected */
  {
    return (-1);
  }
  else if (counter < N * N) /* Input contains too few values. */
  {
    return (-2);
  }
  else if (counter > N * N) /* Input contains too many values. */
  {
    return (-3);
  }
  else /* Input is valid */
  {
    return (0);
  }
}

/* printMatrix: Receieves an N dimensional matrix. Prints it in indented form. */

void printMatrix (int mat[N][N])
{
  int i, width = 0, *p = &mat[0][0], max = *p;

  for (i = 1; i < N * N; i++) /* Find largest number in the matrix */
  {
    if (*p > max)
    {
      max = *p;
    }

    p++;
  }

  while (max != 0) /* Calculate necessary field width for matrix values. */
  {
    width++;
    max /= 10;
  }

  p = &mat[0][0]; /* Return pointer to first matrix element */

  for (i = 1; i <= N * N; i++)
  {
    printf("%*d  ", width, *p); /* Print in indented form */
    p++;

    if (i % N == 0)
    {
      printf("\n\n");
    }
  }
}

/* checkMatrix: Receives an N dimensional matrix.
Return true if the matrix is a basic magic square, and False otherwise. */

int checkMatrix (int mat[N][N])
{
  int magicSum = (N * ((N * N) + 1)) / 2; /* Formula for the magic sum. */
  int i = 0, j = 0, rowSum = 0, colSum = 0, mainDiagSum = 0, antiDiagSum = 0;
  int *c = &mat[0][0], *p = c + 1;

  for (i = 0; i < N * N - 1; i++) /* Search for duplicate values using pointers. */
  {
    for (j = i + 1; j < N * N; j++)
    {
      if (*c == *p)  /* Duplicate values detected. */
      {
        return (FALSE);
      }

      p++;
    }

    c++;
    p = c + 1;
  }

  for (i = 0; i < N; i++) /* Calculate sums of rows ,columns and diagnols. */
  {
    rowSum = colSum = 0; /* Reset for next row and column processing. */

    for (j = 0; j < N; j++)
    {
      if (mat[i][j] < 1 || mat[i][j] > N * N) /* Value out of range detected. */
      {
        return (FALSE);
      }
      else
      {
        rowSum += mat[i][j];
        colSum += mat[j][i];

        if (i == j)
        {
          mainDiagSum += mat[i][j];
          antiDiagSum += mat[(N - 1) - i][j];
        }
      }
    }

    if (rowSum != magicSum || colSum != magicSum) /* Row or column sum is not the magic sum. */
    {
      return (FALSE);
    }
  }

  if (mainDiagSum != magicSum || antiDiagSum != magicSum) /* Main or secondary diagonal sum is not the magic sum. */
  {
    return (FALSE);
  }

  return (TRUE); /* Matrix meets all the conditions of a basic magic square */
}

/* End of program */
