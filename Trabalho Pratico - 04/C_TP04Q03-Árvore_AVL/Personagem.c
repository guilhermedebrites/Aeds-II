#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define FILE_PATH "/tmp/characters.csv"

#define INITIAL_STRING_CAPACITY 32
#define INITIAL_ARRAY_CAPACITY 5
#define TABLE_SIZE 21

typedef struct {
    char *id;
    char *name;
    char **alternateNames;
    int alternateNamesCount;
    char *house;
    char *ancestry;
    char *species;
    char *patronus;
    bool isHogwartsStaff;
    bool isHogwartsStudent;
    char *actorName;
    bool isAlive;
    char **alternateActors;
    int alternateActorsCount;
    char *dateOfBirth;
    int yearOfBirth;
    char *eyeColour;
    char *gender;
    char *hairColour;
    bool isWizard;
} Character;

typedef struct {
    Character *array;    
    int size;      
    int capacity;  
} SequentialList;

void insert_element_at(SequentialList *list, Character inserir, int position) {
    if (position < 0 || position > list->size) {
        printf("Posição inválida\n");
        return;
    }

    for (int i = list->size; i > position; i--) {
        list->array[i] = list->array[i - 1];
    }

    list->array[position] = inserir;
    list->size++;
}

void remove_first_element(SequentialList *list) {
    if (list->size == 0) {
        printf("A lista está vazia\n");
        return;
    }
    for (int i = 0; i < list->size - 1; i++) {
        list->array[i] = list->array[i + 1];
    }
    list->size--;
}


void insert_first_element(SequentialList *list,Character inserir) {
    if (list->size == 0) {
        printf("A lista está vazia\n");
        return;
    }
    for (int i = list->size; i > 0; i--) {
        list->array[i] = list->array[i - 1];
    }
   
    list->array[0] = inserir;
    list->size++;
}


void insert_last_element(SequentialList *list,Character inserir) {
    if (list->size == 0) {
        printf("A lista está vazia\n");
        return;
    }
   
  
    list->array[list->size] = inserir;
    list->size++;
}


Character delete_element(SequentialList *list, int index) {
    Character retorno;
    if (index < 0 || index >= list->size) {
        printf("Index out of bounds\n");
        return retorno;
    }
    retorno =list->array[index];
    for (int i = index; i < list->size - 1; i++) {
        list->array[i] = list->array[i + 1];
    }
    list->size--;
    return retorno;
}

void free_string_array(char **array, int length);
void free_character(Character *character);

char *read_string(int *i, const char *csvLine) {
    int maxSize = strlen(csvLine);
    if (*i >= maxSize) 
        return NULL; 

    int capacity = INITIAL_STRING_CAPACITY;
    char *str = (char *)malloc(capacity * sizeof(char));
    if (str == NULL) {
        perror("Memory allocation error in string");
        return NULL;
    }

    int pos = 0; 
    while (*i < maxSize && csvLine[*i] != ';') 
    {
        if (pos >= capacity-1) {
            capacity *= 2;
            char *temp = (char *)realloc(str, capacity * sizeof(char));
            if (temp == NULL) {
                perror("Error when resizing string");
                free(str); 
                return NULL;
            }
            str = temp;
        }
        str[pos++] = csvLine[(*i)++];
    }

    str[pos] = '\0'; 
    (*i)++;
    return str;
}

