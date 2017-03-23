package com.dsm.model.user;

/**
 * 用户信用Bean
 *
 * @author lbwwz
 *
 */
public class UserCredit {
	/*
	 * 信用等级（是根据score生成的），信用等级：淘宝会员在淘宝网上的信用度，分为20个级别，级别如：level = 1 时，表示一心；
	 * level = 2 时，表示二心
	 */
	private int level;

	// 信用总分（“好评”加一分，“中评”不加分，“差评”扣一分。分越高，等级越高）
	private int score;

	//评价总数
	private int totalNum;
	//好评数
	private int goodNum;
	public UserCredit() {
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(int goodNum) {
		this.goodNum = goodNum;
	}

	@Override
	public String toString() {
		return "UserCredit [level=" + level + ", score=" + score + ", totalNum=" + totalNum + ", goodNum=" + goodNum
				+ "]";
	}

}
