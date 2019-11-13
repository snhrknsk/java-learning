package ch06.ex03;

public enum Verbose {
	SILENT(0),
	TERSE(1),
	NORMAL(2),
	VERBOSE(3),;

	private int verbosity;
	Verbose(int verbosity) {
		this.verbosity = verbosity;
	}

	public int getVerbosity() {
		return verbosity;
	}
}
