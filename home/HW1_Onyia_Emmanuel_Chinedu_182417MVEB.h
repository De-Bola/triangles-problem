#ifndef HW1_Onyia_Emmanuel_Chinedu_182417MVEB
#define HW1_Onyia_Emmanuel_Chinedu_182417MVEB   

//defining input and output files
#define INPUT_FILE "HW1_Onyia_Emmanuel_Chinedu_182417MVEB.txt" 
#define OUTPUT_FILE "HW1_Onyia_Emmanuel_Chinedu_182417MVEB_output.txt"

#define N 20
#define MAX_LEN 100

//initializing struuctural array
typedef struct clients{
  char firstName[N];
  char lastName[N];
  char phoneNum[N];
  float totalWinnings;
  float totalLosses;
}clients;

//function prototypes
float average(clients info[MAX_LEN], int n);
void winLossRatio(clients info[MAX_LEN], int n, FILE *fo);
int compareFunc(const void *a, const void *b);

#endif



