#include<stdio.h>
#include<sys/types.h>
#include<unistd.h>
#include<signal.h>
#include<stdlib.h>
#include<ctype.h>

#define SLEEP_IN 2
#define MAX_CHILD 10
int proc_number = 0;
void do_something();

void main(int argc,char* argv[]){
 int child_num = MAX_CHILD;
 int i,ch;
 pid_t child_pid;
 pid_t pid[10]={0};
 if(argc>1)
 {
  child_num = atoi(argv[1]);
  child_num = (child_num > 10)?10:child_num;
 }
 for(i = 0; i < child_num ; i++){
   child_pid =  fork();
	proc_number=i;
	do_something();
	pid[i] = child_pid;
 }

 while((ch = getchar())!='q'){
   if(isdigit(ch)){
     kill(pid[ch-'0'],SIGTERM);
   }
 }

 // 杀死所有进程
 for(i = 0;i<child_num;i++)
	 kill(pid[i],SIGTERM);
 return;
}

void do_something(){
  for(;;){
    printf("this is process,No%d and is pid is %d \n",proc_number,getpid());
	sleep(SLEEP_IN);
  }
}
