import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Jogador {

    public static void main(String[] args) {
        try {
            Jogador jogador = new Jogador();
            Lista lista = new Lista();
            Map<Integer, Jogador> mapper = new HashMap<Integer, Jogador>();
            jogador.ler("/tmp/players.csv", mapper);

            Scanner sc = new Scanner(System.in);
            String entrada = "";

            while (true) {
                entrada = sc.nextLine();
                if(entrada.equals("FIM")) break;
                lista.inserirFim(mapper.get(Integer.parseInt(entrada)));
            }

            int number = Integer.parseInt(sc.nextLine());

            for(int i = 0; i < number; i++){
                entrada = sc.nextLine();
                String[] metodo = entrada.split(" ");
                switch(metodo[0]){
                    case "II":
                        lista.inserirInicio(mapper.get(Integer.parseInt(metodo[1])));
                        break;
                    case "IF":
                        lista.inserirFim(mapper.get(Integer.parseInt(metodo[1])));
                        break;
                    case "I*":
                        lista.inserir(mapper.get(Integer.parseInt(metodo[2])), Integer.parseInt(metodo[1]));
                        break;
                    case "RI":
                        Jogador removidoInicio = lista.removerInicio();
                        System.out.println("(R) " + removidoInicio.getNome());
                        break;
                    case "RF":
                        Jogador removidoFim = lista.removerFim();
                        System.out.println("(R) " + removidoFim.getNome());
                        break;
                    case "R*":
                        Jogador removido = lista.remover(Integer.parseInt(metodo[1]));
                        System.out.println("(R) " + removido.getNome());
                        break;
                }
            }
            lista.mostrar();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {

    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
            String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public Jogador(int id) {
        this.id = id;
        this.nome = "Não informado";
        this.altura = 0;
        this.peso = 0;
        this.universidade = "Não informado";
        this.anoNascimento = 0;
        this.cidadeNascimento = "Não informado";
        this.estadoNascimento = "Não informado";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    public void imprimir() {
        System.out.println("[" + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " +
                anoNascimento + " ## " + universidade + " ## " + cidadeNascimento + " ## " +
                estadoNascimento + "]");
    }

    protected Jogador clone() throws CloneNotSupportedException {
        Jogador novo = new Jogador();
        novo.id = this.id;
        novo.altura = this.altura;
        novo.peso = this.peso;
        novo.universidade = this.universidade;
        novo.anoNascimento = this.anoNascimento;
        novo.cidadeNascimento = this.cidadeNascimento;
        novo.estadoNascimento = this.estadoNascimento;
        return novo;
    }

    public void ler(String nomeDoArquivo, Map<Integer, Jogador> mapper) throws Exception {
        FileReader file = new FileReader(nomeDoArquivo);
        BufferedReader buffer = new BufferedReader(file);

        String line = buffer.readLine();
        String player[] = new String[10];

        while (line != null) {
            line = buffer.readLine();
            if (line == null) {
                break;
            }
            player = line.split(Pattern.quote(","));

            Jogador jogador = new Jogador();
            jogador.setId(Integer.parseInt(player[0]));

            jogador.setNome(player.length > 1 && !player[1].isEmpty() ? player[1] : "nao informado");
            jogador.setAltura(player.length > 2 && !player[2].isEmpty() ? Integer.parseInt(player[2]) : 0);
            jogador.setPeso(player.length > 3 && !player[3].isEmpty() ? Integer.parseInt(player[3]) : 0);
            jogador.setUniversidade(player.length > 4 && !player[4].isEmpty() ? player[4] : "nao informado");

            try {
                jogador.setAnoNascimento(player.length > 5 && !player[5].isEmpty() ? Integer.parseInt(player[5]) : 0);
                jogador.setCidadeNascimento(player.length > 6 && !player[6].isEmpty() ? player[6] : "nao informado");
                jogador.setEstadoNascimento(player.length > 7 && !player[7].isEmpty() ? player[7] : "nao informado");
            } catch (NumberFormatException e) {
                String str = "";
                str += player[4];
                str += ", ";
                if (player.length > 5 && !player[5].isEmpty()) {
                    str += player[5];
                } else {
                    str += "";
                }
                jogador.setUniversidade(str);
                jogador.setAnoNascimento(player.length > 6 && !player[6].isEmpty() ? Integer.parseInt(player[6]) : 0);
                jogador.setCidadeNascimento(player.length > 7 && !player[7].isEmpty() ? player[7] : "nao informado");
                jogador.setEstadoNascimento(player.length > 8 && !player[8].isEmpty() ? player[8] : "nao informado");
            }

            mapper.put(jogador.getId(), jogador);
        }
        buffer.close();
    }
}

class Lista {
    public Celula primeiro;
    public Celula ultimo;

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserirInicio(Jogador jogador){
        Celula tmp = new Celula(jogador);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo){
            ultimo = tmp;
        }
        tmp = null;
    }

    public void inserirFim(Jogador jogador){
        ultimo.prox = new Celula(jogador);
        ultimo = ultimo.prox;
    }
    
    public void inserir(Jogador jogador, int pos) throws Exception{
        int tamanho = tamanho();

        if(pos < 0 || pos > tamanho){
            throw new Exception("Erro ao inserir, posição inválida!");
        }else if(pos == 0){
            inserirInicio(jogador);
        }else if(pos == tamanho){
            inserirFim(jogador);
        }else{
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula(jogador);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    public Jogador removerInicio() throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Erro ao remover (vazia)!");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Jogador player = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
        return player;
    }

    public Jogador removerFim() throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Erro ao remover (vazia)!");
        }

        Celula i;
        for(i = primeiro; i.prox != ultimo; i = i.prox);

        Jogador player = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;
        return player;
    }

    public Jogador remover(int pos) throws Exception{
        Jogador player;
        int tamanho = tamanho();

        if(primeiro == ultimo || pos < 0 || pos >= tamanho){
            throw new Exception("Erro ao remover (vazia)!");
        }else if(pos == 0){
            player = removerInicio();
        }else if(pos == tamanho - 1){
            player = removerFim();
        }else{
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);

            Celula tmp = i.prox;
            player = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }
        return player;
    }


    public int tamanho() {
      int tamanho = 0; 
      for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }

   public void mostrar(){
        int index = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.println("[" + index + "] ## " + i.elemento.getNome() + " ## " + i.elemento.getAltura() + " ## " + i.elemento.getPeso() + " ## " +
                i.elemento.getAnoNascimento() + " ## " + i.elemento.getUniversidade() + " ## " + i.elemento.getCidadeNascimento() + " ## " +
                i.elemento.getEstadoNascimento() + " ##");
            index++;
        }
    }
}

class Celula{
    public Jogador elemento;
    public Celula prox;

    public Celula(){
        this(new Jogador());
    }

    public Celula(Jogador elemento){
        this.elemento = elemento;
        this.prox = null;
    }
}