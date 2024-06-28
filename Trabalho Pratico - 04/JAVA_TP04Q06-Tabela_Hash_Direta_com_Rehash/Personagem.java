import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

public class Personagem {
    private UUID id;
    private String name;
    private ArrayList<String> alternate_names;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private boolean hogwartsStudent;
    private String actorName;
    private boolean alive;
    private LocalDate dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;
    private String birthDateString;

    public Personagem(UUID id, String name, ArrayList<String> alternate_names, String house, String ancestry,
                      String species, String patronus, boolean hogwartsStaff, boolean hogwartsStudent, String actorName,
                      boolean alive, LocalDate dateOfBirth, int yearOfBirth, String eyeColour, String gender, String hairColour,
                      boolean wizard) {
        this.id = id;
        this.name = name;
        this.alternate_names = alternate_names;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStaff = hogwartsStaff;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.alive = alive;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.wizard = wizard;
        this.birthDateString = dateOfBirth != null ? dateOfBirth.toString() : "";
    }

    public Personagem() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAlternate_names() {
        return alternate_names;
    }

    public void setAlternate_names(ArrayList<String> alternate_names) {
        this.alternate_names = alternate_names;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public boolean isHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStaff(boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public boolean getHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setHogwartsStudent(boolean hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public boolean isWizard() {
        return wizard;
    }

    public void setWizard(boolean wizard) {
        this.wizard = wizard;
    }

    public String getBirthDateString() {
        return birthDateString;
    }

    public void setBirthDateString(String birthDateString) {
        this.birthDateString = birthDateString;
    }

    public void imprimir(int x) {
        String alternateNamesStr = "{";
        if (alternate_names != null && !alternate_names.isEmpty()) {
            for (int i = 0; i < alternate_names.size(); i++) {
                alternateNamesStr += alternate_names.get(i) + ",";
            }
            alternateNamesStr = alternateNamesStr.replace("],", "}");
            alternateNamesStr = alternateNamesStr.replace("[", "");
        } else {
            alternateNamesStr = "{}";
        }

        DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = dateOfBirth != null ? dateOfBirth.format(desiredFormatter) : "N/A";

        System.out.println("[" + x + " ## " + id + " ## " + name + " ## " + alternateNamesStr + " ## " + house
                + " ## " + ancestry + " ## " + species + " ## " + patronus
                + " ## " + hogwartsStaff + " ## " + hogwartsStudent + " ## "
                + actorName + " ## " + alive + " ## " + formattedDate + " ## "
                + yearOfBirth + " ## " + eyeColour + " ## " + gender + " ## "
                + hairColour + " ## " + wizard + "]");
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // Início do tempo de execução
        int numComparacoes = 0;

        String nomeDoArquivo = "/tmp/characters.csv";

        Map<UUID, Personagem> personagens = new HashMap<>();
        TabelaHashDiretaComRehash tabelaHash = new TabelaHashDiretaComRehash(21);

        Personagem personagem = new Personagem();
        personagem.ler(nomeDoArquivo, personagens);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String entrada = "";
        try {
            entrada = br.readLine();
            while (!entrada.equals("FIM")) {
                Personagem perso = personagens.get(UUID.fromString(entrada));
                tabelaHash.inserir(perso);
                entrada = br.readLine();
            }

            entrada = br.readLine();
            while (!entrada.equals("FIM")) {
                String resultado = tabelaHash.pesquisar(entrada);
                numComparacoes += tabelaHash.getNumComparacoes();
                System.out.println(resultado);
                entrada = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis(); // Fim do tempo de execução
        long executionTime = endTime - startTime; // Tempo total de execução

        // Escrever informações no arquivo de log
        String logFilename = "808721_hashRehash.txt";
        try (FileWriter logFile = new FileWriter(logFilename)) {
            logFile.write("808721\t" + executionTime + " ms\t" + numComparacoes + " comparações\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ler(String nomeDoArquivo, Map<UUID, Personagem> mapper) {
        try {
            FileReader file = new FileReader(nomeDoArquivo);
            BufferedReader buffer = new BufferedReader(file);

            String line = buffer.readLine();
            while ((line = buffer.readLine()) != null) {
                String[] atributos = line.split(Pattern.quote(";"));
                Personagem newPersonagem = new Personagem();

                newPersonagem.setId(UUID.fromString(atributos[0]));
                newPersonagem.setName(atributos[1]);

                ArrayList<String> alternates_names = new ArrayList<>();
                String[] namesAlternativos = atributos[2].split(Pattern.quote(","));

                for (String namesAlternativo : namesAlternativos) {
                    alternates_names.add(namesAlternativo.replace("'", ""));
                }

                newPersonagem.setAlternate_names(alternates_names);
                newPersonagem.setHouse(atributos[3]);
                newPersonagem.setAncestry(atributos[4]);
                newPersonagem.setSpecies(atributos[5]);
                newPersonagem.setPatronus(atributos[6]);
                newPersonagem.setHogwartsStaff(atributos[7].equalsIgnoreCase("VERDADEIRO"));
                newPersonagem.setHogwartsStudent(atributos[8].equalsIgnoreCase("VERDADEIRO"));
                newPersonagem.setActorName(atributos[9]);
                newPersonagem.setAlive(atributos[10].equalsIgnoreCase("VERDADEIRO"));

                LocalDate date = tentaPatternsDiferentes(atributos[12]);
                newPersonagem.setDateOfBirth(date);
                newPersonagem.setBirthDateString(atributos[12]);

                newPersonagem.setYearOfBirth(Integer.parseInt(atributos[13]));
                newPersonagem.setEyeColour(atributos[14]);
                newPersonagem.setGender(atributos[15]);
                newPersonagem.setHairColour(atributos[16]);
                newPersonagem.setWizard(atributos[17].equalsIgnoreCase("VERDADEIRO"));

                mapper.put(newPersonagem.getId(), newPersonagem);
            }
            buffer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LocalDate tentaPatternsDiferentes(String dateString) {
        String[] patterns = {"dd-MM-yyyy", "dd-M-yyyy"};

        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Continue tentando com o próximo padrão
            }
        }

        throw new DateTimeParseException("Não foi possível converter a string em uma data " + dateString, dateString, 0);
    }
}

class TabelaHashDiretaComRehash {
    private Personagem[] tabela;
    private int tamTab;
    private int numComparacoes;

    public TabelaHashDiretaComRehash(int tamTab) {
        this.tamTab = tamTab;
        this.tabela = new Personagem[tamTab];
        this.numComparacoes = 0;
    }

    public int getNumComparacoes() {
        return numComparacoes;
    }

    private int calcularHash(String chave) {
        int asciiSum = 0;
        for (char c : chave.toCharArray()) {
            asciiSum += c;
        }
        return asciiSum % tamTab;
    }

    private int calcularRehash(String chave, int tentativa) {
        int asciiSum = 0;
        for (char c : chave.toCharArray()) {
            asciiSum += c;
        }
        return (asciiSum + tentativa) % tamTab;
    }

    public void inserir(Personagem personagem) {
        String nome = personagem.getName();
        int posicao = calcularHash(nome);
        if (tabela[posicao] == null) {
            tabela[posicao] = personagem;
        } else {
            int tentativa = 1;
            int novaPosicao;
            while (tentativa < tamTab) { // Limitar o número de tentativas ao tamanho da tabela
                novaPosicao = calcularRehash(nome, tentativa);
                if (tabela[novaPosicao] == null) {
                    tabela[novaPosicao] = personagem;
                    break;
                }
                tentativa++;
            }
        }
    }

    public String pesquisar(String nome) {
        numComparacoes = 0;
        int posicao = calcularHash(nome);
        numComparacoes++;
        if (tabela[posicao] != null && tabela[posicao].getName().equals(nome)) {
            return nome + " (pos: " + posicao + ") SIM";
        } else {
            int tentativa = 1;
            int novaPosicao;
            while (tentativa < tamTab) { // Limitar o número de tentativas ao tamanho da tabela
                novaPosicao = calcularRehash(nome, tentativa);
                numComparacoes++;
                if (tabela[novaPosicao] == null) {
                    break;
                }
                if (tabela[novaPosicao].getName().equals(nome)) {
                    return nome + " (pos: " + novaPosicao + ") SIM";
                }
                tentativa++;
            }
        }
        return nome + " NAO";
    }
}
