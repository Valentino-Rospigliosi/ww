package com.cibertec.edu.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cibertec.edu.Entity.Mensaje;

@Controller
public class ChatController {

	  @MessageMapping("/send-message")
	    @SendTo("/topic/messages")
	    public Mensaje enviarMensaje(Mensaje mensaje) {
	        System.out.println("🟢 Mensaje recibido: " + mensaje.getTexto());
	        return mensaje;
	    }
}