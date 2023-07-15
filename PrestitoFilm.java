
import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class PrestitoFilm {
    //Dichiarazioni variabili utilizzate nel programma
    static Scanner input = new Scanner(System.in);
    static int scelta;

    /* Caricamento dai file txt */

    //Import dell'elenco dei film da file .txt, la stringa viene poi elaborata cosi da creare le istanze dei vari film ed aggiungerli all'elenco di libri*/
    private static ArrayList<Film> caricamentoFilm() {
        ArrayList<Film> elencoFilm = new ArrayList<Film>();
        try {
        
            File importFilm = new File("PrestitoFilm\\ElencoFilm.txt");
            Scanner myReader = new Scanner(importFilm);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitdati = data.split(","); //Array di string
                String id = splitdati[0];
                String titolo = splitdati[1];
                String genere = splitdati[2];
                String Regista = splitdati[3];
                String StringinPrestito = splitdati[4];
                boolean inPrestito = Boolean.parseBoolean(StringinPrestito);
                Film film = new Film(id, titolo, genere, Regista, inPrestito);
                elencoFilm.add(film);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Errore, file non trovato");
            e.printStackTrace();
        }
        return elencoFilm;
    }

    //Import dell'elenco degli utenti da file .txt, la stringa viene elaborata cosi da creare le istanze dei vari utenti ed aggiungerli all'elenco di utenti. La lista sarà utile per convalidare le credenziali di accesso
    private static void caricamentoUtenti(ArrayList<Utente> elencoUtenti) {
        try {
            File importUtenti = new File("PrestitoFilm\\ElencoUtenti.txt");
            Scanner myReader = new Scanner(importUtenti);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitdati = data.split(",");
                String nome = splitdati[0];
                String cognome = splitdati[1];
                String password = splitdati[2];
                String email = splitdati[3];
                String indirizzo = splitdati[4];
                String n_telefono = splitdati[5];
                Utente utente = new Utente(nome, cognome, password, email, indirizzo, n_telefono);
                elencoUtenti.add(utente);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Errore, file non trovato");
            e.printStackTrace();
        }
    }

     //Import delle coppie utente id film richiesto / data di prestito e lo stato del pagamento, verranno poi utilizzate per la effettiva creazione di un elenco dei prestiti
    private static void caricamentoPrestiti(ArrayList<Film> elencoFilm, ArrayList<Utente> elencoUtenti, ArrayList<Prestito> elencoPrestiti) {
        try {
            File importPrestiti = new File("PrestitoFilm\\ElencoPrestiti.txt");
            Scanner myReader = new Scanner(importPrestiti);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitdati = data.split(",");
                String email = splitdati[0];
                String idFilm = splitdati[1];
                String stringdata = splitdati[2];
                LocalDate dataPrestito = LocalDate.parse(stringdata);
                String stringpagato = splitdati[3];
                boolean pagato = Boolean.parseBoolean(stringpagato);
                String coppiePrestiti=email + "," + idFilm;

                for (Utente f : elencoUtenti) {
                    if (f.getEmail().equals(email)) {
                        for (Film y : elencoFilm) {
                            if ((f.getEmail() + "," + y.getID()).equals(coppiePrestiti)) {
                                Prestito p = new Prestito(f, y, dataPrestito, pagato);
                                elencoPrestiti.add(p);
                                
                            }
                        }
                    }
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Errore, file non trovato");
            e.printStackTrace();
        }
    }

   

    //Registrazione o login utente con controllo credenziali. Nel caso di login si ha anche la creazione dell'elenco prestiti dell'utente
    private static Utente accessoUtente(ArrayList<Utente> elencoUtenti, ArrayList<Prestito> elencoPrestiti) {
        Utente utenteMain = null;

        System.out.println("Digitare:\n1 per registrarsi\n2 per il login");
        scelta = input.nextInt();
        input.nextLine();

        if (scelta == 1) {
            System.out.println("Inserisci nome");
            String nome = input.nextLine();
            System.out.println("Inserisci cognome");
            String cognome = input.nextLine();
            System.out.println("Inserisci password");
            String password = input.next();
            input.nextLine();
            System.out.println("Inserisci email");
            String email = input.next();
            input.nextLine();
            System.out.println("Inserisci indirizzo");
            String indirizzo = input.nextLine();
            System.out.println("Inserisci numero di telefono");
            String n_telefono = " " ;
            boolean n_telefono_valido = false;

            while (!n_telefono_valido) {
                n_telefono = input.next();
                input.nextLine();

                if (n_telefono.length() > 8 && n_telefono.length() < 15 && n_telefono.matches("^[0-9]+$")){
                    n_telefono_valido = true;
                }
                else {
                    
                    System.out.println("Numero di telefono non valido!\nInserisci numero di telefono");
                }
            }

            Utente utente = new Utente(nome, cognome, password, email, indirizzo, n_telefono);
            utenteMain = utente;
            elencoUtenti.add(utente);
            System.out.println("Benvenuto!");
        }

        if (scelta == 2) {
            boolean credenziali_corrette = false;

            while (!credenziali_corrette) {
                System.out.println("Inserisci la email");
                String email = input.nextLine();
                System.out.println("Inserisci la password");
                String password = input.nextLine();

                for (Utente utente : elencoUtenti) {
                    if (email.equals(utente.getEmail()) && password.equals(utente.getPassword())) {
                        System.out.println("Accesso eseguito con successo");
                        utenteMain = utente;

                        for (Prestito x : elencoPrestiti) {
                            if (x.getUtente().equals(utenteMain)) {
                                utenteMain.addPrestito(x);
                            }
                        }

                        credenziali_corrette = true;
                        break;
                    } 
                }

                if(!credenziali_corrette){
                    System.out.println("Credenziali errate, si prega di reinserire le credenziali.");
                }       
                        
            }
        }

        return utenteMain;
    }
    

    // Se presenti stampa i prestiti dell'utente
    private static void printPrestiti(Utente utenteMain){
        ArrayList<Prestito> Prestiti=utenteMain.getPrestiti();
        
        if (!Prestiti.isEmpty()){
            System.out.println("\n I film prenotati sono: ");
            for (int i = 0; i < Prestiti.size(); i++){
                String info=Prestiti.get(i).getFilm().getInfo();
                System.out.print(info);
                System.out.print("\n");
            }
        }

        else{
            System.out.println("\nNessun film preso in prestito\n");
        }
    }

    //Stampa di tutti i film
    private static void printElencofilm(ArrayList<Film> elencoFilm) {
        System.out.println("\n");

        for (Film film : elencoFilm) {
            String info=film.getInfo();
            System.out.println(info);
        }

    }

    //Ricerca mediante titolo con possibilità di richiedere prestito
    private static void cercaTitolo(ArrayList<Film> elencoFilm, Utente utenteMain, ArrayList<Prestito> elencoPrestiti, HashMap<String, String> coppiePrestiti) {
        System.out.println("\n");
        System.out.println("Inserisci l'id del film che desideri cercare\nOppure digita exit per uscire");
        boolean titolo_corretto = false;

        while (!titolo_corretto) {
            System.out.println("\n");

            for (Film film : elencoFilm) {
                System.out.println(film.getID() + "\t" + film.getTitolo());
            }

            String id = input.next();
            input.hasNextLine();

            boolean filmTrovato = false;   //Aggiungo un flag per verificare se il film è stato trovato

            for (Film film : elencoFilm) {
                if (id.equals(film.getID())) {
                    System.out.println("\nIl film è presente\n");
                    System.out.println("\n");
                    System.out.println("Digitare 1 per prendere il film in prestito\nDigitare 2 per leggere le informazioni del film\n");
                    scelta = input.nextInt();
                    input.nextLine();

                    if (scelta == 1) {
                        if (film.getStatoPrestito() == false) {
                            elencoPrestiti.add(film.faiPrestito(utenteMain));
                            coppiePrestiti.put(utenteMain.getEmail() + "," + film.getID(), film.returnPrestito(utenteMain).getDataPrestito() + "," + film.returnPrestito(utenteMain).getPagato());
                            filmTrovato = true;
                            titolo_corretto = true;
                        } 
                        
                        else {
                            System.out.println("\nSpiacenti," + film.getTitolo() + " è già in prestito");
                            filmTrovato = true;
                            titolo_corretto = true;
                        }

                    }

                    if (scelta == 2) {
                        String info=film.getInfo();
                        System.out.println(info);
                        filmTrovato = true;
                        titolo_corretto = true;
                    }
                }

                if (id.equals("exit")) {
                    titolo_corretto = true;
                }
            }

            if(!filmTrovato){
                System.out.println("\nNessun film trovato con l'ID inserito.");
            }
        }
    }

    //Ricerca mediante Regista
    private static void cercaRegista(ArrayList<Film> elencoFilm) {
        boolean Regista_corretto = false;

        while (!Regista_corretto) {
            ArrayList<String> elencoRegisti = new ArrayList<String>();
            System.out.println("\n");

            for (Film film : elencoFilm) {
                if (elencoRegisti.contains(film.getRegista())) {
                    continue;
                }
                elencoRegisti.add(film.getRegista());
                System.out.println(film.getRegista());
            }

            System.out.println("\n");
            System.out.println("Inserisci uno dei registi della lista ");
            System.out.println("\n");
            String regista = input.nextLine();
            regista = regista.toLowerCase();

            boolean filmTrovati = false; 

            for (Film film : elencoFilm) {
                if (regista.equals(film.getRegista())) {
                    System.out.println("\n");
                    String info = film.getInfo();     
                    System.out.println(info);
                    filmTrovati = true;
                }

            }

            if (!filmTrovati){
                System.out.println("Nessun film trovato per il regista selezionato.");
            }

            Regista_corretto = true;
        }
    }

    //Ricerca mediante genere
    private static void cercaGenere(ArrayList<Film> elencoFilm) {
        boolean genere_corretto = false;

        while (!genere_corretto) {
            ArrayList<String> elencoGeneri = new ArrayList<String>();
            System.out.println("\n");

            for (Film film : elencoFilm) {
                if (elencoGeneri.contains(film.getGenere())) {
                    continue;
                }
                elencoGeneri.add(film.getGenere());
                System.out.println(film.getGenere());
            }
            System.out.println("\n");
            System.out.println("Inserisci uno dei generi della lista");
            System.out.println("\n");
            String genere = input.nextLine();
            genere = genere.toLowerCase();
            
            boolean filmTrovati = false;  // Aggiunto flag per verificare se sono stati trovati film corrispondenti al genere
            
            for (Film film : elencoFilm) {
                if (genere.equals(film.getGenere())) {
                    String info = film.getInfo();
                    System.out.println(info);
                    filmTrovati = true;  // Imposta il flag a true se viene trovato almeno un film corrispondente al genere
                }
            }
            
            if (!filmTrovati) {
                System.out.println("Nessun film trovato per il genere selezionato.");
            }
            
            genere_corretto = true;
        }
    }
    
    //Restituzione film con relativa rimozione dalle strutture dati
    private static void restituisciFilm(ArrayList<Prestito> elencoPrestiti, Utente utenteMain, HashMap<String, String> coppiePrestiti, ArrayList<Prestito> prestitiDaRimuovere) {
        boolean id_corretto = false;
        while (!id_corretto) {
            if (utenteMain.titoliPrestiti() == true) {
                id_corretto = true;
            } else {
                System.out.println("\n");
                String id = input.next();
                input.nextLine();
                if (id.equals("exit")) {
                    break;
                } else {
                    for (Prestito p : elencoPrestiti) {
                        if (p.getFilm().getID().equals(id)) {
                            String restituzioneFilm= p.getFilm().restituisciFilm(utenteMain, p);
                            System.out.println(restituzioneFilm);
                            coppiePrestiti.remove(utenteMain.getEmail() + "," + p.getFilm().getID());
                            prestitiDaRimuovere.add(p);
                        }
                    }
                    id_corretto = true;
                }
            }

        }
    }

    //Aggiornamento dati utente
    private static void aggiornaDatiUtenti(Utente utenteMain) throws IOException {
        System.out.println(utenteMain.printInfo());
        printPrestiti(utenteMain);
        System.out.println("Questi sono i tuoi dati attuali, sei sicuro di volerli aggiornare? \n1: Si\n2: No\n");
        scelta = input.nextInt();
        input.nextLine();
        if (scelta == 1) {
            utenteMain.aggiornaInformazioniUtente();
        } else {
            System.out.println("Come non detto, tornerai al menu principale");
        }
    }

    /* Salvataggio su file txt */

    //Salvataggio su file dell'elenco dei film
    private static void salvataggioFilm(ArrayList<Film> elencoFilm) {
        try {
            FileWriter myWriter = new FileWriter("ElencoFilm.txt");
            for (Film film : elencoFilm) {
                myWriter.write(film.toString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Errore!");
            e.printStackTrace();
        }
    }

    //Salvataggio su file dell'elenco degli utenti e dei relativi attributi
    private static void salvataggioUtenti(ArrayList<Utente> elencoUtenti) {
        try {
            FileWriter myWriter = new FileWriter("ElencoUtenti.txt");
            for (Utente utente : elencoUtenti) {
                myWriter.write(utente.toString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Errore!");
            e.printStackTrace();
        }
    }

    //Salvataggio su file dell'elenco delle prenotazioni
    private static void salvataggioPrenotazioni(HashMap<String, String> coppiePrestiti) {
        try {
            FileWriter myWriter = new FileWriter("ElencoPrestiti.txt");
            for (String i : coppiePrestiti.keySet()) {
                myWriter.write(i + "," + coppiePrestiti.get(i) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Errore!");
            e.printStackTrace();
        }
    }

    //Corpo del programma in cui si richiamano le funzioni definite precedentemente
    public static void main(String[] args) throws IOException {
        ArrayList<Film> elencoFilm = caricamentoFilm();
        ArrayList<Utente> elencoUtenti = new ArrayList<Utente>();
        ArrayList<Prestito> elencoPrestiti = new ArrayList<>();
        ArrayList<Prestito> prestitiDaRimuovere = new ArrayList<>();
        HashMap<String, String> coppiePrestiti = new HashMap<String, String>();
        Utente utenteMain = null;
        caricamentoUtenti(elencoUtenti);
        caricamentoPrestiti(elencoFilm, elencoUtenti, elencoPrestiti);
        utenteMain = accessoUtente(elencoUtenti, elencoPrestiti);
        for (Prestito z : utenteMain.getPrestiti()) {
            z.getFilm().getID();
        }

        while (true) {
            System.out.println("\nDigitare 1 per l'elenco dei film\nDigitare 2 per effettuare una ricerca per titolo e prendere in prestito un film");
            System.out.println("Digitare 3 per effettuare una ricerca per Regista\nDigitare 4 per effettuare una ricerca per Genere");
            System.out.println("Digitare 5 per restituire un film\nDigitare 6 per vedere i propri dati, i film presi in prestito e aggiornare i dati");
            System.out.println("Digitare 7 per uscire\n");
            scelta = input.nextInt();
            input.nextLine();                       //Usato per ripulire l'input in quanto nextInt non legge il carattere "newline" che verrebbe preso come input in caso di metodo nextLine()

            if (scelta == 1) {
                printElencofilm(elencoFilm);
            }

            if (scelta == 2) {
                cercaTitolo(elencoFilm, utenteMain, elencoPrestiti, coppiePrestiti);
            }

            if (scelta == 3) {
                cercaRegista(elencoFilm);
            }

            if (scelta == 4) {
                cercaGenere(elencoFilm);
            }

            if (scelta == 5) {
                restituisciFilm(elencoPrestiti, utenteMain, coppiePrestiti, prestitiDaRimuovere);
            }

            if (scelta == 6) {
                aggiornaDatiUtenti(utenteMain);
            }

            /*Uscita dal programma*/
            if (scelta == 7) {
                System.out.println("Grazie per averci scelto!");
                break;
            }
        }
        salvataggioFilm(elencoFilm);
        salvataggioPrenotazioni(coppiePrestiti);
        salvataggioUtenti(elencoUtenti);
    }
}
