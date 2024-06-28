import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q02 {

    public static void main(String[] args) throws Exception {
        String nomeArquivo = "/tmp/characters.csv";
        Map<String, Personagem> personagens = new HashMap<>();
        ler(nomeArquivo, personagens);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Arvore arvore = new Arvore();
        arvore.inserir(7);
        arvore.inserir(3);
        arvore.inserir(11);
        arvore.inserir(1);
        arvore.inserir(5);
        arvore.inserir(9);
        arvore.inserir(13);
        arvore.inserir(0);
        arvore.inserir(2);
        arvore.inserir(4);
        arvore.inserir(6);
        arvore.inserir(8);
        arvore.inserir(10);
        arvore.inserir(12);
        arvore.inserir(14);

        String input;
        while (!(input = br.readLine()).equals("FIM")) {
            if (personagens.containsKey(input)) {
                Personagem personagem = personagens.get(input);
                arvore.inserirInterno(personagem.getName(), personagem.getYearOfBirth() % 15);
            }
        }
        while (!(input = br.readLine()).equals("FIM")) {
            for (Personagem personagem : personagens.values()) {
                if (input.equals(personagem.getName())) {
                    arvore.pesquisar(personagem.getYearOfBirth() % 15, input);
                    break;
                }
            }
        }
        br.close();
    }

    public static void ler(String nomeDoArquivo, Map<String, Personagem> mapper) {
        try {
            FileReader file = new FileReader(nomeDoArquivo);
            BufferedReader buffer = new BufferedReader(file);

            String line = buffer.readLine();
            while ((line = buffer.readLine()) != null) {
                String[] atributos = line.split(Pattern.quote(";"));
                Personagem newPersonagem = new Personagem();

                newPersonagem.setId(atributos[0]);
                newPersonagem.setName(atributos[1]);

                ArrayList<String> alternates_names = new ArrayList<>();
                Matcher matcher = Pattern.compile("'(.*?)'").matcher(atributos[2]);
                while (matcher.find()) {
                    alternates_names.add(matcher.group(1));
                }
                newPersonagem.setAlternateNames(String.join(", ", alternates_names));
                newPersonagem.setHouse(atributos[3]);
                newPersonagem.setAncestry(atributos[4]);
                newPersonagem.setSpecies(atributos[5]);
                newPersonagem.setPatronus(atributos[6]);
                newPersonagem.setHogwartsStaff(atributos[7].equalsIgnoreCase("VERDADEIRO"));
                newPersonagem.setHogwartsStudent(atributos[8].equalsIgnoreCase("VERDADEIRO"));
                newPersonagem.setActorName(atributos[9]);
                newPersonagem.setAlive(atributos[10].equalsIgnoreCase("VERDADEIRO"));

                LocalDate date = tentaPatternsDiferentes(atributos[12]);
                newPersonagem.setBirthDate(java.sql.Date.valueOf(date));
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
            }
        }

        throw new DateTimeParseException("Não foi possível converter a string em uma data " + dateString, dateString, 0);
    }

    public static class Arvore {
        No raiz;

        public static class No {
            int elemento;
            No esq, dir;
            NoInterno raizInterna;

            public No(int elemento) {
                this(elemento, null, null);
            }

            public No(int elemento, No esq, No dir) {
                this.elemento = elemento;
                this.esq = esq;
                this.dir = dir;
                this.raizInterna = null;
            }
        }

        public static class NoInterno {
            String nome;
            NoInterno esq, dir;

            public NoInterno(String nome) {
                this.nome = nome;
                this.esq = null;
                this.dir = null;
            }
        }

        public void inserir(int x) {
            raiz = inserir(x, raiz);
        }

        private No inserir(int x, No i) {
            if (i == null) {
                i = new No(x);
            } else if (x < i.elemento) {
                i.esq = inserir(x, i.esq);
            } else if (x > i.elemento) {
                i.dir = inserir(x, i.dir);
            }
            return i;
        }

        public void inserirInterno(String nome, int elementoPrincipal) {
            No noPrincipal = buscarNo(elementoPrincipal, this.raiz);
            if (noPrincipal != null) {
                if (noPrincipal.raizInterna == null) {
                    noPrincipal.raizInterna = new NoInterno(nome);
                } else {
                    inserirInterno(nome, noPrincipal.raizInterna);
                }
            } else {
                System.out.println("Elemento principal não encontrado na árvore.");
            }
        }

        private void inserirInterno(String nome, NoInterno raizInterna) {
            if (nome.compareTo(raizInterna.nome) < 0) {
                if (raizInterna.esq == null) {
                    raizInterna.esq = new NoInterno(nome);
                } else {
                    inserirInterno(nome, raizInterna.esq);
                }
            } else if (nome.compareTo(raizInterna.nome) > 0) {
                if (raizInterna.dir == null) {
                    raizInterna.dir = new NoInterno(nome);
                } else {
                    inserirInterno(nome, raizInterna.dir);
                }
            }
        }

        public void imprimir() {
            System.out.print("Elementos da árvore principal: ");
            imprimir(raiz);
            System.out.println();
        }

        private void imprimir(No i) {
            if (i != null) {
                imprimir(i.esq);
                System.out.print(i.elemento + " ");

                if (i.raizInterna != null) {
                    System.out.print("(" + i.elemento + ": ");
                    printDentro(i.raizInterna);
                    System.out.print(") ");
                }

                imprimir(i.dir);
            }
        }

        private void printDentro(NoInterno raizInterna) {
            if (raizInterna != null) {
                printDentro(raizInterna.esq);
                System.out.print(raizInterna.nome + " ");
                printDentro(raizInterna.dir);
            }
        }

        public void pesquisar(int chave, String nome) {
            System.out.printf("%s => raiz", nome);
            if (pesquisar(raiz, chave, nome)) {
                System.out.printf(" SIM\n");
            } else {
                System.out.printf(" NAO\n");
            }
        }

        private boolean pesquisar(No i, int chave, String nome) {
            if (i == null) {
                return false;
            }

            boolean achou = false;
            NoInterno pesquisa = i.raizInterna;
            while (pesquisa != null) {
                if (pesquisa.nome.compareTo(nome) == 0) {
                    return true;
                } else if (nome.compareTo(pesquisa.nome) < 0) {
                    System.out.print("->esq");
                    pesquisa = pesquisa.esq;
                } else {
                    System.out.print("->dir");
                    pesquisa = pesquisa.dir;
                }
            }

            if (!achou) {
                System.out.print(" ESQ");
                achou = pesquisar(i.esq, chave, nome);
            }

            if (!achou) {
                System.out.print(" DIR");
                achou = pesquisar(i.dir, chave, nome);
            }

            return achou;
        }

        private No buscarNo(int elemento, No i) {
            if (i == null || elemento == i.elemento) {
                return i;
            } else if (elemento < i.elemento) {
                return buscarNo(elemento, i.esq);
            } else {
                return buscarNo(elemento, i.dir);
            }
        }
    }

    public static class Personagem {
        private String id;
        private String name;
        private String alternateNames;
        private String house;
        private String ancestry;
        private String species;
        private String patronus;
        private boolean hogwartsStaff;
        private boolean hogwartsStudent;
        private String actorName;
        private boolean alive;
        private Date birthDate;
        private String birthDateString;
        private int yearOfBirth;
        private String eyeColour;
        private String gender;
        private boolean wizard;
        private String alternateActors;
        private String hairColour;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlternateNames() {
            return alternateNames;
        }

        public void setAlternateNames(String alternateNames) {
            this.alternateNames = alternateNames;
        }

        public String getHouse() {
            return house;
        }

        public void setHouse(String house) {
            this.house = house;
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

        public boolean getHogwartsStaff() {
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

        public boolean getAlive() {
            return alive;
        }

        public void setAlive(boolean alive) {
            this.alive = alive;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
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

        public boolean getWizard() {
            return wizard;
        }

        public void setWizard(boolean wizard) {
            this.wizard = wizard;
        }

        public String getAncestry() {
            return ancestry;
        }

        public void setAncestry(String ancestry) {
            this.ancestry = ancestry;
        }

        public String getAlternateActors() {
            return alternateActors;
        }

        public void setAlternateActors(String alternateActors) {
            this.alternateActors = alternateActors;
        }

        public String getHairColour() {
            return hairColour;
        }

        public void setHairColour(String hairColour) {
            this.hairColour = hairColour;
        }

        public String getBirthDateString() {
            return birthDateString;
        }

        public void setBirthDateString(String birthDateString) {
            this.birthDateString = birthDateString;
        }

        public void imprimir() {
            System.out.printf("[" + id + " ## " + name + " ## " + alternateNames + " ## " + house + " ## " + ancestry + " ## " + species + " ## " + patronus + " ## " +
                    hogwartsStaff + " ## " + hogwartsStudent + " ## " + actorName + " ## " + alive + " ## " + birthDateString +
                    " ## " + yearOfBirth + " ## " + eyeColour + " ## " + gender + " ## " + hairColour + " ## " + wizard + ']' + '\n');
        }
    }
}
