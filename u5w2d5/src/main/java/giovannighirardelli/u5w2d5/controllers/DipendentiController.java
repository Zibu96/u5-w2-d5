package giovannighirardelli.u5w2d5.controllers;

import giovannighirardelli.u5w2d5.entities.Dipendenti;
import giovannighirardelli.u5w2d5.exceptions.BadRequestException;
import giovannighirardelli.u5w2d5.payloads.NewDipendentiDTO;
import giovannighirardelli.u5w2d5.payloads.NewDipendentiResponseDTO;
import giovannighirardelli.u5w2d5.servicies.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    @Autowired
    private DipendentiService dipendentiService;

    @GetMapping
    public Page<Dipendenti> getDipendenti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy) {

        return this.dipendentiService.getDipendenti(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewDipendentiResponseDTO saveDipendenti(@RequestBody @Validated NewDipendentiDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }

        return new NewDipendentiResponseDTO(this.dipendentiService.saveDipendenti(body).getId());
    }

    @GetMapping("/{dipendentiId}")
    public Dipendenti findById(@PathVariable UUID dipendentiId) {
        return this.dipendentiService.findById(dipendentiId);
    }

    @PutMapping("/{dipendentiId}")
    public Dipendenti findByIdAndUpdate(@PathVariable UUID dipendentiId, @RequestBody Dipendenti body){
        return this.dipendentiService.findByIdAndUpdate(dipendentiId, body);

    }

    @DeleteMapping("/{dipendentiId}")
    public void findByIdAndDelete(@PathVariable UUID dipendentiId){
        this.dipendentiService.findByIdAndDelete(dipendentiId);
    }

//    @PostMapping("/{dipendentiId}/avatar")
//    public String uploadAvatar(@RequestParam("avatar") MultipartFile image, @PathVariable UUID dipendentiId) throws IOException {
//        return this.dipendentiService.uploadImage(autoreId, image);
//    }

}