package giovannighirardelli.u5w2d5.servicies;

import com.cloudinary.Cloudinary;
import giovannighirardelli.u5w2d5.entities.Dipendenti;
import giovannighirardelli.u5w2d5.exceptions.BadRequestException;
import giovannighirardelli.u5w2d5.exceptions.NotFoundException;
import giovannighirardelli.u5w2d5.payloads.NewDipendentiDTO;
import giovannighirardelli.u5w2d5.repositories.DipendentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DipendentiService {

    @Autowired
    private DipendentiRepository dipendentiRepository;
    @Autowired
    private Cloudinary cloudinary;


    public Page<Dipendenti> getDipendenti(int pageNumber, int pageSize, String sortBy){
        if (pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return dipendentiRepository.findAll(pageable);
    }

    public Dipendenti saveDipendenti(NewDipendentiDTO body) {
        if (this.dipendentiRepository.existsByEmail(body.email())) throw new BadRequestException("Il dipendente con " + body.email() + " esiste già");
        if (this.dipendentiRepository.existsByUsername(body.username())) throw new BadRequestException("Il dipendente con " + body.username() + " esiste già");

        Dipendenti dipendenti = new Dipendenti(body.username(), body.name(), body.lastName(), body.email(), "https://ui-avatars.com/api/?name=" + body.name() + "+" + body.lastName());


        return this.dipendentiRepository.save(dipendenti);

    }

    public Dipendenti findById(UUID id) {
        return this.dipendentiRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Dipendenti findByIdAndUpdate(UUID id, Dipendenti updatedDipendenti) {
        Dipendenti found = this.findById(id);

        found.setUsername(updatedDipendenti.getUsername());
        found.setName(updatedDipendenti.getName());
        found.setLastName(updatedDipendenti.getLastName());
        found.setEmail(updatedDipendenti.getEmail());
        found.setProfilePic("https://ui-avatars.com/api/?name=" + updatedDipendenti.getName() + "+" + updatedDipendenti.getLastName());

        return this.dipendentiRepository.save(updatedDipendenti);

    }

    public void findByIdAndDelete(UUID id) {
        Dipendenti found = this.findById(id);
        this.dipendentiRepository.delete(found);

    }
}
