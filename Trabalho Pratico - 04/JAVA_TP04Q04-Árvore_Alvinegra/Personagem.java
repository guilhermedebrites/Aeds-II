import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

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
        Alvinegra arvore = new Alvinegra();

        Personagem personagem = new Personagem();
        personagem.ler(nomeDoArquivo, personagens);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String entrada = "";
        try{
            entrada = br.readLine();
            while(!entrada.equals("FIM")){
                Personagem perso = personagens.get(UUID.fromString(entrada));
                arvore.inserir(perso);
                entrada = br.readLine();
            }

            entrada = br.readLine();
            while(!entrada.equals("FIM")){
                System.out.print(entrada);
                if(arvore.pesquisar(entrada)) {
                    System.out.println( " SIM" );
                } else {
                    System.out.println( " NAO" );
                }
                entrada = br.readLine();
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

class Alvinegra {
    private NoAN raiz; // Raiz da arvore.
 
    public Alvinegra() {
       raiz = null;
    }
 
    public boolean pesquisar(String x) {
       System.out.print(" => raiz");
       return pesquisar(x, raiz);
    }
 
    private boolean pesquisar(String x, NoAN i) {
       boolean resp;
         if (i == null) {
          resp = false;
 
       } else if (x.equals(i.elemento.getName())) {
          resp = true;
 
       } else if (x.compareTo(i.elemento.getName()) < 0) {
          System.out.print(" esq");
          resp = pesquisar(x, i.esq);
 
       } else {
          System.out.print(" dir");
          resp = pesquisar(x, i.dir);
       }
       return resp;
     }
 
    public void caminharCentral() {
       caminharCentral(raiz);
    }
 
    private void caminharCentral(NoAN i) {
       if (i != null) {
          caminharCentral(i.esq); // Elementos da esquerda.
          System.out.print(((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
          i.elemento.imprimir();
          caminharCentral(i.dir); // Elementos da direita.
       }
    }
 
    public void caminharPre() {
       System.out.print("[ ");
       caminharPre(raiz);
       System.out.println("]");
    }
 
    private void caminharPre(NoAN i) {
       if (i != null) {
          System.out.print(((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
          i.elemento.imprimir();
          caminharPre(i.esq); // Elementos da esquerda.
          caminharPre(i.dir); // Elementos da direita.
       }
    }
 
    public void caminharPos() {
       caminharPos(raiz);
    }
 
    private void caminharPos(NoAN i) {
       if (i != null) {
          caminharPos(i.esq); // Elementos da esquerda.
          caminharPos(i.dir); // Elementos da direita.
          System.out.print(((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
          i.elemento.imprimir();
       }
    }
 
    public void inserir(Personagem elemento) throws Exception {
       // Se a arvore estiver vazia
       if (raiz == null) {
          raiz = new NoAN(elemento);
 
       // Senao, se a arvore tiver um elemento
       } else if (raiz.esq == null && raiz.dir == null) {
          if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
             raiz.esq = new NoAN(elemento);
          } else {
             raiz.dir = new NoAN(elemento);
          }
 
       // Senao, se a arvore tiver dois elementos (raiz e dir)
       } else if (raiz.esq == null) {
          if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
             raiz.esq = new NoAN(elemento);
 
          } else if (elemento.getName().compareTo(raiz.dir.elemento.getName()) < 0) {
             raiz.esq = new NoAN(raiz.elemento);
             raiz.elemento = elemento;
 
          } else {
             raiz.esq = new NoAN(raiz.elemento);
             raiz.elemento = raiz.dir.elemento;
             raiz.dir.elemento = elemento;
          }
          raiz.esq.cor = raiz.dir.cor = false;
 
       // Senao, se a arvore tiver dois elementos (raiz e esq)
       } else if (raiz.dir == null) {
          if (elemento.getName().compareTo(raiz.elemento.getName()) > 0) {
             raiz.dir = new NoAN(elemento);
             
          } else if (elemento.getName().compareTo(raiz.esq.elemento.getName()) > 0) {
             raiz.dir = new NoAN(raiz.elemento);
             raiz.elemento = elemento;
             
          } else {
             raiz.dir = new NoAN(raiz.elemento);
             raiz.elemento = raiz.esq.elemento;
             raiz.esq.elemento = elemento;
          }
          raiz.esq.cor = raiz.dir.cor = false;
 
       // Senao, a arvore tem tres ou mais elementos
       } else {
          inserir(elemento, null, null, null, raiz);
       }
       raiz.cor = false;
    }
 
    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
       // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
       if (pai.cor == true) {
          // 4 tipos de reequilibrios e acoplamento
          if (pai.elemento.getName().compareTo(avo.elemento.getName()) > 0) { // rotacao a esquerda ou direita-esquerda
             if (i.elemento.getName().compareTo(pai.elemento.getName()) > 0) {
                avo = rotacaoEsq(avo);
             } else {
                avo = rotacaoDirEsq(avo);
             }
          } else { // rotacao a direita ou esquerda-direita
             if (i.elemento.getName().compareTo(pai.elemento.getName()) < 0) {
                avo = rotacaoDir(avo);
             } else {
                avo = rotacaoEsqDir(avo);
             }
          }
          if (bisavo == null) {
             raiz = avo;
          } else if (avo.elemento.getName().compareTo(bisavo.elemento.getName()) < 0) {
             bisavo.esq = avo;
          } else {
             bisavo.dir = avo;
          }
          // reestabelecer as cores apos a rotacao
          avo.cor = false;
          avo.esq.cor = avo.dir.cor = true;
 
       } // if(pai.cor == true)
    }
 
    private void inserir(Personagem elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
       if (i == null) {
          if (elemento.getName().compareTo(pai.elemento.getName()) < 0) {
             i = pai.esq = new NoAN(elemento, true);
          } else {
             i = pai.dir = new NoAN(elemento, true);
          }
          if (pai.cor == true) {
             balancear(bisavo, avo, pai, i);
          }
       } else {
          // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
          if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
             i.cor = true;
             i.esq.cor = i.dir.cor = false;
             if (i == raiz) {
                i.cor = false;
             } else if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
             }
          }
          if (elemento.getName().compareTo(i.elemento.getName()) < 0) {
             inserir(elemento, avo, pai, i, i.esq);
          } else if (elemento.getName().compareTo(i.elemento.getName()) > 0) {
             inserir(elemento, avo, pai, i, i.dir);
          } else {
             throw new Exception("Erro inserir (elemento repetido)!");
          }
       }
    }
 
    private NoAN rotacaoDir(NoAN no) {
       NoAN noEsq = no.esq;
       NoAN noEsqDir = noEsq.dir;
 
       noEsq.dir = no;
       no.esq = noEsqDir;
 
       return noEsq;
    }
 
    private NoAN rotacaoEsq(NoAN no) {
       NoAN noDir = no.dir;
       NoAN noDirEsq = noDir.esq;
 
       noDir.esq = no;
       no.dir = noDirEsq;
       return noDir;
    }
 
    private NoAN rotacaoDirEsq(NoAN no) {
       no.dir = rotacaoDir(no.dir);
       return rotacaoEsq(no);
    }
 
    private NoAN rotacaoEsqDir(NoAN no) {
       no.esq = rotacaoEsq(no.esq);
       return rotacaoDir(no);
    }
 }

 class NoAN {
    public boolean cor;
    public Personagem elemento;
    public NoAN esq, dir;
  
    public NoAN() {
      this(null);
    }
  
    public NoAN(Personagem elemento) {
      this(elemento, false, null, null);
    }
  
    public NoAN(Personagem elemento, boolean cor) {
      this(elemento, cor, null, null);
    }
  
    public NoAN(Personagem elemento, boolean cor, NoAN esq, NoAN dir) {
      this.cor = cor;
      this.elemento = elemento;
      this.esq = esq;
      this.dir = dir;
    }
  }