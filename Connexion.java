import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Connexion extends UnicastRemoteObject implements ConnexionInterface {
    private final List<String> Nom;
    private int cpt;

    public Connexion() throws RemoteException {
        cpt = 0;
        Nom = new ArrayList<>();
    }

    public int getConnected() throws RemoteException {
        return cpt;
    }

    public String addConnected(String name) throws RemoteException {
        Nom.add(name);
        cpt++;
        StringBuilder message = new StringBuilder();
        message.append(name).append(" a été ajouté à la liste des personnes connectées.\n");
        message.append("Liste des personnes connectées : \n");
        for (String connectedName : Nom) {
            message.append(connectedName).append("\n");
        }
        return message.toString();
    }

    public void disconnect(String name) throws RemoteException {
        if (Nom.remove(name)) {
            cpt--;
        }
    }
}