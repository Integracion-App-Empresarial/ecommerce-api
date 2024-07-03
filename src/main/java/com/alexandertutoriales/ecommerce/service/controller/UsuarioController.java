package com.alexandertutoriales.ecommerce.service.controller;

import com.alexandertutoriales.ecommerce.service.entity.Usuario;
import com.alexandertutoriales.ecommerce.service.service.EmailSenderService;
import com.alexandertutoriales.ecommerce.service.service.UsuarioService;
import com.alexandertutoriales.ecommerce.service.utlis.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    private final UsuarioService service;
    private final EmailSenderService emailSenderService;


    public UsuarioController(UsuarioService service, EmailSenderService emailSenderService) {
        this.service = service;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/login")
    public GenericResponse<Usuario> login(HttpServletRequest request) {
        String email = request.getParameter("email");
        String contrasenia = request.getParameter("pass");
        return this.service.login(email, contrasenia);
    }

    @PostMapping
    public GenericResponse save(@RequestBody Usuario u) {
        if(u != null)
            emailSenderService.sendEmail(u.getEmail(),"Bienvenido a El Mana Panadería", "¡Hola!\n" +
                    "\n" +
                    "¡Nos alegra saber que planeas visitarnos en Panadería El Maná! En nuestra panadería, nos dedicamos a ofrecerte los panes y postres más frescos y deliciosos, elaborados con ingredientes de la más alta calidad. Desde croissants y baguettes crujientes hasta pasteles y galletas irresistibles, tenemos algo para satisfacer todos tus antojos.\n" +
                    "\n" +
                    "Nuestro horario de atención es de lunes a sábado, de 7:00 a.m. a 10:00 p.m., y los domingos NO ATENDEMOS. Nos encontramos en Av. España 595.\n" +
                    "\n" +
                    "Estamos ansiosos por darte la bienvenida y hacer de tu visita una experiencia deliciosa.\n" +
                    "\n" +
                    "¡Te esperamos pronto!");
        return this.service.guardarUsuario(u);
    }

    @PutMapping("/{id}")
    public GenericResponse update(@PathVariable int id, @RequestBody Usuario u) {
        return this.service.guardarUsuario(u);
    }
}
