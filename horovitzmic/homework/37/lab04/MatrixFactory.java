import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MatrixFactory<T> {

	private static final String CLASS_NAME_TO_TEST_PROPERTY = "classToTest";

	public Matrix<T> getMatrix(int size, T defaultVal) {
		String classNameToTest = System.getProperty(CLASS_NAME_TO_TEST_PROPERTY);
		return getMatrix(classNameToTest, size, defaultVal);
	}

	public Matrix<T> getMatrix(T defaultVal) {
		return getMatrix(-1, defaultVal);
	}

	@SuppressWarnings("unchecked")
	private Matrix<T> getMatrix(String classNameToTest, int size, T defaultVal) {
		Class<?> c;
		Matrix<T> matrix = null;
		try {
			c = Class.forName(classNameToTest);
			Constructor<?>[] constructors = c.getConstructors();
				for (Constructor<?> cons : constructors) {
					if ((size == -1) && (cons.getParameterCount() == 1))
						matrix = (Matrix<T>) cons.newInstance(defaultVal);
					if ((size > 0) && (cons.getParameterCount() == 2))
						matrix = (Matrix<T>) cons.newInstance(size, defaultVal);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matrix;
	}

}
