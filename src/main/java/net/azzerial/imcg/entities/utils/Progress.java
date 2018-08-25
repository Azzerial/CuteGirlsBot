package net.azzerial.imcg.entities.utils;

public class Progress {

	private final int progress;
	private final int size;

	public Progress(int progress, int size) {
		this.progress = progress;
		this.size = size;
	}

	public int getProgress() {
		return (progress);
	}

	public int getSize() {
		return (size);
	}

	public int getMissingProgress() {
		return (size - progress);
	}

	public int getPercentage() {
		if (size == 0) {
			return (100);
		}
		return ((int)((progress * 1.0f / size ) * 100.0f));
	}

	public String getPercentageAsString() {
		return (getPercentage() + "%");
	}

}