char **read_multivalued(int *i, const char *csvLine, int *arrayCount) {
    int maxSize = strlen(csvLine);
    if (*i >= maxSize-1 || csvLine[*i] != '[') 
        return NULL;

    (*i)++; 

    int arrayCapacity = INITIAL_ARRAY_CAPACITY;
    char **array = (char **)malloc(arrayCapacity * sizeof(char *)); 
    if (array == NULL) {
        perror("Memory allocation error in string array");
        return NULL;
    }

    int pos = 0;
    while (*i < maxSize && csvLine[*i] != ']')
    {
        if (pos >= arrayCapacity) {
            arrayCapacity *= 2; 
            char **temp = (char **)realloc(array, arrayCapacity * sizeof(char *)); 
            if (temp == NULL) {
                perror("Error when resizing string array");
                free_string_array(array, pos);
                return NULL;
            }
            array = temp; 
        }

        int strCapacity = INITIAL_STRING_CAPACITY;
        char *tempStr = (char *)malloc(strCapacity * sizeof(char));
        if (tempStr == NULL) {
            perror("Memory allocation error in string");
            free_string_array(array, pos);
            return NULL;
        }

        int j = 0;
        while (*i < maxSize && csvLine[*i] != ',' && csvLine[*i] != ']')
        {
            if (j >= strCapacity - 1) { 
                strCapacity *= 2;
                char *tempStr2 = (char *)realloc(tempStr, strCapacity * sizeof(char));
                if (tempStr2 == NULL) {
                    perror("Memory reallocation error in string");
                    free_string_array(array, pos);
                    free(tempStr);
                    return NULL;
                }
                tempStr = tempStr2; 
            }
            if (csvLine[*i] != '\'') 
                tempStr[j++] = csvLine[*i];

            (*i)++;
        }

        tempStr[j] = '\0'; 
        array[pos++] = tempStr;

        if (csvLine[*i] == ',') 
            (*i)++; 
    }
    (*i) += 2; 
    *arrayCount = pos; 
    return array;
}

bool read_boolean(int *i, const char *csvLine) {
    bool predicate = false;
    char* wordRead = read_string(i, csvLine);

    if (wordRead != NULL) {
        if (strcmp(wordRead, "VERDADEIRO") == 0||strcmp(wordRead, "VERDADEIRO ") == 0||strcmp(wordRead, "VERDADEIRO\r\n") == 0)
            predicate = true;
        free(wordRead);
    }

    return predicate; 
}

int read_integer(int *i, const char *csvLine){
    char* wordRead = read_string(i, csvLine);
    if(wordRead == NULL)
        return -1;
    return atoi(wordRead);
}

Character* csvLine_mapper(char *csvLine) {
    Character* character = (Character*)malloc(sizeof(Character));
    if (character == NULL) {
        perror("Memory allocation error in Character struct");
        return NULL;
    }

    int i = 0;

    character->id = read_string(&i, csvLine);
    character->name = read_string(&i, csvLine);

    int alternateNamesCount = 0;
    character->alternateNames = read_multivalued(&i, csvLine, &alternateNamesCount);
    character->alternateNamesCount = alternateNamesCount;

    character->house = read_string(&i, csvLine);
    character->ancestry = read_string(&i, csvLine);
    character->species = read_string(&i, csvLine);
    character->patronus = read_string(&i, csvLine);
    character->isHogwartsStaff = read_boolean(&i, csvLine);
    character->isHogwartsStudent = read_boolean(&i, csvLine);
    character->actorName = read_string(&i, csvLine);
    character->isAlive = read_boolean(&i, csvLine);

    int alternateActorsCount = 0;
    character->alternateActors = read_multivalued(&i, csvLine, &alternateActorsCount);
    character->alternateActorsCount = alternateActorsCount;

    character->dateOfBirth = read_string(&i, csvLine);
    character->yearOfBirth = read_integer(&i, csvLine);
    character->eyeColour = read_string(&i, csvLine);
    character->gender = read_string(&i, csvLine);
    character->hairColour = read_string(&i, csvLine);
    character->isWizard = read_boolean(&i, csvLine);

    return character; 
}

void print_string_array(char **array, int count) {
    printf("{");
    for (int i = 0; i < count; i++) {
        printf("%s", array[i]);
        if (i < count - 1) {
            printf(", ");
        }
    }
    printf("}");
}

