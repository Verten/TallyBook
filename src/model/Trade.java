package model;

import java.io.Serializable;
import java.util.Date;

public class Trade implements Serializable{
	int number;
	Date trade_date;
	String trade_way;
	String trade_purpose;
	double trade_count;
	
	public Trade(int number, Date tradeDate, String tradeWay,
			String tradePurpose, double tradeCount) {
		super();
		this.number = number;
		trade_date = tradeDate;
		trade_way = tradeWay;
		trade_purpose = tradePurpose;
		trade_count = tradeCount;
	}

	public Trade() {
		super();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getTrade_date() {
		return trade_date;
	}

	public void setTrade_date(Date tradeDate) {
		trade_date = tradeDate;
	}

	public String getTrade_way() {
		return trade_way;
	}

	public void setTrade_way(String tradeWay) {
		trade_way = tradeWay;
	}

	public String getTrade_purpose() {
		return trade_purpose;
	}

	public void setTrade_purpose(String tradePurpose) {
		trade_purpose = tradePurpose;
	}

	public double getTrade_count() {
		return trade_count;
	}

	public void setTrade_count(double tradeCount) {
		trade_count = tradeCount;
	}

	@Override
	public String toString() {
		return "Trade [number=" + number + ", trade_count=" + trade_count
				+ ", trade_date=" + trade_date + ", trade_purpose="
				+ trade_purpose + ", trade_way=" + trade_way + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		long temp;
		temp = Double.doubleToLongBits(trade_count);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((trade_date == null) ? 0 : trade_date.hashCode());
		result = prime * result
				+ ((trade_purpose == null) ? 0 : trade_purpose.hashCode());
		result = prime * result
				+ ((trade_way == null) ? 0 : trade_way.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (number != other.number)
			return false;
		if (Double.doubleToLongBits(trade_count) != Double
				.doubleToLongBits(other.trade_count))
			return false;
		if (trade_date == null) {
			if (other.trade_date != null)
				return false;
		} else if (!trade_date.equals(other.trade_date))
			return false;
		if (trade_purpose == null) {
			if (other.trade_purpose != null)
				return false;
		} else if (!trade_purpose.equals(other.trade_purpose))
			return false;
		if (trade_way == null) {
			if (other.trade_way != null)
				return false;
		} else if (!trade_way.equals(other.trade_way))
			return false;
		return true;
	}
	
	
	
}
