/**
 * File:           HW1_Onyia_Emmanuel_Chinedu_182417MVEB.c
 * Author:         Emmanuel Chinedu Onyia
 * Created:        11.10.2019
 * Last edit:      12.10.2019
 *
 * Description:    This code reads records of a gambling establishment containing contact details of customers with their wins and losses.
 *                 It then calculates the average profit of the establishment as well as the win-loss ratio of each of the customers.
 *                 Output contain those customers who make above average profit as well as their win-loss ratio and total losses.
 *                 The output is sorted according to who has the most losses.
 */


#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <math.h>
#include "HW1_Onyia_Emmanuel_Chinedu_182417MVEB.h"  //including my header file library.



int main()
{
  clients data[MAX_LEN];
  //initializing file pointers
  FILE *fi = fopen(INPUT_FILE, "r");         //opening the gambling establishment text file to be read from.
  FILE *fo = fopen(OUTPUT_FILE, "w");        //opening the output file to be written to.
  
  int row = 0;

  if(fi == NULL)        //if statement to confirm if input file was opened
  {
    printf("\"file %s did not open seccessfully\"\n", INPUT_FILE);
    exit(-1);
  }else if(fo == NULL)  //if statement to confirm if input file was opened
   {
    printf("\"file %s did not open seccessfully\"\n", OUTPUT_FILE);
    exit(-1);
   }else   
  {
    while (row < MAX_LEN && fscanf(fi,"%s %s %s %f %f", data[row].firstName, data[row].lastName, data[row].phoneNum, &data[row].totalWinnings, &data[row].totalLosses) == 5)
      {
        row++;    //counts number of customers
      }
  }
  //prints out the average profit of the establishment.
  printf("\n\nThe average profit is %.2f\n\n", average(data, row));     //calling function to calculate average
  winLossRatio(data, row, fo);                                              //calling function to calculate win-loss ratio

  fclose(fi);     //closing the input file
  fclose(fo);     //closing the output file
 
  
  return 0;
}

/**
 * Description:     Calculates the average profit by finding the total profit
 *                   and dividing it by the number of customers
 *
 * Parameters:      client info[] - structure, contains client information
 *                  n - int, reference to rows(number of customers)
 *                  
 *
 * Function type:   float
 *
 * Return:          average
 */

float average(clients info[MAX_LEN], int n)
{
  int i;
  float average;
  float totalprofit = 0;
  float profit[n];

  //calculate profit of each custonmer and sum of profits
  for(i=0; i<n; i++)
  {
    profit[i] = info[i].totalWinnings - info[i].totalLosses;
    totalprofit += profit[i];
  }

  average = totalprofit / n;     //value of average


return average;  //returning value of average to main function.
}

/**
 * Description:     Calculates the win-loss ratio of each customer
 *                   losses divided by wins
 *
 * Parameters:      client info[] - structure, contains client information
 *                  n - int, reference to rows(number of customers)
 *                  FILE *fo - output file
 *
 * Function type:   void
 *
 * Return:          none
 */
void winLossRatio(clients info[MAX_LEN], int n, FILE *fo)
 {
   int i;
   float ave = average(info, n);
   float winlossratio[n];
   
   //print headings on screen
   printf("List of those who made more than average profit:\n\n");
   printf("FirstName\tLastName\tPhone\t\tRatio  Losses\n\n"); 
   //print headings to output file
   fprintf(fo,"List of those who made more than average profit:\n\n");
   fprintf(fo,"F.Name\t L.Name\t Phone\t\t Ratio\t Losses\n\n"); 

   //calculating the winloss ratio of each customer
   for(i=0; i<n; i++) 
   {
     winlossratio[i] = info[i].totalLosses / info[i].totalWinnings;

     if(info[i].totalWinnings > ave)
     {
      qsort(info, (size_t)n, sizeof(clients), compareFunc);       //calling the sort function
      printf("%-15s %-15s %-15s %-6.2f %-6.2f\n", info[i].firstName, info[i].lastName, info[i].phoneNum, winlossratio[i], info[i].totalLosses);  //output on screen
      fprintf(fo,"%s\t %s\t %s\t %.2f\t %.2f\n", info[i].firstName, info[i].lastName, info[i].phoneNum, winlossratio[i], info[i].totalLosses);   //output to file
     }
   }
}

/**
 * Description:     Function to sort losses from highest to lowest
 *                   losses divided by wins
 *
 * Parameters:      a,b - constant type void pointers
 * 
 * Function type:   int        
 */
int compareFunc(const void *a, const void *b)
{
  clients *p1 = (clients *)a;
  clients *p2 = (clients *)b;
  return (int)(100.2f*p2->totalLosses - 100.2f*p1->totalLosses);
}

