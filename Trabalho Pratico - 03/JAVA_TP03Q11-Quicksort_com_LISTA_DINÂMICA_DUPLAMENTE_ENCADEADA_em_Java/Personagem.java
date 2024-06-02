import java.util.*;

import javax.swing.text.StyledEditorKit.BoldAction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import java.time.format.DateTimeFormatter;

class Celula{
    Personagem elemento;
    Celula prox;
    Celula ant;
    Celula(){
        this(null);
    }
    Celula(Personagem elemento){
        this.elemento = elemento;
        prox = ant = null;
    }
}

class Lista{
    Celula inicio, fim;
    Lista(){
        inicio = new Celula();
        fim = inicio;
    }

    public void inserirFim(Personagem elemento){
        fim.prox = new Celula(elemento);
        fim.prox.ant = fim;
        fim = fim.prox;
    }

    public void inserirInicio(Personagem elemento){
        Celula temp = new Celula(elemento);
        temp.ant = inicio;
        temp.prox = inicio.prox;
        inicio.prox = temp;
        if(inicio == fim){
            fim = temp;
        }else{
            temp.prox.ant = temp;
        }
        temp = null;
    }

    public int tamanho(){
        int contador = 0;
        for(Celula i = inicio.prox; i != null; contador++, i = i.prox);
        return contador;
    }

    public void inserir(Personagem elemento, int pos)throws Exception{
        int tamanho = tamanho();
        if(pos < 0 || pos > tamanho){ throw new Exception("ERRO!");
        }else if(pos == 0){inserirInicio(elemento);
        }else if(pos == tamanho){inserirFim(elemento);
        }else{
            Celula i = inicio;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula temp = new Celula(elemento);
            temp.ant = i;
            temp.prox = i.prox;
            temp.ant.prox = temp.prox.ant = temp;
            temp = i = null;
    
        }
    }

    public void removerFim()throws Exception{
        if(inicio == fim)
            throw new Exception("Erro");
            System.out.println("(R) " + fim.elemento.getName());
            fim = fim.ant;
            fim.prox.ant = null;
            fim.prox = null;
    }



    public void removerInicio()throws Exception{
        if(inicio == fim)
            throw new Exception("Erro ao remover");
        Celula temp = inicio;
        inicio = inicio.prox;
        System.out.println("(R) " + inicio.elemento.getName());
        temp.prox = inicio.ant = null;
        temp = null;
    }

    
    public void remover(int pos)throws Exception{
        int tamanho = tamanho();
        if(inicio == fim || pos < 0 || pos >= tamanho){throw new Exception("ERRO");
    }else if(pos == 0){ removerInicio();
    }else if(pos == tamanho - 1){   removerFim();
    }else{
        Celula i = inicio.prox;
        for(int j = 0; j < pos; j++, i = i.prox);
        i.ant.prox = i.prox;
        i.prox.ant = i.ant;
        System.out.println("(R) " + i.elemento.getName());
        i.prox = i.ant = null;
        i = null;
    }
    }

    public void quicksort(){
        quicksort(inicio.prox, fim);
    }

    private void quicksort(Celula esq, Celula dir){
        if (esq != null && dir != null && esq != dir && esq.ant != dir) {
            Celula pivo = partition(esq, dir);
            quicksort(esq, pivo.ant);
            quicksort(pivo.prox, dir);
        }
    }

    private Celula partition(Celula esq, Celula dir){
        Celula i = esq.ant;
        for (Celula j = esq; j != dir; j = j.prox){
            if (((Personagem.statecmpr(j.elemento, dir.elemento)) < 0) || ((Personagem.statecmpr(j.elemento, dir.elemento) == 0) && (Personagem.strcmpr (j.elemento, dir.elemento)) < 0)) {
                i = (i == null) ? esq : i.prox;
                swap(i, j);
        }
    }
    i = (i == null) ? esq : i.prox;
    swap(i, dir);
    return i;
    }
    
    private void swap(Celula i, Celula j) {
        Personagem temp = i.elemento;
        i.elemento = j.elemento;
        j.elemento = temp;
    }

    public void imprimirLista(){
        int contador = 0;
        for(Celula i = inicio.prox; i != null; i = i.prox){
            System.out.print("["); i.elemento.imprimir();
        }
    }


}

class Fila {

    public Personagem[] array;
    public int n;

    public Fila() {
        this(6);
    }

    public Fila(int tamanho) {
        array = new Personagem[tamanho];
        n = 0;
    }

