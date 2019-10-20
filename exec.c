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
        sleep(5);
        printf("Before exec: It is a child process: %d\n", getpid());
        //execl("/bin/ls", "ls", NULL);
        //execl("/bin/ls", "ls", "-l", NULL);
        execlp("ls", "ls", "-l", NULL);
        printf("After exec: Child's parent process: %d\n", getppid());
        sleep(5);
    } else {
        sleep(5);
        printf("It is a parent process\n");
        printf("parent's own id: %d\n", getpid());
        printf("parent's child id: %d\n", pid);
        printf("parent's parent (terminal) id: %d\n", getppid());
        sleep(5);
    }

    printf("Print by both process: %d\n", getpid());

    return 0;
}