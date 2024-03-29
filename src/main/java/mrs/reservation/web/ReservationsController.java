package mrs.reservation.web;

import am.ik.yavi.core.Validated;
import mrs.reservation.ReservableRoom;
import mrs.reservation.ReservableRoomId;
import mrs.reservation.Reservation;
import mrs.reservation.ReservationException;
import mrs.reservation.ReservationService;
import mrs.room.RoomService;
import mrs.user.ReservationUserDetails;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import jakarta.validation.Valid;

@Controller
@RequestMapping("reservations/{date}/{roomId}")
public class ReservationsController {

	private final ReservationService reservationService;

	private final RoomService roomService;

	public ReservationsController(RoomService roomService, ReservationService reservationService) {
		this.roomService = roomService;
		this.reservationService = reservationService;
	}

	@PostMapping(params = "cancel")
	public String cancel(@RequestParam Integer reservationId, @PathVariable Integer roomId,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate date, Model model) {

		try {
			Reservation reservation = this.reservationService.findOne(reservationId);
			this.reservationService.cancel(reservation);
		}
		catch (AccessDeniedException e) {
			model.addAttribute("error", e.getMessage());
			return reserveForm(date, roomId, model);
		}
		return "redirect:/reservations/{date}/{roomId}";
	}

	@PostMapping
	public String reserve(@Valid ReservationForm form, BindingResult bindingResult,
			@AuthenticationPrincipal ReservationUserDetails userDetails,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate date, @PathVariable Integer roomId,
			Model model) {
		if (bindingResult.hasErrors()) {
			return reserveForm(date, roomId, model);
		}

		ReservableRoom reservableRoom = new ReservableRoom(new ReservableRoomId(roomId, date), null);
		Validated<Reservation> reservationValidated = form.toReservation(null, reservableRoom, userDetails.getUser());
		if (!reservationValidated.isValid()) {
			reservationValidated.errors().apply(bindingResult::rejectValue);
			return reserveForm(date, roomId, model);
		}
		try {
			Reservation reservation = reservationValidated.value();
			this.reservationService.reserve(reservation);
		}
		catch (ReservationException e) {
			model.addAttribute("error", e.getMessage());
			return reserveForm(date, roomId, model);
		}
		return "redirect:/reservations/{date}/{roomId}";
	}

	@GetMapping
	public String reserveForm(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate date,
			@PathVariable Integer roomId, Model model) {
		ReservableRoomId reservableRoomId = new ReservableRoomId(roomId, date);
		List<Reservation> reservations = this.reservationService.findReservations(reservableRoomId);

		LocalTime baseTime = LocalTime.of(0, 0);
		List<LocalTime> timeList = IntStream.range(0, 24 * 2)
			.mapToObj(i -> baseTime.plusMinutes(30L * i))
			.collect(Collectors.toList());
		model.addAttribute("room", roomService.findMeetingRoom(roomId));
		model.addAttribute("reservations", reservations);
		model.addAttribute("timeList", timeList);
		return "reservations/reserveForm";
	}

	@ModelAttribute
	public ReservationForm setUpForm() {
		ReservationForm form = new ReservationForm();
		// デフォルト値
		form.setStartTime(LocalTime.of(9, 0));
		form.setEndTime(LocalTime.of(10, 0));
		return form;
	}

}
