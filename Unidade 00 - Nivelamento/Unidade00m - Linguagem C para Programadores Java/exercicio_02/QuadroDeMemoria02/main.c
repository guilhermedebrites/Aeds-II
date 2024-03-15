#include <stdio.h>
#include <stdlib.h>

int main()
{
    int x1, x2, x3; int *p;
    x1 = 11; x2 = 22; x3 = 33;
    p = &x1;
    x2 = *p;
    *p = x3;
    p = &x3;
    *p = 0;

    printf("cont:%d %d %d %d\n", x1, x2, x3, *p);
    printf("addr:%p %p %p %p", &x1, &x2, &x3, p);

    return 0;
}
