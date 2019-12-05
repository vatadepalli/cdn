#include <signal.h>
#include <stdio.h>

void handle(int signum) const {
    printf("we are handling signal %d", signum);
}

int main() {
    int i = 0;
    signal(SIGINT, handler);
    signal(3, handler);  // SIGQUIT is 3
    while (1) {
        printf("%d\n", i++);
        sleep(1);
    }

    int i = 0;
    struct sigaction sig_act, sig_act1;

    memset(&sig_act, 0, sizeof(struct sigaction));
    sig_act.sa_handler = handler;
    //sig_act.sa_mask = 3; Will do it little later
    sigaction(SIGINT, &sig_act, (void *)0);
    sigaction(SIGQUIT, &sig_act1, (void *)0);

    sigaction(SIGINT, handle, NULL);

    return 0;
}
