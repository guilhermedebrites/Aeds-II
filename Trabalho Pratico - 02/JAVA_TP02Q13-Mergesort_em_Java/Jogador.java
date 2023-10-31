import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Jogador {

    public static void main(String[] args) {
        try {
            Jogador jogador = new Jogador();
            ArrayList<Jogador> players = new ArrayList<Jogador>();
            ArrayList<Jogador> playersInseridos = new ArrayList<Jogador>();
            jogador.ler("/tmp/players.csv", players);

            Scanner sc = new Scanner(System.in);
            String entrada = "";
            while (!entrada.equals("FIM")) {
                entrada = sc.nextLine();
                if (entrada.equals("FIM")) {
                    break;
                }
                for (int i = 0; i < (players.size() - 1); i++) {
                    if (players.get(i).getId() == Integer.parseInt(entrada)) {
                        playersInseridos.add(players.get(i).clone());
                        break;
                    }
                }
            }
            mergesort(playersInseridos, 0, playersInseridos.size() - 1);
            garanteOrdem(playersInseridos);
            for (int i = 0; i < playersInseridos.size(); i++) {
                playersInseridos.get(i).imprimir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trocar(ArrayList<Jogador> jogadores, int i, int j) {
        Jogador temp = jogadores.get(i);
        jogadores.set(i, jogadores.get(j));
        jogadores.set(j, temp);
    }

    public static void garanteOrdem(ArrayList<Jogador> jogadores) {
        for (int i = 0; i < jogadores.size(); i++) {
            for (int j = i + 1; j < jogadores.size(); j++) {
                if (jogadores.get(i).getUniversidade().compareTo(jogadores.get(j).getUniversidade()) == 0) {
                    if (jogadores.get(i).nome.compareTo(jogadores.get(j).nome) > 0) {
                        trocar(jogadores, i, j);
                    }
                }
            }
        }
    }

    public static void ordernarMergeSort(ArrayList<Jogador> jogadores, int esq, int meio, int dir) throws IOException {

        FileWriter escritor = new FileWriter("808721_mergesort.txt");
        BufferedWriter buffer = new BufferedWriter(escritor);

        LocalDateTime dataHoraInicio = LocalDateTime.now();
        int contadorComparacoes = 0;
        int n1 = meio - esq + 1;
        int n2 = dir - meio;

        ArrayList<Jogador> a1 = new ArrayList<>();
        ArrayList<Jogador> a2 = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            a1.add(jogadores.get(esq + i));
        }

        for (int j = 0; j < n2; j++) {
            a2.add(jogadores.get(meio + j + 1));
        }

        int i = 0, j = 0, k = esq;
        while (i < n1 && j < n2) {
            if (a1.get(i).getUniversidade().compareTo(a2.get(j).getUniversidade()) <= 0) {
                jogadores.set(k, a1.get(i));
                i++;
            } else {
                jogadores.set(k, a2.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            jogadores.set(k, a1.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            jogadores.set(k, a2.get(j));
            j++;
            k++;
        }

        buffer.write("Matricula: 808721\t");
        LocalDateTime dataHoraFinal = LocalDateTime.now();
        Duration duracao = Duration.between(dataHoraInicio, dataHoraFinal);
        long duracaoMillis = duracao.toMillis();
        buffer.write("Tempo de execucao: " + duracaoMillis + "s\t");
        buffer.write("Numero de comparacoes: " + contadorComparacoes + "\t");
        buffer.close();
    }

    public static void mergesort(ArrayList<Jogador> jogadores, int esq, int dir) throws IOException {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesort(jogadores, esq, meio);
            mergesort(jogadores, meio + 1, dir);
            ordernarMergeSort(jogadores, esq, meio, dir);
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
        novo.nome = this.nome;
        novo.altura = this.altura;
        novo.peso = this.peso;
        novo.universidade = this.universidade;
        novo.anoNascimento = this.anoNascimento;
        novo.cidadeNascimento = this.cidadeNascimento;
        novo.estadoNascimento = this.estadoNascimento;
        return novo;
    }

    public void ler(String nomeDoArquivo, ArrayList<Jogador> array) throws Exception {
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
            array.add(jogador);
        }
        buffer.close();
    }
}