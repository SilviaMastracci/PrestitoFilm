
import java.time.LocalDate;
public class Film {
    /**
     * Imformazioni del libro
     */
    private String ID;
    private String Titolo;
    private String Genere;
    private String Regista;
    private boolean inPrestito;

    /**
     * @param id ID del film
     * @param t Titolo del film
     * @param g Genere del film
     * @param r Regista del film 
     * @param inprestito Stato del film
     */
    public Film(String id, String t, String g, String r, boolean inprestito){
        ID = id;
        Titolo = t;
        Genere = g;
        Regista = r;
        inPrestito = inprestito;
    }   
    
    /* Getter e Setter */

    /*
      * Stampa le informazioni generali del film
      */
    public String getInfo(){
        return Titolo + "\t" + Regista + "\t" + Genere;
        
    }

    /**
     * Ritorna il titolo del film
     */
    public String getTitolo(){
        return Titolo;
    }

    /**
     * Ritorna il genere del libro
     * @return valore di Genere
     */
    public String getGenere(){
        return Genere;
    }

    /**
     * Ritorna il regista 
     * @return valore di autore
     */
    public String getRegista(){
        return Regista;
    }

    /**
     * Ritorna lo stato del prestito
     * @return valore di inPrestito
     */
    public boolean getStatoPrestito(){
        return inPrestito;
    }

    /**
     * Ritorna l'ID del titolo
     * @return  valore di ID
     */
    public String getID(){
        return ID;
    }

    /**
     * Cambia il titolo del film
     * @param t nuovo titolo da dare al film
     */
    public void setTitolo(String t){
        Titolo = t;
    }

    /**
     * Camcia il genere del film
     * @param g nuovo genere da dare al libro
     */
    public void setGenere(String g){
        Genere = g;
    }

    /**
     * Cambia il regista del film
     * @param r nuovo regista da assegnare al film
     */
    public void setRegista(String r){
        Regista = r;
    }

    /**
     * Cambia lo stato del prestito
     * @param s nuovo valore di inprestito
     */
    public void setStatoPrestito(boolean s){
        inPrestito = s;
    }

    /**
     * Cambia l'id del libro 
     * @param i nuovo id da dare al regsitro  
     */
    public void setID(String i){
        ID = i;
    }

    /**
     * Prende il film in prestito
     * @param utente utente che prende in prestito il film
     * @return valore di prestito per usarlo nel main
     */
    public Prestito faiPrestito(Utente utente){
        LocalDate pData = LocalDate.now();
        setStatoPrestito(true);
        System.out.println("\nIl libro " + Titolo + " è stato preso in prestito da " + utente.getNome());
        Prestito prestito = new Prestito(utente, this, pData, false);
        utente.addPrestito(prestito);
        return prestito;
    }

    /**
     * Usato per accedere alla data senza richiamare addPrestito presente nella funzione sopra
     * @param utente utente che prende in prestito il film
     * @return valore di prestito
     */
    public Prestito returnPrestito(Utente utente){
        LocalDate pData = LocalDate.now();
        Prestito prestito = new Prestito(utente, this, pData, false);
        return prestito;
    }

    /**
     * Restituisce il film
     * @param utente utente che restituisce il libro
     * @param prestito il prestito che viene estinto 
     */
    public String restituisciFilm(Utente utente, Prestito prestito){
        prestito.pagaPenale();
        prestito.getFilm().setStatoPrestito(false);
        utente.removePrestito(prestito);
        return "\nIl film " + prestito.getFilm().getTitolo() + " è stato restituito con successo da " + utente.getNome();
    }

    /**
     * Return dei dati dei film, è usato nel salvataggio dei film prima della chiusura del programma
     * @return Stringa con i dati del film
     */
    @Override
    public String toString(){
        return(ID+","+Titolo+","+Genere+","+Regista+","+inPrestito);
    }
}

