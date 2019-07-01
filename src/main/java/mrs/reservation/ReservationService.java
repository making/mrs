package mrs.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReservationService {

    private final ReservableRoomRepository reservableRoomRepository;

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository, ReservableRoomRepository reservableRoomRepository) {
        this.reservationRepository = reservationRepository;
        this.reservableRoomRepository = reservableRoomRepository;
    }

    // USERロールの場合は予約者がログインユーザーの場合に取り消し可能
    // ADMINロールの場合は全予約取り消し可能
    @PreAuthorize("hasRole('ADMIN') or #reservation.user.userId == principal.user.userId")
    public void cancel(@P("reservation") Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    public Reservation findOne(Integer reservationId) {
        return reservationRepository.findById(reservationId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "存在しない予約です。"));
    }

    public List<ReservableRoom> findReservableRooms(LocalDate date) {
        return reservableRoomRepository
            .findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(
                date);
    }

    public List<Reservation> findReservations(ReservableRoomId reservableRoomId) {
        return reservationRepository
            .findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(
                reservableRoomId);
    }

    public Reservation reserve(Reservation reservation) {
        ReservableRoomId reservableRoomId = reservation.getReservableRoom()
            .getReservableRoomId();
        // 悲観ロック
        reservableRoomRepository
            .findOneForUpdateByReservableRoomId(reservableRoomId)
            .orElseThrow(() -> new ReservationException.Unavailable("入力の日付・部屋の組み合わせは予約できません。"));

        // 該当の日付・部屋の全予約情報をReservableRoomテーブルから取得し、重複をチェック
        boolean overlap = reservationRepository
            .findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(
                reservableRoomId)
            .stream().anyMatch(x -> x.overlap(reservation));
        if (overlap) {
            throw new ReservationException.AlreadyReserved("入力の時間帯は既に予約済みです。");
        }
        // 予約情報の登録
        reservationRepository.save(reservation);
        return reservation;
    }
}
