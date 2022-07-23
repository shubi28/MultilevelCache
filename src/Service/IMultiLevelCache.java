package Service;

public interface IMultiLevelCache{

	void write(int key, int value);

	void delete(int key);

	int read(int key);
}