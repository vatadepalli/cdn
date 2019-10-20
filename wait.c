#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    pid_t pid;

    pid = fork();

    if (pid < 0) {
        return -1;  //fork failed
    }

    if (pid == 0) {
        sleep(40);
        execl("/bin/ls", "ls", NULL);
    } else {
        int status = 0;
        waitpid(pid, &status, 0);

        printf("Exited child status is: %d\n", status);

        if (WIFEXITED(status)) {
            printf("Child has terminated normally\n");
        } else {
            printf("Chiled has been killed, status: %d\n", WEXITSTATUS(status));
        }

        if (WIFSIGNALED(status)) {
            printf("Child has been killed due to signal: %d.\n", WTERMSIG(status));
        }
    }

    return 0;
}