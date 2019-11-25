#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    int ret = mkfifo("mypipe", O_CREAT | 0666);

    if (ret < 0) {
        printf("mkfifo command failed\n");
        return -2;
    }

    char str1[100];

    int fd = open("mypipe", O_WRONLY);

    printf("Enter your name\n");
    scanf("%s", str1);

    write(fd, str1, strlen(str1) + 1);
    close(fd);

    return 0;
}