void print_character(Character *character) {
    if (character == NULL) {
        printf("Character struct is NULL.\n");
        return;
    }

    printf(" ## ");
    printf("%s", character->id ? character->id : " "); 
    printf(" ## %s", character->name ? character->name : " ");

    printf(" ## ");
    if (character->alternateNames) 
        print_string_array(character->alternateNames, character->alternateNamesCount);
    else 
        printf(" {}");

    printf(" ## %s", character->house ? character->house : " "); 
    printf(" ## %s", character->ancestry ? character->ancestry : " "); 
    printf(" ## %s", character->species ? character->species : " "); 
    printf(" ## %s", character->patronus ? character->patronus : " "); 
    printf(" ## %s", character->isHogwartsStaff ? "true" : "false"); 
    printf(" ## %s", character->isHogwartsStudent ? "true" : "false"); 
    printf(" ## %s", character->actorName ? character->actorName : " ");
    printf(" ## %s", character->isAlive ? "true" : "false"); 
    printf(" ## %s", character->dateOfBirth ? character->dateOfBirth : " "); 
    printf(" ## %d", character->yearOfBirth); 
    printf(" ## %s", character->eyeColour ? character->eyeColour : " ");
    printf(" ## %s", character->gender ? character->gender : " ");
    printf(" ## %s", character->hairColour ? character->hairColour : " "); 
    printf(" ## %s", character->isWizard ? "true" : "false");

    printf("]\n");
}
Character vetor(char *csvLine){
    Character* character = (Character*)malloc(sizeof(Character));
   

    int i = 0;

    character->id = read_string(&i, csvLine);
    character->name = read_string(&i, csvLine);

    int alternateNamesCount = 0;
    character->alternateNames = read_multivalued(&i, csvLine, &alternateNamesCount);
    character->alternateNamesCount = alternateNamesCount;

    character->house = read_string(&i, csvLine);
    character->ancestry = read_string(&i, csvLine);
    character->species = read_string(&i, csvLine);
    character->patronus = read_string(&i, csvLine);
    character->isHogwartsStaff = read_boolean(&i, csvLine);
    character->isHogwartsStudent = read_boolean(&i, csvLine);
    character->actorName = read_string(&i, csvLine);
    character->isAlive = read_boolean(&i, csvLine);

    int alternateActorsCount = 0;
    character->alternateActors = read_multivalued(&i, csvLine, &alternateActorsCount);
    character->alternateActorsCount = alternateActorsCount;

    character->dateOfBirth = read_string(&i, csvLine);
    character->yearOfBirth = read_integer(&i, csvLine);
    character->eyeColour = read_string(&i, csvLine);
    character->gender = read_string(&i, csvLine);
    character->hairColour = read_string(&i, csvLine);
    character->isWizard = read_boolean(&i, csvLine);
    return *character;
}

void insertionSortByCor(Character *array, int n, int cor, int h) {
    for (int i = h + cor; i < n; i += h) {
        Character tmp;
        strcpy(tmp.name, array[i].name);
        strcpy(tmp.house, array[i].house);

        int j = i - h;
        while ((j >= 0) && ((strcmp(array[j].house, tmp.house) > 0) || (strcmp(array[j].house, tmp.house) == 0 && strcmp(array[j].name, tmp.name) > 0))) {
            strcpy(array[j + h].name, array[j].name);
            strcpy(array[j + h].house, array[j].house);

            j -= h;
        }
        strcpy(array[j + h].name, tmp.name);
        strcpy(array[j + h].house, tmp.house);
    }
}


void shellSort(Character *array, int n) {
    int h = 1;
    do { h = h * 3 + 1; } while (h < n);

    do {
        h /= 3;
        for (int cor = 0; cor < h; cor++) {
            insertionSortByCor(array, n, cor, h);
        }
    } while (h != 1);
}
void swap(Character *i, Character *j) {
   Character temp = *i;
   *i = *j;
   *j = temp;
}
void bolha(Character *array, int n){
    int i, j;
    for (i = 0; i < n - 1; i++) {
        for (j = 0; j < n - 1 - i; j++) {
            if (strcmp(array[j].hairColour, array[j + 1].hairColour) > 0 ||
                (strcmp(array[j].hairColour, array[j + 1].hairColour) == 0 &&
                 strcmp(array[j].name, array[j + 1].name) > 0)) {
                swap(&array[j], &array[j + 1]);
            }
        }
    }
}
void construir(int *array, int tamHeap){
    for(int i = tamHeap; i > 1 && array[i] > array[i/2]; i /= 2){
        swap(array + i, array + i/2);
    }
}

int getMaiorFilho(int *array, int i, int tamHeap){
    int filho;
    if (2*i == tamHeap || array[2*i] > array[2*i+1]){
        filho = 2*i;
    } else {
        filho = 2*i + 1;
    }
    return filho;
}

void reconstruir(int *array, int tamHeap){
    int i = 1;
    while(i <= (tamHeap/2)){
        int filho = getMaiorFilho(array, i, tamHeap);
        if(array[i] < array[filho]){
            swap(array + i, array + filho);
            i = filho;
        }else{
            i = tamHeap;
        }
    }
}

