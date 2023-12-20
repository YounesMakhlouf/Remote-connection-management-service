import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ConnexionClient {
    private static void displayConnectedCount(ConnexionInterface connexion) throws RemoteException {
        int connectedCount = connexion.getConnected();
        System.out.println("Nombre de personnes connectées : " + connectedCount);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Nombre d'arguments incompatible! Veuillez fournir le nom de la personne qui se connecte en argument.");
            System.exit(1);
        }
        String nom = args[0];

        try {
            // Obtenir une référence à l'objet distant enregistré dans le registre RMI
            ConnexionInterface connexion = (ConnexionInterface) Naming.lookup("rmi://localhost/connexion");

            Scanner scanner = new Scanner(System.in);

            String continuer = "oui";
            while (continuer.equalsIgnoreCase("oui")) {
                // Ajouter le nom de la personne qui a connecté
                String message = connexion.addConnected(nom);

                // Incrémenter et afficher le nombre de personnes qui ont connecté
                displayConnectedCount(connexion);

                // Afficher la liste de personnes connectées
                System.out.println(message);

                System.out.print("Voulez-vous ajouter une autre personne ? (oui/non) ");
                continuer = scanner.nextLine();
                if (continuer.equalsIgnoreCase("oui")) {
                    System.out.print("Entrez le nom de la personne à connecter : ");
                    nom = scanner.nextLine();
                }
            }

            System.out.print("Voulez-vous déconnecter une personne ? (oui/non) ");
            continuer = scanner.nextLine();
            while (continuer.equalsIgnoreCase("oui")) {
                System.out.print("Entrez le nom de la personne à déconnecter : ");
                String nameToDisconnect = scanner.nextLine();
                connexion.disconnect(nameToDisconnect);

                // Décrémenter et afficher le nombre de personnes qui ont connecté
                displayConnectedCount(connexion);

                System.out.print("Voulez-vous déconnecter une autre personne ? (oui/non) ");
                continuer = scanner.nextLine();
            }

        } catch (Exception e) {
            System.err.println("Erreur du client : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
