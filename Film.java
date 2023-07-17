
import java.time.LocalDate;
public class Film {
    /**
     * Imformazioni del film
     */
    private String ID;
    private String titolo;
    private String genere;
    private String regista;
    private String descrizione;
    private boolean inPrestito;

    /**
     * @param id ID del film
     * @param t Titolo del film
     * @param g Genere del film
     * @param r Regista del film 
     * @param d Descrizione del film
     * @param inprestito Stato del film
     */
    public Film(String id, String t, String g, String r, String d, boolean inprestito){
        this.ID = id;
        this.titolo = t;
        this.genere = g;
        this.regista = r;
        this.descrizione = d;
        this.inPrestito = inprestito;
    }   
    
    /* Getter e Setter */

    /*
      * Ritorna le informazioni generali del film
      */
    public String getInfo(){
        return this.titolo + "\t" + this.regista + "\t" + this.genere;
        
    }

    /**
     * Ritorna il titolo del film
     * @return valore di Titolo
     */
    public String getTitolo(){
        return this.titolo;
    }

    /**
     * Ritorna il genere del film
     * @return valore di Genere
     */
    public String getGenere(){
        return this.genere;
    }

    /**
     * Ritorna il regista 
     * @return valore di regista
     */
    public String getRegista(){
        return this.regista;
    }

    /**
     * Ritorna la descrizione
     * @return valore di descrizione
     */
    public String getDescrizione(){
        return this.descrizione;
    }

    /**
     * Ritorna lo stato del prestito
     * @return valore di inPrestito
     */
    public boolean getStatoPrestito(){
        return this.inPrestito;
    }

    /**
     * Ritorna l'ID del titolo
     * @return  valore di ID
     */
    public String getID(){
        return this.ID;
    }

    /**
     * Cambia il titolo del film
     * @param t nuovo titolo da dare al film
     */
    public void setTitolo(String t){
        this.titolo = t;
    }

    /**
     * Camcia il genere del film
     * @param g nuovo genere da dare al film
     */
    public void setGenere(String g){
        this.genere = g;
    }

    /**
     * Cambia il regista del film
     * @param r nuovo regista da assegnare al film
     */
    public void setRegista(String r){
        this.regista = r;
    }

    /**
     * Cambia la descrizione del film
     * @param d nuova descrizione da assegnare al film
     */
    public void setDescrizione(String d){
        this.descrizione = d;
    }

    /**
     * Cambia lo stato del prestito
     * @param s nuovo valore di inprestito
     */
    public void setStatoPrestito(boolean s){
        this.inPrestito = s;
    }

    /**
     * Cambia l'id del film 
     * @param i nuovo id da dare al regsitro  
     */
    public void setID(String i){
        this.ID = i;
    }

    /**
     * Prende il film in prestito
     * @param utente utente che prende in prestito il film
     * @return valore di prestito per usarlo nel main
     */
    public Prestito faiPrestito(Utente utente){
        LocalDate pData = LocalDate.now();
        setStatoPrestito(true);
        System.out.println("\nIl film " + titolo + " è stato preso in prestito da " + utente.getNome());
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
     * @param utente utente che restituisce il film
     * @param prestito il prestito che viene estinto 
     * @return stringa
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
        return this.ID + ";" + this.titolo + ";" + this.genere + ";" + this.regista + ";" + this.descrizione + ";" + this.inPrestito;
    }
}

