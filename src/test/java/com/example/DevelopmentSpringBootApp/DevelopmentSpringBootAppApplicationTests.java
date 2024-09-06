package com.example.DevelopmentSpringBootApp;

import com.example.DevelopmentSpringBootApp.entities.Postazione;
import com.example.DevelopmentSpringBootApp.entities.Prenotazione;
import com.example.DevelopmentSpringBootApp.entities.Utente;

import com.example.DevelopmentSpringBootApp.services.PostazioneService;
import com.example.DevelopmentSpringBootApp.services.PrenotazioneService;
import com.example.DevelopmentSpringBootApp.services.UtenteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DevelopmentSpringBootAppApplicationTests {

	@Autowired
	private PostazioneService postazioneService;

	@Autowired
	private PrenotazioneService prenotazioneService;

	@Autowired
	private UtenteService utenteService;


	@Test
	public void testMargherita() {
		Postazione postazionePrivata1FromDB =  postazioneService.findPostazioneById(UUID.fromString("5eba77af-6047-4315-adf2-25bb85c24f96"));
		Utente utenteDiegoFromDB =  utenteService.findUtenteById(UUID.fromString("a71e89be-f6c1-43e9-bbca-867cc52bf861"));
		Prenotazione prenotazione1= new Prenotazione(LocalDate.of(2024,9,12), postazionePrivata1FromDB, utenteDiegoFromDB);
		prenotazioneService.saveprenotazione(prenotazione1);

		// Recupera la prenotazione appena salvata (puoi farlo tramite l'ID o un altro metodo)
		Prenotazione prenotazioneFromDB = prenotazioneService.findPrenotazioneById(prenotazione1.getId());

		// Verifica che la prenotazione sia stata salvata correttamente
		assertNotNull(prenotazioneFromDB, "La prenotazione non dovrebbe essere null.");
		assertEquals(prenotazione1.getDataPrenotazione(), prenotazioneFromDB.getDataPrenotazione(), "La data della prenotazione non coincide.");
		assertEquals(prenotazione1.getPostazione(), prenotazioneFromDB.getPostazione(), "La postazione non coincide.");
		assertEquals(prenotazione1.getUtente(), prenotazioneFromDB.getUtente(), "L'utente non coincide.");
	}

}
