package giovannighirardelli.u5w2d5.payloads;

import giovannighirardelli.u5w2d5.enums.Stato;
import giovannighirardelli.u5w2d5.enums.Tipologia;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record NewDispositiviDTO(
        @NotEmpty(message = "Il dispositivio deve avere una tipologia")
        Tipologia tipologia,
        @NotEmpty(message = "Il dispositivo deve avere uno stato")
        Stato stato,
        UUID dipendentiId

) {
}
