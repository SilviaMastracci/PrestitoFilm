
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Prestito {
    /**
     * Informazioni del prestito
     */
    private Utente utente;
    private Film film;
    private LocalDate pData;
    private boolean pagato;

    /**
     * @param utente utente del prestito
     * @param f film del prestito
     * @param p data del prestito
     * @param pag stato del pagamento
     */
    public Prestito(Utente ute, Film f, LocalDate p, boolean pag){
        this.utente = ute;
        this.film = f;
        this.pData = p;
        this.pagato = pag;
    }

   
    /**
     * Permette il pagamento della penale
     */
    public void pagaPenale(){
        double Penale = calcoloPenale(30);
        if (Penale > 0){
            System.out.println("La penale è di €" + Penale);
            System.out.println("Vuoi pagarla? \n1: Si\n2: No");
            Scanner input = new Scanner(System.in);
            int scelta = input.nextInt();
            input.nextLine();
            if (scelta == 1){
                pagato = true;
                System.out.println("Pagamento avvenuto con successo");
            }
            if (scelta == 2){
                pagato = false;
                System.out.println("Non hai pagato la penale");
            }
            else {
                System.out.println("Nessuna penale da pagare");
                pagato = true;
            }
        input.close();
        }
    }

    /**
     * Ritorna il film oggetto del prestito
     * @return oggetto di tipo film
     */
    public Film getFilm(){   //restituisce il film
        return this.film;
    }

    /**
     * Ritorna l'utente che ottiene il prestito
     * @return oggetto di tipo utente
     */
    public Utente getUtente(){   //restituisce colui che ha preso in prestito il film
        return this.utente;
    }

    /**
     * Ritorna la data del prestito
     * @return oggetto di tipo data
     */
    public LocalDate getDataPrestito(){
        return this.pData;
    } 

    /**
     * Ritorna lo stato del pagameneto del prestito
     * @return valore di pagato
     */
    public boolean getPagato(){
        return this.pagato;
    }

    /**
     * Cambia l'oggetto film del prestito
     * @param f nuovo film
     */
    public void setFilm(Film f){
        this.film = f;
    }

    /**
     * Cambia l'oggetto utente del prestito
     * @param r nnuovo utente
     */
    public void setUtente(Utente r){
        this.utente = r;
    }

    /**
     * Cambia l'oggetto data del prestito
     * @param d nuova data
     */
    public void setDataPrestito(LocalDate d){
        this.pData = d;
    }

    /**
     * Cambia lo stato del pagamento del prestito
     * @param p nuovo valore di pagato
     */
    public void setPagato(boolean p){
        this.pagato = p;
    }

    /* Metodi privati */

    /**
     * Calcola la penale per la restituzione del film dopo i 30 giorni pattuiti
     * @param max_giorni giorni dopo i quali bisogna pagare la penale
     * @return valore in double della penale
     */
    private double calcoloPenale(long max_giorni){
        double penale = 0;
        if (!pagato){
            LocalDate rDate= LocalDate.now();
            long giorni = pData.until(rDate, ChronoUnit.DAYS);
            giorni = giorni - max_giorni;

            if (giorni > 0){
                penale = giorni * 3;
            }
            else{
                penale = 0;
            }
        }
        return penale;
    }

}


