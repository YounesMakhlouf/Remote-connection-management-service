import java.rmi.Naming;

public class ConnexionServer {
    public static void main(String[] args) {
        try {
            //créer une instance connexion (qui représente le serveur)
            Connexion server = new Connexion();

            //enregistrer l'objet serveur dans le registre
            Naming.rebind("rmi://localhost/connexion", server);

            System.out.println("[System] Le serveur est prêt.");
        } catch (Exception e) {
            System.err.println("Erreur du serveur : " + e);
            e.printStackTrace();
        }
    }
}