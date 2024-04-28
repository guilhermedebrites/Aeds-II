import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Personagem{

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
    }

    public Personagem(){};

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

    public void imprimir(){
        String alternateNamesStr = "{";
        if (alternate_names != null && !alternate_names.isEmpty()) {
            for(int i = 0; i < alternate_names.size(); i++){
                alternateNamesStr += alternate_names.get(i) + ",";
            }
            alternateNamesStr = alternateNamesStr.replace("],", "}");
            alternateNamesStr = alternateNamesStr.replace("[", "");
        }else{
            alternateNamesStr = "{}";
        }

        DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = dateOfBirth.format(desiredFormatter);

        System.out.println("[" + id + " ## " + name + " ## " + alternateNamesStr + " ## " + house
                        + " ## " + ancestry + " ## " + species + " ## " + patronus
                        + " ## " + hogwartsStaff + " ## " + hogwartsStudent + " ## "
                        + actorName + " ## " + alive + " ## " + formattedDate + " ## "
                        + yearOfBirth + " ## " + eyeColour + " ## " + gender + " ## "
                        + hairColour + " ## " + wizard + "]");
    }

    public static void main(String args[]){
        String nomeDoArquivo = "/tmp/characters.csv";
        
        Map<UUID, Personagem> personagens = new HashMap<>();
        ArrayList<Personagem> arrayPersonagens = new ArrayList<Personagem>();

        Personagem personagem = new Personagem();
        personagem.ler(nomeDoArquivo, personagens);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String entrada = "";
        try{
            entrada = br.readLine();
            while(!entrada.equals("FIM")){
                arrayPersonagens.add(personagens.get(UUID.fromString(entrada)));
                entrada = br.readLine();
            }

            mergesort(arrayPersonagens, 0 , arrayPersonagens.size() - 1);
            // selectionSort(arrayPersonagens);
            for(Personagem p : arrayPersonagens){
                p.imprimir();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ler(String nomeDoArquivo, Map<UUID, Personagem> mapper){
        try{
            FileReader file = new FileReader(nomeDoArquivo);
            BufferedReader buffer = new BufferedReader(file);

            String line = buffer.readLine();
            String atributos[] = new String[20];

            line = buffer.readLine();
            while(line != null){
                atributos = line.split(Pattern.quote(";"));
                Personagem newPersonagem = new Personagem();

                newPersonagem.setId(UUID.fromString(atributos[0]));
                newPersonagem.setName(atributos[1]);

                ArrayList<String> alternates_names = new ArrayList<String>();
                String namesAlternativos[] = new String[10];
                namesAlternativos = atributos[2].split(Pattern.quote(","));

                for(int i = 0; i < namesAlternativos.length; i++){
                    alternates_names.add(namesAlternativos[i].replace("'", ""));
                }

                newPersonagem.setAlternate_names(alternates_names);
                newPersonagem.setHouse(atributos[3]);
                newPersonagem.setAncestry(atributos[4]);
                newPersonagem.setSpecies(atributos[5]);
                newPersonagem.setPatronus(atributos[6]);
                newPersonagem.setHogwartsStaff(atributos[7].equals("VERDADEIRO") ? true : false);
                newPersonagem.setHogwartsStudent(atributos[8].equals("VERDADEIRO") ? true : false);
                newPersonagem.setActorName(atributos[9]);
                newPersonagem.setAlive(atributos[10].equals("VERDADEIRO") ? true : false);

                LocalDate date = tentaPatternsDiferentes(atributos[12]);
                newPersonagem.setDateOfBirth(date);

                newPersonagem.setYearOfBirth(Integer.parseInt(atributos[13]));
                newPersonagem.setEyeColour(atributos[14]);
                newPersonagem.setGender(atributos[15]);
                newPersonagem.setHairColour(atributos[16]);
                newPersonagem.setWizard(atributos[17].equals("VERDADEIRO") ? true : false);

                mapper.put(newPersonagem.getId(), newPersonagem);
                line = buffer.readLine();
            }
            buffer.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void selectionSort(ArrayList<Personagem> personagems) {
        for (int i = 0; i < personagems.size(); i++) {
            for (int j = i + 1; j < personagems.size(); j++) {
                if (personagems.get(i).getYearOfBirth() == personagems.get(j).getYearOfBirth()) {
                    if (personagems.get(i).getName().compareTo(personagems.get(j).getName()) > 0) {
                        Personagem temp = personagems.get(i);
                        personagems.set(i, personagems.get(j));
                        personagems.set(j, temp);
                    }
                }
            }
        }
    }
    
    public static void ordernarMergeSort(ArrayList<Personagem> personagens, int esq, int meio, int dir) throws IOException {
        int n1 = meio - esq + 1;
        int n2 = dir - meio;

        ArrayList<Personagem> a1 = new ArrayList<>();
        ArrayList<Personagem> a2 = new ArrayList<>();

        for (int i = 0; i < n1; i++) {
            a1.add(personagens.get(esq + i));
        }

        for (int j = 0; j < n2; j++) {
            a2.add(personagens.get(meio + j + 1));
        }

        int i = 0, j = 0, k = esq;
        while (i < n1 && j < n2) {
            if (a1.get(i).getActorName().compareTo(a2.get(j).getActorName()) < 0) {
                personagens.set(k, a1.get(i));
                i++;
            } else if(a1.get(i).getActorName().compareTo(a2.get(j).getActorName()) == 0){
                if(a1.get(i).getName().compareTo(a2.get(j).getName()) < 0){
                    personagens.set(k, a1.get(i));
                    i++;
                }else{
                    personagens.set(k, a2.get(j));
                    j++;
                }
            }else {
                personagens.set(k, a2.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            personagens.set(k, a1.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            personagens.set(k, a2.get(j));
            j++;
            k++;
        }
    }

    public static void mergesort(ArrayList<Personagem> personagens, int esq, int dir) throws IOException {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesort(personagens, esq, meio);
            mergesort(personagens, meio + 1, dir);
            ordernarMergeSort(personagens, esq, meio, dir);
        }
    }

    public static LocalDate tentaPatternsDiferentes(String dateString){
        String[] patterns = {"dd-MM-yyyy", "dd-M-yyyy"};

        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e){}
        }

        throw new DateTimeParseException("Não foi possível converter a string em uma data"+ dateString, dateString, 0);
    }
}