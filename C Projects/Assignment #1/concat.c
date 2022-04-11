/* This program converts any consecutive sequence of three or more alpha - numerical characters in the ASCII character
set of an input string to a contracted output string where any such sequence of characters is represented in the form:
[First alpha - numerical character in sequence]-[Last alpha - numerical character in sequence]*/

#include <stdio.h>
#include <ctype.h>

#define MAX_LENGTH 81 /* Maximum length of the input string */
#define TRUE 1
#define FALSE 0

void contract (char s1 [], char s2 []); /* The contracting function where s1 is the input string and s2 is the contracted output string */

int main ()
{
  char input [MAX_LENGTH];
  char output [MAX_LENGTH];

  printf ("Please enter a string of characters from the ASCII character set (up to 80 characters):\n\n");

  fgets (input, MAX_LENGTH, stdin);

  contract (input, output);

  printf ("\n");
  printf ("The original string is:\n%s", input);
  printf ("\n");
  printf ("The contracted string is:\n%s", output);

  return (1);
}

void contract (char s1 [], char s2 [])
{
  int i, j = 0, current, next, counter = 0;

  for (i = 0; s1 [i] != '\0'; i++)
  {
    current = s1 [i];
    next = s1 [i + 1];

    if (isalnum (current) && isalnum (next) && (s1 [i + 1] - s1 [i] == 1)) /* The current and next characters are alpha - numerical and consecutive */
    {
      counter++; /* Count the number of consecutive characters */
    }
    else /* The consecutive sequence was broken */
    {
      if (counter == 1)
      {
        s2 [j++] = s1 [i - counter];
      }
      else if (counter > 1)
      {
        s2 [j++] = s1 [i - counter];
        s2 [j++] = '-';
      }

      s2 [j++] = s1 [i];

      counter = 0; /* Resets the counter once the sequence is broken */
    }
  }

  s2 [j] = '\0'; /* Close contracted string */
}
