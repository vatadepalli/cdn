#include <stdio.h>
#include <unistd.h>

int main() {
    pid_t pid;

    pid = fork();

    if (pid < 0) {
        printf("Fork is failed\n");
        return -1;
    }

    if (pid == 0) {
        printf("It is a child process: %d\n", getpid());
        printf("Child's parent process: %d\n", getppid());
        while (1)
            ;
    } else {
        printf("It is a parent process\n");
        printf("parent's own id: %d\n", getpid());
        printf("parent's child id: %d\n", pid);
        printf("parent's parent (terminal) id: %d\n", getppid());
        sleep(20);
    }

    printf("Print by both process: %d\n", getpid());

    return 0;
}