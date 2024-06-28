package giovannighirardelli.u5w2d5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDipendentiDTO(
        @NotEmpty(message = "Lo username è obbligatorio per la creazione di un dipendente")
        @Size(min = 4, max = 20, message = "Lo username del dipendente non può essere minore di 4 caratteri o maggiore di 20")
        String username,
    @NotEmpty(message = "Il nome è obbligatorio per la creazione di un dipendente")
    @Size(min = 3, max = 16, message = "Il nome non può essere minore di 3 caratteri o maggiore di 16")
    String name,
    @NotEmpty(message = "Il cognome è obbligatorio per la creazione di un dipendente")
    String lastName,
    @NotEmpty(message = "Il dipendente deve avere una email valida")
    @Email(message = "Formato indirizzo mail non valido")
    String email) {
}