    public void inserirFim(Personagem personagem) throws Exception {
        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }
        array[n++] = personagem;
    }

    void inserirInicio(Personagem personagem)throws Exception{
        if(n >= array.length){
            throw new Exception("ERRO AO INSERIR NO INCIO!");
        }
        
        for(int i = n; i > 0; i--){
            array[i] = array[i-1];
        }
        array[0] = personagem;
        n++;
    }

    void inserir(Personagem personagem, int pos)throws Exception{

        if(n >= array.length || pos < 0 || pos > n)
            throw new Exception("ERRO AO INSERIR POS!");

        
        for(int i = n; i > pos; i--){
            array[i] = array[i - 1];
        }
        array[pos] = personagem;
        n++;
    }

    Personagem removerInicio()throws Exception{
        if(n == 0)
            throw new Exception("ERRO AO REMOVER NO INICIO!");
        
            Personagem resp = array[0];
            n--;

            for(int i = 0; i < n; i++){
                array[i] = array[i+1];
            }
           System.out.println("(R) " + resp.getName());
            
            return resp;
    }

    Personagem removerFim()throws Exception{
        if(n == 0)
            throw new Exception("ERRO AO REMOVER NO FIM!");
            int i = n - 1;
           System.out.println("(R) " + array[i].getName());
            return array[--n];
    }

    Personagem remover(int pos)throws Exception{
        if(n == 0)
            throw new Exception("ERRO AO REMOVER POS!");
            Personagem resp = array[pos];
            n--;
            for(int i = pos; i < n; i++){
                array[i] = array[i+1];
            }
          System.out.println("(R) " + resp.getName());
            return resp;
    }




    public void imprimirLista() {
        for (int i = 0; i < n; i++) {

           System.out.print("["); array[i].imprimir();
        }
    }

   

    

 

}


class Personagem{

    public static final String FILE = "/tmp/characters.csv";
    public static ArrayList<Personagem> list = new ArrayList<Personagem>();
    int contador = 0;

    private String id, name, house, ancestry;
    private String especies, patronus;
    private String  actorName, eyeColour, gender, hairColour;
    private String[] alternate_names;
    private Boolean hogwartsStaff, alive, wizard, hogwartsStudent;
    private int yearOfBirth;
    public LocalDate dateOfBirth;


    Personagem(){

        id = "";
        name = "";
        house = "";
        ancestry = "";
        especies = "";
        patronus = "";
        hogwartsStudent = null;
        actorName = "";
        eyeColour = "";
        gender = "";
        hairColour = "";
        alternate_names = null;
        hogwartsStaff = false;
        alive = false;
        wizard = false;
        yearOfBirth = 0;
        dateOfBirth = LocalDate.now();
    }

