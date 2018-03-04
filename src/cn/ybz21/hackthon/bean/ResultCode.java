package cn.ybz21.hackthon.bean;

import java.io.Serializable;

public class ResultCode implements Serializable{
	public int result;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public ResultCode() {
		super();
	}

	public ResultCode(int result) {
		super();
		this.result = result;
	}

}
