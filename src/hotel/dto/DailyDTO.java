package hotel.dto;

import java.sql.Date;

public record DailyDTO(Double value, Date dateCheckin, Date dateCheckout) {

}
