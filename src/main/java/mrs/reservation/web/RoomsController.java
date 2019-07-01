package mrs.reservation.web;

import java.time.LocalDate;
import java.util.List;

import mrs.reservation.ReservableRoom;
import mrs.reservation.ReservationService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("rooms")
public class RoomsController {
	private final ReservationService reservationService;

	public RoomsController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public String listRooms(Model model) {
		LocalDate today = LocalDate.now();
		model.addAttribute("date", today);
		return listRooms(today, model);
	}

	@GetMapping(path = "{date}")
	public String listRooms(
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
			Model model) {
		List<ReservableRoom> rooms = reservationService.findReservableRooms(date);
		model.addAttribute("rooms", rooms);
		return "rooms/listRooms";
	}
}