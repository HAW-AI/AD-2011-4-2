package hyperGraph;


public class HGRawData {

	private int width;
	private int height;
	private int[] values;

	private HGRawData(int width, int height, int[] values) {
		this.width = width;
		this.height = height;
		this.values = values;
	}

	public static HGRawData creator(int width, int height, int[] values) {
		return new HGRawData(width, height, values);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getValues() {
		return values;
	}

}
