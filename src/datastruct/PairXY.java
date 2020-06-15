package datastruct;

public class PairXY implements Comparable<PairXY> {
	private static int count = 0;
	
	public int X, Y;
	public int index;
	
	public PairXY() { this.X = this.Y = 0; this.index = count++; }
	public PairXY(int x, int y) { this.X = x; this.Y = y; this.index = count++; }
	public PairXY(PairXY ref) { this.X = ref.X; Y = ref.Y; this.index = count++; }
	
	// -- override functions
	@Override
	public String toString() {
		return ("X " + this.X + " | Y = " + this.Y);
	}
	
	@Override
	public int compareTo(PairXY o) {
		if (this.X == o.X) {
			if (this.Y != o.Y) return (this.Y < o.Y ? -1 : 1);
		} else {
			return (this.X < o.index ? -1 : 1);
		}
		return 0;
	}
	
}
