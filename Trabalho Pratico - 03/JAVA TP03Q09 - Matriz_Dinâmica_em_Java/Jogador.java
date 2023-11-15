import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

class Jogador{

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

     public static void main(String[] args) {
        try {
            Jogador jogador = new Jogador();
            Pilha pilha = new Pilha();
            Map<Integer, Jogador> mapper = new HashMap<Integer, Jogador>();
            jogador.ler("/tmp/players.csv", mapper);

            Scanner sc = new Scanner(System.in);
            String entrada = "";

            while (true) {
                entrada = sc.nextLine();
                if(entrada.equals("FIM")) break;
                pilha.inserir(mapper.get(Integer.parseInt(entrada)));
            }

            int number = Integer.parseInt(sc.nextLine());

            for(int i = 0; i < number; i++){
                entrada = sc.nextLine();
                String[] metodo = entrada.split(" ");
                switch(metodo[0]){
                    case "I":
                        pilha.inserir(mapper.get(Integer.parseInt(metodo[1])));
                        break;
                    case "R":
                        Jogador removido = pilha.remover();
                        System.out.println("(R) " + removido.getNome());
                        break;
                }
            }
            pilha.mostrar();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Matriz {
    private Celula inicio;
    private int linha, coluna;

    public Matriz() {
        this(3, 3);
    }

    public Matriz(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
        this.inicio = new Celula();
        Celula tmp = inicio;
        for(int i = 0; i < linha; i++){
            tmp.prox = new Celula();
            tmp = tmp.prox;
            for(int j = 0; j < coluna; j++){
                tmp.prox = new Celula();
                tmp = tmp.prox;
            }
        }
    }

    public Matriz soma (Matriz m){
        Matriz resp = null;
        if(this.linha == m.linha && this.coluna == m.coluna){
            resp = new Matriz(this.linha, this.coluna);
            Celula tmp = resp.inicio;
            Celula tmp1 = this.inicio;
            Celula tmp2 = m.inicio;
            for(int i = 0; i < this.linha; i++){
                tmp = tmp.prox;
                tmp1 = tmp1.prox;
                tmp2 = tmp2.prox;
                for(int j = 0; j < this.coluna; j++){
                    tmp.elemento = tmp1.elemento + tmp2.elemento;
                    tmp = tmp.prox;
                    tmp1 = tmp1.prox;
                    tmp2 = tmp2.prox;
                }
            }
        }
        return resp;
    }


}

class Celula {
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