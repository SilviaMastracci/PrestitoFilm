
import java.util.*;
import java.io.*;

public class Utente extends Persona {
    /**
     * ArrayList contenente i prestiti
     */
    private ArrayList<Prestito> Prestiti;

    /**
     * @param nome nome della persona
     * @param cognome cognome della persona
     * @param password password della persona
     * @param email email della persona
     * @param indirizzo indirizzo della persona
     * @param telefono numero di telefono della persona
     * ereditati dalla classe persona
     */
    public Utente(String nome, String cognome, String password, String email, String indirizzo, String telefono){
        super(nome, cognome, password, email, indirizzo, telefono);
        Prestiti = new ArrayList<>();
    }

     /**
      * Stampa le informazioni dell'utente
      */
    @Override
    public String printInfo(){
        return super.printInfo();
        
    }

    /**
     * Aggiorna le informazioni dell'utente 
     * @throws IOException il metodo aggiornaInformazioniUtente lancia un eccezione
     */
    public void aggiornaInformazioniUtente() throws IOException{
        String decisione;

        Scanner sc = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int j = 8;
        while(j!=0){
          System.out.println("\nDigitare il numero che appare di fianco il campo che si vuole aggiornare: \n1: Aggiorna Email\n2: Aggiorna Password\n3: Aggiorna Indirizzo\n4: Aggiorna Numero di telefono\n0: Esci");  
          decisione = sc.next();
          aggiornaEmailUtente(decisione, sc);
          aggiornaPassword(decisione, sc);
          aggiornaIndirizzoUtente(decisione,reader);
          aggiornaTelefonoUtente(decisione, sc);
          if(decisione.equals("0")){
            j=0;
          }
        }
        System.out.println("\nDati aggiornati con successo");
    }

    
    /**
     * Ritorna i dati dell'utente
     * @return stringa con i dati 
     */
    @Override
    public String toString(){
        return (getNome() + ";" + getCognome() + ";" + getPassword() + ";" + getEmail() + ";" + getIndirizzo() + ";" + getTelefono());
    }

    /**
     * Aggiunge il prestito all'utente
     * @param prestito da aggiungere
     */
    public void addPrestito(Prestito prestito){
        Prestiti.add(prestito);
    }

    /**
     * Rimuove il prestito dell'utente
     * @param prestito da aggiungere
     */
    public void removePrestito(Prestito prestito){
        Prestiti.remove(prestito);
    }

    /**
     * Stampa i titoli dei film presi in prestito dall'utente, se presenti
     * @return parametro usato nel main
     */
    public boolean titoliPrestiti(){
        boolean vuoto = false;
        if(Prestiti.isEmpty()){
            System.out.println("\nNon hai film da restituire");
            vuoto = true;
            return vuoto;
        }
        else{
            System.out.println("\nQuali tra i seguenti film vuoi restituire?\nDigita l'id del film corrispondente\n\nDigita 'exit' per uscire\n\n");
            for(Prestito x : Prestiti){
                System.out.println(x.getFilm().getID()+"\t"+x.getFilm().getTitolo());
            }
            vuoto = false;
            return vuoto;
        }
    }

    /**
     * Ritorna l'arraylist contenente i prestiti dell'utente
     * @return arraylist
     */
    public ArrayList<Prestito> getPrestiti(){
        return Prestiti;
    }

    /* Metodi privati */

    /**
     * Aggiorna il numero di telefono dell'utente
     * 
     * @param decisione scelta dell'utente
     * @param sc scanner
     */
    private void aggiornaTelefonoUtente(String decisione, Scanner sc){
        if (decisione.equals("4"))
        {
            System.out.println("\nInserisci il nuovo numero di telefono: ");
            boolean telefono_valido = false;
            while (!telefono_valido) {
                String telefono = sc.next();
                sc.nextLine();
                if (telefono.length() > 8 && telefono.length() < 12 && telefono.matches("^[0-9]+$")){
                    telefono_valido = true;
                    setTelefono(telefono);
                }
                else {
                    System.out.println("Numero di telefono non valido\nInserisci numero di telefono");
                    
                }
            }
            System.out.println("\nIl numero di telefono è stato aggiornato con successo");
        }
    }

    /**
     * Aggiorna l'indirizzo dell'utente
     * @param decisione scelta dell'utente
     * @param reader buffer reader
     * @throws IOException
     */
    private void aggiornaIndirizzoUtente(String decisione, BufferedReader reader) throws IOException{
        if(decisione.equals("3")){
            System.out.println("2\nInserisci il nuovo indirizzo: ");
            setIndirizzo (reader.readLine());
            System.out.println("\nL'indirizzo è stato aggiornato con successo");
        }
    }

    /**
     * Aggiorna l'email dell'utente
     * @param decisione scelta dell'utente 
     * @param sc scanner  
     */
    private void aggiornaEmailUtente(String decisione, Scanner sc){
        if(decisione.equals("1")){
            System.out.println("\nInserisci il nuovo indirizzo email: ");
            setEmail(sc.next());
            System.out.println("\nL'indirizzo email è stato aggiornato con successo");
        }
    }

    /**
     * Aggiorna la password dell'utente
     * @param decisione scelta dell'utente
     * @param sc scanner
     */
    private void aggiornaPassword(String decisione, Scanner sc){
        if(decisione.equals("2")){
            boolean password_corretto = false;
            while(!password_corretto){
                System.out.println("Inserisci la vecchia password");
                String oldPass = sc.next();
                if(!oldPass.equals(getPassword())){
                        System.out.println("La password inserita non è valida\n");
                    }
                if(oldPass.equals(getPassword())){
                    System.out.println("\nInserisci la nuova Password: ");
                    setPassword(sc.next());
                    System.out.println("\nLa password è stata aggiornata con successo");
                    password_corretto = true;
                }
            }
        }
    }

}