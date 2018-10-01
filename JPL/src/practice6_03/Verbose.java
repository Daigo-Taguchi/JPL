package practice6_03;

public interface Verbose {
	enum Verbosity{
		SILENT,
		TERSE,
		NORMAL,
		VERBOSE;
	}
	void setVerbosity(Verbosity v);
	int getVerbosity();
}