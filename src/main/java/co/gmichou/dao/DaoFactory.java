package co.gmichou.dao;

public final class DaoFactory {
	
	private static final String IMPL_TYPE = "jdbc";
	
	private DaoFactory(){
		
	}
	
	public static ContactsDao getContactsDao() throws DaoException{
		switch(IMPL_TYPE) {
			case "jdbc":
				return new JdbcContactsDao();
			default:
				throw new DaoException("No suitable implementation available");
		}
		
	}
 }
