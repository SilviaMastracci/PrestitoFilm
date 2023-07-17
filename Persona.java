
public class Persona {
    /**
     * Inforamzioni generali di una persona
     */
    private String nome;
    private String cognome;
    private String password;
    private String email;
    private String indirizzo;
    private String telefono;

    /**
     * @param nome nome della persona
     * @param cognome cognome della persona
     * @param password password della persona
     * @param email email della persona
     * @param indirizzo indirizzo della persona
     * @param telefono numero di telefono della persona
     */
    public Persona(String nome, String cognome, String password, String email, String indirizzo, String telefono){
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.email = email;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
    }

    /**
     * Stampa le infromazioni generali della persona
     * @return stringa
     */
    public String printInfo(){
        return "-----------------------------------------" + "\nI tuoi dati sono i seguenti: \n" + "Nome e cognome: " + nome + " " + cognome + "\n" + "Email: " + email + "\n" + "Indirizzo: " + indirizzo + "\n" + "Numero di telefono: " + telefono + "\n";
    }

    /**
     * Cambia il nome della persona
     * @param n nuovo nome
     */
    public void setNome(String n){
        this.nome = n;
    }

    /**
     * Cambia il cognome della persona
     * @param c nuovo cognome
     */
    public void setCognome(String c){
        this.cognome = c;
    }
    
    /**
     * Cambia la password della persona 
     * @param p nuova password
     */
    public void setPassword(String p){
        this.password = p;
    }

    /**
     * Cambia l'email della persona 
     * @param e nuova email
     */
    public void setEmail(String e){
        this.email = e;
    }

    /**
     * Cambia l'indirzzo della persona
     * @param a nuovo indirizzo
     */
    public void setIndirizzo(String a){
        this.indirizzo = a;
    }

    /**
     * Cambia il numero di telefono della persona
     * @param t nuovo numero di telefono
     */
    public void setTelefono(String t){
        this.telefono = t;
    }

    /**
     * Ritorna il nome della persona
     * @return vslore di nome
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * Ritorna il cognome della persona
     * @return valore di cognome
     */
    public String getCognome(){
        return this.cognome;
    }

    /**
     * Return la password della persona
     * @return valore di password
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Ritorna l'eamil della persona
     * @return valore di email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Ritorna l'indirizzo della persona
     * @return valore di indirizzo 
     */
    public String getIndirizzo(){
        return this.indirizzo;
    }

    /**
     * Ritorna il numero di telefono della persona
     * @return valore di telefono
     */
    public String getTelefono(){
        return this.telefono;
    }
}
