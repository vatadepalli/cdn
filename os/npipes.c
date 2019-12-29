#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    pid_t pid;

    int ret = mkfifo("mypipe", O_CREAT | 0666);

    if (ret < 0) {
        printf("mkfifo command failed\n");
        return -2;
    }

    pid = fork();

    if (pid < 0) {
        return -1;  // fork failed
    }

    if (pid == 0)  //child
    {
        char str1[100];

        int fd = open("mypipe", O_WRONLY);

        printf("Enter your name\n");
        scanf("%s", str1);

        write(fd, str1, strlen(str1) + 1);
        close(fd);
    } else  //parent
    {
        //		waitpid(pid, NULL, 0);
        char str2[100];
        int fd = open("mypipe", O_RDONLY);
        read(fd, str2, sizeof(str2));
        printf("Child has passed: %s\n", str2);
        close(fd);
    }

    return 0;
}