import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il titolo dell'evento:");
        String titolo = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Inserisci la data dell'evento (giorno/mese/anno):");
        String dataInput = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataInput, formatter);

        System.out.println("Inserisci il numero di posti totali:");
        int postiTotali = Integer.parseInt(scanner.nextLine());

        try {
            Evento evento = new Evento(titolo, data, postiTotali);
            System.out.println("Evento creato: " + evento);

            System.out.println("Inserisci l'ora del concerto (ora:minuti): ");
            String oraInput = scanner.nextLine();
            LocalTime oraConcerto = LocalTime.parse(oraInput);

            System.out.println("Inserisci il prezzo del concerto: ");
            double prezzoConcerto = Double.parseDouble(scanner.nextLine());

            Concerto concerto = new Concerto(titolo, data, postiTotali, oraConcerto, prezzoConcerto);

            System.out.println("Quante prenotazioni vuoi fare?");
            int prenotazioni = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < prenotazioni; i++) {

                try {
                    evento.prenota();
                    System.out.println("Prenotazione effettuata.");
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Posti prenotati: " + evento.getPostiPrenotati() + " - Posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));

            System.out.println("Quanti posti vuoi disdire?");
            int disdette = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < disdette; i++) {
                try {
                    evento.disdici();
                    System.out.println("Disdetta effettuata.");
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Posti prenotati: " + evento.getPostiPrenotati() + " - Posti disponibili: " + (evento.getPostiTotali() - evento.getPostiPrenotati()));
            
            System.out.println("Concerto: " + concerto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}