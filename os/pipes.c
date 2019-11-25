#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    pid_t pid;

    int fd[2];

    int ret = pipe(fd);

    if (ret < 0) {
        printf("pipe command failed\n");
        return -2;
    }

    pid = fork();

    if (pid < 0) {
        return -1;  // fork failed
    }

    if (pid > 0)  //parent
    {
        char str1[100];
        close(fd[0]);

        printf("Enter your name\n");
        scanf("%s", str1);

        write(fd[1], str1, strlen(str1) + 1);
        close(fd[1]);
    } else  //child
    {
        //		waitpid(pid, NULL, 0);
        char str2[100];
        close(fd[1]);
        read(fd[0], str2, sizeof(str2));
        printf("Child has passed: %s\n", str2);
        close(fd[0]);
    }

    return 0;
}