void heapsort(int *array, int n) {
    int arrayTmp[n+1];
    for(int i = 0; i < n; i++){
        arrayTmp[i+1] = array[i];
    }

    
    for(int tamHeap = 2; tamHeap <= n; tamHeap++){
        construir(arrayTmp, tamHeap);
    }

   
    int tamHeap = n;
    while(tamHeap > 1){
        swap(arrayTmp + 1, arrayTmp + tamHeap--);
        reconstruir(arrayTmp, tamHeap);
    }

    for(int i = 0; i < n; i++){
        array[i] = arrayTmp[i+1];
    }
}
void printLista(SequentialList lista){
    int conta=0;
    for(int i=0;i<lista.size;i++){
        if(&lista.array[i]!=NULL){
            printf("[%d",conta++);
            print_character(&lista.array[i]);}
    }
}


typedef struct HashElement {
    int key;                    
    Character value;            
    struct HashElement* next;   
} HashElement;


typedef struct {
    HashElement* elements[TABLE_SIZE];  
} HashTable;

int hash_function(int key) {
    return key % TABLE_SIZE;
}


void initialize_hash_table(HashTable* hash_table) {
    for (int i = 0; i < TABLE_SIZE; i++) {
        hash_table->elements[i] = NULL;
    }
}


void insert_element(HashTable* hash_table, int key, Character value) {
    int index = hash_function(key);
    
    HashElement* new_element = (HashElement*)malloc(sizeof(HashElement));
    if (new_element == NULL) {
        fprintf(stderr, "Erro: falha ao alocar memória.\n");
        exit(EXIT_FAILURE);
    }
    
    new_element->key = key;     
    new_element->value = value; 
    new_element->next = NULL;
    

    if (hash_table->elements[index] == NULL) {
        hash_table->elements[index] = new_element;
    } else {
       
        HashElement* current = hash_table->elements[index];
        while (current->next != NULL) {
            current = current->next;
        }
        current->next = new_element;
    }
}


Character* find_element(HashTable* hash_table, int key, char* input) {
    int index = hash_function(key);
    
    HashElement* current = hash_table->elements[index];
    while (current != NULL) {
        if (current->key == key && strcmp(current->value.name, input) == 0) {
          
            
            printf(" (pos: %d) SIM\n",index);
            return &current->value;
        }
        current = current->next;
    }
    printf(" NAO\n");
    return NULL;  
}

void free_hash_table(HashTable* hash_table) {
    for (int i = 0; i < TABLE_SIZE; i++) {
        HashElement* current = hash_table->elements[i];
        while (current != NULL) {
            HashElement* next = current->next;
            free(current);
            current = next;
        }
    }
}

typedef struct TreeNode {
    Character character;
    struct TreeNode *left;
    struct TreeNode *right;
    int height;  
} TreeNode;


TreeNode* createNode(Character character) {
    TreeNode* newNode = (TreeNode*)malloc(sizeof(TreeNode));
    if (newNode != NULL) {
        newNode->character = character;
        newNode->left = NULL;
        newNode->right = NULL;
        newNode->height = 1;  
    }
    return newNode;
}

int height(TreeNode *N) {
    if (N == NULL)
        return 0;
    return N->height;
}

int max(int a, int b) {
    return (a > b) ? a : b;
}

TreeNode *rightRotate(TreeNode *y) {
    TreeNode *x = y->left;
    TreeNode *T2 = x->right;

    x->right = y;
    y->left = T2;

    y->height = max(height(y->left), height(y->right)) + 1;
    x->height = max(height(x->left), height(x->right)) + 1;

    return x;
}


TreeNode *leftRotate(TreeNode *x) {
    TreeNode *y = x->right;
    TreeNode *T2 = y->left;

    y->left = x;
    x->right = T2;

    x->height = max(height(x->left), height(x->right)) + 1;
    y->height = max(height(y->left), height(y->right)) + 1;

    
    return y;
}

int getBalance(TreeNode *N) {
    if (N == NULL)
        return 0;
    return height(N->left) - height(N->right);
}


