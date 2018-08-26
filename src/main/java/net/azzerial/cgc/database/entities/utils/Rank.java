package net.azzerial.cgc.database.entities.utils;

public class Rank {

	private final int rank;
	private final int size;

	public Rank(int rank, int size) {
		this.rank = rank;
		this.size = size;
	}

	public int getRank() {
		return (rank);
	}

	public int getSize() {
		return (size);
	}

	public String asString() {
		StringBuilder builder = new StringBuilder();

		builder.append(rank).append("/").append(size);
		return (builder.toString());
	}

}
