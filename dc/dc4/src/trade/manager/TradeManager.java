package src.trade.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import src.trade.coin.PARAM_KEY;

/**
 * Manage current order and completed transaction.
 */
public class TradeManager {

	private static TradeManager tradeManager = null;
	private static Logger log = Logger.getLogger(TradeManager.class);
	Map<String, TradeEntity> orderIDMap = new HashMap<>();
	List<TradedOrderEntity> tradeCompletedList = new ArrayList<>();

	private TradeManager(){}

	public static synchronized TradeManager getInstance(){
		if (tradeManager == null){
			tradeManager = new TradeManager();
		}
		return tradeManager;
	}

	/**
	 * add the registered order to managed order map
	 * @param orderJSON this json must be response by {@link trade.coin.TRADE_API#buy} or {@link trade.coin.TRADE_API#sell} request
	 */
	public void addOrder(JSONObject orderJSON, String algorithm){
		String id = String.valueOf(orderJSON.getLong(PARAM_KEY.id.name()));
		boolean isOrderBuy = orderJSON.getString(PARAM_KEY.order_type.name()).equals("buy");
		TradeEntity entity = new TradeEntity(String.valueOf(orderJSON.getString(PARAM_KEY.rate.name())), orderJSON.getString(PARAM_KEY.amount.name()),
				isOrderBuy, orderJSON.getString(PARAM_KEY.created_at.name()), algorithm);
		orderIDMap.put(id, entity);
	}

	public void deleteOrder(String id){
		if (orderIDMap.remove(id) == null){
			log.warn("Order is already canceled. ID : " + id);
		} else {
			log.info("The order is deleted completely. ID " + id);
		}
	}

	public TradeEntity getOrder(String id){
		return orderIDMap.get(id);
	}

	public boolean isCompletedOrder(String id){
		if (orderIDMap.get(id) == null){
			return true;
		}
		return false;
	}

	public Map<String, TradeEntity> getAllOrder(){
		return orderIDMap;
	}

	/**
	 * This message must be the following format(csv)<br>
	 * Date, Order Id, isBuy, Trade Id, Rate, Amount
	 * @param element
	 */
	public void completeTrade(TradedOrderEntity element){
		tradeCompletedList.add(element);
	}
	public List<TradedOrderEntity> getCompletedTradeList(){
		return tradeCompletedList;
	}


	public static class TradeEntity {
		private boolean isBuyOrder = true;
		private double rate;
		private double orderAmount;
		private double amount;
		private double funds;
		private String date;
		private String logic;

		public TradeEntity(String rate, String amount, boolean isBuyOrder, String date, String algorithm) {
			this.rate = Double.valueOf(rate);
			this.amount = Double.valueOf(amount);
			this.orderAmount = Double.valueOf(amount);
			this.isBuyOrder = isBuyOrder;
			this.date = date;
			this.logic = algorithm;
			this.funds = this.rate * this.amount;
		}

		public double getAmount() { return amount; }
		public double getOrderAmount() { return orderAmount; }
		public double getFunds() { return funds; }
		public double getRate() { return rate; }
		public boolean isBuyOrder() { return isBuyOrder; };
		public String getDate(){ return date; }
		public String getLogic() { return logic; }

		/**
		 * Reduce Settlement amount. This order is all executed return true.
		 *
		 * @param reduction
		 * @return
		 */
		public boolean execSettlement(double reduction) {
			amount -= reduction;
			if (amount <= 0) {
				return true;
			}
			System.out.println(amount + " is remain.");
			return false;
		}
	}

	public static class TradedOrderEntity{
		private boolean isBuyOrder ;
		private double rate;
		private double amount;
		private String orderId = "";
		private String tradeId = "";
		private String date = "";
		private String logic = "";
		private double tradedFund = 0;

		TradedOrderEntity(Builder builder){
			isBuyOrder = builder.isBuyOrder;
			rate = builder.rate;
			amount = builder.amount;
			orderId = builder.orderId;
			tradeId = builder.tradeId;
			date = builder.date;
			tradedFund = amount * rate;
			logic = builder.logic;
		}
		public double getAmount() {
			return amount;
		}
		public double getRate() {
			return rate;
		}
		public double getTradedFund() { return tradedFund; }
		public boolean isBuyOrder(){ return isBuyOrder; }
		public String getOrderId(){ return orderId; }
		public String getTradeId(){ return tradeId; }
		public String getDate(){ return date; }
		public String getLogic(){ return logic; }

		public static class Builder{
			double rate;
			double amount;
			boolean isBuyOrder;
			String orderId = "";
			String tradeId = "";
			String date = "";
			String logic = "";
			public Builder(double rate, double amount, boolean isBuyOrder){
				this.rate = rate;
				this.amount = amount;
				this.isBuyOrder = isBuyOrder;
			}
			public Builder orderId(String orderId){
				this.orderId = orderId;
				return this;
			}
			public Builder tradeId(String tradeId){
				this.tradeId = tradeId;
				return this;
			}
			public Builder date(String date){
				this.date = date;
				return this;
			}
			public Builder logic(String logic){
				this.logic = logic;
				return this;
			}
			public TradedOrderEntity build(){
				return new TradedOrderEntity(this);
			}
		}
	}

}
