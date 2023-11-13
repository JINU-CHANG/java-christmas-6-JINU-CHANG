package christmas.domain.result;

import christmas.domain.menu.Menu;

public class PresentEventResult extends EventResult{
	private final Menu present;
	private final int presentQuantity;

	public PresentEventResult(String name, int benefit, Menu present, int presentQuantity) {
		super(name, benefit);
		this.present = present;
		this.presentQuantity = presentQuantity;
	}

	public Menu getPresent() {
		return present;
	}

	public int getPresentQuantity() {
		return presentQuantity;
	}
}
