
public class Persona {
    /**
     * Inforamzioni generali di una persona
     */
    private String Nome;
    private String Cognome;
    private String Password;
    private String Email;
    private String Indirizzo;
    private String N_Telefono;

    /**
     * @param nome nome della persona
     * @param cognome cognome della persona
     * @param password password della persona
     * @param email email della persona
     * @param indirizzo indirizzo della persona
     * @param n_telefono numero di telefono della persona
     */
    public Persona(String nome, String cognome, String password, String email, String indirizzo, String n_telefono){
        Nome = nome;
        Cognome = cognome;
        Password = password;
        Email = email;
        Indirizzo = indirizzo;
        N_Telefono = n_telefono;
    }

    /**
     * Stampa le infromazioni generali della persona
     */
    public String printInfo(){
        return "-----------------------------------------" + "\nI tuoi dati sono i seguenti: \n" + "Nome e cognome: " + Nome + " " + Cognome + "\n" + "Email: " + Email + "\n" + "Indirizzo: " + Indirizzo + "\n" + "Numero di telefono: " + N_Telefono + "\n";
    }

    /**
     * Cambia il nome della persona
     * @param n nuovo nome
     */
    public void setNome(String n){
        Nome = n;
    }

    /**
     * Cambia il cognome della persona
     * @param c nuovo cognome
     */
    public void setCognome(String c){
        Cognome = c;
    }
    
    /**
     * Cambia la password della persona 
     * @param p nuova password
     */
    public void setPassword(String p){
        Password = p;
    }

    /**
     * Cambia l'email della persona 
     * @param e nuova email
     */
    public void setEmail(String e){
        Email = e;
    }

    /**
     * Cambia l'indirzzo della persona
     * @param a nuovo indirizzo
     */
    public void setIndirizzo(String a){
        Indirizzo = a;
    }

    /**
     * Cambia il numero di telefono della persona
     * @param t nuovo numero di telefono
     */
    public void setN_Telefono(String t){
        N_Telefono = t;
    }

    /**
     * Ritorna il nome della persona
     * @return vslore di nome
     */
    public String getNome(){
        return Nome;
    }

    /**
     * Ritorna il cognome della persona
     */
    public String getCognome(){
        return Cognome;
    }

    /**
     * Return la password della persona
     * @return valore di password
     */
    public String getPassword(){
        return Password;
    }

    /**
     * Ritorna l'eamil della persona
     * @return valore di email
     */
    public String getEmail(){
        return Email;
    }

    /**
     * Ritorna l'indirizzo della persona
     * @return valore di indirizzo 
     */
    public String getIndirizzo(){
        return Indirizzo;
    }

    /**
     * Ritorna il numero di telefono della persona
     * @return valore di n_telefono
     */
    public String getN_Telefono(){
        return N_Telefono;
    }
}
