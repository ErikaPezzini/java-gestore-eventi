import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati = 0;

    public Evento(String titolo, LocalDate data, int postiTotali) throws IllegalArgumentException {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento non può essere nel passato.");
        }
        if (postiTotali <= 0) {
            throw new IllegalArgumentException("Il numero di posti totali deve essere positivo.");
        }
        this.titolo = titolo;
        this.data = data;
        this.postiTotali = postiTotali;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    public void prenota() throws IllegalStateException {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalStateException("L'evento è già passato.");
        }
        if (postiPrenotati >= postiTotali) {
            throw new IllegalStateException("Non ci sono più posti disponibili.");
        }
        postiPrenotati++;
    }

    public void disdici() throws IllegalStateException {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalStateException("L'evento è già passato.");
        }
        if (postiPrenotati <= 0) {
            throw new IllegalStateException("Non ci sono prenotazioni da disdire.");
        }
        postiPrenotati--;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter) + " - " + titolo;
    }
}