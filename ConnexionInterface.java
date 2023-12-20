import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnexionInterface extends Remote {
    //retourne le nombre de personnes qui ont connecté
    int getConnected() throws RemoteException;

    //ajoute le nom de la personne connectée à la liste de noms (tableau) et retourne une chaine contenant les noms de tous les clients
    String addConnected(String name) throws RemoteException;

    //déconnecte une personne connectée
    void disconnect(String name) throws RemoteException;
}