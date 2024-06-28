package giovannighirardelli.u5w2d5.payloads;

import giovannighirardelli.u5w2d5.enums.Stato;
import giovannighirardelli.u5w2d5.enums.Tipologia;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewDispositiviDTO(
        @NotNull(message = "Il dispositivio deve avere una tipologia")
        Tipologia tipologia,
        @NotNull(message = "Il dispositivo deve avere uno stato")
        Stato stato,
        UUID dipendentiId

) {
}
