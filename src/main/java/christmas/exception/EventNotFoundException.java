package christmas.exception;

public class EventNotFoundException extends CommonRuntimeException{
	public static final String EVENT_NOT_FOUND_MESSAGE = "존재하지 않는 이벤트입니다.";

	public EventNotFoundException() {
		super(EVENT_NOT_FOUND_MESSAGE);
	}
}
