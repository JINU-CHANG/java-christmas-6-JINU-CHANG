package christmas.exception;

public class MenuNotFoundException extends CommonIllegalArgumentException{
	public static final String MENU_NOT_FOUND_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
	public MenuNotFoundException() {
		super(MENU_NOT_FOUND_MESSAGE);
	}
}
