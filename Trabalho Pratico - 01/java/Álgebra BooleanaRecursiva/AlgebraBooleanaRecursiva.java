public class AlgebraBooleanaRecursiva {
    public static void main(String[] args) {
        boolean fim;
        int numQuantidade;
        String entradaString = new String();
        int tamanho;
        char[] entradaCharArray = new char[1000];

        do {
            numQuantidade = MyIO.readInt();
            fim = isFim(numQuantidade);
            if (fim) {
                String[] listaValores = lerValores(numQuantidade);
                entradaString = MyIO.readLine();
                strToCharArray(entradaString, entradaCharArray);
                tamanho = entradaString.length();
                tamanho = removerEspacos(entradaCharArray, tamanho);
                trocar(entradaCharArray, listaValores, tamanho);
                char resp = algebraBooleana(entradaCharArray, tamanho, 0, tamanho);
                MyIO.println(resp);
            }
        } while (fim);
    }

    public static void strToCharArray(String entradaString, char[] entradaCharArray) {
        for (int i = 0; i < entradaString.length(); i++) {
            entradaCharArray[i] = entradaString.charAt(i);
        }
    }

    public static int removerEspacos(char[] entradaCharArray, int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            if (entradaCharArray[i] == ' ' || entradaCharArray[i] == ',') {
                for (int j = i; j < tamanho - 1; j++) {
                    entradaCharArray[j] = entradaCharArray[j + 1];
                }
                i--;
                entradaCharArray[tamanho] = '\0';
                tamanho--;
            }
        }
        return tamanho;
    }

    public static void imprimir(char[] entradaCharArray, int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            MyIO.print(entradaCharArray[i]);
        }
        MyIO.println("\n");
    }

    public static void trocar(char[] entradaCharArray, String[] listaValores, int tamanho) {
        int index = 0;
        for (int i = 0; i < listaValores.length; i++) {
            for (int j = 0; j < tamanho; j++) {
                if ((int) entradaCharArray[j] == index + 65) {
                    entradaCharArray[j] = (char) listaValores[index].charAt(0);
                }
            }
            index++;
        }
    }

    public static String[] lerValores(int numQuantidade) {
        String[] listaValores = new String[numQuantidade];
        for (int i = 0; i < numQuantidade; i++) {
            listaValores[i] = MyIO.readString();
        }
        return listaValores;
    }

    public static char not(char[] valores, int numValores) {
        char retorno = '1';
        for (int i = 0; i < numValores; i++) {
            if (valores[i] == '1') {
                retorno = '0';
                i = numValores;
                valores = new char[10];
            }
        }
        return retorno;
    }

    public static char and(char[] valores, int numValores) {
        char retorno = '1';
        for (int i = 0; i < numValores; i++) {
            if (valores[i] == '0') {
                retorno = '0';
                i = numValores;
                valores = new char[10];
            }
        }
        return retorno;
    }

    public static char or(char[] valores, int numValores) {
        char retorno = '0';
        for (int i = 0; i < numValores; i++) {
            if (valores[i] == '1') {
                retorno = '1';
                i = numValores;
                valores = new char[10];
            }
        }
        return retorno;
    }

    public static char algebraBooleana(char[] entradaCharArray, int tamanho, int indexI, int indexJ) {
        char substituir;
        char[] valores = new char[10];
        int numValores = 0;

        if (indexI < tamanho) {
            if (entradaCharArray[indexI] == ')') {
                if (entradaCharArray[indexJ] == '(') {
                    if (entradaCharArray[indexJ - 1] == 't') {
                        valores[numValores] = entradaCharArray[indexJ + 1];
                        numValores++;

                        substituir = not(valores, numValores);
                        entradaCharArray[indexJ - 3] = substituir;
                        for (int k = 0; k < numValores + 4; k++) {
                            for (int w = indexJ - 2; w < tamanho - 1; w++) {
                                entradaCharArray[w] = entradaCharArray[w + 1];
                            }
                            entradaCharArray[tamanho - k] = '\0';
                        }
                        tamanho -= numValores + 4;
                        valores = new char[10];
                        algebraBooleana(entradaCharArray, tamanho, 0, tamanho);
                    } else if (entradaCharArray[indexJ - 1] == 'd') {
                        for (int k = indexJ + 1; k < indexI; k++) {
                            valores[numValores] = entradaCharArray[k];
                            numValores++;
                        }
                        substituir = and(valores, numValores);
                        entradaCharArray[indexJ - 3] = substituir;

                        for (int k = 0; k < numValores + 4; k++) {
                            for (int w = indexJ - 2; w < tamanho - 1; w++) {
                                entradaCharArray[w] = entradaCharArray[w + 1];
                            }
                            entradaCharArray[tamanho - k] = '\0';
                        }

                        tamanho -= numValores + 4;
                        valores = new char[10];
                        algebraBooleana(entradaCharArray, tamanho, 0, tamanho);
                    } else if (entradaCharArray[indexJ - 1] == 'r') {
                        for (int k = indexJ + 1; k < indexI; k++) {
                            valores[numValores] = entradaCharArray[k];
                            numValores++;
                        }
                        substituir = or(valores, numValores);
                        entradaCharArray[indexJ - 2] = substituir;

                        for (int k = 0; k < numValores + 3; k++) {
                            for (int w = indexJ - 1; w < tamanho - 1; w++) {
                                entradaCharArray[w] = entradaCharArray[w + 1];
                            }
                            entradaCharArray[tamanho - k] = '\0';
                        }
                        tamanho -= numValores + 3;
                        valores = new char[10];
                        algebraBooleana(entradaCharArray, tamanho, 0, tamanho);
                    }
                } else if (indexJ > 0) {
                    valores = new char[10];
                    algebraBooleana(entradaCharArray, tamanho, indexI, indexJ - 1);
                }
            } else if (indexI < tamanho) {
                valores = new char[10];
                algebraBooleana(entradaCharArray, tamanho, indexI + 1, tamanho);
            }
        }
        return entradaCharArray[0];
    }

    public static boolean isFim(int numQuantidade) {
        boolean fim = true;
        if (numQuantidade == 0) {
            fim = false;
        }
        return fim;
    }
}
