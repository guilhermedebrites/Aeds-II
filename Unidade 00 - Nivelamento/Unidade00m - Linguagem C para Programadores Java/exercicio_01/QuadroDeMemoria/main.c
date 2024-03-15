#include <stdio.h>
#include <stdlib.h>

int main()
{
    int x = 10;
    int * y = & x;
    printf("\n%i", x);
    printf("\n%p", &x);
    printf("\n%p", y);
    printf("\n%p", &y);
    printf("\n%i", *y);
    return 0;
}
