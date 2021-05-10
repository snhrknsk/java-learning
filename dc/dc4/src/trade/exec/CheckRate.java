package src.trade.exec;

import org.json.JSONObject;

import src.trade.coin.CoinCheckClient;
import src.trade.manager.CoinManager;

/**
 * Check current price.<br>
 * Save latest price in {@link CoinManager}.
 */
public class CheckRate implements ITradeLogic{
	@Override
	public void exec() {
		String result = CoinCheckClient.getCurrentPrice();
		JSONObject jsonObject = new JSONObject(result);
		String rate = jsonObject.getString("rate");
		CoinManager.getInstance().setCurrentRate(rate);
	}
}