     Personagem(String id, String name, String house, String ancestry, String especies, String patronus, Boolean hogwartsStudent, String actorName, String eyeColour,
                      String gender, String hairColour, String[] alternate_names,
                      Boolean hogwartsStaff, Boolean alive, Boolean wizard, int yearOfBirth,
                      LocalDate dateOfBirth){
        this.id = id;
        this.name = name;
        this.house = house;
        this.ancestry = ancestry;
        this.especies = especies;
        this.patronus = patronus;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.alternate_names = alternate_names;
        this.hogwartsStaff = hogwartsStaff;
        this.alive = alive;
        this.wizard = wizard;
        this.yearOfBirth = yearOfBirth;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId(){return this.id;}
    public String getName(){return this.name;}
    public String getHouse(){return this.house;}
    public String getAncestry(){return this.ancestry;}
    public String getEspecies(){return this.especies;}
    public String getPatronus(){return this.patronus;}
    public String getActorName(){return this.actorName;}
    public String getEyeColour(){return this.eyeColour;}
    public String getGender(){return this.gender;}
    public String getHairColour(){return this.hairColour;}
    public String[] getAlternate_names(){return this.alternate_names;}
    public Boolean getHogwartsStaff(){return this.hogwartsStaff;}
    public Boolean getHogwartsStudent(){return this.hogwartsStudent;}
    public Boolean getAlive(){return this.alive;}
    public Boolean getWizard(){return this.wizard;}
    public int getYearOfBirth(){return this.yearOfBirth;}
    public LocalDate dateOfBirth(){return this.dateOfBirth;}

    public void setId(String id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setHouse(String house){this.house = house;}
    public void setAncestry(String ancestry){this.ancestry = ancestry;}
    public void setEspecies(String especies){this.especies = especies;}
    public void setPatronus(String patronus){this.patronus = patronus;}
    public void setActorName(String actorName){this.actorName = actorName;}
    public void setEyeColour(String eyeColour){this.eyeColour = eyeColour;}
    public void setGender(String gender){this.gender = gender;}
    public void setHairColour(String hairColour){this.hairColour = hairColour;}
    public void setHairColour(String[] alternate_names){this.alternate_names = alternate_names;}
    public void setHogwartsStaff(Boolean hogwartsStaff){this.hogwartsStaff = hogwartsStaff;}
    public void setHogwartsStudent(Boolean hogwartsStudent){this.hogwartsStudent = hogwartsStudent;}
    public void setAlive(Boolean alive){this.alive = alive;}
    public void setWizard(Boolean wizard){this.wizard = wizard;}
    public void setYearOfBirth(int yearOfBirth){this.yearOfBirth = yearOfBirth;}
    public void setWizard(LocalDate dateOfBirth){this.dateOfBirth = dateOfBirth;}

    static String[] retiraAlternate_name(String line){

        int inicio = line.indexOf("[");
        int fim = line.indexOf("]");

        if(inicio == fim - 1){

            return null;

        }else{

            line = line.substring(1, line.length() - 1);

        String[] aux = line.split(", ");
        String[] resp = new String[aux.length];
        
        for(int i = 0; i < aux.length; i++){

            resp[i] = aux[i].substring(1, aux[i].length() - 1);

        }
        return resp;
        }
    }


    public static String[] lerAlternate_names(String linha){

        int inicio = linha.indexOf("[");
        int fim = linha.indexOf("]");

        if(inicio == fim - 1){
            return null;
        }else{

        linha = linha.substring(inicio + 1, fim);

        String[] aux = linha.split(", ");

        String[] resp = new String[aux.length];

            for(int i = 0; i < aux.length; i++){
                resp[i] = aux[i].substring(1, aux[i].length() - 1);
            }

        return resp;

        }

    }

    public void ler(String linha){

        String[] split = linha.split(";", -1);

        

        this.id = split[0];
        this.name = split[1];
        this.alternate_names = retiraAlternate_name(split[2]);
        
        this.house = split[3];
        this.ancestry = split[4];
        this.especies = split[5];
        this.patronus = split[6];

        this.hogwartsStaff = (split[7].equals("VERDADEIRO")) ? true : false;
        this.hogwartsStudent = (split[8].equals("VERDADEIRO")) ? true : false;
        this.actorName = split[9];
        this.alive = (split[10].equals("VERDADEIRO")) ? true : false;
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-M-yyyy");
        this.dateOfBirth = LocalDate.parse(split[12], formater);
        this.yearOfBirth = Integer.parseInt(split[13]);
        this.eyeColour = split[14];
        this.gender = split[15];
        this.hairColour = split[16];
        this.wizard = (split[17].equals("VERDADEIRO")) ? true : false;

    }

    public static int statecmpr(Personagem player1, Personagem player2)
    {
        return player1.getHouse().compareTo(player2.getHouse());
    }

    public static int strcmpr(Personagem player1, Personagem player2)
    {
        return player1.getName().compareTo(player2.getName());
    }

    public String printAlternateNames(String[] alternateNames){

        String resultado = "";

        if(alternateNames == null){
            resultado = "{}";
            return resultado;
        }else{

            resultado += "{";

            for(String s : alternateNames){
                resultado += s + ", ";
            }

            resultado = resultado.substring(0, resultado.length()-2);
            resultado += "}";
        }

        return resultado;

    }


    public void imprimir(){

        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String data = dateOfBirth.format(formater);

        System.out.println(this.id + " ## " + this.name + " ## " + printAlternateNames(alternate_names) + " ## " + this.house + " ## " + this.ancestry + " ## " + this.especies + " ## " + this.patronus + " ## " + 
        this.hogwartsStaff + " ## " + this.hogwartsStudent + " ## " + this.actorName + " ## " + this.alive + " ## " + data + " ## " + this.yearOfBirth + " ## " + 
        this.eyeColour + " ## " + this.gender + " ## " + this.hairColour + " ## " + this.wizard + "]");
        
    }

    public static void startPlayers(){
            
        try{

            FileInputStream arquivo = new FileInputStream(FILE);
            BufferedReader br = new BufferedReader(new InputStreamReader(arquivo));

            String linha = br.readLine();


             
            while((linha = br.readLine()) != null){

                Personagem personagem = new Personagem();
                
                personagem.ler(linha);

                list.add(personagem);

            }
            

            arquivo.close();

        }catch(IOException e) {e.printStackTrace();}

    }

    public static Personagem persquisar(String id, ArrayList<Personagem> personagem){

        for(int i = 0; i < personagem.size(); i++){

            if(personagem.get(i).getId().equals(id)) return personagem.get(i);
        }

        return null;
    }



    public static boolean pesquisaSequencial(Personagem[] personagens, String nome) {
        for (int i = 0; i < personagens.length; i++) {
            if (personagens[i] != null && personagens[i].getName().equals(nome)) {
                return true; 
            }
        }
        return false; 
    }

    public static void main(String[] args) throws Exception {

        startPlayers();

        Scanner sc = new Scanner(System.in);

        Personagem personagem = new Personagem();

        Fila fila = new Fila(200);
        Lista lista = new Lista();
        String linha = sc.nextLine();

        while (!linha.equals("FIM")) {
            String id = linha;
            // Pesquisa e retorna o personagem
            personagem = persquisar(id, list);
            if (personagem != null)
                lista.inserirFim(personagem);
            else
                System.out.println("nao encontrado");
            linha = sc.nextLine();
        }

        
       lista.quicksort();
        
       lista.imprimirLista();
        sc.close();

    }

}