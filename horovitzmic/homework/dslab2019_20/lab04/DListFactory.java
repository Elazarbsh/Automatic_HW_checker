public class DListFactory<T> {

	private static final String DLIST_CLASS_NAME_TO_TEST_PROPERTY="classToTest";
	
	public List<T> getDList() {
		String classNameToTest = System.getProperty(DLIST_CLASS_NAME_TO_TEST_PROPERTY);
		return getDList(classNameToTest);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getDList(String classNameToTest) {
		Class<?> c;
		List<T> dlist=null;
		try {
			c = Class.forName(classNameToTest);
			dlist = (List<T>) c.newInstance();
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return dlist;
	}

}
