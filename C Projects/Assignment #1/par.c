/* This program checks for basic compilation errors as unmatched parentheses, brackets or braces */

#include <stdio.h>
#include <ctype.h>

#define TRUE 1
#define FALSE 0
#define EMPTY -1
#define MAX_LINE 100

int checkLine (char line []);
int blockBraces (char line []);
void trim (char line [], char trimmed []);
void message (int balanced);
void push (char stack [], int c);
int pop (char stack []);
int top (char stack []);

int pos = 0; /* External stack variable - It was mentioned in the forum that this is OK */

int main ()
{
  int fullyBalanced = TRUE, blockBraceCounter = 0;

  char line [MAX_LINE];
  char trimmed [MAX_LINE];

  printf ("Please enter your code line after line:\n\n");

  while (fgets (line, MAX_LINE, stdin) != NULL)
  {
    printf("%s\n", line);

    blockBraceCounter += blockBraces (line);
    trim (line, trimmed);
    message (checkLine (trimmed));

    if (checkLine (trimmed) == FALSE) /* At least one line is not balanced */
    {
      fullyBalanced = FALSE;
    }
  }

  if (fullyBalanced == TRUE)
  {
    printf ("\nThe code is fully balanced.");
  }
  else
  {
    printf ("\nThe code is not fully balanced.");
  }

  return (1);
}

int checkLine (char line []) /* Checks if the line is valid */
{
  int i, c, isBalanced;
  pos = 0;

  char stack [MAX_LINE];

  for (i = 0; line [i] != '\0'; i++)
  {
    c = line [i];

    if (c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}') /* Character is relevant */
    {
      if ((top (stack) == '(' && c == ')') || (top (stack) == '[' && c == ']') || (top (stack) == '{' && c == '}')) /* Previous is matching */
      {
        pop (stack); /* Pop the previous */
      }
      else
      {
        push (stack, c); /* Push onto stack */
      }
    }
  }

  isBalanced = (top (stack) == EMPTY) ? TRUE : FALSE; /* The line is balanced if the stack has emptied */

  return (isBalanced);
}

void trim (char line [], char trimmed []) /* Remove text in quotes or comments from original line */
{
  int i, j = 0, current, next, mark, inQuote = FALSE, inComment = FALSE;

  for (i = 0; line [i] != '\0'; i++)
  {
    current = line [i];
    next = line [i + 1];

    trimmed [j++] = current;

    if (current == '\"' && inComment == FALSE) /* Quote signal */
    {
      if (inQuote == TRUE) /* Quote ended */
      {
        inQuote = FALSE;
        j = --mark; /* Return to position before quote */
      }
      else /* Quote begins */
      {
        inQuote = TRUE;
        mark = j;
      }
    }
    else if (current == '/' && next == '*') /* Comment signal */
    {
      inComment = TRUE; /* Comment begins */
      mark = j;
    }
    else if (current == '*' && next == '/' && inComment == TRUE) /* Comment ends */
    {
      i++;
      inComment = FALSE;
      j = --mark; /* Return to position before comment */
    }
  }

  trimmed [j] = '\0'; /* Close trimmed string */
}

int blockBraces (char line []) /* Checks if the line is a single brace */
{
  int i, c, counter = 0, result = 0, brace, valid = TRUE;

  for (i = 0; line [i] != '\0'; i++)
  {
    c = line [i];

    if (isspace (c) == FALSE || c != '{' || c != '}')
    {
      valid = FALSE; /* The line contains irelevant characters */
    }
    if (c == '{' || c == '}') /* The line contains a brace */
    {
      brace = c;
      counter++;
    }
  }

  if (valid == TRUE && counter == 1) /* The line contains a single brace */
  {
    if (brace == '{')
    {
      result = 1; /* Opening brace for the counter in main function */
    }
    else if (brace == '}')
    {
      result = -1; /* Closing brace for the counter in main function */
    }
  }

  return (result);
}

void message (int balanced) /* Displays the relevant message */
{
  if (balanced == TRUE)
  {
    printf ("The line is balanced.\n");
  }
  else
  {
    printf ("The line is not balanced.\n");
  }
}

/* Stack functions */

void push (char stack [], int c) /* Push character onto stack */
{
  stack [pos++] = c;
}

int pop (char stack []) /* Pop character from the stack */
{
  if (pos > 0)
  {
    return (stack [--pos]);
  }
  else
  {
    return (-1);
  }
}

int top (char stack []) /* Peek to the top of the stack */
{
  if (pos > 0)
  {
    return (stack [pos - 1]);
  }
  else
  {
    return (EMPTY);
  }
}
