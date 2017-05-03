package be.vdab.repositories;

public class GastenboekRepository extends AbstractRepository {
	private static final String READ_ALL = "select date, name, message from  gastenboek";
}
