#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    char str1[100];

    int fd = open("mypipe", O_RDONLY);

    read(fd, str1, sizeof(str1));
    printf("Pipe had: %s\n", str1);

    close(fd);

    return 0;
}