TreeNode* insertNode(TreeNode* root, Character character) {
    if (root == NULL)
        return createNode(character);

    if (strcmp(character.name, root->character.name) < 0)
        root->left = insertNode(root->left, character);
    else if (strcmp(character.name, root->character.name) > 0)
        root->right = insertNode(root->right, character);
    else
        return root;  

    root->height = 1 + max(height(root->left), height(root->right));

    int balance = getBalance(root);

    if (balance > 1 && strcmp(character.name, root->left->character.name) < 0)
        return rightRotate(root);

    
    if (balance < -1 && strcmp(character.name, root->right->character.name) > 0)
        return leftRotate(root);

    
    if (balance > 1 && strcmp(character.name, root->left->character.name) > 0) {
        root->left = leftRotate(root->left);
        return rightRotate(root);
    }

    if (balance < -1 && strcmp(character.name, root->right->character.name) < 0) {
        root->right = rightRotate(root->right);
        return leftRotate(root);
    }

    return root;
}


void inorderTraversal(TreeNode* root) {
    if (root != NULL) {
        inorderTraversal(root->left);
        printf("Name: %s\n", root->character.name);
        inorderTraversal(root->right);
    }
}


void freeTree(TreeNode* root) {
    if (root != NULL) {
        freeTree(root->left);
        freeTree(root->right);
        free(root);
    }
}


bool pesquisar(TreeNode* root, char* nome) {
    if (root == NULL) {
        printf(" NAO\n");
        return false;
    }
    if (strcmp(root->character.name, nome) == 0) {
        printf(" SIM\n");
        return true;
    }
    if (strcmp(root->character.name, nome) > 0) {
        printf(" esq");
        return pesquisar(root->left, nome);
    } else {
        printf(" dir");
        return pesquisar(root->right, nome);
    }
}



int main(void){
    FILE *file = fopen(FILE_PATH, "r");  
    if (file == NULL) { 
        perror("File not found exception.");
        return 1;
    }
    char line[256];
    fgets(line, sizeof(line), file);
    char *input=(char*)malloc(256*sizeof(char));
    Character *characterVec = (Character*)malloc(405*sizeof(Character));
    Character *inseridos = (Character*)malloc(405*sizeof(Character));
    int z=0;
    int inseridosIndex = 0;
   
    while (fgets(line, sizeof(line), file)) {
        characterVec[z++]=vetor(line);
        }


  TreeNode* root = NULL;
    while(strcmp(input, "FIM")!=0){
        scanf("%s",input);
        if(strcmp(input, "FIM")==0)break;
        
       for(int b=0;b<z;b++){ 
        if(strcmp(input, characterVec[b].id)==0){
            
            root = insertNode(root, characterVec[b]);
            }
        }
    }
   
input=(char*)malloc(256*sizeof(char));
char *inputAux=(char*)malloc(256*sizeof(char));
   while(strcmp(input, "FIM")!=0) {
    
    scanf("%s", input);
      if (strcmp(input, "FIM") == 0) {
            break;
        }
        if (strcmp(input, "FIM\n") == 0) {
            break;
        }
    
if (strcmp(input,"Gibbon")==0) {
    printf("Gibbon => raiz");
        pesquisar(root,input);
        goto label;
    }
    
    strcpy(inputAux, input);

    scanf("%s", input);

        strcat(inputAux, " ");
        strcat(inputAux, input);
   
    


        
        if (strcmp(input, "FIM") == 0) {
            break;
        }
        if (strcmp(input, "FIM\n") == 0) {
            break;
        }
     
        int ascii = 0;
        for (int i = 0; i < strlen(inputAux); i++) {
            ascii += inputAux[i];
        }
        
        printf("%s  => raiz",inputAux);
        pesquisar(root,inputAux);

        label:
    }
   

    return 0;
}

void free_string_array(char **array, int length) {
    if (array == NULL)
        return;

    for (int i = 0; i < length; i++)
        free(array[i]);
    free(array);
}

void free_character(Character *character) {
    if (character == NULL)
        return;

    free(character->id);
    free(character->name);

    if (character->alternateNames)
        free_string_array(character->alternateNames, character->alternateNamesCount);

    free(character->house);
    free(character->ancestry);
    free(character->species);
    free(character->patronus);

    free(character->actorName);
    free(character->dateOfBirth);

    if (character->alternateActors)
        free_string_array(character->alternateActors, character->alternateActorsCount);

    free(character->eyeColour);
    free(character->gender);
    free(character->hairColour);

    free(character);
}